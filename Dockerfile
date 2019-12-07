FROM openjdk:8u212-jdk-alpine3.9

EXPOSE 8081/tcp

ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app


ENTRYPOINT ["java","-Xms128m","-Xmx256m","-cp","app:app/lib/*",\
  "de.th.koeln.archilab.fae.faeteam3service.FaeTeam3ServiceApplication"]