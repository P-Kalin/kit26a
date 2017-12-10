package ua.khpi.oop.malokhvii05.common.persistence;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.Maps;

@XmlRootElement(name = "hibernate.configuration")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class HibernateConfigurationBean implements PersitenceConfigurationBean {

    public class Builder {

        private Builder() {

        }

        public HibernateConfigurationBean build() {
            return HibernateConfigurationBean.this;
        }

        public Builder serPersistenceUnitName(
                final String persistenceUnitName) {
            HibernateConfigurationBean.this.persistenceUnitName = checkNotNull(
                    persistenceUnitName);
            return this;
        }

        public Builder setFormatSqlAvailable(final boolean formatSqlAvailable) {
            HibernateConfigurationBean.this.formatSqlAvailable = formatSqlAvailable;
            return this;
        }

        public Builder setHbm2dll(final String mode) {
            hbm2dll = checkNotNull(mode);
            return this;
        }

        public Builder setPassword(final String password) {
            HibernateConfigurationBean.this.password = checkNotNull(password);
            return this;
        }

        public Builder setSqlShowAvailable(final boolean sqlShowAvailable) {
            HibernateConfigurationBean.this.sqlShowAvailable = sqlShowAvailable;
            return this;
        }

        public Builder setUrl(final String url) {
            HibernateConfigurationBean.this.url = checkNotNull(url);
            return this;
        }

        public Builder setUsername(final String username) {
            HibernateConfigurationBean.this.username = checkNotNull(username);
            return this;
        }
    }

    public static HibernateConfigurationBean load(final String path)
            throws JAXBException {
        checkNotNull(path);

        final JAXBContext jaxbContext = JAXBContext
                .newInstance(HibernateConfigurationBean.class);
        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        return (HibernateConfigurationBean) unmarshaller
                .unmarshal(new File(path));
    }

    public static Builder newBuilder() {
        return new HibernateConfigurationBean().new Builder();
    }

    public static void save(final HibernateConfigurationBean configurationBean,
            final String path) throws JAXBException {
        checkNotNull(configurationBean);
        checkNotNull(path);

        final JAXBContext jaxbContext = JAXBContext
                .newInstance(HibernateConfigurationBean.class);
        final Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(configurationBean, new File(path));
    }

    @XmlElement(name = "hibernate.format_sql")
    private boolean formatSqlAvailable = true;

    @XmlElement(name = "hibernate.hbm2dll.auto")
    private String hbm2dll = "create";

    @XmlElement(name = "hibernate.connection.password")
    private String password = "";

    @XmlAttribute(name = "persistence_unit")
    private String persistenceUnitName;

    @XmlElement(name = "hibernate.show_sql")
    private boolean sqlShowAvailable = true;

    @XmlElement(name = "hibernate.connection.url")
    private String url;

    @XmlElement(name = "hibernate.connection.username")
    private String username = "";

    private HibernateConfigurationBean() {

    }

    public String getHbm2dll() {
        return hbm2dll;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getPersistenceUnitName() {
        return persistenceUnitName;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public boolean isFormatSqlAvailable() {
        return formatSqlAvailable;
    }

    public boolean isSqlShowAvailable() {
        return sqlShowAvailable;
    }

    public void setFormatSqlAvailable(final boolean formatSqlAvailable) {
        this.formatSqlAvailable = formatSqlAvailable;
    }

    @Override
    public Map<String, String> toMappedProperties() {
        final Map<String, String> mappedProperties = Maps.newHashMap();
        mappedProperties.put("hibernate.hbm2ddl.auto", hbm2dll);
        mappedProperties.put("javax.persistence.jdbc.url", url);

        mappedProperties.put("hibernate.format_sql",
                Boolean.toString(formatSqlAvailable));
        mappedProperties.put("hibernate.show_sql",
                Boolean.toString(sqlShowAvailable));

        mappedProperties.put("javax.persistence.jdbc.user", username);
        mappedProperties.put("javax.persistence.jdbc.password", password);

        return mappedProperties;
    }

    @Override
    public Properties toProperties() {
        final Properties properties = new Properties();
        properties.putAll(toMappedProperties());
        return properties;
    }
}
