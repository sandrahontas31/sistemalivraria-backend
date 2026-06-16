# Estágio 1: Build da aplicação
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

# Copia apenas os arquivos do Maven primeiro para aproveitar o cache de dependências
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
# Baixa as dependências (ajuda a buildar mais rápido nas próximas vezes)
RUN ./mvnw dependency:go-offline

# Copia o código fonte e gera o arquivo .jar
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Estágio 2: Imagem final de execução (apenas o JRE, muito mais leve)
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copia o arquivo .jar gerado no primeiro estágio (build) para este novo ambiente
COPY --from=build /app/target/SistemaLivraria-Backend-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Executa o jar utilizando a porta da variável de ambiente (comum em deploys como Heroku/Render)
CMD ["sh", "-c", "java -Dserver.port=$PORT -jar app.jar"]