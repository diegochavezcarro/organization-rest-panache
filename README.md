# organization-rest-panache project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `organization-rest-panache-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/organization-rest-panache-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/organization-rest-panache-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.

# RESTEasy JAX-RS

Guide: https://quarkus.io/guides/rest-json

#Levantar una base:

docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 --name quarkus_test -e POSTGRES_USER=quarkus -e POSTGRES_PASSWORD=quarkus -e POSTGRES_DB=quarkusdb -p 5432:5432 postgres:10.5


#Ejecutar con live coding y debugging habilitado:

./mvnw compile quarkus:dev

Probar en browser:

http://localhost:8080/resteasy/hello/Pepe

Corroborar esté el puerto de debug usándose:

netstat -an | grep 5005

Importar proyecto en IntelliJ u otro IDE y atacharse
al Debug, por ej en IntelliJ con:

Run -> Attach to Process

#Probar en Containers:

##JVM:

docker build -f src/main/docker/Dockerfile.jvm -t quarkus-quickstart/getting-started:jvm .

docker run -i --rm -p 8080:8080 quarkus-quickstart/getting-started:jvm

##Nativo:
(https://quarkus.io/guides/building-native-image#using-a-multi-stage-docker-build )

Nativo con Multi Stage Builds (ver .dockerignore):

#darle mas memoria a Docker (yo le di 8GB)
docker build -f src/main/docker/Dockerfile.multistage -t diegochavezcarro/organization-rest-panache:1.0.0-SNAPSHOT .

#Obtener la IP del host para postgres con ipconfig
docker run -i --rm --env POSTGRESQL_SERVICE_HOST=192.168.0.236 -p 8080:8080 diegochavezcarro/organization-rest-panache:1.0.0-SNAPSHOT

#Subir a Docker Host

docker login

docker push diegochavezcarro/organization-rest-panache:1.0.0-SNAPSHOT

#obtener el kubernetes.yml:

./mvnw package

Copiarlo desde target/kubernetes/ en la raiz del proyecto, modificar nombre de la imagen (organizacion),
también agregar variable de entorno con IP del host para postgres
Si se quiere probar de afuera usar NodePort en el Service
Agregar limits y request
Tambien un HPA

#Subir a Kubernetes:

kubectl create -f kubernetes.yml 

Probar en Postman o swagger-ui. Usar "minikube ip" para ver el host
y el puerto del NodePort

 