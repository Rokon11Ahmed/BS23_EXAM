#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_bs23exam_utils_Base_stgBaseUrl( JNIEnv *env, jobject /* this */) {
    std::string url = "https://api.github.com/";
    return env->NewStringUTF(url.c_str());
}