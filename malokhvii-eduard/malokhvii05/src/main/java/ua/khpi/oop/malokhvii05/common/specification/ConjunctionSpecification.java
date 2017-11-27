package ua.khpi.oop.malokhvii05.common.specification;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

final class ConjunctionSpecification<T> extends AbstractSpecificationPair<T> {

    private static final long serialVersionUID = -5411010535540982689L;

    ConjunctionSpecification(@Nonnull Specification<T> firstSpecification,
            @Nonnull final Specification<T> secondSpecification) {
        super(firstSpecification, secondSpecification);
    }

    @Override
    public boolean isSatisfiedBy(@Nullable final T candidate) {
        return getFirstSpecification().isSatisfiedBy(candidate)
                && getSecondSpecification().isSatisfiedBy(candidate);
    }
}
