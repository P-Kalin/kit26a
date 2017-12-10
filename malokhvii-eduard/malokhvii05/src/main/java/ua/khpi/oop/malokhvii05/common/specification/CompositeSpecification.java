package ua.khpi.oop.malokhvii05.common.specification;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Deque;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.Queues;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

public interface CompositeSpecification<T> extends Specification<T> {

    static class CompositeSpecificationIterator<T>
            implements Iterator<Specification<T>> {

        private final Deque<Iterator<Specification<T>>> iterators;

        private CompositeSpecificationIterator(
                @Nonnull final Iterator<Specification<T>> initalIterator) {
            iterators = Queues.newArrayDeque();
            iterators.push(checkNotNull(checkNotNull(initalIterator)));
        }

        @Override
        public boolean hasNext() {
            if (iterators.isEmpty()) {
                return false;
            } else {
                final Iterator<Specification<T>> iterator = iterators.peek();

                if (!iterator.hasNext()) {
                    iterators.pop();
                    return hasNext();
                }

                return true;
            }
        }

        @Override
        @CanIgnoreReturnValue
        public @Nonnull Specification<T> next() {
            if (hasNext()) {
                final Iterator<Specification<T>> iterator = iterators.peek();
                final Specification<T> specification = iterator.next();
                iterators.addLast(specification.iterator());
                return specification;
            }

            return null;
        }
    }

    default @Nonnull Set<Specification<T>> getUnsatisfied(
            @Nullable T candidate) {
        return getSpecifications().stream().filter(
                specification -> !specification.isSatisfiedBy(candidate))
                .collect(Collectors.toSet());
    }

    default @Nonnull Set<Specification<T>> getSatisfied(@Nullable T candidate) {
        return getSpecifications().stream()
                .filter(specification -> specification.isSatisfiedBy(candidate))
                .collect(Collectors.toSet());
    }

    default @Nullable Specification<T> get(@Nonnegative int index) {
        return getSpecifications().get(index);
    }

    @CanIgnoreReturnValue
    default @Nullable Specification<T> set(@Nonnegative int index,
            @Nullable Specification<T> specification) {
        return getSpecifications().set(index, specification);
    }

    @CanIgnoreReturnValue
    default boolean add(@Nonnull Specification<T> specification) {
        return getSpecifications().add(checkNotNull(specification));
    }

    @CanIgnoreReturnValue
    default @Nullable Specification<T> remove(@Nonnegative int index) {
        return getSpecifications().remove(index);
    }

    @Override
    default @Nonnull Iterator<Specification<T>> iterator() {
        return new CompositeSpecificationIterator<>(
                getSpecifications().iterator());
    }

    @Override
    default boolean isSatisfiedBy(T candidate) {
        for (final Specification<T> specification : getSpecifications()) {
            if (!specification.isSatisfiedBy(candidate)) {
                return false;
            }
        }

        return true;
    }
}
