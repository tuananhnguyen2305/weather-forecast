# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-alpine

# Set the working directory inside the container
WORKDIR /app

# Create a directory
RUN mkdir /app/log
RUN mkdir /app/mail_templates

# Copy the JAR file from your host to the container
COPY target/fs-cms-v2-api-0.1.jar .

# Expose the port on which your Spring Boot application listens (if applicable)
EXPOSE 8085

# Specify the command to run your application
CMD ["java", "-jar", "fs-cms-v2-api-0.1.jar"]

