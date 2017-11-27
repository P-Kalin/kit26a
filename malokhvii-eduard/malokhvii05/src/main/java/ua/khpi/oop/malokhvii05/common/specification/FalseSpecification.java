package ua.khpi.oop.malokhvii05.common.specification;

import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.annotation.Nullable;

final class FalseSpecification<T> extends AbstractSpecification<T> {

    private static final long serialVersionUID = 6716434692814601251L;

    @SuppressWarnings("rawtypes")
    static Specification INSTANCE = new FalseSpecification<>();

    @Override
    public boolean isSatisfiedBy(@Nullable T candidate) {
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
