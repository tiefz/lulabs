FROM openjdk:8
ADD agendamento.jar agendamento.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "agendamento.jar"]