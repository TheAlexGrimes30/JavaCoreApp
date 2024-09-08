package org.example;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Класс ToyManager управляет коллекцией игрушек и реализует интерфейс IGood.
 * Он предоставляет методы для добавления, удаления, вывода данных и сравнения игрушек.
 */
@Component
public class ToyManager implements IGood<Toy> {

    // Объект для получения индексов
    GetIndex getIndexObj = new GetIndex();

    // Коллекция для хранения игрушек
    List<Toy> ToyData = new ArrayList<>();

    // Объект для чтения пользовательского ввода
    Scanner scanner = new Scanner(System.in);

    // Команда пользователя для выполнения операции
    String command;

    /*
     * Метод getToyCommand предоставляет пользователю список команд для работы с игрушками.
     * В зависимости от выбранной команды выполняется соответствующий метод.
     */
    public void getToyCommand() {
        System.out.println(
                """
                Введите команду для работы с классом игрушки:
                1) Добавить игрушку
                2) Удалить игрушку
                3) Вывести данные из класса игрушки
                4) Сравнить данные в классе игрушки
                """
        );

        command = scanner.nextLine();
        switch (command) {
            case "1" -> addGood(); // Добавление игрушки
            case "2" -> deleteGood(getIndexObj, ToyData); // Удаление игрушки
            case "3" -> GoodsList(ToyData); // Вывод данных игрушек
            case "4" -> equal(); // Сравнение двух игрушек
            default -> System.out.println("Вы ввели неверную команду!"); // Обработка неверной команды
        }
    }

    /*
     * Метод addGood добавляет новую игрушку в коллекцию ToyData.
     * Запрашивает у пользователя информацию об игрушке и создает новый объект Toy.
     * Обрабатывает ошибки ввода и проверки диапазона значений.
     */
    @Override
    public void addGood() {
        // Обработка ошибок неверного типа данных
        try {
            System.out.println("Введите название продукта: ");
            String name = scanner.nextLine();
            System.out.println("Введите цену продукта от 1 до миллиона: ");
            int price = Integer.parseInt(scanner.nextLine());

            // Проверка переменной на диапазон
            if (Limits.checkLimited(1, 1000000, price)) {
                this.addGood();
                return;
            }
            System.out.println("Введите название категории игрушки");
            String category = scanner.nextLine();
            System.out.println("Введите минимальный возраст эксплуатации игрушки (от 0 до 18)");
            // Проверка переменной на диапазон
            int weight = Integer.parseInt(scanner.nextLine());
            if (Limits.checkLimited(0, 18, weight)) {
                this.addGood();
                return;
            }
            Toy toy = new Toy(name, price, category, weight);
            ToyData.add(toy);
        } catch (Exception e) {
            // Обработка исключений при неверном вводе данных
            System.out.println("Неверный тип данных\n" + e.getMessage());
            this.addGood();
            return;
        }
        System.out.println("Игрушка успешно добавлена");
    }

    /*
     * Метод equal сравнивает две игрушки по индексам, введенным пользователем.
     * Проверяет, равны ли объекты по значению их полей, используя методы hashCode и equals.
     * Обрабатывает ошибки ввода и выводит результаты сравнения.
     */
    @Override
    public void equal() {
        int index1 = getIndexObj.getIndex();
        int index2 = getIndexObj.getIndex();
        if (index1 == index2) {
            System.out.println("Вы ввели одинаковые индексы!");
            return;
        }

        // Обработка ошибок при введении индекса
        try {
            Toy toy1 = ToyData.get(index1 - 1);
            Toy toy2 = ToyData.get(index2 - 1);
            if (toy1.hashCode() == toy2.hashCode()) {
                if (toy1.equals(toy2)) {
                    System.out.println("Данные игрушек одинаковые");
                } else {
                    System.out.println("Данные игрушек не одинаковые");
                }
            } else {
                System.out.println("Данные игрушек не одинаковые");
            }
        } catch (Exception e) {
            // Обработка исключений при неверных индексах
            System.out.println("Вы ввели неверные индексы\n");
        }
    }
}
