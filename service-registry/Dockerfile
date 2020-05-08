FROM adoptopenjdk/openjdk11:alpine-jre
COPY target/service-registry-1.0.0.jar /app/app.jar
CMD ["java", "-jar", "/app/app.jar"]
EXPOSE 9090