FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# disable hailo driver shipped with the kernel package
SRC_URI += " \
	file://disable_hailo.cfg \
	file://0001-findvmahailo.patch \
"
