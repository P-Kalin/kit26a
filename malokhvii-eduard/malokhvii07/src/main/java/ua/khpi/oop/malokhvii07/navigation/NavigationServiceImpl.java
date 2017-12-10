package ua.khpi.oop.malokhvii07.navigation;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import ua.khpi.oop.malokhvii05.common.repository.CrudRepository;
import ua.khpi.oop.malokhvii05.common.repository.EntityCriteria;
import ua.khpi.oop.malokhvii07.navigation.component.connection.NavigationConnection;
import ua.khpi.oop.malokhvii07.navigation.component.marker.NavigationMarker;
import ua.khpi.oop.malokhvii07.navigation.component.schema.NavigationSchema;

class NavigationServiceImpl implements NavigationService {

    private final CrudRepository<UUID, NavigationConnection> navigationConnections;
    private final CrudRepository<UUID, NavigationMarker> navigationMarkers;
    private final CrudRepository<UUID, NavigationSchema> navigationSchemas;

    @Inject
    NavigationServiceImpl(
            final CrudRepository<UUID, NavigationMarker> navigationMarkers,
            final CrudRepository<UUID, NavigationConnection> navigationConnections,
            final CrudRepository<UUID, NavigationSchema> navigationSchemas) {
        this.navigationMarkers = checkNotNull(navigationMarkers);
        this.navigationConnections = checkNotNull(navigationConnections);
        this.navigationSchemas = checkNotNull(navigationSchemas);
    }

    @Override
    public List<NavigationConnection> getNavigationConnections() {
        return navigationConnections.findAll();
    }

    @Override
    public List<NavigationConnection> getNavigationConnections(
            final EntityCriteria<NavigationConnection> criteria) {
        return navigationConnections.findAll(checkNotNull(criteria));
    }

    @Override
    public Optional<NavigationConnection> getNavigationConnecton(
            final UUID id) {
        return navigationConnections.findById(id);
    }

    @Override
    public Optional<NavigationMarker> getNavigationMarker(final UUID id) {
        return navigationMarkers.findById(id);
    }

    @Override
    public List<NavigationMarker> getNavigationMarkers() {
        return navigationMarkers.findAll();
    }

    @Override
    public List<NavigationMarker> getNavigationMarkers(
            final EntityCriteria<NavigationMarker> criteria) {
        return navigationMarkers.findAll(checkNotNull(criteria));
    }

    @Override
    public Optional<NavigationSchema> getNavigationSchema(final UUID id) {
        return navigationSchemas.findById(id);
    }

    @Override
    public List<NavigationSchema> getNavigationSchemas() {
        return navigationSchemas.findAll();
    }

    @Override
    public List<NavigationSchema> getNavigationSchemas(
            final EntityCriteria<NavigationSchema> criteria) {
        return navigationSchemas.findAll(checkNotNull(criteria));
    }

    @Override
    @Transactional
    public void refreshNavigationConnection(
            final NavigationConnection navigationConnection) {
        if (!navigationConnections.exist(navigationConnection)) {
            throw new RuntimeException();
        }
        navigationConnections.update(navigationConnection);
    }

    @Override
    @Transactional
    public void refreshNavigationMarker(
            final NavigationMarker navigationMarker) {
        if (!navigationMarkers.exist(navigationMarker)) {
            throw new RuntimeException();
        }
        navigationMarkers.update(navigationMarker);
    }

    @Override
    @Transactional
    public void refreshNavigationShema(final NavigationSchema navigationShema) {
        if (!navigationSchemas.exist(navigationShema)) {
            throw new RuntimeException();
        }
        navigationSchemas.update(navigationShema);
    }

    @Override
    @Transactional
    public void registerNavigationConnection(
            final NavigationConnection navigationConnection) {
        if (navigationMarkers
                .exist(navigationConnection.getOutputNavigationMarker())
                && navigationMarkers.exist(
                        navigationConnection.getInputNavigationMarker())) {
            throw new RuntimeException();
        }
        navigationConnections.save(navigationConnection);
    }

    @Override
    @Transactional
    public void registerNavigationMarker(
            final NavigationMarker navigationMarker) {
        navigationMarkers.save(navigationMarker);
    }

    @Override
    @Transactional
    public void registerNavigationShema(
            final NavigationSchema navigationShema) {
        navigationSchemas.save(navigationShema);
    }

    @Override
    @Transactional
    public void unregisterNavigationConnection(
            final NavigationConnection navigationConnection) {
        if (!navigationConnections.exist(navigationConnection)) {
            throw new RuntimeException();
        }
        navigationConnections.delete(navigationConnection);
    }

    @Override
    @Transactional
    public void unregisterNavigationMarker(
            final NavigationMarker navigationMarker) {
        if (!navigationMarkers.exist(navigationMarker)) {
            throw new RuntimeException();
        }
        navigationMarkers.delete(navigationMarker);
    }

    @Override
    @Transactional
    public void unregisterNavigationShema(
            final NavigationSchema navigationShema) {
        if (!navigationSchemas.exist(navigationShema)) {
            throw new RuntimeException();
        }
        navigationSchemas.delete(navigationShema);
    }
}
