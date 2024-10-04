# Prueba técnica BICE VIDA

## Clonar repositorio

```bash
git clone https://github.com/bvlebvle/prueba-tecnica-BV.git
```

## Instalar y configurar entorno de java usando sdkman

```bash
sdk install java 11.0.10.hs-amzn

sdk use java 11.0.24-amzn
```

## Instalar y configurar entorno de maven usando sdkman

```bash
sdk install maven 3.9.9

sdk use maven 3.9.9
```

## Run application

```bash
mvn --no-transfer-progress install

java -cp target/classes prueba.main.Main
```

## Run unit tests

```bash
mvn -Dtest=test.MainTest --no-transfer-progress process-test-classes surefire:test
```
