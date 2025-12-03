# ====== Etapa 1: Build ======
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build

WORKDIR /app

# Copiamos pom y código fuente
COPY pom.xml .
COPY src ./src

# Compilamos el proyecto (sin tests para ir más rápido)
RUN mvn -q -DskipTests package


# ====== Etapa 2: Runtime ======
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copiamos el JAR construido en la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Copiamos la wallet a una ruta fija
COPY oracle_wallet /opt/oracle/wallet

# Configuramos la ruta de la wallet
ENV TNS_ADMIN=/opt/oracle/wallet
ENV JAVA_TOOL_OPTIONS="-Doracle.net.tns_admin=/opt/oracle/wallet"

# Puerto de Spring Boot
EXPOSE 8080

# Arrancamos la app
ENTRYPOINT ["java", "-jar", "app.jar"]
