package ua.khpi.oop.malokhvii05.common.specification;

import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.annotation.Nullable;

final class TrueSpecification<T> extends AbstractSpecification<T> {

    private static final long serialVersionUID = -7584950563646370573L;

    @SuppressWarnings("rawtypes")
    static Specification INSTANCE = new TrueSpecification<>();

    @Override
    public boolean isSatisfiedBy(@Nullable final T candidate) {
        return true;
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
