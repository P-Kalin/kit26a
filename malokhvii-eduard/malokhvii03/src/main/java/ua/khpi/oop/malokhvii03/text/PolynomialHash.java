package ua.khpi.oop.malokhvii03.text;

/**
 * Призначений, для забезпечення швидкого порівняння рядків, на відміну від
 * звичайного алгоритму порівняння.
 * <p>
 * Алгоритми хешування рядків допомагають вирішити дуже багато завдань. Але у
 * них є великий недолік: що частіше за все вони не 100% ни, оскільки є безліч
 * рядків, хеші яких збігаються. Інша справа, що в більшості завдань на це можна
 * не звертати уваги, оскільки ймовірність збігу хеш все-таки дуже мала.
 * <p>
 * Нехай у нас є рядок s0..n-1. Поліноміальним хешем цього рядка називається
 * число h = hash (s0..n-1) = s0 + ps1 + p2s2 + ... + pn-1sn-1, де p - деяке
 * натуральне число (пізніше буде сказано, яке саме), а si - код i-ого символу
 * рядка s (майже у всіх сучасних мовах він записується s [i]).
 * <p>
 * Хеші володіють властивістю, що в однакових рядків хеші обов'язково рівні.
 * Тому основна операція, яку дозволяють виконувати хеші - швидке порівняння
 * двох рядків на рівність.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
public final class PolynomialHash {

    /**
     * Максимальна, довжина хешованих рядків, використовується для ініціалізації
     * ступенів деякого числа P.
     *
     * @since 1.0.0
     */
    private static final int MAXIMUM_HASHABLE_CHAR_SEQUENCE_SIZE = 512;

    /**
     * POLYNOMIAL_BASE - деяке число. Розумно вибирати для POLYNOMIAL_BASE
     * просте число, приблизно дорівнює кількості символів у вхідному алфавіті.
     * Наприклад, якщо рядки предполаются складаються тільки з маленьких
     * латинських літер, то хорошим вибором буде POLYNOMIAL_BASE = 31. Якщо
     * букви можуть бути і великими, і маленькими, то, наприклад, можна
     * POLYNOMIAL_BASE = 53.
     *
     * @since 1.0.0
     */
    private static final int POLYNOMIAL_BASE = 53;

    /**
     * Усі ступіні числа POLYNOMIAL_BASE збережені, оскільки немає смислу
     * обчислювати їх кожен раз.
     *
     * @since 1.0.0
     */
    private static long[] polynomials;

    /**
     * Значення нульового хешу, або не дійсного хешу. Використовується для
     * відмітки, що поточне значення хешу не дійсне.
     *
     * @since 1.0.0
     */
    public static final long ZERO_HASH = 0;

    static {
        PolynomialHash.calculatePolynomials(
                PolynomialHash.MAXIMUM_HASHABLE_CHAR_SEQUENCE_SIZE);
    }

    /**
     * Призначений, для початкового розрахування ступінів числа POLYNOMIAL_BASE,
     * для подальшого викоритсання в основному алгоритмі обчислення хешу.
     *
     * @param maximumHashableCharSequenceSize
     *            максимальний розмір символьних послідовностей для хешування
     * @since 1.0.0
     */
    private static void calculatePolynomials(
            final int maximumHashableCharSequenceSize) {
        int index = 1;
        PolynomialHash.polynomials = new long[maximumHashableCharSequenceSize];
        PolynomialHash.polynomials[0] = index;

        for (; index < maximumHashableCharSequenceSize; index++) {
            PolynomialHash.polynomials[index] = PolynomialHash.polynomials[index
                    - 1] * PolynomialHash.POLYNOMIAL_BASE;
        }
    }

    /**
     * Призначений, для отримання поліноміального хешу, для послідовності
     * символів.
     *
     * @param charSequence
     *            послідовність символів
     * @return поліноміальний хеш для послідовності символів
     * @since 1.0.0
     */
    public static long charSequenceHash(final CharSequence charSequence) {
        int charSequenceLength = charSequence.length();
        long hashCode = charSequence.charAt(0);

        int index;
        for (index = 1; index < charSequenceLength; index++) {
            hashCode += PolynomialHash.polynomials[index]
                    * charSequence.charAt(index);
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
     * @since 1.0.0
     */
    public static long reversedCharSequenceHash(
            final CharSequence charSequence) {
        int charSequenceLength = charSequence.length();
        long hashCode = charSequence.charAt(charSequenceLength - 1);

        int reversedIndex = charSequenceLength - 2;
        int index;
        for (index = 1; index < charSequenceLength; index++) {
            hashCode += PolynomialHash.polynomials[index]
                    * charSequence.charAt(reversedIndex--);
        }

        return hashCode;
    }

    /**
     * Приватний конструктор, для заборони створення утилітарного класу.
     *
     * @since 1.0.0
     */
    private PolynomialHash() {

    }
}
