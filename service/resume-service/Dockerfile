# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="btambara@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8081 available to the world outside this container
EXPOSE 8081

ENV OMOIKANE_DB_USERNAME=$OMOIKANE_DB_USERNAME

ENV OMOIKANE_DB_PASSWORD=$OMOIKANE_DB_PASSWORD

# The application's jar file
ARG JAR_FILE=target/resume-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} resume.jar

ENV JAVA_OPTS=""

# Run the jar file
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /resume.jar"]
