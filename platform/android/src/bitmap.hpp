#pragma once

#include <mbgl/util/image.hpp>

#include <jni/jni.hpp>

#include "output_stream.hpp"

namespace mbgl {
namespace android {

class Bitmap {
public:
    class Config {
    public:
        static constexpr auto Name() {
            return "android/graphics/Bitmap$Config";
        };
        static void registerNative(jni::JNIEnv&);

        enum Value {
            ALPHA_8,
            ARGB_4444,
            ARGB_8888,
            RGB_565,
        };

        static jni::Object<Config> Create(jni::JNIEnv&, Value);

    private:
        static jni::Class<Config> _class;
    };

    class CompressFormat {
    public:
        static constexpr auto Name() {
            return "android/graphics/Bitmap$CompressFormat";
        };
        static void registerNative(jni::JNIEnv&);

        enum Value {
            JPEG,
            PNG,
            WEBP,
        };

        static jni::Object<CompressFormat> Create(jni::JNIEnv&, Value);

    private:
        static jni::Class<CompressFormat> _class;
    };

    static constexpr auto Name() {
        return "android/graphics/Bitmap";
    };
    static void registerNative(jni::JNIEnv&);

    static jni::Object<Bitmap>
    CreateBitmap(jni::JNIEnv&, jni::jint width, jni::jint height, jni::Object<Config>);
    static jni::Object<Bitmap>
    CreateBitmap(jni::JNIEnv& env, jni::jint width, jni::jint height, Config::Value config) {
        return CreateBitmap(env, width, height, Config::Create(env, config));
    }

    static jni::jboolean Compress(jni::JNIEnv&,
                                  jni::Object<Bitmap>,
                                  jni::Object<CompressFormat> format,
                                  jni::jint quality,
                                  jni::Object<OutputStream> stream);

    static PremultipliedImage GetImage(jni::JNIEnv&, jni::Object<Bitmap>);
    static jni::Object<Bitmap> CreateBitmap(jni::JNIEnv&, const PremultipliedImage&);

private:
    static jni::Class<Bitmap> _class;
};

} // namespace android
} // namespace mbgl
