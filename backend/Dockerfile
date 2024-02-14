FROM openjdk:17-alpine
CMD ["./gradlew", "clean", "bootJar"]
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "app.jar"]
RUN mkdir -p /download/live
RUN mkdir -p /download/shortping