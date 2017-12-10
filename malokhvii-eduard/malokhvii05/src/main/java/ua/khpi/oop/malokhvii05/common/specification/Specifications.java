package ua.khpi.oop.malokhvii05.common.specification;

import javax.annotation.Nonnull;

public final class Specifications {

    public static @Nonnull <T> CompositeSpecification<T> newCompositeSpecification() {
        return new NotFixedCompositeSpecification<>();
    }

    public static @Nonnull <T> CompositeSpecification<T> newCompositeSpecification(
            @Nonnull final Iterable<Specification<T>> specitfications) {
        return new NotFixedCompositeSpecification<>(specitfications);
    }

    @SafeVarargs
    public static @Nonnull <T> CompositeSpecification<T> newCompositeSpecification(
            @Nonnull final Specification<T>... specifications) {
        return new NotFixedCompositeSpecification<>(specifications);
    }

    public static @Nonnull <T> Specification<T> newConjuctionSpecification(
            @Nonnull final Specification<T> specification1,
            @Nonnull final Specification<T> specification2) {
        return new ConjunctionSpecification<>(specification1, specification2);
    }

    public static @Nonnull <T> Specification<T> newDisjunctionSpecification(
            @Nonnull final Specification<T> specification1,
            @Nonnull final Specification<T> specification2) {
        return new DisjunctionSpecification<>(specification1, specification2);
    }

    @SuppressWarnings("unchecked")
    public static @Nonnull <T> Specification<T> newEmptySpecification() {
        return EmptySpecification.INSTANCE;
    }

    public static @Nonnull <T> Specification<T> newFalseSpecification() {
        return new Specification<T>() {

            @Override
            public boolean isSatisfiedBy(final T candidate) {
                return false;
            }
        };
    }

    public static @Nonnull <T> Specification<T> newNegationSpecification(
            @Nonnull final Specification<T> specification) {
        return new NegationSpecification<>(specification);
    }

    public static @Nonnull <T> Specification<T> newTrueSpecification() {
        return new Specification<T>() {

            @Override
            public boolean isSatisfiedBy(final T candidate) {
                return true;
            }
        };
    }

    private Specifications() {

    }
}
