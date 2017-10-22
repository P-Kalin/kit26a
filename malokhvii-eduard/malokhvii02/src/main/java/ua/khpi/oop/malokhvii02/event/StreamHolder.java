package ua.khpi.oop.malokhvii02.event;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Обгортка на потоки введення та виведення, використовується для зручнішого
 * обміну між подіями.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see EventLoop
 * @see Event
 */
public final class StreamHolder {

    /**
     * Обгортка над потоком введення.
     *
     * @since 1.0.0
     */
    private final Scanner in;

    /**
     * Обгортка над потоком вивдення.
     *
     * @since 1.0.0
     */
    private final PrintStream out;

    /**
     * Індекс останнього звернення до потоку виведення.
     *
     * @since 1.0.0
     */
    private long outIndex;

    /**
     * Призначений для ініціалізації потоків введення/виведення.
     *
     * @param out
     *            Поток виведення
     * @param in
     *            Поток введення
     * @since 1.0.0
     */
    public StreamHolder(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
        this.outIndex = 0;
    }

    /**
     * Призначений для звільнення ресурсів потоків введення.виведення.
     *
     * @since 1.0.0
     */
    public void closeIn() {
        this.out.close();
    }

    /**
     * Призначений для отимання поточного індексу виклику потоку у вигляді
     * рядку.
     *
     * @return Поточний індекс
     * @since 1.0.0
     */
    public String getCurrentOutLabel() {
        return String.format("[%d]:", ++this.outIndex);
    }

    /**
     * Призначений для отримання посилання на поток введення.
     *
     * @return Поток введення
     * @since 1.0.0
     */
    public Scanner getIn() {
        return this.in;
    }

    /**
     * Призначений для отримання посилання на поток виведення.
     *
     * @return Поток виведення
     * @since 1.0.0
     */
    public PrintStream getOut() {
        return this.out;
    }
}
