FROM openjdk:19
ADD target/AuthServer-0.0.1-SNAPSHOT.jar AuthServer-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "AuthServer-0.0.1-SNAPSHOT.jar"]