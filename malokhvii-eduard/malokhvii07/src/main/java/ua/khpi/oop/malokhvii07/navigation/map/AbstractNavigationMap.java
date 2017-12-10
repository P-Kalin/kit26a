package ua.khpi.oop.malokhvii07.navigation.map;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.google.common.graph.ElementOrder;
import com.google.common.graph.EndpointPair;
import com.google.common.graph.Graph;
import com.google.common.graph.MutableValueGraph;

import ua.khpi.oop.malokhvii07.navigation.component.connection.NavigationConnection;
import ua.khpi.oop.malokhvii07.navigation.component.marker.NavigationMarker;
import ua.khpi.oop.malokhvii07.navigation.component.schema.NavigationSchema;
import ua.khpi.oop.malokhvii07.navigation.component.schema.NavigationSchemaBuilder;

public abstract class AbstractNavigationMap implements NavigationMap {

    protected Map<UUID, NavigationConnection> navigationConnections;
    protected MutableValueGraph<NavigationMarker, NavigationConnection> navigationGraph;

    protected Map<UUID, NavigationMarker> navigationMarkers;

    protected AbstractNavigationMap(final NavigationMapBuilder builder) {
        checkNotNull(builder);
        navigationMarkers = builder.navigationMarkers;
        navigationConnections = builder.navigationConnections;
        navigationGraph = builder.navigationGraph;
    }

    @Override
    public Set<NavigationMarker> adjacentNodes(final NavigationMarker node) {
        return navigationGraph.adjacentNodes(checkNotNull(node));
    }

    @Override
    public boolean allowsSelfLoops() {
        return navigationGraph.allowsSelfLoops();
    }

    @Override
    public Graph<NavigationMarker> asGraph() {
        return navigationGraph.asGraph();
    }

    @Override
    public int degree(final NavigationMarker node) {
        return navigationGraph.degree(checkNotNull(node));
    }

    @Override
    public Set<EndpointPair<NavigationMarker>> edges() {
        return Collections.unmodifiableSet(navigationGraph.edges());
    }

    @Override
    public Optional<NavigationConnection> edgeValue(
            final NavigationMarker uNode, final NavigationMarker vNode) {
        return navigationGraph.edgeValue(checkNotNull(uNode),
                checkNotNull(vNode));
    }

    @Override
    public NavigationConnection edgeValueOrDefault(final NavigationMarker uNode,
            final NavigationMarker vNode,
            final NavigationConnection defaultValue) {
        return navigationGraph.edgeValueOrDefault(checkNotNull(uNode),
                checkNotNull(vNode), defaultValue);
    }

    @Override
    public boolean hasEdgeConnecting(final NavigationMarker uNode,
            final NavigationMarker vNode) {
        return navigationGraph.hasEdgeConnecting(checkNotNull(uNode),
                checkNotNull(vNode));
    }

    @Override
    public int inDegree(final NavigationMarker node) {
        return navigationGraph.inDegree(checkNotNull(node));
    }

    @Override
    public boolean isDirected() {
        return navigationGraph.isDirected();
    }

    @Override
    public ElementOrder<NavigationMarker> nodeOrder() {
        return navigationGraph.nodeOrder();
    }

    @Override
    public Set<NavigationMarker> nodes() {
        return navigationGraph.nodes();
    }

    @Override
    public int outDegree(final NavigationMarker node) {
        return navigationGraph.outDegree(checkNotNull(node));
    }

    @Override
    public Set<NavigationMarker> predecessors(
            final NavigationMarker navigationMarker) {
        return navigationGraph.predecessors(checkNotNull(navigationMarker));
    }

    @Override
    public Set<NavigationMarker> successors(final NavigationMarker node) {
        return navigationGraph.successors(checkNotNull(node));
    }

    @Override
    public NavigationSchema toNavigationSchema() {
        final NavigationSchemaBuilder builder = NavigationSchemaBuilder
                .newBuilder().setNavigationMarkers(nodes());
        for (final EndpointPair<NavigationMarker> edge : edges()) {
            builder.setNavigationConnection(navigationGraph
                    .edgeValue(edge.source(), edge.target()).get());
        }
        return builder.build();
    }
}
