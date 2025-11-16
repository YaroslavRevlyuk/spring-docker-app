# Spring Boot Docker Application

Простое Spring Boot приложение с контейнеризацией в Docker.

##  Функциональность

-  REST API с базовыми CRUD операциями
-  Работа с базой данных
-  Docker контейнеризация
-  Автоматическая сборка Maven
-  Готовая конфигурация для развертывания

##  Технологии

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **Docker**
- **Maven**

##  Быстрый старт

### Предварительные требования
- Java 17 или выше
- Maven 3.6+
- Docker (опционально)

### Запуск в development режиме
```bash
# Сборка приложения
mvn clean compile

# Запуск
mvn spring-boot:run
