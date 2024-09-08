package org.example;

import java.util.Objects;

/*
 * Класс DairyProduct представляет молочные продукты и наследует базовые свойства от класса Product.
 * Включает дополнительные поля, такие как тип молочного продукта и его вес.
 */

public class DairyProduct extends Product{

    // Поля, специфичные для молочного продукта (type и weight)

    protected String type;
    protected int weight;

    // Конструктор по умолчанию

    public DairyProduct(){}

    /*
     * Конструктор для создания экземпляра DairyProduct с указанием всех его характеристик.
     * Параметры:
     * - name: Название продукта
     * - price: Цена продукта
     * - description: Описание продукта
     * - calories: Калорийность продукта
     * - type: Тип молочного продукта
     * - weight: Вес продукта
     */

    public DairyProduct(String name, int price,
                        String description, int calories,
                        String type, int weight){

        super(name, price, description, calories);
        this.type = type;
        this.weight = weight;
    }

    /*
     * Переопределенный метод equals для сравнения объектов DairyProduct.
     * Возвращает true, если объекты равны по значению всех полей, false в противном случае.
     */

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DairyProduct that = (DairyProduct) obj;
        return price == that.price &&
                Objects.equals(name, that.name) &&
                calories == that.calories &&
                Objects.equals(description, that.description) &&
                weight == that.weight &&
                Objects.equals(type, that.type);
    }

    /*
     * Переопределенный метод hashCode для генерации хэш-кода на основе полей name, type и description.
     * Используется для эффективного хранения и поиска объектов в коллекциях.
     */

    @Override
    public int hashCode() {
        return Objects.hash(name, type, description);
    }

    /*
     * Переопределенный метод toString для создания строкового представления объекта DairyProduct.
     * Включает информацию о базовых полях из класса Product, а также специфические поля type и weight.
     */

    @Override
    public String toString() {
        return super.toString() + ", Type: " + type + "; Weight: " + weight;
    }
}
