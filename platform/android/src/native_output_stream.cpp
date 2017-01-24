#include "native_output_stream.hpp"

namespace mbgl {
namespace android {

#define METHOD(MethodPtr, name) jni::MakeNativePeerMethod<decltype(MethodPtr), (MethodPtr)>(name)

void NativeOutputStream::registerNative(jni::JNIEnv& env) {
    _class = *jni::Class<NativeOutputStream>::Find(env).NewGlobalRef(env).release();

    jni::RegisterNativePeer<NativeOutputStream>(
        env, _class, "peer",
        std::make_unique<NativeOutputStream, jni::JNIEnv&>,
        "initialize", "finalize",
        METHOD(&NativeOutputStream::writeByteArray, "write"),
        METHOD(&NativeOutputStream::writeInt, "write"));
}

jni::Class<NativeOutputStream> NativeOutputStream::_class;

NativeOutputStream::NativeOutputStream(jni::JNIEnv&) {
}

void NativeOutputStream::writeByteArray(jni::JNIEnv& env,
                                        jni::Array<jni::jbyte> b,
                                        jni::jint off,
                                        jni::jint len) {
    const auto end = data.size();
    data.resize(end + len);
    jni::GetArrayRegion(env, jni::SafeDereference(env, b.Get()), off, len,
                        reinterpret_cast<signed char*>(const_cast<char*>(data.data() + end)));
}

void NativeOutputStream::writeInt(jni::JNIEnv&, jni::jint b) {
    data.append(1, char(b));
}

jni::Object<NativeOutputStream> NativeOutputStream::New(jni::JNIEnv& env) {
    return _class.New(env, jni::Constructor<NativeOutputStream>{ env, _class });
}

std::string NativeOutputStream::TakeResult(jni::JNIEnv& env, jni::Object<NativeOutputStream> obj) {
    jni::Field<NativeOutputStream, jni::jlong> field{ env, _class, "peer" };
    return std::move(reinterpret_cast<NativeOutputStream*>(obj.Get(env, field))->data);
}

} // namespace android
} // namespace mbgl
