package ua.khpi.oop.malokhvii07.navigation.map;

import com.google.common.graph.MutableValueGraph;

import ua.khpi.oop.malokhvii07.navigation.component.connection.NavigationConnection;
import ua.khpi.oop.malokhvii07.navigation.component.marker.NavigationMarker;

public interface MutableNavigationMap
        extends MutableValueGraph<NavigationMarker, NavigationConnection>,
        NavigationMap {

}
