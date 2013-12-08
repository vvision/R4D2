LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_SRC_FILES := bmpfile.c
LOCAL_C_INCLUDES := external/libmp/libbmp
LOCAL_MODULE := libbmp-shared
LOCAL_MODULE_TAGS := optional
include $(BUILD_SHARED_LIBRARY)


include $(CLEAR_VARS)
LOCAL_SRC_FILES := bmpfile.c
LOCAL_C_INCLUDES := external/libmp/libbmp
LOCAL_MODULE := libbmp-static
LOCAL_MODULE_TAGS := optional
include $(BUILD_STATIC_LIBRARY)
