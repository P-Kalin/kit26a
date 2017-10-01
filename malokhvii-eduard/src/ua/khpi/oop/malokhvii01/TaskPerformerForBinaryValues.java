package ua.khpi.oop.malokhvii01;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;

/**
 * Призначений для виконання завдання над вхідними літералами у вигляді об'єкту
 * типу {@link TaskValues}. Згідно завдання, підраховує кількість одиниць та
 * нулів у кожному числі з вхідного об'єкту у двійковому представленні.
 *
 * @author malokhvii-ee
 * @version 1.0.0
 * @see TaskValues
 */
public class TaskPerformerForBinaryValues {

    /**
     * Вхідні числа у двійковому вигляді, для зручнішої подальшої обробки.
     */
    private ArrayList<String> binaryNumbers;

    /**
     * Посилання на об'єкт вхідних даних.
     */
    private TaskValues taskValues;

    /**
     * Призначений для втсановлення значення полів
     * {@link TaskPerformerForBinaryValues#binaryNumbers},
     * {@link TaskPerformerForBinaryValues#taskValues}.
     *
     * @param taskValues
     *            Вхідні дані, для подальших обчислень
     */
    public TaskPerformerForBinaryValues(final TaskValues taskValues) {
        this.binaryNumbers = new ArrayList<String>();
        this.setTaskValues(taskValues);
    }

    /**
     * Призначений для підрахунку кількості очікуваних символів у двійковому
     * поданні числа.
     *
     * @param binaryNumber
     *            Двійкове представлення числа
     * @param bitCharacter
     *            Символ біту, кількість якого буде підраховано
     * @return Кількість підрахованих повторень очікуваного символу
     */
    public long countAmountOfBitsInNumber(final String binaryNumber,
            final char bitCharacter) {
        final CharacterIterator characterSelector = new StringCharacterIterator(
                binaryNumber);
        long amountOfCoincidences = 0;
        char i;
        for (i = characterSelector
                .first(); i != CharacterIterator.DONE; i = characterSelector
                        .next()) {
            if (i == bitCharacter) {
                ++amountOfCoincidences;
            }
        }
        return amountOfCoincidences;
    }

    /**
     * Призначений для підрахунку кількості одиниць у двійковому поданні усіх
     * вхідних чисел.
     *
     * @return Кількість одиниць у двійковому поданні усіх вхідних чисел
     */
    public long countAmountOfOnesInNumbers() {
        long amountOfOnes = 0;
        for (String binaryNumber : this.binaryNumbers) {
            amountOfOnes += this.countAmountOfBitsInNumber(binaryNumber, '1');
        }

        return amountOfOnes;
    }

    /**
     * Призначений для підрахунку кількості нулів у двійковому поданні усіх
     * вхідних чисел.
     *
     * @return Кількість нулів у двійковому поданні усіх вхідних чисел
     */
    public long countAmountOfZeroesInNumbers() {
        long amountOfZeroes = 0;
        for (String binaryNumber : this.binaryNumbers) {
            amountOfZeroes += this.countAmountOfBitsInNumber(binaryNumber, '0');
        }

        return amountOfZeroes;
    }

    /**
     * Призначений для оновлення вхідних даних, та оновлення записів у масиві
     * двійкових чисел.
     *
     * @param taskValues
     *            Вхідні дані, для подальших обчислень
     */
    public void setTaskValues(final TaskValues taskValues) {
        this.taskValues = taskValues;

        this.binaryNumbers.clear();
        this.binaryNumbers.add(
                Long.toBinaryString(this.taskValues.getMobilePhoneNumber()));
        this.binaryNumbers.add(Integer.toBinaryString(
                this.taskValues.getLastTwoDigitsOfMobilePhoneNumber()));
        this.binaryNumbers.add(Integer.toBinaryString(
                this.taskValues.getLastFourDigitsOfMobilePhoneNumber()));

        this.binaryNumbers.add(
                Integer.toBinaryString(this.taskValues.getRecordBookNumber()));
        this.binaryNumbers.add(Integer
                .toBinaryString(this.taskValues.getEnglishUpperCaseLetter()));
    }
}
