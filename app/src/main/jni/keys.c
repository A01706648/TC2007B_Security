#include <jni.h>

JNIEXPORT jstring
Java_com_itesm_esenciapatrimonio_ui_LoginFragment_getUsername(JNIEnv *env, jobject instance) {
 return (*env)-> NewStringUTF(env, "patrimonio");
}

JNIEXPORT jstring
Java_com_itesm_esenciapatrimonio_ui_LoginFragment_getPassword(JNIEnv *env, jobject instance) {
 return (*env)-> NewStringUTF(env, "1234");
}