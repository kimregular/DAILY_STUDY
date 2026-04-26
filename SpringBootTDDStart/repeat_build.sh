#!/bin/sh

while true; do
  ./gradlew clean build -i
  if [ $? -ne 0 ]; then
    echo "Build did not exit successfully. Stopping the script."
    break
  fi
  echo "Build succeeded. Retrying..."
done
