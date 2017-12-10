package ua.khpi.oop.malokhvii07.navigation.component.shema;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ua.khpi.oop.malokhvii07.navigation.component.NavigationComponent_;
import ua.khpi.oop.malokhvii07.navigation.component.connection.NavigationConnection;
import ua.khpi.oop.malokhvii07.navigation.component.marker.NavigationMarker;

@Generated(value="Dali", date="2017-12-10T20:30:54.756+0200")
@StaticMetamodel(NavigationSchema.class)
public class NavigationSchema_ extends NavigationComponent_ {
	public static volatile SingularAttribute<NavigationSchema, String> label;
	public static volatile ListAttribute<NavigationSchema, NavigationMarker> navigationMarkers;
	public static volatile ListAttribute<NavigationSchema, NavigationConnection> navigationConnections;
	public static volatile SingularAttribute<NavigationSchema, Boolean> mutable;
	public static volatile SingularAttribute<NavigationSchema, Boolean> directed;
	public static volatile SingularAttribute<NavigationSchema, Boolean> selfLoops;
}
