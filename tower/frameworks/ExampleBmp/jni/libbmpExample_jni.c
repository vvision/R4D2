#include <stdio.h>
#include <bmpfile.h>
#include <jni.h>

JNIEXPORT jint JNICALL Java_com_android_utbm_lo52_libbmp_Example_bpmFunction(JNIEnv *env, jobject obj, 
jstring filename, jint width, jint height, jint depth) {

  bmpfile_t *bmp;
  int i, j;
	const char *nativeString = (*env)->GetStringUTFChars(env, filename, 0);
  rgb_pixel_t pixel = {128, 64, 0, 0};

  if ((bmp = bmp_create((int) width, (int) height, (int) depth)) == NULL) {
    printf("Invalid depth value: %s.\n", (int) depth);
    return 1;
  }

  for (i = 10, j = 10; j < (int) height; ++i, ++j) {
    bmp_set_pixel(bmp, i, j, pixel);
    pixel.red++;
    pixel.green++;
    pixel.blue++;
    bmp_set_pixel(bmp, i + 1, j, pixel);
    bmp_set_pixel(bmp, i, j + 1, pixel);
  }

  bmp_save(bmp, (*env)->ReleaseStringUTFChars(env, filename, nativeString));
  bmp_destroy(bmp);

  return 0;
}
