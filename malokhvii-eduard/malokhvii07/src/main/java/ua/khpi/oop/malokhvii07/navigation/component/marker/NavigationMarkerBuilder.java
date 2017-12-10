package ua.khpi.oop.malokhvii07.navigation.component.marker;

import static com.google.common.base.Preconditions.checkNotNull;

import ua.khpi.oop.malokhvii07.navigation.component.NavigationComponentBuilder;

public class NavigationMarkerBuilder implements NavigationComponentBuilder {

    public static NavigationMarkerBuilder newBuilder() {
        return new NavigationMarkerBuilder();
    }

    String label;
    Double latitude;

    Double longitude;

    @Override
    public NavigationMarker build() {
        return new NavigationMarker(this);
    }

    public NavigationMarkerBuilder setLabel(final String label) {
        this.label = checkNotNull(label);
        return this;
    }

    public NavigationMarkerBuilder setLatitude(final Double latitude) {
        this.latitude = checkNotNull(latitude);
        return this;
    }

    public NavigationMarkerBuilder setLongitude(final Double longitude) {
        this.longitude = checkNotNull(longitude);
        return this;
    }
}
