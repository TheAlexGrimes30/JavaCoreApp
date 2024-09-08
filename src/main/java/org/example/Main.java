package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
Класс Main для запуска приложения
 * Этот класс содержит метод main, который служит точкой входа в приложение.
 * Он создает контекст Spring с использованием класса конфигурации AppConfig
 * и запускает основной цикл программы, используя bean ProgramManager.
*/

public class Main {
    public static void main(String[] args) {

        /*
            Метод main для запуска программы

         * Создает контекст Spring с использованием AnnotationConfigApplicationContext и
         * загружает конфигурацию из класса AppConfig.
         * Получает bean ProgramManager из контекста и запускает цикл выполнения программы,
         * проверяя активность программы через isProgramActive() и вызывая метод getGoods().
         * После завершения работы программы контекст закрывается.
         */

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProgramManager programManager = context.getBean(ProgramManager.class);

        while (programManager.isProgramActive()) {
            programManager.getGoods();
        }

        context.close();
    }
}