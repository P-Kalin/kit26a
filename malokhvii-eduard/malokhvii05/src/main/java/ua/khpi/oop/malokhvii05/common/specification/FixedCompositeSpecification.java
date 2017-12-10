package ua.khpi.oop.malokhvii05.common.specification;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.google.common.collect.Lists;

public class FixedCompositeSpecification<T>
        implements CompositeSpecification<T> {

    private final int fixedSize;
    private final List<Specification<T>> specifications;

    public FixedCompositeSpecification(@Nonnegative final int fixedSize) {
        checkArgument(fixedSize > 0);
        this.fixedSize = fixedSize;
        this.specifications = Lists.newArrayListWithExpectedSize(fixedSize);
    }

    public FixedCompositeSpecification(@Nonnegative final int fixedSize,
            @Nonnull final Iterable<Specification<T>> specifications) {
        this(fixedSize);
        for (final Specification<T> specification : specifications) {
            add(specification);
        }
    }

    @SafeVarargs
    public FixedCompositeSpecification(@Nonnegative final int fixedSize,
            @Nonnull final Specification<T>... specifications) {
        this(fixedSize);
        for (final Specification<T> specification : specifications) {
            add(specification);
        }
    }

    @Override
    public boolean add(@Nonnull final Specification<T> specification) {
        if (specifications.size() < fixedSize) {
            return specifications.add(checkNotNull(specification));
        }

        return false;
    }

    public @Nonnegative int getFixedSize() {
        return fixedSize;
    }

    @Override
    public List<Specification<T>> getSpecifications() {
        return specifications;
    }
}
