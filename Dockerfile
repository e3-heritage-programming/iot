FROM ingensi/oracle-jdk
MAINTAINER Charles-William Crete <craftthatblock@gmail.com>

RUN yum install unzip

EXPOSE 9000

RUN mkdir /app
WORKDIR /app

COPY /data/target/universal/*-SNAPSHOT.zip .
RUN unzip *-SNAPSHOT.zip
RUN mv -r *-SNAPSHOT/* .

RUM rm -rf bin/*.bat

ENV PLAY_REMOTE_REST http://localhost:9001

CMD ["./bin/*"]