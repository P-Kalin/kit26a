package ua.khpi.oop.malokhvii07.navigation.component.connection;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ua.khpi.oop.malokhvii07.navigation.component.NavigationComponent_;
import ua.khpi.oop.malokhvii07.navigation.component.marker.NavigationMarker;

@Generated(value="Dali", date="2017-12-10T20:30:54.736+0200")
@StaticMetamodel(NavigationConnection.class)
public class NavigationConnection_ extends NavigationComponent_ {
	public static volatile SingularAttribute<NavigationConnection, Double> distance;
	public static volatile SingularAttribute<NavigationConnection, NavigationMarker> outputNavigationMarker;
	public static volatile SingularAttribute<NavigationConnection, NavigationMarker> inputNavigationMarker;
}
