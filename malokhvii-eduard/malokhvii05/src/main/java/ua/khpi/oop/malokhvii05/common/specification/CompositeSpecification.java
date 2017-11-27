package ua.khpi.oop.malokhvii05.common.specification;

import java.util.Iterator;
import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

public interface CompositeSpecification<T> extends Specification<T> {

    interface CompositeSpecificationIterator<T>
            extends Iterator<Specification<T>> {

    }

    @Nonnull
    Set<Specification<T>> getUnsatisfiedSpecifications(@Nullable T candidate);

    @Nonnull
    Set<Specification<T>> getSatisfiedSpecifications(@Nullable T candidate);

    @Nullable
    Specification<T> getSpecification(@Nonnegative int index);

    @CanIgnoreReturnValue
    boolean addSpecification(@Nonnull Specification<T> specification);

    @CanIgnoreReturnValue
    @Nullable
    Specification<T> removeSpecification(@Nonnegative int index);
}
