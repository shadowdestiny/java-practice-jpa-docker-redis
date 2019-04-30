# select image
FROM maven:3.5-jdk-8 as base

# copy your source tree
COPY ./ ./app
WORKDIR ./app

# build for release
#RUN mvn install

# set the startup command to run your binary
#CMD ["java", "-jar", "./order-service/target/com-order-service-1.0.jar"]