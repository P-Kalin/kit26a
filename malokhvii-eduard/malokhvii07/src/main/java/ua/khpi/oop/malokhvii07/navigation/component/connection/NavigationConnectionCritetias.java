package ua.khpi.oop.malokhvii07.navigation.component.connection;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ua.khpi.oop.malokhvii05.common.repository.EntityCriteria;
import ua.khpi.oop.malokhvii07.navigation.component.marker.NavigationMarker;

public class NavigationConnectionCritetias {

    public static EntityCriteria<NavigationConnection> newCriteriaByDistance(
            final Double distance) {
        checkNotNull(distance);
        return new EntityCriteria<NavigationConnection>() {

            @Override
            public boolean isSatisfiedBy(final NavigationConnection candidate) {
                checkNotNull(candidate);
                return distance.equals(candidate.getDistance());
            }

            @Override
            public Predicate toPredicate(final Root<NavigationConnection> root,
                    final CriteriaBuilder criteriaBuilder) {
                checkNotNull(root);
                checkNotNull(criteriaBuilder);
                return criteriaBuilder.equal(
                        root.get(NavigationConnection_.distance), distance);
            }
        };
    }

    public static EntityCriteria<NavigationConnection> newCriteriaByDistanceBetween(
            final Double from, final Double to) {
        checkNotNull(from);
        checkNotNull(to);
        return new EntityCriteria<NavigationConnection>() {

            @Override
            public boolean isSatisfiedBy(final NavigationConnection candidate) {
                checkNotNull(candidate);
                final Double distance = candidate.getDistance();
                return from.compareTo(distance) > 0
                        && to.compareTo(distance) < 0;
            }

            @Override
            public Predicate toPredicate(final Root<NavigationConnection> root,
                    final CriteriaBuilder criteriaBuilder) {
                checkNotNull(root);
                checkNotNull(criteriaBuilder);
                return criteriaBuilder.between(
                        root.get(NavigationConnection_.distance), from, to);
            }
        };
    }

    public static EntityCriteria<NavigationConnection> newCriteriaByDistanceGreaterThan(
            final Double distance) {
        checkNotNull(distance);
        return new EntityCriteria<NavigationConnection>() {

            @Override
            public boolean isSatisfiedBy(final NavigationConnection candidate) {
                checkNotNull(candidate);
                return distance.compareTo(candidate.getDistance()) > 0;
            }

            @Override
            public Predicate toPredicate(final Root<NavigationConnection> root,
                    final CriteriaBuilder criteriaBuilder) {
                checkNotNull(root);
                checkNotNull(criteriaBuilder);
                return criteriaBuilder.greaterThan(
                        root.get(NavigationConnection_.distance), distance);
            }
        };
    }

    public static EntityCriteria<NavigationConnection> newCriteriaByDistanceGreaterThanOrEqual(
            final Double distance) {
        checkNotNull(distance);
        return new EntityCriteria<NavigationConnection>() {

            @Override
            public boolean isSatisfiedBy(final NavigationConnection candidate) {
                checkNotNull(candidate);
                return distance.compareTo(candidate.getDistance()) >= 0;
            }

            @Override
            public Predicate toPredicate(final Root<NavigationConnection> root,
                    final CriteriaBuilder criteriaBuilder) {
                checkNotNull(root);
                checkNotNull(criteriaBuilder);
                return criteriaBuilder.greaterThanOrEqualTo(
                        root.get(NavigationConnection_.distance), distance);
            }
        };
    }

    public static EntityCriteria<NavigationConnection> newCriteriaByDistanceLessThan(
            final Double distance) {
        checkNotNull(distance);
        return new EntityCriteria<NavigationConnection>() {

            @Override
            public boolean isSatisfiedBy(final NavigationConnection candidate) {
                checkNotNull(candidate);
                return distance.compareTo(candidate.getDistance()) < 0;
            }

            @Override
            public Predicate toPredicate(final Root<NavigationConnection> root,
                    final CriteriaBuilder criteriaBuilder) {
                checkNotNull(root);
                checkNotNull(criteriaBuilder);
                return criteriaBuilder.lessThan(
                        root.get(NavigationConnection_.distance), distance);
            }
        };
    }

    public static EntityCriteria<NavigationConnection> newCriteriaByDistanceLessThanOrEqual(
            final Double distance) {
        checkNotNull(distance);
        return new EntityCriteria<NavigationConnection>() {

            @Override
            public boolean isSatisfiedBy(final NavigationConnection candidate) {
                checkNotNull(candidate);
                return distance.compareTo(candidate.getDistance()) <= 0;
            }

            @Override
            public Predicate toPredicate(final Root<NavigationConnection> root,
                    final CriteriaBuilder criteriaBuilder) {
                checkNotNull(root);
                checkNotNull(criteriaBuilder);
                return criteriaBuilder.lessThanOrEqualTo(
                        root.get(NavigationConnection_.distance), distance);
            }
        };
    }

    public static EntityCriteria<NavigationConnection> newCriteriaByOutputNavigationMarker(
            final NavigationMarker outputNavigationMarker) {
        checkNotNull(outputNavigationMarker);
        return new EntityCriteria<NavigationConnection>() {

            @Override
            public boolean isSatisfiedBy(final NavigationConnection candidate) {
                checkNotNull(candidate);
                return outputNavigationMarker
                        .equals(candidate.getOutputNavigationMarker());
            }

            @Override
            public Predicate toPredicate(final Root<NavigationConnection> root,
                    final CriteriaBuilder criteriaBuilder) {
                checkNotNull(root);
                checkNotNull(criteriaBuilder);
                return criteriaBuilder.equal(
                        root.get(NavigationConnection_.outputNavigationMarker),
                        outputNavigationMarker);
            }
        };
    }

    public static EntityCriteria<NavigationConnection> newCritetiaByInputNavigationMarker(
            final NavigationMarker inputNavigationMarker) {
        checkNotNull(inputNavigationMarker);
        return new EntityCriteria<NavigationConnection>() {

            @Override
            public boolean isSatisfiedBy(final NavigationConnection candidate) {
                checkNotNull(candidate);
                return inputNavigationMarker
                        .equals(candidate.getInputNavigationMarker());
            }

            @Override
            public Predicate toPredicate(final Root<NavigationConnection> root,
                    final CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(
                        root.get(NavigationConnection_.inputNavigationMarker),
                        inputNavigationMarker);
            }
        };
    }

    private NavigationConnectionCritetias() {

    }
}
