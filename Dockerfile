FROM openjdk:8-jdk-alpine

EXPOSE 443

WORKDIR /app

COPY . .

RUN chmod 755 /app/mvnw

RUN ./mvnw dependency:go-offline -B

RUN ./mvnw package -DskipTests

RUN ls -al

ENTRYPOINT ["java","-jar","target/secme-0.0.1-SNAPSHOT.jar"]