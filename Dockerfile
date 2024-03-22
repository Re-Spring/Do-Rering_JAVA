# Start with a base image that has Java installed
FROM openjdk:17-jdk-slim

# Add a volume pointing to /tmp for any temporary file storage needs
VOLUME /tmp

# Make the application's port available outside the container
EXPOSE 8001

# Copy the built JAR file from your host into the Docker image
COPY build/libs/rest-0.0.1-SNAPSHOT.jar app.jar

# Specify the entry point to run your app
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]