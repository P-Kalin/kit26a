package ua.khpi.oop.malokhvii07.navigation.map;

import static com.google.common.base.Preconditions.checkNotNull;

import ua.khpi.oop.malokhvii07.navigation.component.connection.NavigationConnection;
import ua.khpi.oop.malokhvii07.navigation.component.marker.NavigationMarker;

public class ConfigurableMutableNavigationMap extends AbstractNavigationMap
        implements MutableNavigationMap {

    ConfigurableMutableNavigationMap(final NavigationMapBuilder builder) {
        super(builder);
    }

    @Override
    public boolean addNode(final NavigationMarker node) {
        checkNotNull(node);
        if (!navigationMarkers.containsKey(node.getId())) {
            navigationMarkers.put(node.getId(), node);
        } else {
            return false;
        }
        navigationGraph.addNode(node);
        return true;
    }

    @Override
    public NavigationConnection putEdgeValue(final NavigationMarker nodeU,
            final NavigationMarker nodeV, final NavigationConnection value) {
        checkNotNull(value);
        if (!value.getOutputNavigationMarker().equals(nodeU)
                || !value.getInputNavigationMarker().equals(nodeV)) {
            return null;
        }

        addNode(nodeU);
        addNode(nodeV);
        return navigationGraph.putEdgeValue(nodeU, nodeV, value);
    }

    @Override
    public NavigationConnection removeEdge(final NavigationMarker nodeU,
            final NavigationMarker nodeV) {
        checkNotNull(nodeU);
        checkNotNull(nodeV);

        return navigationGraph.removeEdge(nodeU, nodeV);
    }

    @Override
    public boolean removeNode(final NavigationMarker node) {
        checkNotNull(node);
        if (!navigationMarkers.containsKey(node.getId())) {
            return false;
        }

        navigationMarkers.remove(node.getId());
        navigationGraph.removeNode(node);
        return true;
    }
}
