package ua.khpi.oop.malokhvii05.common.repository;

import java.io.Serializable;

public interface Entity<K> extends Serializable {

    K getId();

    void setId(K id);
}