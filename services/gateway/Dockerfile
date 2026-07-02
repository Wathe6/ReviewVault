FROM eclipse-temurin:26-jre

WORKDIR /app

COPY services/gateway/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]