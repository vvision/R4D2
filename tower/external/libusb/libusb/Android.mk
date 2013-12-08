LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_SRC_FILES := core.c descriptor.c io.c sync.c os/linux_usbfs.c os/threads_posix.c
LOCAL_C_INCLUDES := external/libusb/libusb external/libusb/libusb/os
LOCAL_CFLAGS += -DLIBUSB_DESCRIBE
LOCAL_MODULE := libusb-shared
LOCAL_MODULE_TAGS := optional
include $(BUILD_SHARED_LIBRARY)


include $(CLEAR_VARS)
LOCAL_SRC_FILES := core.c descriptor.c io.c sync.c os/linux_usbfs.c os/threads_posix.c
LOCAL_C_INCLUDES := external/libusb/libusb external/libusb/libusb/os
LOCAL_CFLAGS += -DLIBUSB_DESCRIBE
LOCAL_MODULE := libusb-static
LOCAL_MODULE_TAGS := optional
include $(BUILD_STATIC_LIBRARY)
