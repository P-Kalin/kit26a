package ua.khpi.oop.malokhvii05.common.specification;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Predicate;

public interface Specification<T>
        extends Serializable, Iterable<Specification<T>> {

    boolean isSatisfiedBy(@Nullable T candidate);

    @Nonnull
    Specification<T> or(@Nonnull final Specification<T> specification);

    @Nonnull
    Specification<T> and(@Nonnull final Specification<T> specification);

    @Nonnull
    Specification<T> not();

    @Nonnull
    Predicate<T> toPredicate();

    @Nonnull
    List<Specification<T>> getSpecifications();
}
