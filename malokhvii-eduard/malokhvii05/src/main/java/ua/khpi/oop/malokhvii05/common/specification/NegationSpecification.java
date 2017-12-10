package ua.khpi.oop.malokhvii05.common.specification;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

final class NegationSpecification<T> implements Specification<T> {

    private final Specification<T> specification;

    NegationSpecification(@Nonnull final Specification<T> specification) {
        this.specification = checkNotNull(specification);
    }

    @Override
    public boolean isSatisfiedBy(@Nullable final T candidate) {
        return !specification.isSatisfiedBy(candidate);
    }
}
