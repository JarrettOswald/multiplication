FROM adoptopenjdk/openjdk17:alpine-jre
WORKDIR /app
COPY target/multiplication-24.03.02.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]