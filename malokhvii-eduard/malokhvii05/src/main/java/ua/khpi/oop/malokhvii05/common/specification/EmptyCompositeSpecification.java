package ua.khpi.oop.malokhvii05.common.specification;

import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

final class EmptyCompositeSpecification<T> extends AbstractSpecification<T>
        implements CompositeSpecification<T> {

    private static final long serialVersionUID = 4476817164012730086L;

    @SuppressWarnings("rawtypes")
    static CompositeSpecification INSTANCE = new EmptyCompositeSpecification<>();

    EmptyCompositeSpecification() {

    }

    @Override
    public @Nonnull Set<Specification<T>> getUnsatisfiedSpecifications(
            @Nullable final T candidate) {
        return Collections.emptySet();
    }

    @Override
    public @Nonnull Set<Specification<T>> getSatisfiedSpecifications(
            @Nullable final T candidate) {
        return Collections.emptySet();
    }

    @SuppressWarnings("unchecked")
    @Override
    public @Nonnull Specification<T> getSpecification(
            @Nonnegative final int index) {
        return EmptySpecification.INSTANCE;
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
    public boolean isSatisfiedBy(@Nullable final T candidate) {
        return false;
    }

    private void writeObject(
            @Nullable final ObjectOutputStream objectOutputStream)
            throws NotSerializableException {
        throw new NotSerializableException();
    }

    private void readObject(@Nullable final ObjectInputStream objectInputStream)
            throws NotSerializableException {
        throw new NotSerializableException();
    }
}
