FROM openjdk:17-alpine
CMD ["./gradlew", "clean", "build"]
COPY *.jar app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "app.jar"]