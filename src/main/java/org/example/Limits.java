package org.example;

/**
 * Класс Limits предоставляет методы для проверки, находится ли значение в пределах допустимого диапазона.
 */
public class Limits {

    /**
     * Конструктор класса Limits.
     */
    public Limits() {}

    /**
     * Метод для проверки, находится ли значение в пределах заданного диапазона.
     * @param minValue Минимальное значение допустимого диапазона.
     * @param maxValue Максимальное значение допустимого диапазона.
     * @param checkValue Значение, которое необходимо проверить.
     * @return true, если значение находится вне диапазона; false, если значение находится в пределах диапазона.
     */
    public static boolean checkLimited(int minValue, int maxValue, int checkValue) {
        if (minValue > checkValue || maxValue < checkValue) {
            System.out.println("Данное число не входит в допустимый диапазон от " + minValue + " до " + maxValue);
            return true; // Значение вне диапазона
        }
        return false; // Значение в пределах диапазона
    }
}