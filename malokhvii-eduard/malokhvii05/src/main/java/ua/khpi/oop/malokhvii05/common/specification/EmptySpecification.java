package ua.khpi.oop.malokhvii05.common.specification;

import javax.annotation.Nullable;

final class EmptySpecification<T> implements Specification<T> {

    @SuppressWarnings("rawtypes")
    static Specification INSTANCE = new EmptySpecification<>();

    EmptySpecification() {

    }

    @Override
    public boolean isSatisfiedBy(@Nullable final T candidate) {
        return false;
    }
}
