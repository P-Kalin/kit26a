package ua.khpi.oop.malokhvii02;

import java.util.Scanner;

import ua.khpi.oop.malokhvii02.data.DataContainer;
import ua.khpi.oop.malokhvii02.data.NumberEqualityContainer;
import ua.khpi.oop.malokhvii02.event.EventLoop;
import ua.khpi.oop.malokhvii02.event.EventsContainer;
import ua.khpi.oop.malokhvii02.event.GlobalEventsContainer;

/**
 * Призначений для демонстрації обчисленнь згідно з завдання.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
public final class Application {

    /**
     * Призначений для оголошення точки входу у програму.
     *
     * @param args
     *            Аргументи командного рядку
     * @since 1.0.0
     */
    public static void main(final String[] args) {
        DataContainer dataContainer = new NumberEqualityContainer();
        EventsContainer loopEvents = GlobalEventsContainer.getInstance();
        EventLoop eventLoop = new EventLoop(dataContainer, loopEvents,
                System.out, new Scanner(System.in));

        eventLoop.launchLoop();
        while (eventLoop.isRunning()) {
            eventLoop.handleCurrentEvent();
        }
        eventLoop.closeStream();
    }

    /**
     * Оголошен приватним для заборони створення класу.
     *
     * @since 1.0.0
     */
    private Application() {

    }
}
