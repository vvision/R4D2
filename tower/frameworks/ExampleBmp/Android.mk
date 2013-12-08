LOCAL_PATH := $(call my-dir)

#Build the java library
include $(CLEAR_VARS)
LOCAL_SRC_FILES := \
	$(call all-java-files-under, java)
LOCAL_MODULE_TAGS := optionnal
LOCAL_MODULE := com.android.utbm.lo52.libbmp
include $(BUILD_JAVA_LIBRARY)

#Build the JNI bindings



#Copy permissions file








#TODO: TO BE COMPLETED...
