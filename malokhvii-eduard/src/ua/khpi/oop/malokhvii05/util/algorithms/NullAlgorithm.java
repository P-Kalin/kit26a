package ua.khpi.oop.malokhvii05.util.algorithms;

public abstract class NullAlgorithm<T> implements Algorithm<T> {

    @Override
    public boolean isNull() {
        return true;
    }
}
