LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_SRC_FILES := example.c
LOCAL_C_INCLUDES := external/libbmp/libbmp
LOCAL_MODULE := libbmpExample
LOCAL_MODULE_TAGS := optional
include $(BUILD_EXECUTABLE)
