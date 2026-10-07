#!/usr/bin/env bash
set -e

ROOT_DIR="$(cd "$(dirname "$0")/.." && pwd)"

cd "$ROOT_DIR"

echo "--------------------------------------------"
echo "Building Spring Boot services..."
echo "--------------------------------------------"

if ! ./gradlew clean \
  :services:contracts:jar \
  :services:eureka:bootJar \
  :services:gateway:bootJar \
  :services:media:bootJar \
  :services:review:bootJar \
  :services:profile:bootJar; then
  echo "Gradle build failed. Docker Compose was not started." >&2
  exit 1
fi

echo "--------------------------------------------"
echo "Starting Services Docker Compose..."
echo "--------------------------------------------"

docker compose -f infrastructure/docker-compose.services.yaml up --build
