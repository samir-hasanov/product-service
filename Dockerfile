FROM openjdk:19
EXPOSE 8089
ARG JAR_FILE=target/*.jar
COPY ./target/product-service-1.0.1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]