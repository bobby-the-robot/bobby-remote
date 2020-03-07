FROM circleci/openjdk:11-jdk
CMD exec java -Xms128m -Xmx512m -jar build/libs/*.jar
