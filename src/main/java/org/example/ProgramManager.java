package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Класс ProgramManager управляет выбором и взаимодействием с различными менеджерами продуктов.
 * Он позволяет пользователю выбрать тип товара для работы и контролирует активность программы.
 */
@Component
public class ProgramManager {

    // Менеджеры для различных типов товаров
    private final ProductManager productManager;
    private final DairyProductManager dairyProductManager;
    private final ToyManager toyManager;
    List<Object> GoodData = new ArrayList<>();
    GetIndex getIndexObj = new GetIndex();
    /*
     * Конструктор класса ProgramManager инициализирует менеджеры продуктов.
     * Аннотация @Autowired позволяет Spring автоматически внедрять зависимости.
     */
    @Autowired
    public ProgramManager(ProductManager productManager, DairyProductManager dairyProductManager, ToyManager toyManager) {
        this.productManager = productManager;
        this.dairyProductManager = dairyProductManager;
        this.toyManager = toyManager;
    }

    // Флаг для отслеживания активности программы
    private boolean programIsActive = true;

    // Объект для чтения пользовательского ввода
    private final Scanner scanner = new Scanner(System.in);

    /*
     * Метод isProgramActive возвращает текущее состояние активности программы.
     * @return true, если программа активна, иначе false
     */
    public boolean isProgramActive() {
        return programIsActive;
    }

    /*
     * Метод getGoods отображает меню выбора товара и обрабатывает команду пользователя.
     * В зависимости от выбранной команды вызываются соответствующие методы для работы с товаром.
     * Если пользователь выбирает завершение работы программы, устанавливается флаг активности в false.
     */
    public void getGoods() {
        System.out.println(
                """
                Выберете товар с которым вы будете работать:
                1) Продукт
                2) Молочный продукт
                3) Игрушка
                4) Сравнить индексы элементов списка
                5) Удалить элемент из списка по индексу
                6) Вывести данные списка
                7) Завершение работы программы
                """
        );
        String command = scanner.nextLine();
        switch (command) {
            case "1" -> productManager.getProductCommand(GoodData); // Управление продуктами
            case "2" -> dairyProductManager.getDairyProductCommand(GoodData); // Управление молочными продуктами
            case "3" -> toyManager.getToyCommand(GoodData); // Управление игрушками
            case "4" -> productManager.equal(GoodData);
            case "5" -> productManager.DeleteGood(getIndexObj, GoodData);
            case "6" -> productManager.GoodList(GoodData);
            case "7" -> {
                System.out.println("Работа программы завершена"); // Завершение работы программы
                programIsActive = false; // Установка флага активности в false
            }
            default -> System.out.println("Вы ввели неверную команду!"); // Обработка неверной команды
        }
    }


}
