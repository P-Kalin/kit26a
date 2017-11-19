package ua.khpi.oop.malokhvii04.shell;

import ua.khpi.oop.malokhvii05.common.collect.Array;

/**
 * Призначений, для збереження даних інтерактивної оболонки, для подальшої
 * передачі між командами.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.1.1
 */
public final class ShellData {

    /**
     * Посилання, на буффер для вхідного тексту.
     *
     * @since 1.0.0
     */
    private Array<String> textLines;

    /**
     * Приватний конструктор, задля створення об'єкт за допомогою Builder-а.
     *
     * @since 1.0.0
     */
    ShellData() {
        textLines = new Array<>();
    }

    /**
     * Призначений, для отримання буферу рядків вхідного тексту.
     *
     * @return буфер рядків вхідного тексту
     * @since 1.0.0
     */
    public Array<String> getTextLines() {
        return textLines;
    }

    /**
     * Призначений, для оновлення буферу рядків вхідного тексту.
     *
     * @param textLines
     *            новий буфер рядків вхідного тексту
     * @since 1.0.0
     */
    public void setTextLines(final Array<String> textLines) {
        this.textLines = textLines;
    }

    /**
     * Призначений, для оновлення буферу рядків вхідного тексту.
     *
     * @param textLines
     *            новий буфер рядків вхідного тексту
     * @since 1.1.1
     */
    public void setTextLines(final String[] textLines) {
        this.textLines.clear();
        for (String line : textLines) {
            this.textLines.add(line);
        }
    }
}
