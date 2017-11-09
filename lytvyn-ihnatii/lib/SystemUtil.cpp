#include "SystemUtil.h"
#include <stdlib.h>
JNIEXPORT void JNICALL Java_ua_khpi_oop_lytvyn07_Application_system(JNIEnv *env, jclass jclazz, jstring jstr){
    const char *str = (*env).GetStringUTFChars(jstr, NULL);
    if (str == NULL) {
        return; // Exception?
    }
    system(str);
    (*env).ReleaseStringUTFChars(jstr, str);
}