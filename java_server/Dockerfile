FROM gradle:7.6.1-jdk17-alpine
COPY . .
RUN gradle build
EXPOSE 44555
CMD ["java", "-jar", "./build/libs/java_server.jar"]

