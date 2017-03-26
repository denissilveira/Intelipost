FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD intelipost-docker-0.1.0.jar intelipost-1.0.0.jar
RUN sh -c 'touch /intelipost-1.0.0.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /intelipost-1.0.0.jar" ]