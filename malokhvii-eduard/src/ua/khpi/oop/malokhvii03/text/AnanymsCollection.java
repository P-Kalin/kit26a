package ua.khpi.oop.malokhvii03.text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Призначений, для збереження знайдених ананимів (анаграм) у вигляді суцільної
 * колекції.
 *
 * @author malokhvii-ee
 * @version 1.0.1
 * @see Ananym
 * @see Anagram
 */
public final class AnanymsCollection {

    /**
     * Колекція ананимів (анаграм).
     */
    private List<Ananym> ananyms;

    /**
     * Призначений, для створення колекції ананимів (анаграм).
     */
    public AnanymsCollection() {
        this.ananyms = new ArrayList<Ananym>();
    }

    /**
     * Призначений для додавання ананиму (анаграми) до колекції.
     *
     * @param ananym
     *            ананим (анаграма) для додавання
     * @return результат додавання ананиму (анаграми)
     */
    public boolean putAnanym(final Ananym ananym) {
        this.ananyms.add(ananym);
        return true;
    }

    /**
     * Призначений, для очищення колекції ананимів.
     */
    public void clear() {
        this.ananyms.clear();
    }

    /**
     * Призначений, для отримання поточного розміру колекції.
     *
     * @return поточний розмір колекції
     */
    public int size() {
        return this.ananyms.size();
    }

    /**
     * Призначений, для отримання поточного стану колекції.
     *
     * @return поточний стан заповнення колекції
     */
    public boolean isEmpty() {
        return this.ananyms.isEmpty();
    }

    /**
     * Призначений, для отримання ітератору, для обходження колекції.
     *
     * @return об'єкт ітератору
     */
    public Iterator<Ananym> getIterator() {
        return this.ananyms.iterator();
    }
}
