FROM gradle:8-jdk17
WORKDIR /dev/mnt
CMD ["./build.sh"]