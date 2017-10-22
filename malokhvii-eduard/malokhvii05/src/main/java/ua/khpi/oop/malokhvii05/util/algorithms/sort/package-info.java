/**
 * Пакет, містить у собі реалізацію типових алгоритмів сортування на основі
 * порівняння. Реалізації алгоритмів на інших методах не розглядались.
 * <p>
 * Таблиця - Реалізованих алгоритмів сортування
 * <table border="1" summary="">
 * <tr align="center">
 * <td>Назва</td>
 * <td>Метод</td>
 * <td>Найкраща швидкодія</td>
 * <td>Середня швидкодія</td>
 * <td>Найгірша швидкодія</td>
 * <td>Просторова складність</td>
 * <td>Стабільний</td>
 * </tr>
 * <tr align="center">
 * <td>{@link ua.khpi.oop.malokhvii05.util.algorithms.sort.SimpleQuickSort
 * SimpleQuickSort}</td>
 * <td>Partitioning</td>
 * <td>Ω(n log(n))</td>
 * <td>Θ(n log(n))</td>
 * <td>O(n^2)</td>
 * <td>O(log(n))</td>
 * <td>Ні</td>
 * </tr>
 * <tr align="center">
 * <td>{@link ua.khpi.oop.malokhvii05.util.algorithms.sort.BottomUpMergeSort
 * BottomUpMergeSort}</td>
 * <td>Merging</td>
 * <td>Ω(n log(n))</td>
 * <td>Θ(n log(n))</td>
 * <td>O(n log(n))</td>
 * <td>O(n)</td>
 * <td>Так</td>
 * </tr>
 * <tr align="center">
 * <td>{@link ua.khpi.oop.malokhvii05.util.algorithms.sort.TopDownMergeSort
 * TopDownMergeSort}</td>
 * <td>Merging</td>
 * <td>Ω(n log(n))</td>
 * <td>Θ(n log(n))</td>
 * <td>O(n log(n))</td>
 * <td>O(n)</td>
 * <td>Так</td>
 * </tr>
 * <tr align="center">
 * <td>{@link ua.khpi.oop.malokhvii05.util.algorithms.sort.TimSort TimSort}</td>
 * <td>Insertion + Merging</td>
 * <td>Ω(n)</td>
 * <td>Θ(n log(n))</td>
 * <td>O(n log(n))</td>
 * <td>O(n)</td>
 * <td>Так</td>
 * </tr>
 * <tr align="center">
 * <td>{@link ua.khpi.oop.malokhvii05.util.algorithms.sort.HeapSort
 * HeapSort}</td>
 * <td>Selection</td>
 * <td>Ω(n log(n))</td>
 * <td>Θ(n log(n))</td>
 * <td>O(n log(n))</td>
 * <td>O(1)</td>
 * <td>Ні</td>
 * </tr>
 * <tr align="center">
 * <td>{@link ua.khpi.oop.malokhvii05.util.algorithms.sort.JSort JSort}</td>
 * <td>Selection + Insertion</td>
 * <td>Ω(n)</td>
 * <td>?</td>
 * <td>O(n^2)</td>
 * <td>O(n)</td>
 * <td>Ні</td>
 * </tr>
 * <tr align="center">
 * <td>{@link ua.khpi.oop.malokhvii05.util.algorithms.sort.BubbleSort
 * BubbleSort}</td>
 * <td>Exchanging</td>
 * <td>Ω(n)</td>
 * <td>Θ(n^2)</td>
 * <td>O(n^2)</td>
 * <td>O(1)</td>
 * <td>Так</td>
 * </tr>
 * <tr align="center">
 * <td>{@link ua.khpi.oop.malokhvii05.util.algorithms.sort.InsertionSort
 * InsertionSort}</td>
 * <td>Insertion</td>
 * <td>Ω(n)</td>
 * <td>Θ(n^2)</td>
 * <td>O(n^2)</td>
 * <td>O(1)</td>
 * <td>Так</td>
 * </tr>
 * <tr align="center">
 * <td>{@link ua.khpi.oop.malokhvii05.util.algorithms.sort.SelectionSort
 * SelectionSort}</td>
 * <td>Selection</td>
 * <td>Ω(n^2)</td>
 * <td>Θ(n^2)</td>
 * <td>O(n^2)</td>
 * <td>O(1)</td>
 * <td>Ні</td>
 * </tr>
 * <tr align="center">
 * <td>{@link ua.khpi.oop.malokhvii05.util.algorithms.sort.ShellSort
 * ShellSort}</td>
 * <td>Insertion</td>
 * <td>Ω(n log(n))</td>
 * <td>Θ(n(log(n))^2)</td>
 * <td>O(n(log(n))^2)</td>
 * <td>O(1)</td>
 * <td>Ні</td>
 * </tr>
 * <tr align="center">
 * <td>{@link ua.khpi.oop.malokhvii05.util.algorithms.sort.GnomeSort
 * GnomeSort}</td>
 * <td>Exchanging</td>
 * <td>Ω(n)</td>
 * <td>Θ(n^2)</td>
 * <td>O(n^2)</td>
 * <td>O(1)</td>
 * <td>Так</td>
 * </tr>
 * </table>
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 */
package ua.khpi.oop.malokhvii05.util.algorithms.sort;
