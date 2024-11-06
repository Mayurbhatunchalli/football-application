FROM openjdk:8
EXPOSE 8080
ADD target/football-devops-integration.jar football-devops-integration.jar
ENTRYPOINT ["java","-jar","/football-devops-integration.jar"]