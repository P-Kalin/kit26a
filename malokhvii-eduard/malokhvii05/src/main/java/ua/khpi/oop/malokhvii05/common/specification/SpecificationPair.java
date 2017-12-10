package ua.khpi.oop.malokhvii05.common.specification;

import javax.annotation.Nonnull;

public class SpecificationPair<T> extends FixedCompositeSpecification<T> {

    public SpecificationPair(final Specification<T> specification1,
            final Specification<T> specification2) {
        super(2, specification1, specification2);
    }

    public @Nonnull Specification<T> getFirst() {
        return get(0);
    }

    public @Nonnull Specification<T> getSecond() {
        return get(1);
    }

    public @Nonnull Specification<T> setFirst(
            final Specification<T> specification) {
        return set(0, specification);
    }

    public @Nonnull Specification<T> setSecond(
            final Specification<T> specification) {
        return set(1, specification);
    }
}
