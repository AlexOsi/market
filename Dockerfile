FROM openjdk:8-jdk-alpine
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000
ADD target/market-0.0.1-SNAPSHOT.jar market.jar
ENTRYPOINT ["java", "-jar", "market.jar"]