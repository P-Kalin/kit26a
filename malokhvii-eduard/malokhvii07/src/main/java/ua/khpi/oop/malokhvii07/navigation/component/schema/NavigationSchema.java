package ua.khpi.oop.malokhvii07.navigation.component.schema;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

import ua.khpi.oop.malokhvii07.navigation.component.NavigationComponent;
import ua.khpi.oop.malokhvii07.navigation.component.connection.NavigationConnection;
import ua.khpi.oop.malokhvii07.navigation.component.marker.NavigationMarker;

@Entity
@Table(name = "navigation_schemas")
public class NavigationSchema extends NavigationComponent {

    private static final long serialVersionUID = 6311244210543293937L;

    @Column(name = "directed", nullable = false)
    private Boolean directed;

    @Column(name = "label", unique = true, nullable = false)
    private String label;

    @Column(name = "mutable", nullable = false)
    private Boolean mutable;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "navigation_connections_group",
            joinColumns = @JoinColumn(name = "schema_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "marker_id",
                    referencedColumnName = "id"))
    private List<NavigationConnection> navigationConnections;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "navigation_markers_group",
            joinColumns = @JoinColumn(name = "schema_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "marker_id",
                    referencedColumnName = "id"))
    private List<NavigationMarker> navigationMarkers;

    @Column(name = "self_loops", nullable = false)
    private Boolean selfLoops;

    public NavigationSchema() {
        navigationConnections = Lists.newArrayList();
        navigationConnections = Lists.newArrayList();
    }

    NavigationSchema(final NavigationSchemaBuilder builder) {
        checkNotNull(builder);
    }

    public Boolean getDirected() {
        return directed;
    }

    public String getLabel() {
        return label;
    }

    public Boolean getMutable() {
        return mutable;
    }

    public List<NavigationConnection> getNavigationConnections() {
        return navigationConnections;
    }

    public NavigationMarker getNavigationMarker(final int index) {
        return navigationMarkers.get(index);
    }

    public List<NavigationMarker> getNavigationMarkers() {
        return navigationMarkers;
    }

    public Boolean getSelfLoops() {
        return selfLoops;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(label, directed, mutable, selfLoops,
                navigationMarkers, navigationConnections);
    }

    public void setDirected(final Boolean directed) {
        this.directed = checkNotNull(directed);
    }

    public void setLabel(final String label) {
        this.label = label;
    }

    public void setMutable(final Boolean mutable) {
        this.mutable = checkNotNull(mutable);
    }

    public void setNavigationConnections(
            final List<NavigationConnection> navigationConnections) {
        this.navigationConnections = checkNotNull(navigationConnections);
    }

    public void setNavigationMarker(final int index,
            final NavigationMarker navigationMarker) {
        navigationMarkers.add(index, checkNotNull(navigationMarker));
    }

    public void setNavigationMarkers(
            final List<NavigationMarker> navigationMarkers) {
        this.navigationMarkers = navigationMarkers;
    }

    public void setSelfLoops(final Boolean selfLoops) {
        this.selfLoops = checkNotNull(selfLoops);
    }
}
