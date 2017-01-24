#pragma once

#include <jni/jni.hpp>

#include "bitmap.hpp"

namespace mbgl {
namespace android {

class OutputStream {
public:
    static constexpr auto Name() {
        return "java/io/OutputStream";
    }

    static auto GetClass(jni::JNIEnv& env) {
        static auto clazz = *jni::Class<OutputStream>::Find(env).NewGlobalRef(env).release();
        return clazz;
    }

    template <class T>
    static auto Cast(jni::JNIEnv& env, const jni::Object<T>& object) {
        return jni::Cast(env, object, GetClass(env));
    }
};

} // namespace android
} // namespace mbgl
