FROM openjdk:17
EXPOSE 8080
ADD target/comprehensive-1-0.0.1-SNAPSHOT.jar comprehensive-1-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "comprehensive-1-0.0.1-SNAPSHOT.jar"]

