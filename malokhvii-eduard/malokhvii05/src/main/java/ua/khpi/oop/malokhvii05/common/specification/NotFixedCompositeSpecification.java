package ua.khpi.oop.malokhvii05.common.specification;

import java.util.List;

import com.google.common.collect.Lists;

public class NotFixedCompositeSpecification<T>
        implements CompositeSpecification<T> {

    private List<Specification<T>> specifications;

    public NotFixedCompositeSpecification() {
        this.specifications = Lists.newArrayList();
    }

    @SafeVarargs
    public NotFixedCompositeSpecification(Specification<T>... specifications) {
        this();
        for (final Specification<T> specification : specifications) {
            add(specification);
        }
    }

    public NotFixedCompositeSpecification(
            Iterable<Specification<T>> specifications) {
        this();
        for (final Specification<T> specification : specifications) {
            add(specification);
        }
    }

    @Override
    public List<Specification<T>> getSpecifications() {
        return specifications;
    }
}
