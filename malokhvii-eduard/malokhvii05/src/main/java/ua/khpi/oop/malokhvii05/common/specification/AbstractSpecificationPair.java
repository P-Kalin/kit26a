package ua.khpi.oop.malokhvii05.common.specification;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

public abstract class AbstractSpecificationPair<T> extends
        AbstractCompositeSpecification<T> implements SpecificationPair<T> {

    private static final long serialVersionUID = -970230615728396948L;

    protected AbstractSpecificationPair(
            @Nonnull final Specification<T> specification1,
            @Nonnull final Specification<T> specification2) {
        super(Lists.newArrayList(checkNotNull(specification1),
                checkNotNull(specification2)));
    }

    @Override
    public final @Nonnull Specification<T> getFirstSpecification() {
        return getSpecification(0);
    }

    @Override
    public final @Nonnull Specification<T> getSecondSpecification() {
        return getSpecification(1);
    }

    @Override
    @CanIgnoreReturnValue
    public boolean addSpecification(
            @Nullable final Specification<T> specification) {
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    @CanIgnoreReturnValue
    public @Nonnull Specification<T> removeSpecification(
            @Nonnegative final int index) {
        return EmptySpecification.INSTANCE;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("specification1", getFirstSpecification())
                .add("specification2", getSecondSpecification()).toString();
    }
}
