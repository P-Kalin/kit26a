package ua.khpi.oop.malokhvii05.common.specification;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

public abstract class AbstractCompositeSpecification<T>
        extends AbstractSpecification<T> implements CompositeSpecification<T> {

    private static final long serialVersionUID = 6083340448663494760L;

    private final class AbstractCompositeSpecificationIterator
            implements CompositeSpecificationIterator<T> {

        private final Deque<Iterator<Specification<T>>> iterators;

        private AbstractCompositeSpecificationIterator(
                @Nonnull final Iterator<Specification<T>> initalIterator) {
            this.iterators = Queues.newArrayDeque();
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
        public Specification<T> next() {
            if (hasNext()) {
                final Iterator<Specification<T>> iterator = iterators.peek();
                final Specification<T> specification = iterator.next();
                iterators.addLast(specification.iterator());
                return specification;
            }

            return null;
        }
    }

    private final List<Specification<T>> specifications;

    protected AbstractCompositeSpecification() {
        this.specifications = Lists.newArrayList();
    }

    @SafeVarargs
    protected AbstractCompositeSpecification(
            @Nonnull final Specification<T>... specifications) {
        this.specifications = Lists.newArrayList(checkNotNull(specifications));
    }

    protected AbstractCompositeSpecification(
            @Nonnull final Iterable<Specification<T>> specifications) {
        this.specifications = Lists.newArrayList(checkNotNull(specifications));
    }

    @Override
    public @Nonnull final List<Specification<T>> getSpecifications() {
        return specifications;
    }

    @Override
    public final @Nonnull Set<Specification<T>> getUnsatisfiedSpecifications(
            @Nullable final T candidate) {
        final Set<Specification<T>> unsatisfiedSpecifications = Sets
                .newHashSet();
        for (final Specification<T> specification : getSpecifications()) {
            if (!specification.isSatisfiedBy(candidate)) {
                unsatisfiedSpecifications.add(specification);
            }
        }

        return Collections.unmodifiableSet(unsatisfiedSpecifications);
    }

    @Override
    public final @Nonnull Set<Specification<T>> getSatisfiedSpecifications(
            @Nullable final T candidate) {
        final Set<Specification<T>> satisfiedSpecifications = Sets.newHashSet();
        for (final Specification<T> specification : getSpecifications()) {
            if (specification.isSatisfiedBy(candidate)) {
                satisfiedSpecifications.add(specification);
            }
        }

        return Collections.unmodifiableSet(satisfiedSpecifications);
    }

    @Override
    public @Nonnull Specification<T> getSpecification(
            @Nonnegative final int index) {
        return specifications.get(index);
    }

    @Override
    public @Nonnull Iterator<Specification<T>> iterator() {
        return new AbstractCompositeSpecificationIterator(
                specifications.iterator());
    }

    @Override
    @CanIgnoreReturnValue
    public @Nonnull Specification<T> removeSpecification(
            @Nonnegative final int index) {
        return specifications.remove(index);
    }

    @Override
    @CanIgnoreReturnValue
    public boolean addSpecification(
            @Nonnull final Specification<T> specification) {
        return specifications.add(specification);
    }

    @Override
    public @Nonnull String toString() {
        final ToStringHelper toStringHelper = MoreObjects.toStringHelper(this);
        final int index = 0;
        for (final Specification<T> specification : this) {
            toStringHelper.add(Integer.toString(index), specification);
        }

        return toStringHelper.toString();
    }

    @Override
    public boolean isSatisfiedBy(@Nullable final T candidate) {
        for (final Specification<T> specification : specifications) {
            if (!specification.isSatisfiedBy(candidate)) {
                return false;
            }
        }
        return true;
    }
}
