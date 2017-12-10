package ua.khpi.oop.malokhvii07.navigation;

import java.util.UUID;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;

import ua.khpi.oop.malokhvii05.common.repository.CrudRepository;
import ua.khpi.oop.malokhvii05.common.repository.JpaCrudRepository;
import ua.khpi.oop.malokhvii07.navigation.component.connection.NavigationConnection;
import ua.khpi.oop.malokhvii07.navigation.component.marker.NavigationMarker;
import ua.khpi.oop.malokhvii07.navigation.component.schema.NavigationSchema;

public class NavigationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(NavigationService.class).to(NavigationServiceImpl.class);
        bind(new TypeLiteral<CrudRepository<UUID, NavigationMarker>>() {
        }).to(new TypeLiteral<JpaCrudRepository<UUID, NavigationMarker>>() {
        });
        bind(new TypeLiteral<CrudRepository<UUID, NavigationConnection>>() {
        }).to(new TypeLiteral<JpaCrudRepository<UUID, NavigationConnection>>() {
        });
        bind(new TypeLiteral<CrudRepository<UUID, NavigationSchema>>() {
        }).to(new TypeLiteral<JpaCrudRepository<UUID, NavigationSchema>>() {
        });
    }

    @Provides
    protected Class<NavigationConnection> provideNavigationConnectionClass() {
        return NavigationConnection.class;
    }

    @Provides
    protected Class<NavigationMarker> provideNavigationMarkerClass() {
        return NavigationMarker.class;
    }

    @Provides
    protected Class<NavigationSchema> provideNavigationSchemaClass() {
        return NavigationSchema.class;
    }
}
