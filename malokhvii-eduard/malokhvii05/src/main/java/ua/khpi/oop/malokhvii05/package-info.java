/**
 * Пакет, містить у собі розв'язок до лабораторної роботи
 * <a href="https://oop-khpi.github.io/#task_05">Розробка власних контейнерів.
 * Ітератори</a>
 * <p>
 * Мета:
 * <ul>
 * <li>Набуття навичок розробки власних контейнерів.</li>
 * <li>Використання ітераторів.</li>
 * </ul>
 * <p>
 * Вимоги:
 * <ol>
 * <li>Розробити клас-контейнер, що ітерується для збереження початкових даних
 * завдання л.р. №3 у вигляді масиву рядків з можливістю додавання, видалення і
 * зміни елементів.</li>
 * <li>В контейнері реалізувати та продемонструвати наступні методи:
 * <ul>
 * <li>{@code String toString()} повертає вміст контейнера у вигляді рядка;</li>
 * <li>{@code void add(String string)} додає вказаний елемент до кінця
 * контейнеру;</li>
 * <li>{@code void clear()} видаляє всі елементи з контейнеру;</li>
 * <li>{@code boolean remove(String string)} видаляє перший випадок вказаного
 * елемента з контейнера;</li>
 * <li>{@code Object[] toArray()} повертає масив, що містить всі елементи у
 * контейнері;</li>
 * <li>{@code int size()} повертає кількість елементів у контейнері;</li>
 * <li>{@code boolean contains(String string)} повертає true, якщо контейнер
 * містить вказаний елемент;</li>
 * <li>{@code boolean containsAll(Container container)} повертає true, якщо
 * контейнер містить всі елементи з зазначеного у параметрах;</li>
 * <li>{@code public Iterator<String> iterator()} повертає ітератор відповідно
 * до Interface Iterable.</li>
 * </ul>
 * </li>
 * <li>В класі ітератора відповідно до Interface Iterator реалізувати методи:
 * <ul>
 * <li>{@code public boolean hasNext()};</li>
 * <li>{@code public String next()};</li>
 * <li>{@code public void remove()}.</li>
 * </ul>
 * </li>
 * <li>Продемонструвати роботу ітератора за допомогою циклів while и for
 * each.</li>
 * <li>Забороняється використання контейнерів (колекцій) і алгоритмів з Java
 * Collections Framework.</li>
 * </ol>
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 */
package ua.khpi.oop.malokhvii05;
