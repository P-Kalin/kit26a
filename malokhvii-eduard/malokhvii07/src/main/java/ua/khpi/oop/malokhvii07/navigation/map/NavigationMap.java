package ua.khpi.oop.malokhvii07.navigation.map;

import com.google.common.graph.ValueGraph;

import ua.khpi.oop.malokhvii07.navigation.component.connection.NavigationConnection;
import ua.khpi.oop.malokhvii07.navigation.component.marker.NavigationMarker;
import ua.khpi.oop.malokhvii07.navigation.component.schema.NavigationSchema;

public interface NavigationMap
        extends ValueGraph<NavigationMarker, NavigationConnection> {

    NavigationSchema toNavigationSchema();
}
