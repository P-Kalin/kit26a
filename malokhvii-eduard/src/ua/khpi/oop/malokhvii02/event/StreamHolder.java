package ua.khpi.oop.malokhvii02.event;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Обгортка на потоки введення та виведення, використовується для зручнішого
 * обміну між подіями.
 *
 * @author malokhvii-ee
 * @version 1.0.0
 * @see EventLoop
 * @see Event
 */
public final class StreamHolder {

    /**
     * Обгортка над потоком введення.
     */
    private Scanner in;

    /**
     * Обгортка над потоком вивдення.
     */
    private PrintStream out;

    /**
     * Індекс останнього звернення до потоку виведення.
     */
    private long outIndex;

    /**
     * Призначений для ініціалізації потоків введення/виведення.
     *
     * @param out
     *            Поток виведення
     * @param in
     *            Поток введення
     */
    public StreamHolder(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
        this.outIndex = 0;
    }

    /**
     * Призначений для отримання посилання на поток введення.
     *
     * @return Поток введення
     */
    public Scanner getIn() {
        return this.in;
    }

    /**
     * Призначений для отримання посилання на поток виведення.
     *
     * @return Поток виведення
     */
    public PrintStream getOut() {
        return this.out;
    }

    /**
     * Призначений для отимання поточного індексу виклику потоку у вигляді
     * рядку.
     *
     * @return Поточний індекс
     */
    public String getCurrentOutLabel() {
        return String.format("[%d]:", ++this.outIndex);
    }

    /**
     * Призначений для звільнення ресурсів потоків введення.виведення.
     */
    public void closeIn() {
        this.out.close();
    }
}
