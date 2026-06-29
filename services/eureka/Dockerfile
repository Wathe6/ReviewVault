# ---------- Build stage ----------
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app
COPY . .
RUN ./gradlew clean bootJar --no-daemon

# ---------- Runtime ----------
FROM eclipse-temurin:21-jre

WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
