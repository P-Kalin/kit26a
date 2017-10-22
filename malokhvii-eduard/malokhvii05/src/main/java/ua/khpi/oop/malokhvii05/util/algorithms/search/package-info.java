/**
 * Пакет, містить у собі реалізацію типових алгоритмів сортування на основі
 * порівняння. Реалізації алгоритмів на інших методах не розглядались.
 * <p>
 * Таблиця - Реалізованих алгоритмів пошуку
 * <table border="1" summary="">
 * <tr align="center">
 * <td>Назва</td>
 * <td>Найкраща швидкодія</td>
 * <td>Середня швидкодія</td>
 * <td>Найгірша швидкодія</td>
 * <td>Просторова складність</td>
 * </tr>
 * <tr align="center">
 * <td>{@link ua.khpi.oop.malokhvii05.util.algorithms.search.BinarySearch
 * BinarySearch}</td>
 * <td>Ω(1)</td>
 * <td>Θ(log(n))</td>
 * <td>O(log(n))</td>
 * <td>O(1)</td>
 * </tr>
 * <tr align="center">
 * <td>{@link ua.khpi.oop.malokhvii05.util.algorithms.search.FibonacciSearch
 * FibonacciSearch}</td>
 * <td>Ω(1)</td>
 * <td>Θ(log(n))</td>
 * <td>O(log(n))</td>
 * <td>O(1)</td>
 * </tr>
 * <tr align="center">
 * <td>{@link ua.khpi.oop.malokhvii05.util.algorithms.search.ExponentialSearch
 * ExponentialSearch}</td>
 * <td>Ω(1)</td>
 * <td>Θ(log(i))</td>
 * <td>O(log(i))</td>
 * <td>O(1)</td>
 * </tr>
 * <tr align="center">
 * <td>{@link ua.khpi.oop.malokhvii05.util.algorithms.search.GallopSearch
 * GallopSearch}</td>
 * <td>Ω(1)</td>
 * <td>?</td>
 * <td>O((log(n))^2)</td>
 * <td>O(1)</td>
 * </tr>
 * <tr align="center">
 * <td>{@link ua.khpi.oop.malokhvii05.util.algorithms.search.LinearWithBarrierSearch
 * LinearWithBarrierSearch}</td>
 * <td>Ω(1)</td>
 * <td>Θ(n)</td>
 * <td>O(n)</td>
 * <td>O(1)</td>
 * </tr>
 * <tr align="center">
 * <td>{@link ua.khpi.oop.malokhvii05.util.algorithms.search.LinearSearch
 * LinearSearch}</td>
 * <td>Ω(1)</td>
 * <td>Θ(n)</td>
 * <td>O(n)</td>
 * <td>O(1)</td>
 * </tr>
 * </table>
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 */
package ua.khpi.oop.malokhvii05.util.algorithms.search;
