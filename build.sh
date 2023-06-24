#!/bin/sh
chmod +x gradlew
cd Task-Tracker-Entities
if [ ! -d "gradle" ]; then
    ln -s ../gradle
fi
if [ ! -e "gradlew" ]; then
    ln -s ../gradlew
fi
./gradlew check
./gradlew publishToMavenLocal

cd ../Task-Tracker-Entities-Impl
if [ ! -d "gradle" ]; then
    ln -s ../gradle
fi
if [ ! -e "gradlew" ]; then
    ln -s ../gradlew
fi
./gradlew check
./gradlew publishToMavenLocal

cd ../Task-Tracker-Usecases
if [ ! -d "gradle" ]; then
    ln -s ../gradle
fi
if [ ! -e "gradlew" ]; then
    ln -s ../gradlew
fi
./gradlew check
./gradlew publishToMavenLocal

cd ../Task-Tracker-Usecases-SQLite-Impl
if [ ! -d "gradle" ]; then
    ln -s ../gradle
fi
if [ ! -e "gradlew" ]; then
    ln -s ../gradlew
fi
./gradlew check
./gradlew publishToMavenLocal

cd ../Task-Tracker-CLI
if [ ! -d "gradle" ]; then
    ln -s ../gradle
fi
if [ ! -e "gradlew" ]; then
    ln -s ../gradlew
fi
./gradlew check
./gradlew assembleDist