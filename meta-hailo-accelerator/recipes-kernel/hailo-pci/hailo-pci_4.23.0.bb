DESCRIPTION = "hailo pcie driver \
               compiles the kernel driver for pci communication with hailo8 \
               the recipe calls the compilation process with the proper cross-compiler and kernel directory. \
               the output of the compilation (hailo_pci.ko) is copied to the target's rootfs"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://../../LICENSE;md5=39bba7d2cf0ba1036f2a6e2be52fe3f0"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = " \
	git://git@github.com/hailo-ai/hailort-drivers.git;protocol=https;branch=hailo8 \
	file://0001-hailo-vdma-locking-fix.patch;patchdir=${WORKDIR}/git;apply=no \
	file://0002-hailo-silence-vdma-locking.patch;patchdir=${WORKDIR}/git;apply=yes \
"

SRCREV = "ce1087bfe8132c99b41374e3128fc78612a3f492"

inherit module

S = "${WORKDIR}/git/linux/pcie"

EXTRA_OEMAKE += "KERNEL_DIR=${STAGING_KERNEL_DIR}"
MAKE_TARGETS = "all"
MODULES_INSTALL_TARGET = "install"

do_install:append() {
	install -d ${D}/etc/modprobe.d
	install ${S}/hailo_pci.conf ${D}/etc/modprobe.d
	install -d ${D}/etc/udev/rules.d
	install ${S}/51-hailo-udev.rules ${D}/etc/udev/rules.d
}

FILES:${PN} += " /etc/modprobe.d/hailo_pci.conf /etc/udev/rules.d/51-hailo-udev.rules"
