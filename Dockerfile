FROM openjdk
COPY ./build/libs/CoursesMVP-0.0.1-SNAPSHOT.jar /app/start.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "/app/start.jar"]