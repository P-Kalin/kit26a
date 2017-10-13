package ua.khpi.oop.malokhvii02.data;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Контейнер даних, призначений для обчислення завдання (Перевірити чи дорівнює
 * сума перших трьох цифр сумі останніх трьох цифр в десятковому запису
 * 6-значного цілого числа).
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @see DataContainer
 * @since 1.0.0
 */
public class NumberEqualityContainer implements DataContainer {

    /**
     * Система счислення отриманого числа ({@value}).
     *
     * @since 1.0.0
     */
    private static final short NUMBER_BASE = 10;

    /**
     * Мінімальне допустиме вхідне число для обробки ({@value}).
     *
     * @since 1.0.0
     */
    private static final long MIN_NUMBER = -0x2386F26FC0FFFFL;

    /**
     * Максимальне допустиме вхідне число для обробки ({@value}).
     *
     * @since 1.0.0
     */
    private static final long MAX_NUMBER = 0x2386F26FC0FFFFL;

    /**
     * Початкове число, для обчислення.
     *
     * @since 1.0.0
     */
    private long number;

    /**
     * Довжина отриманого числа.
     *
     * @since 1.0.0
     */
    private double numberLength;

    /**
     * Відділена ліва частина числа.
     *
     * @since 1.0.0
     */
    private long leftSide;

    /**
     * Віддалена права частина числа.
     *
     * @since 1.0.0
     */
    private long rightSide;

    /**
     * Результат порівняння лівой та правої частини числа на рівність.
     *
     * @since 1.0.0
     */
    private boolean isLeftSideEqualToRightSide;

    /**
     * Призначений для отримання поточного значення числа для обчислення.
     *
     * @return Поточне значення числа
     * @since 1.0.0
     */
    public long getNumber() {
        return this.number;
    }

    /**
     * Призначений для оновлення поточного значення числа для обчислення.
     *
     * @param number
     *            Нове поточне значення числа для обчислення
     * @since 1.0.0
     */
    public void setNumber(final long number) {
        this.number = number;
    }

    /**
     * Призначений для отримання лівої частини числа.
     *
     * @return Ліва частина числа
     * @since 1.0.0
     */
    public long getLeftNumberSide() {
        return this.leftSide;
    }

    /**
     * Призначений для отримання правої частини числа.
     *
     * @return Права частина числа
     * @since 1.0.0
     */
    public long getRightNumberSide() {
        return this.rightSide;
    }

    /**
     * Призначений для отримання стану порівняння правої та лівої частини числа
     * на рівність.
     *
     * @return Результат порівняння
     * @since 1.0.0
     */
    public boolean isEquals() {
        return this.isLeftSideEqualToRightSide;
    }

    @Override
    public final String prepareDataDescription() {
        return String.format("Divides the whole number by parts and compares "
                + "their equality");
    }

    @Override
    public final String prepareDataVisualization() {
        String dataVisualization = "[-]: Equality is %s!\n[-]: Left"
                + " number side: %d\n" + "[-]: Right number side: %d\n"
                + "[-]: Number length: %.2f";

        if (this.isLeftSideEqualToRightSide) {
            return String.format(dataVisualization, "true", this.leftSide,
                    this.rightSide, this.numberLength);
        }
        return String.format(dataVisualization, "false", this.leftSide,
                this.rightSide, this.numberLength);
    }

    @Override
    public final DataCollectionStatus collectData(final PrintStream out,
            final Scanner in) {
        out.printf("[-]: Please, enter your number: ", MAX_NUMBER);

        try {
            this.number = in.nextLong();
        } catch (InputMismatchException exception) {
            out.println("[-]: Input content is not integer\n");
            return DataCollectionStatus.REPEAT;
        }

        if (this.number <= MIN_NUMBER && this.number <= MAX_NUMBER) {
            out.println("[-]: Input number is out of range\n");
            return DataCollectionStatus.REPEAT;
        }

        return DataCollectionStatus.CONTINUE;
    }

    @Override
    public final DataComputationStatus computeData() {
        this.numberLength = Math.floor(Math.log10(Math.abs(this.number))) + 1;

        this.leftSide = (long) (this.number / Math.pow(
                NumberEqualityContainer.NUMBER_BASE, this.numberLength / 2));
        this.rightSide = (long) (this.number
                - this.leftSide * Math.pow(NumberEqualityContainer.NUMBER_BASE,
                        this.numberLength / 2));

        this.isLeftSideEqualToRightSide = this.leftSide == this.rightSide;
        return DataComputationStatus.SUCCESS;
    }

    @Override
    public final String getContainerName() {
        return NumberEqualityContainer.class.getSimpleName();
    }
}
