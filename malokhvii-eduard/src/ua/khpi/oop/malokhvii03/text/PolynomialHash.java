package ua.khpi.oop.malokhvii03.text;

/**
 * <p>
 * Призначений, для забезпечення швидкого порівняння рядків, на відміну від
 * звичайного алгоритму порівняння.
 * </p>
 * <p>
 * Алгоритми хешування рядків допомагають вирішити дуже багато завдань. Але у
 * них є великий недолік: що частіше за все вони не 100% ни, оскільки є безліч
 * рядків, хеші яких збігаються. Інша справа, що в більшості завдань на це можна
 * не звертати уваги, оскільки ймовірність збігу хеш все-таки дуже мала.
 * </p>
 * <p>
 * Нехай у нас є рядок s0..n-1. Поліноміальним хешем цього рядка називається
 * число h = hash (s0..n-1) = s0 + ps1 + p2s2 + ... + pn-1sn-1, де p - деяке
 * натуральне число (пізніше буде сказано, яке саме), а si - код i-ого символу
 * рядка s (майже у всіх сучасних мовах він записується s [i]).
 * </p>
 * <p>
 * Хеші володіють тим властивістю, що у однакових рядків хеші обов'язково рівні.
 * Тому основна операція, яку дозволяють виконувати хеші - швидке порівняння
 * двох подстрок на рівність.
 * </p>
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 */
public final class PolynomialHash {

    /**
     * Максимальна, довжина хешованих рядків, використовується для ініціалізації
     * ступенів деякого числа P.
     */
    private static final int MAXIMUM_HASHABLE_CHAR_SEQUENCE_SIZE = 512;

    /**
     * POLYNOMIAL_BASE - деяке число. Розумно вибирати для P просте число,
     * приблизно дорівнює кількості символів у вхідному алфавіті. Наприклад,
     * якщо рядки предполаются складаються тільки з маленьких латинських літер,
     * то хорошим вибором буде POLYNOMIAL_BASE = 31. Якщо букви можуть бути і
     * великими, і маленькими, то, наприклад, можна POLYNOMIAL_BASE = 53.
     */
    private static final int POLYNOMIAL_BASE = 53;

    /**
     * Усі ступіні числа POLYNOMIAL_BASE збережені, оскільки немає смислу
     * обчислювати їх кожен раз.
     */
    private static long[] polynomials;

    /**
     * Значення нульового хешу, або не дійсного хешу. Використовується для
     * відмітки, що поточне значення хешу не дійсне.
     */
    public static final long ZERO_HASH = 0;

    static {
        calculatePolynomials(MAXIMUM_HASHABLE_CHAR_SEQUENCE_SIZE);
    }

    /**
     * Призначений, для початкового розрахування ступінів числа POLYNOMIAL_BASE,
     * для подальшого викоритсання в основному алгоритмі обчислення хешу.
     *
     * @param maximumHashableCharSequenceSize
     *            максимальний розмір символьних послідовностей для хешування
     */
    private static void calculatePolynomials(
            final int maximumHashableCharSequenceSize) {
        int index = 1;
        polynomials = new long[maximumHashableCharSequenceSize];
        polynomials[0] = index;

        for (; index < maximumHashableCharSequenceSize; index++) {
            polynomials[index] = polynomials[index - 1] * POLYNOMIAL_BASE;
        }
    }

    /**
     * Призначений, для отримання поліноміального хешу, для послідовності
     * символів.
     *
     * @param charSequence
     *            послідовність символів
     * @return поліноміальний хеш для послідовності символів
     */
    public static long charSequenceHash(final CharSequence charSequence) {
        int charSequenceLength = charSequence.length();
        long hashCode = charSequence.charAt(0);

        int index;
        for (index = 1; index < charSequenceLength; index++) {
            hashCode += polynomials[index] * charSequence.charAt(index);
        }

        return hashCode;
    }

    /**
     * Призначений, для отримання поліноміального хешу, для оберненої
     * послідовності символів.
     *
     * @param charSequence
     *            послідовність символів
     * @return поліноміальний хеш для оберненої послідовності символів
     */
    public static long reversedCharSequenceHash(
            final CharSequence charSequence) {
        int charSequenceLength = charSequence.length();
        long hashCode = charSequence.charAt(charSequenceLength - 1);

        int reversedIndex = charSequenceLength - 2;
        int index;
        for (index = 1; index < charSequenceLength; index++) {
            hashCode += polynomials[index]
                    * charSequence.charAt(reversedIndex--);
        }

        return hashCode;
    }

    /**
     * Приватний конструктор, для заборони створення утилітарного класу.
     */
    private PolynomialHash() {

    }
}
