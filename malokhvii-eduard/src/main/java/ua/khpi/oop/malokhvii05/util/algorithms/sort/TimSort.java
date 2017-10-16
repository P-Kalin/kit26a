package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

/**
 * <p>
 * Призначений, для реалізації алгоритму сортування вхідного масиву. Ключ у
 * фабриці алгоритмів - "tim-sort".
 * </p>
 * <p>
 * Timsort - гібридний алгоритм сортування, що поєднує сортування вставками і
 * сортування злиттям, опублікований в 2002 році Тімом Петерсом. В даний час
 * Timsort є стандартним алгоритмом сортування в Python, OpenJDK 7 і
 * реалізований в Android JDK 1.5. Основна ідея алгоритму в тому, що в реальному
 * світі сортовані масиви даних часто містять в собі впорядковані подмассіви. На
 * таких даних Timsort істотно швидше багатьох алгоритмів сортування.
 * </p>
 * <p>
 * Натхнення для дослідження алгоритму взято із
 * <a href="https://bugs.python.org/file4451/timsort.txt">опису Тіма
 * Петерсона</a>. За основу реалізації було взято реалізації з OpenJDK 7 та
 * реалізацію на Node.js. <a href=
 * "https://github.com/python/cpython/blob/master/Objects/listobject.c">Див.
 * реалізацію з мови Python</a>
 * </p>
 * <ul>
 * <li>Назва: Tim Sort</li>
 * <li>Автор: Tim Peters</li>
 * <li>Метод: Insertion + Merging</li>
 * <li>Найкраща швидкодія: Ω(n)</li>
 * <li>Середня швидкодія: Θ(n log(n))</li>
 * <li>Найгірша швидкодія: O(n log(n))</li>
 * <li>Просторова складність: O(n)</li>
 * <li>Стабільний: Так</li>
 * </ul>
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @see SortAlgorithmFactory
 * @param <T>
 *            Тип даних, елементів масиву для сортування, та компаратору для
 *            порівняння елементів
 * @since 1.0.0
 */
public final class TimSort<T> extends AbstractSortAlgorithm<T> {

    /**
     * Максимальний початковий розмір поточного масиву, для злиття прогонів.
     * Масив може збільшуватися за допомогою методу
     * {@link TimSort#ensureMergeBufferCapacity}. На відміну від реалізації Тима
     * Петерсона не виділяється значна купа пам'яті.
     *
     * @since 1.0.0
     */
    private static final int DEFAULT_MERGE_BUFFER_SIZE = 256;

    /**
     * Коли, ми переходимо в пошук за допмогою галлопу, продовжуємо доки обидва
     * прогони не накоплять кількість коректних проходів меньше ніж {@value} раз
     * подряд. Константа повинна бути ступінню двійки, або близким до нього.
     * Детальніше див.
     * <a href="https://bugs.python.org/file4451/timsort.txt">рекомендації Тіма
     * Петерсона</a>.
     *
     * @since 1.0.0
     */
    private static final int DEFAULT_MIN_GALLOP = 7;

    /**
     * Мінімальний розмір послідовності для злиття, коротші за цей розмір будуть
     * продовжені за допомогою {@link TimSort#binaryInsertionSort}. Якщо весь
     * вхідний масив меньше ніж цей розмір, тоді буде викоритсано так званий
     * "min-tim-sort". Тобто відсортовано масив вставкою.
     *
     * @since 1.0.0
     */
    private static final int DEFAULT_MIN_MERGE = 32;

    static {
        SortAlgorithmFactory.registerAlgorithm("tim-sort", TimSort.class);
        SortAlgorithmFactory.setDefaultAlgorithm(TimSort.class);
    }

    /**
     * Призначений, для сорутвання вхідного масиву за допомогою сортування
     * двійковою вставкою. Це найкращий спосіб сортування малих кількостей
     * елементів. Для цього необхідно O(n log n), найгірша швидкодія O(n ^ 2).
     *
     * @param <T>
     *            Тип даних, елементів масиву для сортування, та компаратору для
     *            порівняння елементів
     * @param array
     *            вхідний масив для сорутвання
     * @param low
     *            індекс першого елемента в діапазоні для сортування
     * @param high
     *            індекс останнього елемента в діапазоні для сортування
     * @param start
     *            індекс першого елемента в діапазоні, який не відомо чи
     *            потрібно сортувати
     * @param comparator
     *            компаратор, для порівняння елементів
     * @since 1.0.0
     */
    private static <T> void binaryInsertionSort(final T[] array, final int low,
            final int high, int start, final Comparator<? super T> comparator) {
        if (start == low) {
            start++;
        }

        T currentValue;
        int middle;
        int left;
        int right;
        int numberOfElementsToMove;

        for (; start < high; start++) {
            currentValue = array[start];

            left = low;
            right = start;
            while (left < right) {
                middle = (left + right) >>> 1;
                if (comparator.compare(currentValue, array[middle]) < 0) {
                    right = middle;
                } else {
                    left = middle + 1;
                }
            }

            numberOfElementsToMove = start - left;
            switch (numberOfElementsToMove) {
            case 2:
                array[left + 2] = array[left + 1];
            case 1:
                array[left + 1] = array[left];
                break;
            default:
                System.arraycopy(array, left, array, left + 1,
                        numberOfElementsToMove);
            }
            array[left] = currentValue;
        }
    }

    /**
     * Призначений, для отримання довжини прогону, з найменшого ідндексу в
     * масиві.
     *
     * @param <T>
     *            Тип даних, елементів масиву для сортування, та компаратору для
     *            порівняння елементів
     * @param array
     *            вхідний масив для підрахунку пробігу, та можливої зміни
     *            порядку
     * @param low
     *            індекс першого елементу в пробігу
     * @param high
     *            індекс елементу після елементу який може бути розміщено в
     *            прогоні
     * @param comparator
     *            компаратор, для порівняння елементів
     * @return довжина прогону, з найменшого індексу в масиві
     * @since 1.0.0
     */
    private static <T> int countRunAndMakeAscending(final T[] array,
            final int low, final int high, final Comparator<T> comparator) {
        int runHigh = low + 1;
        if (runHigh == high) {
            return 1;
        }

        if (comparator.compare(array[runHigh++], array[low]) < 0) {
            while (runHigh < high && comparator.compare(array[runHigh],
                    array[runHigh - 1]) < 0) {
                {
                    runHigh++;
                }
            }
            reverseSlice(array, low, runHigh);
        } else {
            while (runHigh < high && comparator.compare(array[runHigh],
                    array[runHigh - 1]) >= 0) {
                {
                    runHigh++;
                }
            }
        }

        return runHigh - low;
    }

    /**
     * Призначений, для отримання мінімальної допустимої довжини пробігу для
     * вхідного масиву. Пробіги, довжина котрих менше будуть розширин за
     * допомогою {@link TimSort#binaryInsertionSort}. Основні критерії
     * мінімаьнох довжини прогону.
     * <ul>
     * <li>Не повинна бути досить великою, оскільки під час подальших обчислень
     * до кожного прогону буд використано {@link TimSort#binaryInsertionSort
     * сортування вставкою}, вона досить ефективна на не значних кількостях
     * елементів.</li>
     * <li>Не повинна бути досить маленькою, оскільки чим меньша різниця
     * прогонів - ти більша та довша ітерація злиття прогонів< у кінці алгоритму
     * сортування.</li>
     * <li>Необхідно, щоб довжина масива / мінімальну довжину прогону була
     * ступінню двійки або близька до неї. Цей критерій зумовлюється тим, що
     * алгоритм злиття працює досить ефективно на прогонах рівного розміру.</li>
     * </ul>
     *
     * @param arraySize
     *            довжина вхідного масиву
     * @return довжина мінімального пробігу, який необхідно відсортувати
     * @since 1.0.0
     */
    private static int getMinRunSize(int arraySize) {
        int shiftedBits = 0;
        while (arraySize >= DEFAULT_MIN_MERGE) {
            shiftedBits |= (arraySize & 1);
            arraySize >>= 1;
        }

        return arraySize + shiftedBits;
    }

    /**
     * Призначений, для визначення положення, в яке буде вставлено вказане
     * значення, в вказаний відсортований діапазон. Якщо діапазон містить
     * необхідний елемент, рівний взідному значенню, буде повернено індекс
     * найлівішого рівного елементу.
     *
     * @param <T>
     *            Тип даних, елементів масиву для сортування, та компаратору для
     *            порівняння елементів
     * @param array
     *            вхідний масив, для пошуку
     * @param value
     *            значення для пошуку
     * @param base
     *            індекс першого елементу в діапазоні
     * @param length
     *            довжина діапазону для пошуку
     * @param hint
     *            допоміжній індекс, з якого необхідно починати пошук
     * @param comparator
     *            компаратор, для порівняння елементів
     * @return індекс найлівішого рівного елементу
     * @since 1.0.0
     */
    private static <T> int leftGallopSearch(final T[] array, final T value,
            final int base, final int length, final int hint,
            final Comparator<? super T> comparator) {
        int lastOffset = 0;
        int offset = 1;
        int maxOffset;

        if (comparator.compare(value, array[base + hint]) > 0) {
            maxOffset = length - hint;
            while (offset < maxOffset && comparator.compare(value,
                    array[base + hint + offset]) > 0) {
                lastOffset = offset;
                offset = (offset << 1) + 1;

                if (offset <= 0) {
                    offset = maxOffset;
                }
            }

            if (offset > maxOffset) {
                offset = maxOffset;
            }

            lastOffset += hint;
            offset += hint;
        } else {
            maxOffset = hint + 1;
            while (offset < maxOffset && comparator.compare(value,
                    array[base + hint - offset]) <= 0) {
                lastOffset = offset;
                offset = (offset << 1) + 1;

                if (offset <= 0) {
                    offset = maxOffset;
                }
            }

            if (offset > maxOffset) {
                offset = maxOffset;
            }

            int previousOffset = lastOffset;
            lastOffset = hint - offset;
            offset = hint - previousOffset;
        }

        lastOffset++;
        int middle;
        while (lastOffset < offset) {
            middle = lastOffset + ((offset - lastOffset) >>> 1);

            if (comparator.compare(value, array[base + middle]) > 0) {
                lastOffset = middle + 1;
            } else {
                offset = middle;
            }
        }

        return offset;
    }

    /**
     * Призначений, для обернення діпазона в отриманому масиві.
     *
     * @param array
     *            масив, в якому необхідно обернути заданий діпазон
     * @param low
     *            індекс першого елемента в діапазоні, для обернення
     * @param high
     *            індекс елементу після елементу який необхідно обернути
     * @since 1.0.0
     */
    private static void reverseSlice(final Object[] array, int low, int high) {
        high--;
        Object temp;
        while (low < high) {
            temp = array[low];
            array[low++] = array[high];
            array[high--] = temp;
        }
    }

    /**
     * Подібний методу {@link TimSort#leftGallopSearch} крім того, якщо діапазон
     * містить елемент рівний вхідному значенню, тоді буде повернено індекс
     * після найправішого елементу.
     *
     * @param <T>
     *            Тип даних, елементів масиву для сортування, та компаратору для
     *            порівняння елементів
     * @param array
     *            вхідний масив, для пошуку
     * @param value
     *            значення для пошуку
     * @param base
     *            індекс першого елементу в діапазоні
     * @param length
     *            довжина діапазону для пошуку
     * @param hint
     *            допоміжній індекс, з якого необхідно починати пошук
     * @param comparator
     *            компаратор, для порівняння елементів
     * @return індекс після найправішого елемент
     * @since 1.0.0
     */
    private static <T> int rightGallopSearch(final T[] array, final T value,
            final int base, final int length, final int hint,
            final Comparator<? super T> comparator) {
        int offset = 1;
        int lastOffset = 0;
        int maxOffset;

        if (comparator.compare(value, array[base + hint]) < 0) {
            maxOffset = hint + 1;
            while (offset < maxOffset && comparator.compare(value,
                    array[base + hint - offset]) < 0) {
                lastOffset = offset;
                offset = (offset << 1) + 1;

                if (offset <= 0) {
                    offset = maxOffset;
                }
            }

            if (offset > maxOffset) {
                offset = maxOffset;
            }

            int previousOffset = lastOffset;
            lastOffset = hint - offset;
            offset = hint - previousOffset;
        } else {
            maxOffset = length - hint;
            while (offset < maxOffset && comparator.compare(value,
                    array[base + hint + offset]) >= 0) {
                lastOffset = offset;
                offset = (offset << 1) + 1;

                if (offset <= 0) {
                    offset = maxOffset;
                }
            }

            if (offset > maxOffset) {
                offset = maxOffset;
            }

            lastOffset += hint;
            offset += hint;
        }

        lastOffset++;
        int middle;
        while (lastOffset < offset) {
            middle = lastOffset + ((offset - lastOffset) >>> 1);

            if (comparator.compare(value, array[base + middle]) < 0) {
                offset = middle;
            } else {
                lastOffset = middle + 1;
            }
        }

        return offset;
    }

    /**
     * Очікуваний масив для сортування.
     *
     * @since 1.0.0
     */
    private T[] array;

    /**
     * Буфер для злиття прогонів.
     *
     * @since 1.0.0
     */
    private T[] mergeBuffer;

    /**
     * Змінна, контролює момент входження до пошуку галопом. Методи
     * {@link TimSort#mergeHighRuns} та {@link TimSort#mergeLowRuns} збільшують
     * значення змінної для випадкових (хаотично впорядкованих) даних, і навпаки
     * для достатньо упорядкованих даних.
     *
     * @since 1.0.0
     */
    private int minGallop = DEFAULT_MIN_GALLOP;

    /**
     * Стек, для розміщення індексів перших елементів очікуваних прогонів.
     *
     * @since 1.0.0
     */
    private int[] runBaseStack;

    /**
     * Стек, для розміщення розмірів очікуваних прогонів.
     *
     * @since 1.0.0
     */
    private int[] runSizeStack;

    /**
     * Кількість очікуваниз прогонів, які будуть розміщені на стеці.
     *
     * @since 1.0.0
     */
    private int stackSize;

    /**
     * Призначений, для інійіалізації об'єкту компоратором для порівняння
     * вхідних даних.
     *
     * @param comparator
     *            компоратор для вхідних даних
     * @since 1.0.0
     */
    public TimSort(final Comparator<T> comparator) {
        super(comparator);
    }

    /**
     * Призначений, для забезпечення дійсного розміру буфера для злиття
     * прогонів, як найменш зазначеному розміру за-змовчуванням. При
     * необхідності збільшує розмір буфера. Розмір буфера збільшується
     * експоненціально для забезпечення амортизованої лінійної складності часу
     *
     * @param minCapacity
     *            мінімальна необхідна ємність буфера для злиття прогонів
     * @since 1.0.0
     */
    private void ensureMergeBufferCapacity(final int minCapacity) {
        if (mergeBuffer.length < minCapacity) {
            int newSize = minCapacity;
            newSize |= newSize >> 1;
            newSize |= newSize >> 2;
            newSize |= newSize >> 4;
            newSize |= newSize >> 8;
            newSize |= newSize >> 16;
            newSize++;

            if (newSize < 0) {
                newSize = minCapacity;
            } else {
                newSize = Math.min(newSize, array.length >>> 1);
            }

            @SuppressWarnings("unchecked")
            T[] mergeBufferAllocation = (T[]) new Object[newSize];
            mergeBuffer = mergeBufferAllocation;
        }
    }

    /**
     * Призначений, для досідження стеку прогонів, очікуючого на злиття та
     * об'єднання очікуваних прогонів, до того моменту поки не буде відновлено
     * стан стеку. Метод, використовується кожен раз коли новий прогін
     * переміщується до стеку прогонів.
     *
     * @since 1.0.0
     */
    private void mergeAdjacentRuns() {
        int runIndex;
        while (stackSize > 1) {
            runIndex = stackSize - 2;
            if (runIndex > 0
                    && runSizeStack[runIndex - 1] <= runSizeStack[runIndex]
                            + runSizeStack[runIndex + 1]) {
                if (runSizeStack[runIndex - 1] < runSizeStack[runIndex + 1]) {
                    runIndex--;
                }
                mergeAtIndex(runIndex);
            } else if (runSizeStack[runIndex] <= runSizeStack[runIndex + 1]) {
                mergeAtIndex(runIndex);
            } else {
                break;
            }
        }
    }

    /**
     * Призначений, для об'єднання усіх прогонів у стеці очікуваниз прогонів,
     * доки не залишиться один. Використовується для завершення алгоритму
     * сортування.
     *
     * @since 1.0.0
     */
    private void mergeAllRuns() {
        int amountOfRuns;
        while (stackSize > 1) {
            amountOfRuns = stackSize - 2;
            if (amountOfRuns > 0 && runSizeStack[amountOfRuns
                    - 1] < runSizeStack[amountOfRuns + 1]) {
                amountOfRuns--;
            }
            mergeAtIndex(amountOfRuns);
        }
    }

    /**
     * Призначений, для об'єднання пари прогонів із стеку за індексами index та
     * index + 1. Очікувані значення index = stackSize - 2 або stackSize - 3.
     *
     * @param index
     *            індекс першого прогону із двох прогонів у стеці
     * @since 1.0.0
     */
    private void mergeAtIndex(final int index) {
        int runBase1 = runBaseStack[index];
        int runSize1 = runSizeStack[index];

        int runBase2 = runBaseStack[index + 1];
        int runSize2 = runSizeStack[index + 1];

        runSizeStack[index] = runSize1 + runSize2;
        if (index == stackSize - 3) {
            runBaseStack[index + 1] = runBaseStack[index + 2];
            runSizeStack[index + 1] = runSizeStack[index + 2];
        }
        stackSize--;

        int indexOfFirstFromRun2InRun1 = rightGallopSearch(array,
                array[runBase2], runBase1, runSize1, 0, comparator);

        runBase1 += indexOfFirstFromRun2InRun1;
        runSize1 -= indexOfFirstFromRun2InRun1;
        if (runSize1 == 0) {
            return;
        }

        runSize2 = leftGallopSearch(array, array[runBase1 + runSize1 - 1],
                runBase2, runSize2, runSize2 - 1, comparator);

        if (runSize2 == 0) {
            return;
        }

        if (runSize1 <= runSize2) {
            mergeLowRuns(runBase1, runSize1, runBase2, runSize2);
        } else {
            mergeHighRuns(runBase1, runSize1, runBase2, runSize2);
        }
    }

    /**
     * Подібний до методу {@link TimSort#mergeLowRuns}.
     *
     * @param baseRun1
     *            індекс першого елементу у першому прогоні
     * @param runSize1
     *            довжина першого прогону
     * @param baseRun2
     *            індекс першого елементу у другому прогоні
     * @param runSize2
     *            довжина другого прогону
     * @since 1.0.0
     */
    private void mergeHighRuns(final int baseRun1, int runSize1,
            final int baseRun2, int runSize2) {
        ensureMergeBufferCapacity(runSize2);
        System.arraycopy(array, baseRun2, mergeBuffer, 0, runSize2);

        int unorderedArrayCursor = baseRun1 + runSize1 - 1;
        int mergeBufferCursor = runSize2 - 1;
        int orderedArrayCursor = baseRun2 + runSize2 - 1;

        array[orderedArrayCursor--] = array[unorderedArrayCursor--];
        if (--runSize1 == 0) {
            System.arraycopy(mergeBuffer, 0, array,
                    orderedArrayCursor - (runSize2 - 1), runSize2);
            return;
        }

        if (runSize2 == 1) {
            orderedArrayCursor -= runSize1;
            unorderedArrayCursor -= runSize1;
            System.arraycopy(array, unorderedArrayCursor + 1, array,
                    orderedArrayCursor + 1, runSize1);
            array[orderedArrayCursor] = mergeBuffer[mergeBufferCursor];
            return;
        }

        int oldMinGallop = minGallop;
        int amountOfCorrectFirstRuns;
        int amountOfCorrectSecondRuns;
        outerLoop: while (true) {
            amountOfCorrectFirstRuns = 0;
            amountOfCorrectSecondRuns = 0;

            do {
                if (comparator.compare(mergeBuffer[mergeBufferCursor],
                        array[unorderedArrayCursor]) < 0) {
                    array[orderedArrayCursor--] = array[unorderedArrayCursor--];
                    amountOfCorrectFirstRuns++;
                    amountOfCorrectSecondRuns = 0;
                    if (--runSize1 == 0) {
                        break outerLoop;
                    }
                } else {
                    array[orderedArrayCursor--] = mergeBuffer[mergeBufferCursor--];
                    amountOfCorrectSecondRuns++;
                    amountOfCorrectFirstRuns = 0;
                    if (--runSize2 == 1) {
                        break outerLoop;
                    }
                }
            } while ((amountOfCorrectFirstRuns
                    | amountOfCorrectSecondRuns) < oldMinGallop);

            do {
                amountOfCorrectFirstRuns = runSize1 - rightGallopSearch(array,
                        mergeBuffer[mergeBufferCursor], baseRun1, runSize1,
                        runSize1 - 1, comparator);
                if (amountOfCorrectFirstRuns != 0) {
                    orderedArrayCursor -= amountOfCorrectFirstRuns;
                    unorderedArrayCursor -= amountOfCorrectFirstRuns;
                    runSize1 -= amountOfCorrectFirstRuns;

                    System.arraycopy(array, unorderedArrayCursor + 1, array,
                            orderedArrayCursor + 1, amountOfCorrectFirstRuns);
                    if (runSize1 == 0) {
                        break outerLoop;
                    }
                }
                array[orderedArrayCursor--] = mergeBuffer[mergeBufferCursor--];
                if (--runSize2 == 1) {
                    break outerLoop;
                }

                amountOfCorrectSecondRuns = runSize2 - leftGallopSearch(
                        mergeBuffer, array[unorderedArrayCursor], 0, runSize2,
                        runSize2 - 1, comparator);
                if (amountOfCorrectSecondRuns != 0) {
                    orderedArrayCursor -= amountOfCorrectSecondRuns;
                    mergeBufferCursor -= amountOfCorrectSecondRuns;
                    runSize2 -= amountOfCorrectSecondRuns;

                    System.arraycopy(mergeBuffer, mergeBufferCursor + 1, array,
                            orderedArrayCursor + 1, amountOfCorrectSecondRuns);
                    if (runSize2 <= 1) {
                        break outerLoop;
                    }
                }
                array[orderedArrayCursor--] = array[unorderedArrayCursor--];
                if (--runSize1 == 0) {
                    break outerLoop;
                }
                oldMinGallop--;
            } while (amountOfCorrectFirstRuns >= DEFAULT_MIN_GALLOP
                    | amountOfCorrectSecondRuns >= DEFAULT_MIN_GALLOP);
            if (oldMinGallop < 0) {
                oldMinGallop = 0;
            }
            oldMinGallop += 2;
        }
        minGallop = oldMinGallop < 1 ? 1 : oldMinGallop;

        if (runSize2 == 1) {
            orderedArrayCursor -= runSize1;
            unorderedArrayCursor -= runSize1;

            System.arraycopy(array, unorderedArrayCursor + 1, array,
                    orderedArrayCursor + 1, runSize1);
            array[orderedArrayCursor] = mergeBuffer[mergeBufferCursor];
        } else {
            System.arraycopy(mergeBuffer, 0, array,
                    orderedArrayCursor - (runSize2 - 1), runSize2);
        }
    }

    /**
     * Призначений, для злиття двох сусудніх прогонів. Перший елемент першого
     * прогону повинен бути більше ніж перший елемент другого прогону.
     *
     * @param baseRun1
     *            індекс першого елементу у першому прогоні
     * @param runSize1
     *            довжина першого прогону
     * @param baseRun2
     *            індекс першого елементу у другому прогоні
     * @param runSize2
     *            довжина другого прогону
     * @since 1.0.0
     */
    private void mergeLowRuns(final int baseRun1, int runSize1,
            final int baseRun2, int runSize2) {
        ensureMergeBufferCapacity(runSize1);
        System.arraycopy(array, baseRun1, mergeBuffer, 0, runSize1);

        int mergeBufferCursor = 0;
        int unorderedArrayCursor = baseRun2;
        int orderedArrayCursor = baseRun1;

        array[orderedArrayCursor++] = array[unorderedArrayCursor++];
        if (--runSize2 == 0) {
            System.arraycopy(mergeBufferCursor, mergeBufferCursor, array,
                    orderedArrayCursor, runSize1);
            return;
        }
        if (runSize1 == 1) {
            System.arraycopy(array, unorderedArrayCursor, array,
                    orderedArrayCursor, runSize2);
            array[orderedArrayCursor
                    + runSize2] = mergeBuffer[mergeBufferCursor];
            return;
        }

        int oldMinGallop = minGallop;
        int amountOfCorrectFirstRuns;
        int amountOfCorrectSecondRuns;
        outerLoop: while (true) {
            amountOfCorrectFirstRuns = 0;
            amountOfCorrectSecondRuns = 0;

            do {
                if (comparator.compare(array[unorderedArrayCursor],
                        mergeBuffer[mergeBufferCursor]) < 0) {
                    array[orderedArrayCursor++] = array[unorderedArrayCursor++];
                    amountOfCorrectSecondRuns++;
                    amountOfCorrectFirstRuns = 0;
                    if (--runSize2 == 0) {
                        break outerLoop;
                    }
                } else {
                    array[orderedArrayCursor++] = mergeBuffer[mergeBufferCursor++];
                    amountOfCorrectFirstRuns++;
                    amountOfCorrectSecondRuns = 0;
                    if (--runSize1 == 1) {
                        break outerLoop;
                    }
                }
            } while ((amountOfCorrectFirstRuns
                    | amountOfCorrectSecondRuns) < oldMinGallop);

            do {
                amountOfCorrectFirstRuns = rightGallopSearch(mergeBuffer,
                        array[unorderedArrayCursor], mergeBufferCursor,
                        runSize1, 0, comparator);

                if (amountOfCorrectFirstRuns != 0) {
                    System.arraycopy(mergeBuffer, mergeBufferCursor, array,
                            orderedArrayCursor, amountOfCorrectFirstRuns);
                    orderedArrayCursor += amountOfCorrectFirstRuns;
                    mergeBufferCursor += amountOfCorrectFirstRuns;
                    runSize1 -= amountOfCorrectFirstRuns;
                    if (runSize1 <= 1) {
                        break outerLoop;
                    }
                }
                array[orderedArrayCursor++] = array[unorderedArrayCursor++];
                if (--runSize2 == 0) {
                    break outerLoop;
                }

                amountOfCorrectSecondRuns = leftGallopSearch(array,
                        mergeBuffer[mergeBufferCursor], unorderedArrayCursor,
                        runSize2, 0, comparator);
                if (amountOfCorrectSecondRuns != 0) {
                    System.arraycopy(array, unorderedArrayCursor, array,
                            orderedArrayCursor, amountOfCorrectSecondRuns);
                    orderedArrayCursor += amountOfCorrectSecondRuns;
                    unorderedArrayCursor += amountOfCorrectSecondRuns;
                    runSize2 -= amountOfCorrectSecondRuns;
                    if (runSize2 == 0) {
                        break outerLoop;
                    }
                }
                array[orderedArrayCursor++] = mergeBuffer[mergeBufferCursor++];
                if (--runSize1 == 1) {
                    break outerLoop;
                }
                oldMinGallop--;
            } while (amountOfCorrectFirstRuns >= DEFAULT_MIN_GALLOP
                    | amountOfCorrectSecondRuns >= DEFAULT_MIN_GALLOP);

            if (oldMinGallop < 0) {
                oldMinGallop = 0;
            }
            oldMinGallop += 2;
        }
        minGallop = oldMinGallop < 1 ? 1 : oldMinGallop;

        if (runSize1 == 1) {
            System.arraycopy(array, unorderedArrayCursor, array,
                    orderedArrayCursor, runSize2);
            array[orderedArrayCursor
                    + runSize2] = mergeBuffer[mergeBufferCursor];
        } else {
            System.arraycopy(mergeBuffer, mergeBufferCursor, array,
                    orderedArrayCursor, runSize1);
        }
    }

    /**
     * Призначений, для додавання вказаного прогону до стеку очыкуваних
     * прогонів.
     *
     * @param newRunBase
     *            інндекс першго елементу очікуваного прогону
     * @param newRunSize
     *            довжина очікуваного прогону
     * @since 1.0.0
     */
    private void pushRun(final int newRunBase, final int newRunSize) {
        runBaseStack[stackSize] = newRunBase;
        runSizeStack[stackSize] = newRunSize;
        stackSize++;
    }

    /**
     * Призначений, для встановлення потчоного масиву для сортування. На вхід
     * очікується внутрішній буфер об'єкту Array у тому числі з порожніми
     * комірками. Та кількість заповнених комірок буфера. Алгоритм розрахунку
     * розміру стеку описано в
     * <a href="https://bugs.python.org/file4451/timsort.txt">описі алгоритму
     * сортування</a>.
     *
     * @param array
     *            буфер об'єкту Array
     * @param arraySize
     *            кількість заповнених комірок буфера
     * @since 1.0.0
     */
    private void setUnorderedArray(final T[] array, final int arraySize) {
        this.array = array;

        @SuppressWarnings("unchecked")
        T[] mergeBufferAllocation = (T[]) new Object[arraySize < 2
                * DEFAULT_MERGE_BUFFER_SIZE ? arraySize >>> 1
                        : DEFAULT_MERGE_BUFFER_SIZE];
        this.mergeBuffer = mergeBufferAllocation;

        int stackSize = (arraySize < 120 ? 5
                : arraySize < 1542 ? 10 : arraySize < 119151 ? 19 : 40);

        runBaseStack = new int[stackSize];
        runSizeStack = new int[stackSize];
    }

    @Override
    public void sort(final Array<T> array) {
        @SuppressWarnings("unchecked")
        T[] arrayData = (T[]) array.getData();
        int arraySize = array.size();

        int low = 0;
        int high = arraySize;

        int numberOfRemainingElements = high - low;
        if (numberOfRemainingElements < 2) {
            return;
        }

        if (numberOfRemainingElements < DEFAULT_MIN_MERGE) {
            int initialRunSize = countRunAndMakeAscending(arrayData, low, high,
                    comparator);
            binaryInsertionSort(arrayData, low, high, low + initialRunSize,
                    comparator);
            return;
        }

        setUnorderedArray(arrayData, arraySize);
        int minRunSize = getMinRunSize(numberOfRemainingElements);
        int currentRunSize;
        int forceRunSize;

        do {
            currentRunSize = countRunAndMakeAscending(arrayData, low, high,
                    comparator);
            if (currentRunSize < minRunSize) {
                forceRunSize = numberOfRemainingElements <= minRunSize
                        ? numberOfRemainingElements
                        : minRunSize;
                binaryInsertionSort(arrayData, low, low + forceRunSize,
                        low + currentRunSize, comparator);
                currentRunSize = forceRunSize;
            }

            pushRun(low, currentRunSize);
            mergeAdjacentRuns();

            low += currentRunSize;
            numberOfRemainingElements -= currentRunSize;
        } while (numberOfRemainingElements != 0);

        mergeAllRuns();
    }
}
