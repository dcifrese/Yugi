FROM anapsix/alpine-java:8_server-jre

ENV ZIP=rocco.zip
ADD $ZIP /

RUN unzip /$ZIP && rm $ZIP

#move everything from rocco-1.0-SNAPSHOT to the rocco dir
RUN mv rocco* rocco

WORKDIR /rocco

COPY docker-entrypoint.sh /
RUN dos2unix /docker-entrypoint.sh
ENTRYPOINT ["/docker-entrypoint.sh"]

CMD ["/bin/sh", "-c", "bin/rocco"]
