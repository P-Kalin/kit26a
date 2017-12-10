package ua.khpi.oop.malokhvii05.common.specification;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.google.common.collect.Lists;

public class FixedCompositeSpecification<T>
        implements CompositeSpecification<T> {

    private final List<Specification<T>> specifications;
    private final int fixedSize;

    public FixedCompositeSpecification(@Nonnegative int fixedSize) {
        checkArgument(fixedSize > 0);
        this.fixedSize = fixedSize;
        this.specifications = Lists.newArrayListWithExpectedSize(fixedSize);
    }

    @SafeVarargs
    public FixedCompositeSpecification(@Nonnegative int fixedSize,
            @Nonnull Specification<T>... specifications) {
        this(fixedSize);
        for (final Specification<T> specification : specifications) {
            add(specification);
        }
    }

    public FixedCompositeSpecification(@Nonnegative int fixedSize,
            @Nonnull Iterable<Specification<T>> specifications) {
        this(fixedSize);
        for (final Specification<T> specification : specifications) {
            add(specification);
        }
    }

    public @Nonnegative int getFixedSize() {
        return fixedSize;
    }

    @Override
    public boolean add(@Nonnull Specification<T> specification) {
        if (specifications.size() < fixedSize) {
            return specifications.add(checkNotNull(specification));
        }

        return false;
    }

    @Override
    public List<Specification<T>> getSpecifications() {
        return specifications;
    }
}
