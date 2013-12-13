LOCAL_PATH := $(call my-dir)

#Build the java library
include $(CLEAR_VARS)
LOCAL_SRC_FILES := \
	$(call all-java-files-under, java)
LOCAL_MODULE_TAGS := optionnal
LOCAL_MODULE := com.android.utbm.lo52.libbmp
include $(BUILD_JAVA_LIBRARY)

#Build the JNI bindings
include $(CLEAR_VARS)
LOCAL_SRC_FILES := \
  $(call all-c-files-under, jni)
LOCAL_C_INCLUDES += \
  $(JNI_H_INCLUDE) \
   external/libmp/libbmp
LOCAL_SHARED_LIBRARIES := \
  libbmp \
  libcutils \
  libutils
LOCAL_MODULE := libbmpExample_jni
LOCAL_MODULE_TAGS := optional
LOCAL_PRELINK_MODULE := false
include $(BUILD_SHARED_LIBRARY)

#Copy permissions file
include $(CLEAR_VARS)
LOCAL_MODULE_TAGS := optional
LOCAL_MODULE := com.android.utbm.lo52.libbmp.xml
LOCAL_MODULE_CLASS := ETC
LOCAL_MODULE_PATH := $(TARGET_OUT_ETC)/permissions
LOCAL_SRC_FILES := $(LOCAL_MODULE)
include $(BUILD_PREBUILT)
