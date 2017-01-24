#pragma once

#include <mbgl/util/noncopyable.hpp>

#include <jni/jni.hpp>

namespace mbgl {
namespace android {

class NativeOutputStream : public util::noncopyable {
public:
    static constexpr auto Name() {
        return "com/mapbox/mapboxsdk/utils/NativeOutputStream";
    };
    static void registerNative(jni::JNIEnv&);

private:
    // JNI implementation
    friend std::unique_ptr<NativeOutputStream> std::make_unique<NativeOutputStream>(jni::JNIEnv&);
    NativeOutputStream(jni::JNIEnv&);
    void writeByteArray(jni::JNIEnv&, jni::Array<jni::jbyte> b, jni::jint off, jni::jint len);
    void writeInt(jni::JNIEnv&, jni::jint b);

public:
    static jni::Object<NativeOutputStream> New(jni::JNIEnv&);
    static std::string TakeResult(jni::JNIEnv&, jni::Object<NativeOutputStream>);

private:
    static jni::Class<NativeOutputStream> _class;

private:
    std::string data;
};

} // android
} // mbgl
