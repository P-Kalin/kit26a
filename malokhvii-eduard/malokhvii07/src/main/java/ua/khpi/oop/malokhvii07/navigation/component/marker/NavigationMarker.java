package ua.khpi.oop.malokhvii07.navigation.component.marker;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.google.common.base.Objects;

import ua.khpi.oop.malokhvii07.navigation.component.NavigationComponent;

@Entity
@Table(name = "navigation_markers")
public class NavigationMarker extends NavigationComponent {

    private static final long serialVersionUID = -9057007197688777102L;

    @Column(name = "label", unique = true, nullable = false)
    private String label;

    @Column(name = "lattitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    public NavigationMarker() {

    }

    NavigationMarker(final NavigationMarkerBuilder builder) {
        checkNotNull(builder);
        setLongitude(builder.longitude);
        setLatitude(builder.latitude);
        setLabel(builder.label);
    }

    public String getLabel() {
        return label;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(label, latitude, longitude);
    }

    public void setLabel(final String label) {
        this.label = checkNotNull(label);
    }

    public void setLatitude(final Double latitude) {
        this.latitude = checkNotNull(latitude);
    }

    public void setLongitude(final Double longitude) {
        this.longitude = checkNotNull(longitude);
    }
}
