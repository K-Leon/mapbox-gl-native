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
    static jni::jclass* javaClass = jni::NewGlobalRef(env, &jni::FindClass(env, "com/mapbox/mapboxsdk/style/functions/Stop")).release();
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
 * Conversion from core function stops to PropertyFunction java object
 */
template <class T>
class SourceFunctionEvaluator {
public:

    SourceFunctionEvaluator(jni::JNIEnv& _env, const std::string& _property) : env(_env), property(_property) {}

    jni::jobject* operator()(const mbgl::style::CategoricalStops<T> &) const {
        //static jni::jmethodID* constructor = &jni::GetMethodID(env, *this->javaClass(), "<init>", "(Ljava/lang/String)V");

        //return &jni::NewObject(env, *this->javaClass(), *constructor,
        //    jni::Make<jni::String>(env, property).Get(),
        //    jni::Make<jni::String>(env, "categorical").Get(),
        //    *convert<jni::jobject*>(env, value.defaultValue),
        //    toFunctionStopJavaArray(env, value.stops));
        return nullptr;
    }

    jni::jobject* operator()(const mbgl::style::ExponentialStops<T> &value) const {
        static jni::jmethodID* constructor = &jni::GetMethodID(env, *this->javaClass(), "<init>", "(Ljava/lang/String;Ljava/lang/Float;[Lcom/mapbox/mapboxsdk/style/functions/Stop;)V");

        return &jni::NewObject(env, *this->javaClass(), *constructor,
            jni::Make<jni::String>(env, property).Get(),
            jni::Make<jni::String>(env, "exponential").Get(),
            *convert<jni::jobject*>(env, value.base),
            toFunctionStopJavaArray(env, value.stops));
    }

    jni::jobject* operator()(const mbgl::style::IdentityStops<T> &) const {
        static jni::jmethodID* constructor = &jni::GetMethodID(env, *this->javaClass(), "<init>", "(Ljava/lang/String;)V");

        return &jni::NewObject(env, *this->javaClass(), *constructor,
            jni::Make<jni::String>(env, property).Get(),
            jni::Make<jni::String>(env, "identity").Get());
    }

    jni::jobject* operator()(const mbgl::style::IntervalStops<T> &value) const {
        static jni::jmethodID* constructor = &jni::GetMethodID(env, *this->javaClass(), "<init>", "(Ljava/lang/String;[Lcom/mapbox/mapboxsdk/style/functions/Stop;)V");

        return &jni::NewObject(env, *this->javaClass(), *constructor,
            jni::Make<jni::String>(env, property).Get(),
            jni::Make<jni::String>(env, "interval").Get(),
            toFunctionStopJavaArray(env, value.stops));
    }

private:
    jni::JNIEnv& env;
    const std::string& property;

    jni::jclass* javaClass() const {
        static jni::jclass* clazz = jni::NewGlobalRef(env, &jni::FindClass(env, "com/mapbox/mapboxsdk/style/functions/PropertyFunction")).release();
        return clazz;
    }
};

/**
 * Conversion from core function stops to ZoomFunction java object
 */
template <class T>
class CameraFunctionEvaluator {
public:

    CameraFunctionEvaluator(jni::JNIEnv& _env) : env(_env) {}

    jni::jobject* operator()(const mbgl::style::ExponentialStops<T> &value) const {
        static jni::jmethodID* constructor = &jni::GetMethodID(env, *this->javaClass(), "<init>", "(Ljava/lang/String;Ljava/lang/Float;[Lcom/mapbox/mapboxsdk/style/functions/Stop;)V");

        return &jni::NewObject(env, *this->javaClass(), *constructor,
            jni::Make<jni::String>(env, "exponential").Get(),
            *convert<jni::jobject*>(env, value.base),
            toFunctionStopJavaArray(env, value.stops));
    }

    jni::jobject* operator()(const mbgl::style::IntervalStops<T> &value) const {
        static jni::jmethodID* constructor = &jni::GetMethodID(env, *this->javaClass(), "<init>", "(Ljava/lang/String;[Lcom/mapbox/mapboxsdk/style/functions/Stop;)V");

        return &jni::NewObject(env, *this->javaClass(), *constructor,
            jni::Make<jni::String>(env, "interval").Get(),
            toFunctionStopJavaArray(env, value.stops));
    }

private:
    jni::JNIEnv& env;

    jni::jclass* javaClass() const {
        static jni::jclass* clazz = jni::NewGlobalRef(env, &jni::FindClass(env, "com/mapbox/mapboxsdk/style/functions/ZoomFunction")).release();
        return clazz;
    }
};

template <class T>
struct Converter<jni::jobject*, mbgl::style::CameraFunction<T>> {

    Result<jni::jobject*> operator()(jni::JNIEnv& env, const mbgl::style::CameraFunction<T>& value) const {
        CameraFunctionEvaluator<T> evaluator(env);
        return { apply_visitor(evaluator, value.stops) };
    }
};

template <class T>
struct Converter<jni::jobject*, mbgl::style::SourceFunction<T>> {

    Result<jni::jobject*> operator()(jni::JNIEnv& env, const mbgl::style::SourceFunction<T>& value) const {
        SourceFunctionEvaluator<T> evaluator(env, value.property);
        return { apply_visitor(evaluator, value.stops) };
    }
};

} // namespace conversion
} // namespace android
} // namespace mbgl