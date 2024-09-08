package org.example;

import java.util.Objects;

/*
 * Класс Good представляет товары.
 * Включает поля, такие как название и цена.
 */

public class Good {

    // Поля, специфичные для игрушки (name и price)

    protected String name;
    protected int price;

    // Конструктор по умолчанию

    public Good(){}

    /*
     * Конструктор для создания экземпляра Good с указанием всех его характеристик.
     * Параметры:
     * - name: Название продукта
     * - price: Цена продукта
     */

    public Good(String name, int price){
        this.name = name;
        this.price = price;
    }

    /*
     * Переопределенный метод equals для сравнения объектов Good.
     * Возвращает true, если объекты равны по значению всех полей, false в противном случае.
     */

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Good that = (Good) obj;
        return price == that.price &&
                Objects.equals(name, that.name);
    }

    /*
     * Переопределенный метод hashCode для генерации хэш-кода на основе поля name.
     * Используется для эффективного хранения и поиска объектов в коллекциях.
     */

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /*
     * Переопределенный метод toString для создания строкового представления объекта Good.
     * Включает информацию о базовых полях из класса Good, а также специфические поля name и price.
     */

    @Override
    public String toString() {
        return "Name: " + name + "; Price: " + price;
    }
}
