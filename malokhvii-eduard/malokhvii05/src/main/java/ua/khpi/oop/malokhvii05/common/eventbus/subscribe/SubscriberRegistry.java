package ua.khpi.oop.malokhvii05.common.eventbus.subscribe;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.reflect.TypeToken;
import com.google.common.util.concurrent.UncheckedExecutionException;

import ua.khpi.oop.malokhvii05.common.eventbus.annotations.Subscribe;
import ua.khpi.oop.malokhvii05.common.eventbus.meta.AnnotationMetadataException;
import ua.khpi.oop.malokhvii05.common.eventbus.meta.AnnotationMetadatas;
import ua.khpi.oop.malokhvii05.common.eventbus.meta.SubscribeMetadata;
import ua.khpi.oop.malokhvii05.common.eventbus.meta.SubscriberMetadata;
import ua.khpi.oop.malokhvii05.common.eventbus.publish.EventBus;

/**
 * Призначений, для збереження записів про підписчиків для екземпляру
 * {@link EventBus}.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.1
 */
public final class SubscriberRegistry {

    /**
     * Призначений, для оголошення унікального індентифікатору обробника
     * (методу) події.
     *
     * @author malokhvii-eduard (malokhvii.ee@gmail.com)
     * @version 1.0.0
     */
    @Immutable
    private static final class MethodIdentifier {

        /**
         * Назва методу.
         *
         * @since 1.0.0
         */
        private final String name;

        /**
         * Перелік параметрів методу.
         *
         * @since 1.0.0
         */
        private final List<Class<?>> parameterTypes;

        /**
         * Призначений, для ініціалізації унікального індентифікатора методу.
         *
         * @param method
         *            обробник (метод) події
         * @since 1.0.0
         */
        MethodIdentifier(@Nonnull final Method method) {
            name = method.getName();
            parameterTypes = Arrays.asList(method.getParameterTypes());
        }

        @Override
        public boolean equals(@Nonnull final Object object) {
            if (object instanceof MethodIdentifier) {
                final MethodIdentifier identifier = (MethodIdentifier) object;
                return name.equals(identifier.name)
                        && parameterTypes.equals(identifier.parameterTypes);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name, parameterTypes);
        }
    }

    /**
     * Глобальний кеш, містить вирівняну ієрархію типу класу у набір об'єктів
     * {@link Class}, включаючи всі суперкласи (транзитивно) і всі інтерфейси,
     * реалізовані цими суперкласами.
     *
     * @since 1.0.0
     */
    private static final LoadingCache<Class<?>, ImmutableSet<Class<?>>> flattenHierarchyCache = CacheBuilder
            .newBuilder().weakKeys()
            .build(new CacheLoader<Class<?>, ImmutableSet<Class<?>>>() {

                @Override
                public ImmutableSet<Class<?>> load(
                        final Class<?> concreteClass) {
                    return ImmutableSet.<Class<?>>copyOf(
                            TypeToken.of(concreteClass).getTypes().rawTypes());
                }
            });

    /**
     * Глобальний кеш, який містить відображення кожного класу для всіх методів
     * у цьому класі та всі суперкласи, котрі анотовані з {@link Subscribe}. Кеш
     * доступний для всіх екземплярів цього класу, це значно покращує
     * продуктивність при наявності декількох екземплярів {@link EventBus} тобто
     * об'єкти одного класу зареєстровані на всіх них.
     *
     * @since 1.0.0
     */
    private static final LoadingCache<Class<?>, ImmutableList<Method>> subscriberMethodsCache = CacheBuilder
            .newBuilder().weakKeys()
            .build(new CacheLoader<Class<?>, ImmutableList<Method>>() {
                @Override
                public ImmutableList<Method> load(final Class<?> concreteClass)
                        throws Exception {
                    return getAnnotatedMethodsNotCached(concreteClass);
                }
            });

    /**
     * Призначений, для отримання усіх методів, які відмічені анотацією
     * {@link Subscribe}, із {@link #subscriberMethodsCache}.
     *
     * @param subscriberType
     *            тип підписчику
     * @return перелік методів, які відмічені анотацією {@link Subscribe}
     */
    private static @Nonnull ImmutableList<Method> getAnnotatedMethods(
            @Nonnull final Class<?> subscriberType) {
        return subscriberMethodsCache.getUnchecked(subscriberType);
    }

    /**
     * Призначений, для отримання усіх методів, які відмічені анотацією
     * {@link Subscribe}, які досить не присутні в
     * {@link #subscriberMethodsCache}. Якщо кількість аргументів в методі не
     * дорівнює 1 тоді буде отримано виключну ситуацію
     * {@link IllegalArgumentException}.
     *
     * @param subscriberType
     *            тип підписчику
     * @return перелік методів, які відмічені анотацією {@link Subscribe}
     * @throws IllegalArgumentException
     *             кількість параметрів методу не дорівнює 1
     */
    private static @Nonnull ImmutableList<Method> getAnnotatedMethodsNotCached(
            @Nonnull final Class<?> subscriberType) {
        final Set<? extends Class<?>> supertypes = TypeToken.of(subscriberType)
                .getTypes().rawTypes();
        final Map<MethodIdentifier, Method> identifiers = Maps.newHashMap();
        for (final Class<?> supertype : supertypes) {
            for (final Method method : supertype.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Subscribe.class)
                        && !method.isSynthetic() && !method.isBridge()) {
                    final Class<?>[] parameterTypes = method
                            .getParameterTypes();
                    checkArgument(parameterTypes.length == 1);

                    final MethodIdentifier ident = new MethodIdentifier(method);
                    if (!identifiers.containsKey(ident)) {
                        identifiers.put(ident, method);
                    }
                }
            }
        }
        return ImmutableList.copyOf(identifiers.values());
    }

    /**
     * Призначений, для отримання вирівняної ієрархію типу класу у вигляді
     * набіру об'єктів {@link Class}, включаючи всі суперкласи (транзитивно) і
     * всі інтерфейси, реалізовані цими суперкласами.
     *
     * @param concreteClass
     *            класс, для якого необхідно отримати ієрархію
     * @return вирівняна ієрархія типу класу
     * @since 1.0.0
     */
    private static @Nonnull ImmutableSet<Class<?>> getFlattenHierarchy(
            final Class<?> concreteClass) {
        try {
            return flattenHierarchyCache.getUnchecked(concreteClass);
        } catch (final UncheckedExecutionException exception) {
            throw new RuntimeException("Class not available in cache");
        }
    }

    /**
     * Посилання на об'єкт, який відповідний за оповіщення усіх підписчиків.
     *
     * @since 1.0.0
     */
    private final WeakReference<EventBus> eventBus;

    /**
     * Всі зареєстровані підписчики, індексовані за типом події.
     * <p>
     * Значення {@link CopyOnWriteArraySet} полегшує отримання незмінних знімків
     * всіх поточних підписчиків до події без будь-якого блокування.
     *
     * @since 1.0.0
     */
    private final ConcurrentMap<Class<?>, Set<Subscriber>> subscribers;

    /**
     * Призначений, для ініціалізації сховища записів про підписчиків.
     *
     * @param eventBus
     *            посилання на об'єкт, який відповідний за оповіщення усіх
     *            підписчиків
     * @since 1.0.0
     */
    public SubscriberRegistry(@Nonnull final EventBus eventBus) {
        this.eventBus = new WeakReference<>(checkNotNull(eventBus));
        subscribers = new ConcurrentHashMap<>();
    }

    /**
     * Призначений, для перевірки чи присутній підписчик у поточному сховищі.
     *
     * @param subscriber
     *            підписчик, для перевірки
     * @return результат перевірки
     * @since 1.0.1
     */
    public boolean contains(final Object subscriber) {
        checkNotNull(subscriber);
        final Multimap<Class<?>, Subscriber> subscriberMethods = findAllSubscribers(
                subscriber);

        for (final Map.Entry<Class<?>, Collection<Subscriber>> entry : subscriberMethods
                .asMap().entrySet()) {
            final Class<?> eventType = entry.getKey();
            for (final Subscriber subscriberEntry : subscribers
                    .get(eventType)) {
                if (subscriberEntry.getSubscriber() == subscriber) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Призначений, для пошуку усіх підписчиків (обробників) в об'єкті
     * підписчика.
     *
     * @param subscriber
     *            підписчик
     * @return перелік усіх записів про підписчиків
     * @throws AnnotationMetadataException
     *             підписчика не було відмічено анотацією {@link Subscriber},
     *             або виконавців події (методів) не було відмічено анотацією
     *             {@link Subscribe}
     * @since 1.0.0
     */
    private @Nonnull Multimap<Class<?>, Subscriber> findAllSubscribers(
            @Nonnull final Object subscriber)
            throws AnnotationMetadataException {
        final Multimap<Class<?>, Subscriber> methodsInListener = HashMultimap
                .create();

        final Class<?> subscriberType = subscriber.getClass();
        final SubscriberMetadata subscriberMetadata;
        subscriberMetadata = AnnotationMetadatas
                .newSubscriberMetadata(subscriberType);

        for (final Method method : getAnnotatedMethods(subscriberType)) {
            final Class<?>[] parameterTypes = method.getParameterTypes();
            final Class<?> eventType = parameterTypes[0];
            methodsInListener.put(eventType,
                    newSubscriberEntry(subscriber, method, subscriberMetadata));
        }
        return methodsInListener;
    }

    /**
     * Призначений, для отримання ітератору, що представляє незмінний знімок
     * усіх підписчиків даної події на час виклику цього методу.
     *
     * @param event
     *            подія
     * @return ітератор, що представляє незмінний знімок усіх підписчиків даної
     *         події на час виклику цього методу
     * @since 1.0.0
     */
    public @Nonnull Iterator<Subscriber> getSubscribers(
            @Nonnull final Object event) {
        checkNotNull(event);
        final ImmutableSet<Class<?>> eventTypes = getFlattenHierarchy(
                event.getClass());

        final List<Iterator<Subscriber>> subscribers = Lists
                .newArrayListWithCapacity(eventTypes.size());

        for (final Class<?> eventType : eventTypes) {
            final Set<Subscriber> eventSubscribers = this.subscribers
                    .get(eventType);
            if (eventSubscribers != null) {
                subscribers.add(eventSubscribers.iterator());
            }
        }

        return Iterators.concat(subscribers.iterator());
    }

    /**
     * Призначений, для створення нового запису про підписчика.
     *
     * @param subscriber
     *            підписчик
     * @param method
     *            виконавець події
     * @param subscriberMetadata
     *            об'єкте представлення вилучених даних із анотації
     *            {@link Subscriber}
     * @return запис про підписчика
     * @throws AnnotationMetadataException
     *             виконавця події (метод) не було відмічено анотацією
     *             {@link Subscribe}
     * @since 1.0.0
     */
    private @Nonnull Subscriber newSubscriberEntry(
            @Nonnull final Object subscriber, @Nonnull final Method method,
            @Nonnull final SubscriberMetadata subscriberMetadata)
            throws AnnotationMetadataException {
        SubscribeMetadata subscribeMetadata;

        subscribeMetadata = AnnotationMetadatas.newSubscribeMetadata(method);
        switch (subscriberMetadata.getReferencePolicy()) {
        case WEAK:
            return Subscribers.newWeakSubscriber(subscriber, method,
                    eventBus.get(), subscriberMetadata, subscribeMetadata);
        case SOFT:
            return Subscribers.newSoftSubscriber(subscriber, method,
                    eventBus.get(), subscriberMetadata, subscribeMetadata);
        case STRONG:
            return Subscribers.newStrongSubscriber(subscriber, method,
                    eventBus.get(), subscriberMetadata, subscribeMetadata);
        }
        throw new NullPointerException("Reference policy value is null");
    }

    /**
     * Призначений, для регестрації усіх обробників (методів) отриманого
     * підписчика.
     *
     * @param subscriber
     *            підписчик
     * @throws AnnotationMetadataException
     *             підписчика не було відмічено анотацією {@link Subscriber},
     *             або виконавців події (методів) не було відмічено анотацією
     *             {@link Subscribe}
     * @since 1.0.0
     */
    public void register(@Nonnull final Object subscriber)
            throws AnnotationMetadataException {
        checkNotNull(subscriber);
        final Multimap<Class<?>, Subscriber> subscriberMethods = findAllSubscribers(
                subscriber);

        for (final Map.Entry<Class<?>, Collection<Subscriber>> entry : subscriberMethods
                .asMap().entrySet()) {
            final Class<?> eventType = entry.getKey();
            final Collection<Subscriber> eventMethodsInSubscriber = entry
                    .getValue();

            Set<Subscriber> eventSubscribers = subscribers.get(eventType);

            if (eventSubscribers == null) {
                final CopyOnWriteArraySet<Subscriber> newSet = new CopyOnWriteArraySet<>();
                eventSubscribers = MoreObjects.firstNonNull(
                        subscribers.putIfAbsent(eventType, newSet), newSet);
            }

            eventSubscribers.addAll(eventMethodsInSubscriber);
        }
    }

    /**
     * Призначений, для видалення усіх записів про подписчиків, у яких посилання
     * дорівнює {@code null}. Виклик цього методу делегуєтьсься в методі
     * {@link EventBus#post(Object)}
     *
     * @since 1.0.0
     */
    public void removeUnusedSubscribers() {
        for (final Map.Entry<Class<?>, Set<Subscriber>> entry : subscribers
                .entrySet()) {
            for (final Subscriber subscriber : entry.getValue()) {
                if (subscriber.getSubscriber() == null) {
                    final Collection<Subscriber> subscriberMethodsForType = entry
                            .getValue();
                    subscriberMethodsForType.remove(subscriber);
                }
            }
        }
    }

    /**
     * Призначений, для вилучення усіх записів про підписчиків для отриманого
     * підписчика.
     *
     * @param subscriber
     *            підписчик
     * @since 1.0.0
     * @throws IllegalArgumentException
     *             відсутній підписчик події для анотованого методу
     */
    public void unregister(@Nonnull final Object subscriber)
            throws IllegalArgumentException {
        checkNotNull(subscriber);
        final Multimap<Class<?>, Subscriber> subscriberMethods = findAllSubscribers(
                subscriber);

        for (final Map.Entry<Class<?>, Collection<Subscriber>> entry : subscriberMethods
                .asMap().entrySet()) {
            final Class<?> eventType = entry.getKey();
            final Collection<Subscriber> subscriberMethodsForType = entry
                    .getValue();

            final Set<Subscriber> currentSubscribers = subscribers
                    .get(eventType);
            if (currentSubscribers == null || !currentSubscribers
                    .removeAll(subscriberMethodsForType)) {
                throw new IllegalArgumentException(
                        "Missing event subscriber for an annotated method");
            }
        }
    }
}
