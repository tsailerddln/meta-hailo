DESCRIPTION = "hailo firmware eth \
			   hailo8 chip firmware for using the ethernet interface (hailo_fw.bin) \
			   the recipe copies the file to /lib/firmware/hailo/ on the target device’s root file system"

BASE_URI = "https://hailo-hailort.s3.eu-west-2.amazonaws.com"
FW_AWS_DIR = "Hailo8/${PV}/FW"
FW = "hailo8_fw.${PV}_eth.bin"
LICENSE_FILE = "LICENSE"
SRC_URI = "${BASE_URI}/${FW_AWS_DIR}/${FW};md5sum=5d09aa87c742ab49f9687bb5ca4680dd \
		${BASE_URI}/${FW_AWS_DIR}/${LICENSE_FILE};md5sum=263ee034adc02556d59ab1ebdaea2cda"

LICENSE = "LICENSE"
LIC_FILES_CHKSUM = "file://${LICENSE_FILE};md5=263ee034adc02556d59ab1ebdaea2cda"

NO_GENERIC_LICENSE[LICENSE] = "LICENSE"

FW_PATH = "${UNPACKDIR}/${FW}"

S = "${WORKDIR}/sources"
UNPACKDIR = "${S}"

do_install:append() {
	# Stores hailo8_fw.bin in the rootfs under /usr/lib/firmware/hailo/
	install -d ${D}/usr/lib/firmware/hailo
	install -m 0755 ${FW_PATH} ${D}/usr/lib/firmware/hailo/hailo8_fw.bin
}

FILES_${PN} += "/usr/lib /usr/lib/* /usr/lib/firmware/hailo/hailo8_fw*"
