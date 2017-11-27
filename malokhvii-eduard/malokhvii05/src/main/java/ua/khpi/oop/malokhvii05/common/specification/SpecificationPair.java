package ua.khpi.oop.malokhvii05.common.specification;

import javax.annotation.Nonnull;

public interface SpecificationPair<T> extends CompositeSpecification<T> {

    @Nonnull
    Specification<T> getFirstSpecification();

    @Nonnull
    Specification<T> getSecondSpecification();
}
