# Use an official OpenJDK runtime as a parent image
FROM ubuntu:latest

# Install OpenJDK 17
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk

# Set the working directory to /app
WORKDIR /src

# Copy the project jar file into the container at /app
COPY target/AuthServer-0.0.1-SNAPSHOT.jar /src

# Expose port 8080 to the outside world
EXPOSE 5001

# Run the jar file when the container starts
CMD ["java", "-jar", "AuthServer-0.0.1-SNAPSHOT.jar"]