FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/*.jar calculator.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "calculator.jar"]