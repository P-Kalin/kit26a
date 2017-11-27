package ua.khpi.oop.malokhvii05.common.specification;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;

public abstract class AbstractSpecificationDecorator<T>
        extends AbstractSpecification<T> implements SpecificationDecorator<T> {

    private static final long serialVersionUID = -6812822427325802523L;

    private final Specification<T> specification;

    protected AbstractSpecificationDecorator(
            @Nonnull final Specification<T> specification) {
        this.specification = checkNotNull(specification);
    }

    @Override
    public final @Nonnull Specification<T> getSpecification() {
        return specification;
    }

    @Override
    public boolean isSatisfiedBy(@Nullable final T candidate) {
        return specification.isSatisfiedBy(candidate);
    }

    @Override
    public @Nonnull String toString() {
        return MoreObjects.toStringHelper(this)
                .add("specification", specification).toString();
    }
}
