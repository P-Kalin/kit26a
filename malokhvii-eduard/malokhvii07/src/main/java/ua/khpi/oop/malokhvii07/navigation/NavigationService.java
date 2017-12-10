package ua.khpi.oop.malokhvii07.navigation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.google.inject.persist.Transactional;

import ua.khpi.oop.malokhvii05.common.repository.EntityCriteria;
import ua.khpi.oop.malokhvii07.navigation.component.connection.NavigationConnection;
import ua.khpi.oop.malokhvii07.navigation.component.marker.NavigationMarker;
import ua.khpi.oop.malokhvii07.navigation.component.schema.NavigationSchema;

public interface NavigationService {

    List<NavigationConnection> getNavigationConnections();

    List<NavigationConnection> getNavigationConnections(
            EntityCriteria<NavigationConnection> criteria);

    Optional<NavigationConnection> getNavigationConnecton(UUID id);

    Optional<NavigationMarker> getNavigationMarker(UUID id);

    List<NavigationMarker> getNavigationMarkers();

    List<NavigationMarker> getNavigationMarkers(
            EntityCriteria<NavigationMarker> criteria);

    Optional<NavigationSchema> getNavigationSchema(UUID id);

    List<NavigationSchema> getNavigationSchemas();

    List<NavigationSchema> getNavigationSchemas(
            EntityCriteria<NavigationSchema> criteria);

    @Transactional
    void refreshNavigationConnection(NavigationConnection navigationConnection);

    @Transactional
    void refreshNavigationMarker(NavigationMarker navigationMarker);

    @Transactional
    void refreshNavigationShema(NavigationSchema navigationShema);

    @Transactional
    void registerNavigationConnection(
            NavigationConnection navigationConnection);

    @Transactional
    void registerNavigationMarker(NavigationMarker navigationMarker);

    @Transactional
    void registerNavigationShema(NavigationSchema navigationShema);

    @Transactional
    void unregisterNavigationConnection(
            NavigationConnection navigationConnection);

    @Transactional
    void unregisterNavigationMarker(NavigationMarker navigationMarker);

    @Transactional
    void unregisterNavigationShema(NavigationSchema navigationShema);
}
