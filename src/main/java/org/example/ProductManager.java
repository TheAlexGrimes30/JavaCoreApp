package org.example;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

/*
 * Класс ProductManager управляет коллекцией продуктов и реализует интерфейс IGood.
 * Он предоставляет методы для добавления, удаления, вывода данных и сравнения продуктов.
 */
@Component
public class ProductManager implements IGood {

    // Объект для получения индексов
    GetIndex getIndexObj = new GetIndex();

    // Объект для чтения пользовательского ввода
    Scanner scanner = new Scanner(System.in);

    // Команда пользователя для выполнения операции
    String command;

    // Конструктор по умолчанию
    public ProductManager(){}

    /*
     * Метод getProductCommand предоставляет пользователю список команд для работы с продуктами.
     * В зависимости от выбранной команды выполняется соответствующий метод.
     */
    public void getProductCommand(List<Object> GoodData) {
        System.out.println(
                """
                Введите команду для работы с классом продукта:
                1) Добавить продукт
                2) Удалить продукт
                3) Вывести данные из класса продукт
                4) Сравнить данные в классе продукта
                """
        );

        command = scanner.nextLine();
        switch (command) {
            case "1" -> addGood(GoodData); // Добавление продукта
            case "2" -> DeleteGood(getIndexObj, GoodData); // Удаление продукта
            case "3" -> GoodList(GoodData); // Вывод данных продуктов
            case "4" -> equal(GoodData); // Сравнение двух продуктов
            default -> System.out.println("Вы ввели неверную команду!"); // Обработка неверной команды
        }
    }

    /*
     * Метод addGood добавляет новый продукт в коллекцию ProductData.
     * Запрашивает у пользователя информацию о продукте и создает новый объект Product.
     * Обрабатывает ошибки ввода и проверки диапазона значений.
     */
    @Override
    public void addGood(List<Object> GoodData) {
        // Обработка ошибок неверного типа данных
        try {
            System.out.println("Введите название продукта: ");
            String name = scanner.nextLine();
            System.out.println("Введите цену продукта от 1 до миллиона: ");
            int price = Integer.parseInt(scanner.nextLine());

            // Проверка переменной на диапазон
            if (Limits.checkLimited(1, 1000000, price)) {
                this.addGood(GoodData);
                return;
            }
            System.out.println("Введите описание продукта");
            String description = scanner.nextLine();
            System.out.println("Введите калорийность продукта (от 1 до 5000 КК)");
            // Проверка переменной на диапазон
            int calories = Integer.parseInt(scanner.nextLine());
            if (Limits.checkLimited(1, 5000, calories)) {
                this.addGood(GoodData);
                return;
            }
            Product product = new Product(name, price, description, calories);
            GoodData.add(product);
        } catch (Exception e) {
            // Обработка исключений при неверном вводе данных
            System.out.println("Неверный тип данных\n" + e.getMessage());
            this.addGood(GoodData);
            return;
        }
        System.out.println("Продукт успешно добавлен");
    }

    /*
     * Метод equal сравнивает два продукта по индексам, введенным пользователем.
     * Проверяет, равны ли объекты по значению их полей, используя методы hashCode и equals.
     * Обрабатывает ошибки ввода и выводит результаты сравнения.
     */
    @Override
    public void equal(List<Object> GoodData) {
        int index1 = getIndexObj.getIndex();
        int index2 = getIndexObj.getIndex();
        if (index1 == index2) {
            System.out.println("Вы ввели одинаковые индексы!");
            return;
        }

        // Обработка ошибок при введении индекса
        try {
            Good good1 = (Good) GoodData.get(index1 - 1);
            Good good2 = (Good) GoodData.get(index2 - 1);
            if (good1.hashCode() == good2.hashCode()) {
                if (good1.equals(good2)) {
                    System.out.println("Данные продуктов одинаковые");
                } else {
                    System.out.println("Данные продуктов не одинаковые");
                }
            } else {
                System.out.println("Данные продуктов не одинаковые");
            }
        } catch (Exception e) {
            // Обработка исключений при неверных индексах
            System.out.println("Вы ввели неверные индексы\n");
        }
    }

}
