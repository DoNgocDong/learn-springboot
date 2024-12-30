# Sử dụng Maven image với openjdk-21
FROM openjdk:21

# Thu muc lam viec chinh trong Container
WORKDIR /app

# copy folder .mvn(local) vaof .mvn(container)
COPY .mvn/ .mvn
# Copy mvnw, pom.xml vao workdir(container)
COPY mvnw pom.xml ./

# install toan bo dependencies
RUN ./nvmw dependency:go-offline

# Copy all(exclue in dockerignore) to workdir
COPY . .

# Run cmd build project
RUN ./mvnw clean package -DskipTests

# Start Springboot project
CMD ["./mvnw", "spring-boot:run"]
