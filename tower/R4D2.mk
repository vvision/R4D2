$(call inherit-product, device/linaro/pandaboard/pandaboard.mk

PRODUCT_PROPERTY_OVERRIDES := ro.hw=lo52
PRODUCT_PROPERTY_OVERRIDES += hw.nobattery=true
DEVICE_PACKAGE_OVERLAY:=device/linaro/R4D2/overlay
PRODUCT_PACKAGES += \
  libusb-shared \
  libusb-static \
  libbmp-shared \
  libbmp-static \
  libbmpExample \
  libbmpExample_jni

PRODUCT_COPY_FILES += \
  system/media/bootanimation.zip:system/media/bootanimation.zip

PRODUCT_NAME := R4D2
PRODUCT_DEVICE := R4D2
PRODUCT_BRAND := R4D2
PRODUCT_MODELS := R4D2

include $(call all-subdir-makefiles)
