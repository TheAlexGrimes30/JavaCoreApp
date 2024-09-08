package org.example;

import java.util.Objects;

/*
 * Класс Toy представляет игрушки и наследует базовые свойства от класса Good.
 * Включает дополнительные поля, такие как категория игрушки и минимальный возраст её эксплуатации.
 */

public class Toy extends Good{

    // Поля, специфичные для игрушки (category и minAge)

    protected String category;
    protected int minAge;

    // Конструктор по умолчанию

    public Toy(){}

    /*
     * Конструктор для создания экземпляра Toy с указанием всех его характеристик.
     * Параметры:
     * - name: Название продукта
     * - price: Цена продукта
     * - category: Категория игрушки
     * - minAge: Минимальный возраст эксплуатации игрушки
     */

    public Toy(String name, int price, String category, int minAge){
        super(name, price);
        this.category = category;
        this.minAge = minAge;
    }

    /*
     * Переопределенный метод equals для сравнения объектов Toy.
     * Возвращает true, если объекты равны по значению всех полей, false в противном случае.
     */

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Toy that = (Toy) obj;
        return price == that.price &&
                Objects.equals(name, that.name) &&
                minAge == that.minAge &&
                Objects.equals(category, that.category);
    }

    /*
     * Переопределенный метод hashCode для генерации хэш-кода на основе полей name и category.
     * Используется для эффективного хранения и поиска объектов в коллекциях.
     */

    @Override
    public int hashCode() {
        return Objects.hash(name, category);
    }

    /*
     * Переопределенный метод toString для создания строкового представления объекта Toy.
     * Включает информацию о базовых полях из класса Good, а также специфические поля category и minAge.
     */

    @Override
    public String toString() {
        return super.toString() + ", Category: " + category+ "; Minimal Age: " + minAge;
    }
}
