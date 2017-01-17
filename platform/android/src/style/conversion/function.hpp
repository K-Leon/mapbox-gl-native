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

template <class T>
inline jni::jobject* toFunctionStopJavaArray(jni::JNIEnv& env, std::map<float, T> value) {
    static jni::jclass* javaClass = jni::NewGlobalRef(env, &jni::FindClass(env, "com/mapbox/mapboxsdk/style/functions/Stop")).release();
    static jni::jmethodID* constructor = &jni::GetMethodID(env, *javaClass, "<init>", "(Ljava/lang/Object;Ljava/lang/Object;)V");

    jni::jarray<jni::jobject>& jarray = jni::NewObjectArray(env, value.size(), *javaClass);

    size_t i = 0;
    for (auto const& stop : value) {
        jni::jobject* in = *convert<jni::jobject*, float>(env, stop.first);
        jni::jobject* out = *convert<jni::jobject*, T>(env, stop.second);
        jni::SetObjectArrayElement(env, jarray, i, &jni::NewObject(env, *javaClass, *constructor, in, out));
        i++;
    }

    return &jarray;
}

/**
 * Conversion from core function stops to ZoomFunction java object
 */
template <typename T>
class CameraFunctionEvaluator {
public:

    CameraFunctionEvaluator(jni::JNIEnv& _env) : env(_env) {}

    jni::jobject* operator()(const mbgl::style::ExponentialStops<T> &value) const {
        static jni::jclass* javaClass = jni::NewGlobalRef(env, &jni::FindClass(env, "com/mapbox/mapboxsdk/style/functions/ZoomFunction")).release();
        static jni::jmethodID* constructor = &jni::GetMethodID(env, *javaClass, "<init>", "(Ljava/lang/String;Ljava/lang/Float;[Lcom/mapbox/mapboxsdk/style/functions/Stop;)V");

        return &jni::NewObject(env, *javaClass, *constructor,
            jni::Make<jni::String>(env, "exponential").Get(),
            *convert<jni::jobject*>(env, value.base),
            toFunctionStopJavaArray(env, value.stops));
    }

    jni::jobject* operator()(const mbgl::style::IntervalStops<T> &) const {
        return nullptr;
    }

private:
    jni::JNIEnv& env;

};

template <class T>
struct Converter<jni::jobject*, mbgl::style::CameraFunction<T>> {

    Result<jni::jobject*> operator()(jni::JNIEnv& env, const mbgl::style::CameraFunction<T>& value) const {
        CameraFunctionEvaluator<T> evaluator(env);
        return { apply_visitor(evaluator, value.stops) };
    }
};

} // namespace conversion
} // namespace android
} // namespace mbgl