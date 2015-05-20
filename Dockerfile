FROM ingensi/oracle-jdk
MAINTAINER Charles-William Crete <craftthatblock@gmail.com>

RUN yum install -y unzip

EXPOSE 9000

RUN mkdir /app
WORKDIR /app

ENV PLAY_REMOTE_REST http://localhost:9001

CMD bash start