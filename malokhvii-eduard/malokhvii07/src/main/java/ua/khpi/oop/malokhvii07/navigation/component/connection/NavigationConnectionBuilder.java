package ua.khpi.oop.malokhvii07.navigation.component.connection;

import static com.google.common.base.Preconditions.checkNotNull;

import ua.khpi.oop.malokhvii07.navigation.component.NavigationComponentBuilder;
import ua.khpi.oop.malokhvii07.navigation.component.marker.NavigationMarker;

public class NavigationConnectionBuilder implements NavigationComponentBuilder {

    public static NavigationConnectionBuilder newBuilder() {
        return new NavigationConnectionBuilder();
    }

    Double distance;
    NavigationMarker inputNavigationMarker;

    NavigationMarker outputNavigationMarker;

    @Override
    public NavigationConnection build() {
        return new NavigationConnection(this);
    }

    public NavigationConnectionBuilder setDistance(final Double distance) {
        this.distance = checkNotNull(distance);
        return this;
    }

    public NavigationConnectionBuilder setInputNavigationMarker(
            final NavigationMarker inputNavigationMarker) {
        this.inputNavigationMarker = checkNotNull(inputNavigationMarker);
        return this;
    }

    public NavigationConnectionBuilder setOutputNavigationMarker(
            final NavigationMarker outputNavigationMarker) {
        this.outputNavigationMarker = checkNotNull(outputNavigationMarker);
        return this;
    }
}
