# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

ARG JAR_FILE="/target/*.jar"

COPY ${JAR_FILE} app.jar

# Run the jar file
CMD ["java", "-jar", "app.jar"]
