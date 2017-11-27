package ua.khpi.oop.malokhvii05.common.specification;

import javax.annotation.Nonnull;

final class DefaultCompositeSpecification<T>
        extends AbstractCompositeSpecification<T> {

    private static final long serialVersionUID = -1896263182977788514L;

    DefaultCompositeSpecification() {

    }

    DefaultCompositeSpecification(
            @Nonnull final Iterable<Specification<T>> specifications) {
        super(specifications);
    }

    @SafeVarargs
    DefaultCompositeSpecification(
            @Nonnull final Specification<T>... specifications) {
        super(specifications);
    }
}
