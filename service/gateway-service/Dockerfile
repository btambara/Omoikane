# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="btambara@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8761 available to the world outside this container
EXPOSE 8761

ENV OMOIKANE_DB_USERNAME=$OMOIKANE_DB_USERNAME

ENV OMOIKANE_DB_PASSWORD=$OMOIKANE_DB_PASSWORD

# The application's jar file
ARG JAR_FILE=target/gateway-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} gateway.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=prod","-jar","/gateway.jar"]
