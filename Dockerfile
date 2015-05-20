FROM ingensi/oracle-jdk
MAINTAINER Charles-William Crete <craftthatblock@gmail.com>

RUN yum install -y unzip

EXPOSE 9000

RUN mkdir /app
WORKDIR /app

ENV PLAY_REMOTE_REST $PLAY_REMOTE_REST
ENV PLAY_REST_MYSQL $PLAY_REST_MYSQL

CMD bash start