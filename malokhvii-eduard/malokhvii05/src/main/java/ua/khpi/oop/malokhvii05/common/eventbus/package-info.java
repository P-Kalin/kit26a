/**
 * Публікація-підписка патерн є однією з варіацій паттерна Observer. Виходячи з
 * назви у патерні виділяють два компоненти Publisher (видавець) та Subscriber
 * Subscriber (підписчик) об'єктами здійснюється за допомогою каналу зв'язку
 * {@link ua.khpi.oop.malokhvii05.common.eventbus.publish.EventBus EventBus}
 * (шини подій). Publisher надсилає свої події в
 * {@link ua.khpi.oop.malokhvii05.common.eventbus.publish.EventBus EventBus}, а
 * Subscriber підписується на потрібну подію і слухає її на шині, що забезпечує
 * відсутність прямого зв'язку між підписчиком і видавцем.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 */
package ua.khpi.oop.malokhvii05.common.eventbus;
