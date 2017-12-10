package ua.khpi.oop.malokhvii07.navigation.map;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;
import java.util.UUID;

import com.google.common.collect.Maps;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;

import ua.khpi.oop.malokhvii07.navigation.component.connection.NavigationConnection;
import ua.khpi.oop.malokhvii07.navigation.component.marker.NavigationMarker;
import ua.khpi.oop.malokhvii07.navigation.component.schema.NavigationSchema;

public class NavigationMapBuilder {

    public static NavigationMapBuilder newBuilder() {
        return new NavigationMapBuilder();
    }

    boolean directed;
    boolean mutable;
    Map<UUID, NavigationConnection> navigationConnections;
    MutableValueGraph<NavigationMarker, NavigationConnection> navigationGraph;
    Map<UUID, NavigationMarker> navigationMarkers;

    boolean selfLoops;

    private NavigationMapBuilder() {
        navigationConnections = Maps.newHashMap();
        navigationMarkers = Maps.newHashMap();
    }

    public NavigationMapBuilder allowSelfLoops() {
        selfLoops = true;
        return this;
    }

    public NavigationMap build() {
        navigationGraph = buildNavigationGraph();
        if (mutable) {
            return new ConfigurableMutableNavigationMap(this);
        }
        return new ConfigurableNavigationMap(this);
    }

    private MutableValueGraph<NavigationMarker, NavigationConnection> buildNavigationGraph() {
        final MutableValueGraph<NavigationMarker, NavigationConnection> navigationGraph;
        if (directed) {
            navigationGraph = ValueGraphBuilder.directed()
                    .allowsSelfLoops(selfLoops).build();
        } else {
            navigationGraph = ValueGraphBuilder.undirected()
                    .allowsSelfLoops(selfLoops).build();
        }

        for (final NavigationMarker navigationMarker : navigationMarkers
                .values()) {
            navigationGraph.addNode(navigationMarker);
        }

        for (final NavigationConnection navigationConnection : navigationConnections
                .values()) {

            navigationGraph.putEdgeValue(
                    navigationConnection.getOutputNavigationMarker(),
                    navigationConnection.getInputNavigationMarker(),
                    navigationConnection);
        }
        return navigationGraph;
    }

    public NavigationMapBuilder directed() {
        directed = true;
        return this;
    }

    public NavigationMapBuilder immutable() {
        mutable = false;
        return this;
    }

    public NavigationMapBuilder mutable() {
        mutable = true;
        return this;
    }

    public NavigationMapBuilder setNavigationConnection(
            final NavigationConnection navigationConnection) {
        checkNotNull(navigationConnection);

        final NavigationMarker outputNavigationMarker = navigationConnection
                .getOutputNavigationMarker();
        checkNotNull(outputNavigationMarker);
        if (!navigationMarkers.containsKey(outputNavigationMarker.getId())) {
            setNavigationMarker(outputNavigationMarker);
        }

        final NavigationMarker inputNavigationMarker = navigationConnection
                .getInputNavigationMarker();
        checkNotNull(inputNavigationMarker);
        if (!navigationMarkers.containsKey(inputNavigationMarker.getId())) {
            setNavigationMarker(inputNavigationMarker);
        }

        navigationConnections.put(navigationConnection.getId(),
                navigationConnection);
        return this;
    }

    public NavigationMapBuilder setNavigationConnections(
            final Iterable<NavigationConnection> navigationConnections) {
        checkNotNull(navigationConnections);
        navigationConnections.forEach(this::setNavigationConnection);
        return this;
    }

    public NavigationMapBuilder setNavigationMarker(
            final NavigationMarker navigationMarker) {
        checkNotNull(navigationMarker);
        navigationMarkers.put(navigationMarker.getId(), navigationMarker);
        return this;
    }

    public NavigationMapBuilder setNavigationMarkers(
            final Iterable<NavigationMarker> navigationMarkers) {
        checkNotNull(navigationMarkers);
        navigationMarkers.forEach(this::setNavigationMarker);
        return this;
    }

    public NavigationMapBuilder setNavigationSchema(
            final NavigationSchema navigationSchema) {
        checkNotNull(navigationSchema);
        setNavigationMarkers(navigationSchema.getNavigationMarkers());
        setNavigationConnections(navigationSchema.getNavigationConnections());

        mutable = navigationSchema.getMutable();
        directed = navigationSchema.getDirected();
        selfLoops = navigationSchema.getSelfLoops();
        return this;
    }
}
