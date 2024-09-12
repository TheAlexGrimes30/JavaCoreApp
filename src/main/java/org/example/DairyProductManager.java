package org.example;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

/*
 * Класс DairyProductManager управляет коллекцией молочных продуктов и реализует интерфейс IGood.
 * Он предоставляет методы для добавления, удаления, вывода данных и сравнения молочных продуктов.
 */
@Component
public class DairyProductManager implements IGood {

    // Объект для получения индексов
    GetIndex getIndexObj = new GetIndex();

    // Объект для чтения пользовательского ввода
    Scanner scanner = new Scanner(System.in);

    // Команда пользователя для выполнения операции
    String command;

    //Конструктор по умолчанию
    public DairyProductManager(){}

    /*
     * Метод getDairyProductCommand предоставляет пользователю список команд для работы с молочными продуктами.
     * В зависимости от выбранной команды выполняется соответствующий метод.
     */
    public void getDairyProductCommand(List<Object> GoodData) {
        System.out.println(
                """
                Введите команду для работы с классом молочного продукта:
                1) Добавить молочный продукт
                2) Удалить молочный продукт
                3) Вывести данные из класса молочного продукта
                4) Сравнить данные в классе молочного продукта
                """
        );

        command = scanner.nextLine();
        switch (command) {
            case "1" -> addGood(GoodData); // Добавление молочного продукта
            case "2" -> DeleteGood(getIndexObj, GoodData); // Удаление молочного продукта
            case "3" -> GoodList(GoodData); // Вывод данных молочных продуктов
            case "4" -> equal(GoodData); // Сравнение двух молочных продуктов
            default -> System.out.println("Вы ввели неверную команду!"); // Обработка неверной команды
        }
    }

    /*
     * Метод addGood добавляет новый молочный продукт в коллекцию DairyProductData.
     * Запрашивает у пользователя информацию о продукте и создает новый объект DairyProduct.
     * Обрабатывает ошибки ввода и проверки диапазона значений.
     * Параметр List<Object> GoodData
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
            System.out.println("Введите тип молочного продукта");
            String type = scanner.nextLine();
            System.out.println("Введите вес молочного продукта в граммах");
            int weight = Integer.parseInt(scanner.nextLine());
            if (Limits.checkLimited(1, 30000, weight)) {
                this.addGood(GoodData);
                return;
            }
            DairyProduct product = new DairyProduct(name, price, description, calories, type, weight);
            GoodData.add(product);
        } catch (Exception e) {
            // Обработка исключений при неверном вводе данных
            System.out.println("Неверный тип данных\n" + e.getMessage());
            this.addGood(GoodData);
            return;
        }
        System.out.println("Молочный продукт успешно добавлен");
    }

    /*
     * Метод equal сравнивает два молочных продукта по индексам, введенным пользователем.
     * Проверяет, равны ли объекты по значению их полей, используя методы hashCode и equals.
     * Обрабатывает ошибки ввода индексов и выводит результат сравнения.
     * Параметр List<Object> GoodData
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
