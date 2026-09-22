# Simple Dockerfile for the backend.
#
# 1. Create the jar from the backend folder:
#    .\gradlew.bat clean bootJar
#
# 2. Build the Docker image:
#    docker build -t transport-issue-reporting-platform-backend:0.0.1 .
#
# 3. Run the Docker image:
#    docker run --rm -p 8080:8080 transport-issue-reporting-platform-backend:0.0.1
#
# 4. Push the image to Docker Hub:
#    docker login
#    docker tag transport-issue-reporting-platform-backend:0.0.1 YOUR_DOCKERHUB_USERNAME/transport-issue-reporting-platform-backend:0.0.1
#    docker push YOUR_DOCKERHUB_USERNAME/transport-issue-reporting-platform-backend:0.0.1
#
# To update this Dockerfile:
# - If the app changes, rebuild the jar and rebuild the Docker image.
# - If the Java version changes in build.gradle, update the Java image below.
# - If the app port changes, update EXPOSE and the docker run -p command.

# Use Java 21 because this project uses Java 21 in build.gradle.
FROM eclipse-temurin:21-jre-alpine

# Put the app inside /app in the container.
WORKDIR /app

# Copy the jar created by Gradle into the container.
COPY build/libs/*.jar app.jar

# Spring Boot uses port 8080 by default.
EXPOSE 8080

# Start the backend.
ENTRYPOINT ["java", "-jar", "app.jar"]
