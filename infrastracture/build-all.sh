#!/usr/bin/env bash
set -e

ROOT_DIR=$(pwd)

echo "--------------------------------------------"
echo "Building eureka..."
echo "--------------------------------------------"
cd "$ROOT_DIR/eureka"
./gradlew clean bootJar

echo "--------------------------------------------"
echo "Building gateway..."
echo "--------------------------------------------"
cd "$ROOT_DIR/gateway"
./gradlew clean bootJar

echo "--------------------------------------------"
echo "All projects built successfully."
echo "Artifacts:"
echo " - eureka:  eureka/build/libs/"
echo " - gateway: gateway/build/libs/"
echo "--------------------------------------------"
