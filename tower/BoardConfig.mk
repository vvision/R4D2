include device/linaro/pandaboard/BoardConfig.mk

ifneq ($(wildcard $(TOP)/kernel/arch/arm/configs/android_omap4plus_defconfig),)
KERNEL_CONFIG := android_omap4plus_defconfig
else
KERNEL_CONFIG := omap4plus_defconfig
endif
