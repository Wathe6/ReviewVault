FROM eclipse-temurin:26-jre

WORKDIR /app

COPY services/eureka/build/libs/*.jar app.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "app.jar"]