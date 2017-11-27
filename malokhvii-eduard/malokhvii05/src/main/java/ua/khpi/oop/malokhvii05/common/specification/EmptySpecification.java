package ua.khpi.oop.malokhvii05.common.specification;

import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.annotation.Nullable;

final class EmptySpecification<T> extends AbstractSpecification<T> {

    private static final long serialVersionUID = 550748049196623572L;

    @SuppressWarnings("rawtypes")
    static Specification INSTANCE = new EmptySpecification<>();

    EmptySpecification() {

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
