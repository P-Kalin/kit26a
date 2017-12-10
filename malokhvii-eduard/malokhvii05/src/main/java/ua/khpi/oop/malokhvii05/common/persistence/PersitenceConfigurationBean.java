package ua.khpi.oop.malokhvii05.common.persistence;

import java.util.Map;
import java.util.Properties;

public interface PersitenceConfigurationBean {

    String getPersistenceUnitName();

    Map<String, String> toMappedProperties();

    Properties toProperties();
}
