package org.example;

import java.util.Objects;

/*
 * Класс Product представляет продукты и наследует базовые свойства от класса Good.
 * Включает дополнительные поля, такие как описание продукта и его калории.
 */

public class Product extends Good{

    // Поля, специфичные для молочного продукта (description и calories)

    protected String description;
    protected int calories;

    // Конструктор по умолчанию

    public Product(){}

    /*
     * Конструктор для создания экземпляра DairyProduct с указанием всех его характеристик.
     * Параметры:
     * - name: Название продукта
     * - price: Цена продукта
     * - description: Описание продукта
     * - calories: Калорийность продукта
     */

    public Product(String name, int price, String description, int calories){
        super(name, price);
        this.description = description;
        this.calories = calories;
    }

    /*
     * Переопределенный метод equals для сравнения объектов Product.
     * Возвращает true, если объекты равны по значению всех полей, false в противном случае.
     */

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product that = (Product) obj;
        return price == that.price &&
                Objects.equals(name, that.name) &&
                calories == that.calories &&
                Objects.equals(description, that.description);
    }

    /*
     * Переопределенный метод hashCode для генерации хэш-кода на основе полей name и description.
     * Используется для эффективного хранения и поиска объектов в коллекциях.
     */

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    /*
     * Переопределенный метод toString для создания строкового представления объекта DairyProduct.
     * Включает информацию о базовых полях из класса Good, а также специфические поля description и calories.
     */

    @Override
    public String toString() {
        return super.toString() + ", Description: " + description + "; Calories: " + calories;
    }
}
