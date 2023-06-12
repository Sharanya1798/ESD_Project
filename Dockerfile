FROM openjdk:8
COPY ./target/Lab3Junit-1.0-SNAPSHOT-jar-with-dependencies.jar ./
WORKDIR ./
CMD ["java", "-jar", "Lab3Junit-1.0-SNAPSHOT-jar-with-dependencies.jar"]