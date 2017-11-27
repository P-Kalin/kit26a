package ua.khpi.oop.malokhvii05.common.specification;

import javax.annotation.Nonnull;

public final class Specifications {

    private Specifications() {

    }

    @SuppressWarnings("unchecked")
    public static @Nonnull <T> Specification<T> newEmptySpecification() {
        return EmptySpecification.INSTANCE;
    }

    @SuppressWarnings("unchecked")
    public static @Nonnull <T> CompositeSpecification<T> newEmptyCompositeSpecification() {
        return EmptyCompositeSpecification.INSTANCE;
    }

    public static @Nonnull <T> SpecificationPair<T> newDisjunctionSpecification(
            @Nonnull final Specification<T> specification1,
            @Nonnull final Specification<T> specification2) {
        return new DisjunctionSpecification<>(specification1, specification2);
    }

    public static @Nonnull <T> SpecificationPair<T> newConjuctionSpecification(
            @Nonnull final Specification<T> specification1,
            @Nonnull final Specification<T> specification2) {
        return new ConjunctionSpecification<>(specification1, specification2);
    }

    public static @Nonnull <T> SpecificationDecorator<T> newNegationSpecification(
            @Nonnull final Specification<T> specification) {
        return new NegationSpecification<>(specification);
    }

    @SuppressWarnings("unchecked")
    public static @Nonnull <T> Specification<T> newTrueSpecification() {
        return TrueSpecification.INSTANCE;
    }

    @SuppressWarnings("unchecked")
    public static @Nonnull <T> Specification<T> newFalseSpecification() {
        return FalseSpecification.INSTANCE;
    }

    public static @Nonnull <T> CompositeSpecification<T> newCompositeSpecification() {
        return new DefaultCompositeSpecification<>();
    }

    public static @Nonnull <T> CompositeSpecification<T> newCompositeSpecification(
            @Nonnull final Iterable<Specification<T>> specitfications) {
        return new DefaultCompositeSpecification<>(specitfications);
    }

    @SafeVarargs
    public static @Nonnull <T> CompositeSpecification<T> newCompositeSpecification(
            @Nonnull final Specification<T>... specifications) {
        return new DefaultCompositeSpecification<>(specifications);
    }
}
