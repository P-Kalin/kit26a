package ua.khpi.oop.malokhvii05.common.specification;

import javax.annotation.Nonnull;

public interface SpecificationDecorator<T> extends Specification<T> {

    @Nonnull
    Specification<T> getSpecification();
}
