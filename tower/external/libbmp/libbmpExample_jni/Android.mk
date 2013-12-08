LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_PRELINK_MODULE := false
LOCAL_SRC_FILES := libbmpExample_jni.c
LOCAL_C_INCLUDES := external/libmp/libbmp \
										$(JNI_H_INCLUDE)
LOCAL_SHARED_LIBRARIES := libbmp \
													libcutils \
													libutils
LOCAL_MODULE := libbmpExample_jni
LOCAL_MODULE_TAGS := optional
include $(BUILD_SHARED_LIBRARY)
