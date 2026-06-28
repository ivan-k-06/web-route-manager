# Web Route Manager 🗺️
![Java](https://img.shields.io/badge/Java-17+-orange.svg)
![Maven](https://img.shields.io/badge/Build-Maven-blue.svg)
![HTML/CSS](https://img.shields.io/badge/HTML-5+-E34F26?logo=html5)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/-Docker-2496ED?style=flat-square&logo=Docker&logoColor=white)

**Web Route Manager** — это веб-приложение для управления коллекцией маршрутов. Пользователи могут регистрироваться в системе, просматривать общую базу маршрутов и добавлять свои собственные.

## Технологический стек
* **Язык:** Java 17
* **Бэкенд:** Jakarta Servlet API 6.0 (Tomcat 10)
* **Фронтенд:** HTML/CSS, JSP, JSTL
* **База данных:** PostgreSQL 15
* **Сборка:** Maven
* **Инфраструктура:** Docker, Docker Compose

## Основной функционал
* Регистрация и авторизация пользователей
* Сессионная аутентификация
* Просмотр коллекции маршрутов в виде интерактивной таблицы
* Добавление новых маршрутов
* Автоматическая генерация структуры базы данных при первом запуске

## Как запустить проект (Docker)

**Требования:** Установленный Docker и Docker Compose

1. Склонируйте репозиторий:
   ```bash
   git clone https://github.com/ivan-k-06/web-route-manager.git
   cd web-route-manager
   ```

2. Запустите приложение с помощью Docker Compose:
   ```bash
   docker-compose up --build
   ```

3. Откройте в браузере:
   👉 **http://localhost:8080/auth**

*Для остановки нажмите `Ctrl+C`. Чтобы удалить базу данных и начать с нуля, выполните `docker-compose down -v`.*

## Структура проекта
Проект реализован по классическому паттерну **MVC (Model-View-Controller)**:

* `src/main/java/.../model/` — **(M)odel**: Сущности бизнес-логики
* `src/main/java/.../db/` — **(M)odel**: Слой доступа к данным
* `src/main/webapp/WEB-INF/views/` — **(V)iew**: JSP-шаблоны страниц
* `src/main/java/.../web/servlets/` — **(C)ontroller**: Сервлеты, маршрутизирующие запросы браузера