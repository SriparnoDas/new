FROM adoptopenjdk:11-jdk-hotspot

COPY  ./target/user-stock-preference-0.0.1-SNAPSHOT.jar user-stock-preference-0.0.1-SNAPSHOT.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "user-stock-preference-0.0.1-SNAPSHOT.jar"]
