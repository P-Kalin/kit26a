package ua.khpi.oop.malokhvii07.navigation.component;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

import ua.khpi.oop.malokhvii05.common.repository.Entity;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class NavigationComponent implements Entity<UUID> {

    private static final long serialVersionUID = 6557143085130834575L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false, updatable = false,
            columnDefinition = "BINARY(16)")
    private UUID id;

    @Version
    @Column(nullable = false)
    private Integer version;

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        return hashCode() == obj.hashCode();
    }

    @Override
    public UUID getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    @Override
    public void setId(final UUID id) {
        this.id = checkNotNull(id);
    }

    public void setVersion(final Integer version) {
        this.version = version;
    }
}
