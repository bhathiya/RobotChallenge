FROM openjdk:11.0-jre-slim
WORKDIR /opt
RUN mkdir -p /opt/sample-inputs
COPY target/${project.build.finalName}-full.jar robot-challenge.jar
COPY samples/* sample-inputs/
CMD tail -f /dev/null
