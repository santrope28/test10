TARGETS = console-setup resolvconf mountkernfs.sh ufw hostname.sh plymouth-log screen-cleanup apparmor udev keyboard-setup cryptdisks cryptdisks-early urandom hwclock.sh networking mountdevsubfs.sh checkroot.sh lvm2 checkfs.sh iscsid open-iscsi checkroot-bootclean.sh bootmisc.sh mountnfs.sh mountnfs-bootclean.sh mountall-bootclean.sh mountall.sh procps kmod
INTERACTIVE = console-setup udev keyboard-setup cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
urandom: hwclock.sh
hwclock.sh: mountdevsubfs.sh
networking: resolvconf mountkernfs.sh urandom procps
mountdevsubfs.sh: mountkernfs.sh udev
checkroot.sh: hwclock.sh keyboard-setup mountdevsubfs.sh hostname.sh
lvm2: cryptdisks-early mountdevsubfs.sh udev
checkfs.sh: cryptdisks checkroot.sh lvm2
iscsid: networking
open-iscsi: networking iscsid
checkroot-bootclean.sh: checkroot.sh
bootmisc.sh: checkroot-bootclean.sh mountnfs-bootclean.sh mountall-bootclean.sh udev
mountnfs.sh: networking
mountnfs-bootclean.sh: mountnfs.sh
mountall-bootclean.sh: mountall.sh
mountall.sh: lvm2 checkfs.sh checkroot-bootclean.sh
procps: mountkernfs.sh udev
kmod: checkroot.sh
