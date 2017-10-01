package ua.khpi.oop.malokhvii01;

/**
 * Призначений для оголошення точки входу у програму.
 *
 * @author malokhvii-ee
 * @version 1.0.0
 */
public final class Application {

    /**
     * Оголошен приватним для заборони створення класу.
     */
    private Application() {

    }

    /**
     * Призначений для оголошення точки входу у програму.
     *
     * @param args
     *            Аргументи командного рядку
     */
    public static void main(final String[] args) {
        TaskValues taskValues = TaskValues.literalBuilder()
                .setMobilePhoneNumber(380507079289L).setRecordBookNumber(0x3BF8)
                .setLastTwoDigitsOfMobilePhoneNumber(0b1011001)
                .setLastFourDigitsOfMobilePhoneNumber(022111)
                .setEnglishUpperCaseLetter('I').build();

        TaskPerformerForDecimalValues taskPerformerForDecimalValues = new TaskPerformerForDecimalValues(
                taskValues);
        taskPerformerForDecimalValues.countAmountOfPairedDigitsInNumbers();
        taskPerformerForDecimalValues.countAmountOfUnpairedDigitsInNumbers();

        TaskPerformerForBinaryValues taskPerformerForBinaryValues = new TaskPerformerForBinaryValues(
                taskValues);
        taskPerformerForBinaryValues.countAmountOfOnesInNumbers();
        taskPerformerForBinaryValues.countAmountOfZeroesInNumbers();
    }
}
