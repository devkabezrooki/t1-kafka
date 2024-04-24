# ДЗ №3 для открытой школы Java-разработчиков от T1
**Суть задания:**  Реализация системы мониторинга с использованием Spring Kafka.
# Описание реализации:
Проект состоит из двух модулей:

## Metrics-producer

Миксросервис для сбора и отправки метрик для отслеживания работы приложения. Каждые 2 минуты запускается метод для автоматического сбора следующих метрик:
* **disk.free** - свободное дисковое пространство
* **jvm.memory.used** - объем используемой памяти виртуальной машины Java
* **jvm.buffer.memory.used** - объем используемой памяти буферов в JVM
* **jvm.gc.memory.allocated** - объем памяти, выделенной для сборки мусора в JVM
* **jvm.classes.loaded** - количество загруженных классов в JVM
* **jvm.classes.unloaded** - количество выгруженных классов из JVM
* **tasks.scheduled.execution.active** - количество активных запланированных задач.

Все метрики отправляются в топик **metrics-topic**.

Также есть возможность отправки метрики вручную через REST API, с помощью метода:
* POST /metrics:
  - Принимает в теле запроса дто MetricMeasurementDto (обязательное):
    
    ![image](https://github.com/devkabezrooki/t1-kafka/assets/49373926/f8a075cf-1820-44f8-8309-4653507e7561)

  - В случае успешной отправки метрики возвращает статус 200.

Для взаимодействия с контроллером добавлен Swagger, доступен по адресу /swagger-ui/index.html.

## Metrics-consumer

Миксросервис для получения метрик из топика **metrics-topic** и сохранения их в бд Postgresql в сущность MetricMeasurement:

![image](https://github.com/devkabezrooki/t1-kafka/assets/49373926/37a5f0ea-4589-4f8d-a890-7c62bd88eff0)

Для просмотра метрик реализовано REST API со следующими методами:
* GET /metrics - Получение списка всех метрик:
  - Вовзращает список всех метрик, сохраненных в бд, в виде дто MetricResponseDto.
* GET /metrics/{id} - Получение информации о конкретной метрике:
  - Принимает обязательное поле id - id задачи.
  - В случае успешного выполнения возвращет дто MetricResponseDto с информацией о задаче.
 
Схема дто MetricResponseDto:

![image](https://github.com/devkabezrooki/t1-kafka/assets/49373926/f74d7f38-665b-4600-b9c4-f3853cf4d4fd)

# Использованные технологии:
* Java 17
* Spring Boot
* Spring Kafka
* Spring Boot Actuator
* Spring Data Jpa
* Postgresql
* Liquibase
* Swagger
* Docker
