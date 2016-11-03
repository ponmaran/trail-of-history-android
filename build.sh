#!/bin/bash

# Exit on error
set -e

# Copy mock google-services file if necessary

if [ ! -f ./ranger/google-services.json ]; then
  echo "Using mock google-services.json"
  cp ./mock-google-services.json ./ranger/google-services.json
fi

if [ ! -f ./app/google-services.json ]; then
  echo "Using mock google-services.json"
  cp ./mock-google-services.json ./app/google-services.json
fi

# Copy mock keys.xml file if necessary

if [ ! -f ./app/src/main/res/values/keys.xml ]; then
  echo "Using mock google-services.json"
  cp ./mock_keys.xml ./app/src/main/res/values/keys.xml
fi

# Copy mock google-services file if necessary
if [ ! -f ./ranger/src/main/res/values/keys.xml ]; then
  echo "Using mock google-services.json"
  cp ./mock_keys.xml ./ranger/src/main/res/values/keys.xml
fi

./gradlew check -Dpre-dex=false

# Emulator Management: Create, Start and Wait
echo no | android create avd --force -n test -t android-19 --abi armeabi-v7a
echo "Emulator creation finished"
emulator -avd test -no-window &
android-wait-for-emulator
adb shell input keyevent 82 &

./gradlew app:connectedAndroidTest
./gradlew ranger:connectedAndroidTest
