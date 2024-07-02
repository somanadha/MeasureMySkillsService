FROM openjdk:24-slim-bullseye
ADD target/measure-my-skills-service.jar measure-my-skills-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "measure-my-skills-service.jar"]