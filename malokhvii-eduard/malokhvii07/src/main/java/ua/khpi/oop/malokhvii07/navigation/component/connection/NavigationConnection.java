package ua.khpi.oop.malokhvii07.navigation.component.connection;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.common.base.Objects;

import ua.khpi.oop.malokhvii07.navigation.component.NavigationComponent;
import ua.khpi.oop.malokhvii07.navigation.component.marker.NavigationMarker;

@Entity
@Table(name = "navigation_connections")
public class NavigationConnection extends NavigationComponent {

    private static final long serialVersionUID = -2670928471019108376L;

    @Column(name = "distance", nullable = false)
    private Double distance;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "input_navigation_marker", nullable = false)
    private NavigationMarker inputNavigationMarker;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "output_navigation_marker", nullable = false)
    private NavigationMarker outputNavigationMarker;

    public NavigationConnection() {

    }

    NavigationConnection(final NavigationConnectionBuilder builder) {
        checkNotNull(builder);
        setOutputNavigationMarker(builder.outputNavigationMarker);
        setInputNavigationMarker(builder.inputNavigationMarker);
        setDistance(builder.distance);
    }

    public Double getDistance() {
        return distance;
    }

    public NavigationMarker getInputNavigationMarker() {
        return inputNavigationMarker;
    }

    public NavigationMarker getOutputNavigationMarker() {
        return outputNavigationMarker;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(distance, outputNavigationMarker,
                inputNavigationMarker);
    }

    public void setDistance(final Double distance) {
        this.distance = checkNotNull(distance);
    }

    public void setInputNavigationMarker(
            final NavigationMarker inputNavigationMarker) {
        this.inputNavigationMarker = checkNotNull(inputNavigationMarker);
    }

    public void setOutputNavigationMarker(
            final NavigationMarker outputNavigationMarker) {
        this.outputNavigationMarker = checkNotNull(outputNavigationMarker);
    }
}
