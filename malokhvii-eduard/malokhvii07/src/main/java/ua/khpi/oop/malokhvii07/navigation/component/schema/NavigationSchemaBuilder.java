package ua.khpi.oop.malokhvii07.navigation.component.schema;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.google.common.collect.Lists;

import ua.khpi.oop.malokhvii07.navigation.component.NavigationComponentBuilder;
import ua.khpi.oop.malokhvii07.navigation.component.connection.NavigationConnection;
import ua.khpi.oop.malokhvii07.navigation.component.marker.NavigationMarker;

public class NavigationSchemaBuilder implements NavigationComponentBuilder {

    public static NavigationSchemaBuilder newBuilder() {
        return new NavigationSchemaBuilder();
    }

    List<NavigationConnection> navigationConnections;

    List<NavigationMarker> navigationMarkers;

    private NavigationSchemaBuilder() {
        navigationMarkers = Lists.newArrayList();
        navigationConnections = Lists.newArrayList();
    }

    @Override
    public NavigationSchema build() {
        return new NavigationSchema(this);
    }

    public NavigationSchemaBuilder setNavigationConnection(
            final NavigationConnection navigationConnection) {
        navigationConnections.add(checkNotNull(navigationConnection));
        return this;
    }

    public NavigationSchemaBuilder setNavigationConnections(
            final Iterable<NavigationConnection> navigationConnections) {
        for (final NavigationConnection navigationConnection : navigationConnections) {
            setNavigationConnection(navigationConnection);
        }
        return this;
    }

    public NavigationSchemaBuilder setNavigationMarker(
            final NavigationMarker navigationMarker) {
        navigationMarkers.add(checkNotNull(navigationMarker));
        return this;
    }

    public NavigationSchemaBuilder setNavigationMarkers(
            final Iterable<NavigationMarker> navigationMarkers) {
        for (final NavigationMarker navigationMarker : navigationMarkers) {
            setNavigationMarker(navigationMarker);
        }
        return this;
    }
}
