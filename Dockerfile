FROM adoptopenjdk/openjdk11-openj9:latest
COPY target/*.jar app.jar
CMD ["java", "-Dspring.profiles.active=dev","-jar", "app.jar"]