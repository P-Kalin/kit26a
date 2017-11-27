package ua.khpi.oop.malokhvii05.common.specification;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

final class NegationSpecification<T> extends AbstractSpecificationDecorator<T> {

    private static final long serialVersionUID = 4045950631078403344L;

    NegationSpecification(@Nonnull final Specification<T> specification) {
        super(specification);
    }

    @Override
    public boolean isSatisfiedBy(@Nullable final T candidate) {
        return !super.isSatisfiedBy(candidate);
    }
}
