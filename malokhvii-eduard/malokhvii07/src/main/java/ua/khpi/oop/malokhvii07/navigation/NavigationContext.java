package ua.khpi.oop.malokhvii07.navigation;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.UnitOfWork;
import com.google.inject.persist.jpa.JpaPersistModule;

import ua.khpi.oop.malokhvii05.common.persistence.PersitenceConfigurationBean;

public final class NavigationContext {

    public static NavigationContext create(
            final PersitenceConfigurationBean persitenceConfigurationBean) {
        return new NavigationContext(checkNotNull(persitenceConfigurationBean));
    }

    private final Injector injector;

    private NavigationContext(
            final PersitenceConfigurationBean persitenceConfigurationBean) {
        final JpaPersistModule jpaPersistModule = new JpaPersistModule(
                persitenceConfigurationBean.getPersistenceUnitName());
        jpaPersistModule
                .properties(persitenceConfigurationBean.toMappedProperties());
        injector = Guice.createInjector(jpaPersistModule,
                new NavigationModule());
    }

    public NavigationService createNavigationService() {
        return injector.getInstance(NavigationService.class);
    }

    public PersistService createPersistService() {
        return injector.getInstance(PersistService.class);
    }

    public UnitOfWork createUnitOfWork() {
        return injector.getInstance(UnitOfWork.class);
    }

    public Injector getInjector() {
        return injector;
    }
}
