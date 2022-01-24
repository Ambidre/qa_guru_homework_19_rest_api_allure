# Задание 19. API-автотесты для [Demo Web Shop](http://demowebshop.tricentis.com/) + Allure

## Покрытый функционал
> Разработаны автотесты на <code>API</code>.
1. Тест с использованием cookies, проверяющий добавление товара в корзину
2. Кейс с сочетанием API и UI: логинимся через API, получаем cookie. Устанавливаем cookie в браузер. Переходим в личный кабинет и проверяем данные пользователя.

## Технологический стек

<p align="center">
<img width="6%" title="IntelliJ IDEA" src="images/logo/Intelij_IDEA.svg">
<img width="6%" title="Java" src="images/logo/Java.svg">
<img width="6%" title="Selenide" src="images/logo/Selenide.svg">
<img width="6%" title="Allure Report" src="images/logo/Allure_Report.svg">
<img width="6%" title="Gradle" src="images/logo/Gradle.svg">
<img width="6%" title="JUnit5" src="images/logo/JUnit5.svg">
<img width="6%" title="GitHub" src="images/logo/GitHub.svg">
<img width="6%" title="Jenkins" src="images/logo/Jenkins.svg">
<img width="6%" title="Allure TestOps" src="images/logo/Allure_TestOps.svg">
<img width="6%" title="Rest Assured" src="images/logo/Rest-Assured.svg">
</p>

> В данном проекте автотесты написаны на <code>Java</code> с использованием фреймворка <code>Selenide</code> для UI-тестов и библиотеки <code>REST Assured</code> для API-тестов.
>
> <code>Allure Report</code> формирует отчет о запуске тестов.
>
> Для автоматизированной сборки проекта используется <code>Gradle</code>.
>
> В качестве библиотеки для модульного тестирования используется <code>JUnit 5</code>.
>
> <code>Jenkins</code> выполняет запуск тестов.
>
> Автотесты интегрируются с тест-менеджмент системой <code>Allure TestOps</code>.

## Запуск тестов из терминала
### Локальный запуск тестов

```
gradle clean test
```
