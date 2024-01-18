FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY build/libs/todolist-0.0.1-SNAPSHOT.jar deploy_todolist-0.0.1-SNAPSOT.jar
EXPOSE 8080
CMD [ "java", "-jar", "deploy_todolist-0.0.1-SNAPSOT.jar" ]

