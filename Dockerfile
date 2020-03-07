FROM openjdk:11-jre-slim
CMD exec java -Xms128m -Xmx512m -jar build/libs/*.jar
