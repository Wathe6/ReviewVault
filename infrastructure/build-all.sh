#!/usr/bin/env bash
set -e

ROOT_DIR="$(cd "$(dirname "$0")/.." && pwd)"

cd "$ROOT_DIR"

echo "--------------------------------------------"
echo "Building Spring Boot services..."
echo "--------------------------------------------"

./gradlew clean \
  :services:eureka:bootJar \
  :services:gateway:bootJar \
  :services:media:bootJar

echo "--------------------------------------------"
echo "Starting Services Docker Compose..."
echo "--------------------------------------------"

docker compose -f infrastructure/docker-compose.services.yaml up --build