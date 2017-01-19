#pragma once

#include <mbgl/style/property_value.hpp>
#include "../../conversion/conversion.hpp"
#include "../../conversion/constant.hpp"
#include "types.hpp"

#include <jni/jni.hpp>

#include <tuple>
#include <map>

namespace mbgl {
namespace android {
namespace conversion {

template <class I, class O>
inline jni::jobject* toFunctionStopJavaArray(jni::JNIEnv& env, std::map<I, O> value) {
    static jni::jclass* javaClass = jni::NewGlobalRef(env, &jni::FindClass(env, "com/mapbox/mapboxsdk/style/functions/stops/Stop")).release();
    static jni::jmethodID* constructor = &jni::GetMethodID(env, *javaClass, "<init>", "(Ljava/lang/Object;Ljava/lang/Object;)V");

    jni::jarray<jni::jobject>& jarray = jni::NewObjectArray(env, value.size(), *javaClass);

    size_t i = 0;
    for (auto const& stop : value) {
        jni::jobject* in = *convert<jni::jobject*, I>(env, stop.first);
        jni::jobject* out = *convert<jni::jobject*, O>(env, stop.second);
        jni::SetObjectArrayElement(env, jarray, i, &jni::NewObject(env, *javaClass, *constructor, in, out));
        i++;
    }

    return &jarray;
}

/**
 * Conversion from core function stops to Stops java subclasses
 */
template <class T>
class StopsEvaluator {
public:

    StopsEvaluator(jni::JNIEnv& _env) : env(_env) {}

    jni::jobject* operator()(const mbgl::style::CategoricalStops<T> &) const {
        return nullptr;
        //static jni::jmethodID* constructor = &jni::GetMethodID(env, *this->javaClass(), "<init>", "(Ljava/lang/String)V");

        //return &jni::NewObject(env, *this->javaClass(), *constructor,
        //    *convert<jni::jobject*>(env, value.defaultValue),
        //    toFunctionStopJavaArray(env, value.stops));
    }

    jni::jobject* operator()(const mbgl::style::ExponentialStops<T> &value) const {
        static jni::jclass* clazz = jni::NewGlobalRef(env, &jni::FindClass(env, "com/mapbox/mapboxsdk/style/functions/stops/ExponentialStops")).release();
        static jni::jmethodID* constructor = &jni::GetMethodID(env, *clazz, "<init>", "(Ljava/lang/Float;[Lcom/mapbox/mapboxsdk/style/functions/stops/Stop;)V");

        return &jni::NewObject(env, *clazz, *constructor,
            *convert<jni::jobject*>(env, value.base),
            toFunctionStopJavaArray(env, value.stops));
    }

    jni::jobject* operator()(const mbgl::style::IdentityStops<T> &) const {
        static jni::jclass* clazz = jni::NewGlobalRef(env, &jni::FindClass(env, "com/mapbox/mapboxsdk/style/functions/stops/IdentityStops")).release();
        static jni::jmethodID* constructor = &jni::GetMethodID(env, *clazz, "<init>", "()V");

        return &jni::NewObject(env, *clazz, *constructor);
    }

    jni::jobject* operator()(const mbgl::style::IntervalStops<T> &value) const {
        static jni::jclass* clazz = jni::NewGlobalRef(env, &jni::FindClass(env, "com/mapbox/mapboxsdk/style/functions/stops/IntervalStops")).release();
        static jni::jmethodID* constructor = &jni::GetMethodID(env, *clazz, "<init>", "([Lcom/mapbox/mapboxsdk/style/functions/stops/Stop;)V");

        return &jni::NewObject(env, *clazz, *constructor, toFunctionStopJavaArray(env, value.stops));
    }

private:
    jni::JNIEnv& env;
};

template <class T>
struct Converter<jni::jobject*, mbgl::style::CameraFunction<T>> {

    Result<jni::jobject*> operator()(jni::JNIEnv& env, const mbgl::style::CameraFunction<T>& value) const {
        static jni::jclass* clazz = jni::NewGlobalRef(env, &jni::FindClass(env, "com/mapbox/mapboxsdk/style/functions/CameraFunction")).release();
        static jni::jmethodID* constructor = &jni::GetMethodID(env, *clazz, "<init>", "(Lcom/mapbox/mapboxsdk/style/functions/stops/Stops;)V");

        StopsEvaluator<T> evaluator(env);
        jni::jobject* stops = apply_visitor(evaluator, value.stops);
        jni::jobject* converted = &jni::NewObject(env, *clazz, *constructor, stops);

        return { converted };
    }
};

template <class T>
struct Converter<jni::jobject*, mbgl::style::SourceFunction<T>> {

    Result<jni::jobject*> operator()(jni::JNIEnv& env, const mbgl::style::SourceFunction<T>& value) const {
        static jni::jclass* clazz = jni::NewGlobalRef(env, &jni::FindClass(env, "com/mapbox/mapboxsdk/style/functions/SourceFunction")).release();
        static jni::jmethodID* constructor = &jni::GetMethodID(env, *clazz, "<init>", "(Ljava/lang/String;Lcom/mapbox/mapboxsdk/style/functions/stops/Stops;)V");

        StopsEvaluator<T> evaluator(env);
        jni::jobject* stops = apply_visitor(evaluator, value.stops);

        jni::jobject* converted = &jni::NewObject(env, *clazz, *constructor, jni::Make<jni::String>(env, value.property).Get(), stops);

        return { converted };
    }
};

} // namespace conversion
} // namespace android
} // namespace mbgl