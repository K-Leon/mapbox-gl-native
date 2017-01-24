#include <mbgl/util/image.hpp>
#include <mbgl/util/string.hpp>

#include <string>

#include "attach_env.hpp"
#include "bitmap_factory.hpp"
#include "output_stream.hpp"
#include "native_output_stream.hpp"

namespace mbgl {

PremultipliedImage decodeImage(const std::string& string) {
    auto env{ android::AttachEnv() };

    auto array = jni::Array<jni::jbyte>::New(*env, string.size());
    jni::SetArrayRegion(*env, *array, 0, string.size(),
                        reinterpret_cast<const signed char*>(string.data()));

    auto bitmap = android::BitmapFactory::DecodeByteArray(*env, array, 0, string.size());
    return android::Bitmap::GetImage(*env, bitmap);
}

// On Android, we directly encode an image to Bitmap object to avoid the roundtrip to a byte[].
// This method is only called as part of the core test cases.
std::string encodePNG(const PremultipliedImage& img) {
    auto env{ android::AttachEnv() };

    auto bitmap = android::Bitmap::CreateBitmap(*env, img);
    auto nativeStream = android::NativeOutputStream::New(*env);
    auto success = android::Bitmap::Compress(
        *env, bitmap,
        android::Bitmap::CompressFormat::Create(*env, android::Bitmap::CompressFormat::PNG), 0,
        android::OutputStream::Cast(*env, nativeStream));
    if (!success) {
        throw std::runtime_error("bitmap encoding: failed to compress");
    }
    return android::NativeOutputStream::TakeResult(*env, nativeStream);
}

} // namespace mbgl
