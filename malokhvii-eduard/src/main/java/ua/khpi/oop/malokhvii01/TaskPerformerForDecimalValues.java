package ua.khpi.oop.malokhvii01;

import java.util.ArrayList;

/**
 * Призначений для виконання завдання над вхідними літералами у вигляді об'єкту
 * типу {@link TaskValues}. Згідно завдання, підраховує кількість парних та
 * непарних цифр у кожному числі з вхідного об'єкту.
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @see TaskValues
 * @since 1.0.0
 */
public class TaskPerformerForDecimalValues {

    /**
     * Вхідні числа у десятковому вигляді, для зручнішої подальшої обробки.
     *
     * @since 1.0.0
     */
    private ArrayList<Long> decimalNumbers;

    /**
     * Посилання на об'єкт вхідних даних.
     *
     * @since 1.0.0
     */
    private TaskValues taskValues;

    /**
     * Призначений для втсановлення значення полів
     * {@link TaskPerformerForDecimalValues#decimalNumbers},
     * {@link TaskPerformerForDecimalValues#taskValues}.
     *
     * @param taskValues
     *            Вхідні дані, для подальших обчислень
     * @since 1.0.0
     */
    public TaskPerformerForDecimalValues(final TaskValues taskValues) {
        this.decimalNumbers = new ArrayList<Long>();
        this.setTaskValues(taskValues);
    }

    /**
     * Призначений для підрахунку кількості парних цифр у числі.
     *
     * @param number
     *            Число, для підрахунку парних цифр
     * @return Кількість парних цифр, підрахованих у вхідному числі
     * @since 1.0.0
     */
    public long countAmountOfPairedDigitsInNumber(final Long number) {
        long numberCopy = number;
        long amountOfPairedNumbers = 0;
        while (numberCopy != 0) {
            if (((numberCopy % 10) % 2) == 0) {
                ++amountOfPairedNumbers;
            }
            numberCopy /= 10;
        }

        return amountOfPairedNumbers;
    }

    /**
     * Призначений для обчислення суми парних цифр для усіх цифр.
     *
     * @return Суму парних цифр, у отриманих числах з вхідного об'єкту
     * @since 1.0.0
     */
    public long countAmountOfPairedDigitsInNumbers() {
        long amountOfPairedDigits = 0;
        for (Long number : this.decimalNumbers) {
            amountOfPairedDigits += this
                    .countAmountOfPairedDigitsInNumber(number);
        }

        return amountOfPairedDigits;
    }

    /**
     * Призначений для підрахунку кількості непарних цифр у числі.
     *
     * @param number
     *            Число, для підрахунку непарних цифр
     * @return Кількість непарних цифр, підрахованих у вхідному числі
     * @since 1.0.0
     */
    public long countAmountOfUnpairedDigitsInNumber(final Long number) {
        long numberCopy = number;
        long amountOfUnpairedNumbers = 0;
        while (numberCopy != 0) {
            if (((numberCopy % 10) % 2) != 0) {
                ++amountOfUnpairedNumbers;
            }
            numberCopy /= 10;
        }

        return amountOfUnpairedNumbers;
    }

    /**
     * Призначений для обчислення суми непарних цифр для усіх цифр.
     *
     * @return Суму непарних цифр, у отриманих числах з вхідного об'єкту
     * @since 1.0.0
     */
    public long countAmountOfUnpairedDigitsInNumbers() {
        long amountOfUnpairedDigits = 0;
        for (Long number : this.decimalNumbers) {
            amountOfUnpairedDigits += this
                    .countAmountOfUnpairedDigitsInNumber(number);
        }

        return amountOfUnpairedDigits;
    }

    /**
     * Призначений для отриманння значення поля
     * {@link TaskPerformerForDecimalValues#taskValues}.
     *
     * @return Значення поля {@link TaskPerformerForDecimalValues#taskValues}
     * @since 1.0.0
     */
    public TaskValues getTaskInitialValues() {
        return this.taskValues;
    }

    /**
     * Призначений для оновлення вхідних даних, та оновлення записів у масиві
     * десяткових чисел.
     *
     * @param taskValues
     *            Вхідні дані, для подальших обчислень
     * @since 1.0.0
     */
    public void setTaskValues(final TaskValues taskValues) {
        this.taskValues = taskValues;

        this.decimalNumbers.clear();
        this.decimalNumbers
                .add(new Long(this.taskValues.getMobilePhoneNumber()));
        this.decimalNumbers.add(new Long(
                this.taskValues.getLastTwoDigitsOfMobilePhoneNumber()));
        this.decimalNumbers.add(new Long(
                this.taskValues.getLastFourDigitsOfMobilePhoneNumber()));

        this.decimalNumbers
                .add(new Long(this.taskValues.getRecordBookNumber()));
        this.decimalNumbers
                .add(new Long(this.taskValues.getEnglishUpperCaseLetter()));
    }
}
