package ua.khpi.oop.malokhvii07.navigation.component.marker;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ua.khpi.oop.malokhvii05.common.repository.EntityCriteria;

public class NavigationMarkerCriterias {

    public static EntityCriteria<NavigationMarker> newCriteriaByLabel(
            final String label) {
        checkNotNull(label);
        return new EntityCriteria<NavigationMarker>() {

            @Override
            public boolean isSatisfiedBy(final NavigationMarker candidate) {
                checkNotNull(candidate);
                return label.equals(candidate.getLabel());
            }

            @Override
            public Predicate toPredicate(final Root<NavigationMarker> root,
                    final CriteriaBuilder criteriaBuilder) {
                checkNotNull(root);
                checkNotNull(criteriaBuilder);
                return criteriaBuilder.equal(root.get(NavigationMarker_.label),
                        label);
            }
        };
    }

    public static EntityCriteria<NavigationMarker> newCriteriaByLatitude(
            final Double latitude) {
        checkNotNull(latitude);
        return new EntityCriteria<NavigationMarker>() {

            @Override
            public boolean isSatisfiedBy(final NavigationMarker candidate) {
                checkNotNull(candidate);
                return latitude.equals(candidate.getLatitude());
            }

            @Override
            public Predicate toPredicate(final Root<NavigationMarker> root,
                    final CriteriaBuilder criteriaBuilder) {
                checkNotNull(root);
                checkNotNull(criteriaBuilder);
                return criteriaBuilder
                        .equal(root.get(NavigationMarker_.latitude), latitude);
            }
        };
    }

    public static EntityCriteria<NavigationMarker> newCriteriaByLatitudeBetween(
            final Double from, final Double to) {
        checkNotNull(from);
        checkNotNull(to);
        return new EntityCriteria<NavigationMarker>() {

            @Override
            public boolean isSatisfiedBy(final NavigationMarker candidate) {
                checkNotNull(candidate);
                final Double latitude = candidate.getLatitude();
                return from.compareTo(latitude) > 0
                        && to.compareTo(latitude) < 0;
            }

            @Override
            public Predicate toPredicate(final Root<NavigationMarker> root,
                    final CriteriaBuilder criteriaBuilder) {
                checkNotNull(root);
                checkNotNull(criteriaBuilder);
                return criteriaBuilder.between(
                        root.get(NavigationMarker_.latitude), from, to);
            }
        };
    }

    public static EntityCriteria<NavigationMarker> newCriteriaByLatitudeGreaterThan(
            final Double latitude) {
        checkNotNull(latitude);
        return new EntityCriteria<NavigationMarker>() {

            @Override
            public boolean isSatisfiedBy(final NavigationMarker candidate) {
                checkNotNull(candidate);
                return latitude.compareTo(candidate.getLatitude()) > 0;
            }

            @Override
            public Predicate toPredicate(final Root<NavigationMarker> root,
                    final CriteriaBuilder criteriaBuilder) {
                checkNotNull(root);
                checkNotNull(criteriaBuilder);
                return criteriaBuilder.greaterThan(
                        root.get(NavigationMarker_.latitude), latitude);
            }
        };
    }

    public static EntityCriteria<NavigationMarker> newCriteriaByLatitudeGreaterThanOrEqual(
            final Double latitude) {
        checkNotNull(latitude);
        return new EntityCriteria<NavigationMarker>() {

            @Override
            public boolean isSatisfiedBy(final NavigationMarker candidate) {
                checkNotNull(candidate);
                return latitude.compareTo(candidate.getLatitude()) >= 0;
            }

            @Override
            public Predicate toPredicate(final Root<NavigationMarker> root,
                    final CriteriaBuilder criteriaBuilder) {
                checkNotNull(root);
                checkNotNull(criteriaBuilder);
                return criteriaBuilder.greaterThanOrEqualTo(
                        root.get(NavigationMarker_.latitude), latitude);
            }
        };
    }

    public static EntityCriteria<NavigationMarker> newCriteriaByLatitudeLessThan(
            final Double latitude) {
        checkNotNull(latitude);
        return new EntityCriteria<NavigationMarker>() {

            @Override
            public boolean isSatisfiedBy(final NavigationMarker candidate) {
                checkNotNull(candidate);
                return latitude.compareTo(candidate.getLatitude()) < 0;
            }

            @Override
            public Predicate toPredicate(final Root<NavigationMarker> root,
                    final CriteriaBuilder criteriaBuilder) {
                checkNotNull(root);
                checkNotNull(criteriaBuilder);
                return criteriaBuilder.lessThan(
                        root.get(NavigationMarker_.latitude), latitude);
            }
        };
    }

    public static EntityCriteria<NavigationMarker> newCriteriaByLatitudeLessThanOrEqual(
            final Double latitude) {
        checkNotNull(latitude);
        return new EntityCriteria<NavigationMarker>() {

            @Override
            public boolean isSatisfiedBy(final NavigationMarker candidate) {
                checkNotNull(candidate);
                return latitude.compareTo(candidate.getLatitude()) <= 0;
            }

            @Override
            public Predicate toPredicate(final Root<NavigationMarker> root,
                    final CriteriaBuilder criteriaBuilder) {
                checkNotNull(root);
                checkNotNull(criteriaBuilder);
                return criteriaBuilder.lessThanOrEqualTo(
                        root.get(NavigationMarker_.latitude), latitude);
            }
        };
    }

    public static EntityCriteria<NavigationMarker> newCriteriaByLongitude(
            final Double longitude) {
        checkNotNull(longitude);
        return new EntityCriteria<NavigationMarker>() {

            @Override
            public boolean isSatisfiedBy(final NavigationMarker candidate) {
                checkNotNull(candidate);
                return longitude.equals(candidate.getLongitude());
            }

            @Override
            public Predicate toPredicate(final Root<NavigationMarker> root,
                    final CriteriaBuilder criteriaBuilder) {
                checkNotNull(root);
                checkNotNull(criteriaBuilder);
                return criteriaBuilder.equal(
                        root.get(NavigationMarker_.longitude), longitude);
            }
        };
    }

    public static EntityCriteria<NavigationMarker> newCriteriaByLongitudeBetween(
            final Double from, final Double to) {
        checkNotNull(from);
        checkNotNull(to);
        return new EntityCriteria<NavigationMarker>() {

            @Override
            public boolean isSatisfiedBy(final NavigationMarker candidate) {
                checkNotNull(candidate);
                final Double longitude = candidate.getLongitude();
                return from.compareTo(longitude) > 0
                        && to.compareTo(longitude) < 0;
            }

            @Override
            public Predicate toPredicate(final Root<NavigationMarker> root,
                    final CriteriaBuilder criteriaBuilder) {
                checkNotNull(root);
                checkNotNull(criteriaBuilder);
                return criteriaBuilder.between(
                        root.get(NavigationMarker_.longitude), from, to);
            }
        };
    }

    public static EntityCriteria<NavigationMarker> newCriteriaByLongitudeGreaterThan(
            final Double longitude) {
        checkNotNull(longitude);
        return new EntityCriteria<NavigationMarker>() {

            @Override
            public boolean isSatisfiedBy(final NavigationMarker candidate) {
                checkNotNull(candidate);
                return longitude.compareTo(candidate.getLongitude()) > 0;
            }

            @Override
            public Predicate toPredicate(final Root<NavigationMarker> root,
                    final CriteriaBuilder criteriaBuilder) {
                checkNotNull(root);
                checkNotNull(criteriaBuilder);
                return criteriaBuilder.greaterThan(
                        root.get(NavigationMarker_.longitude), longitude);
            }
        };
    }

    public static EntityCriteria<NavigationMarker> newCriteriaByLongitudeGreaterThanOrEqual(
            final Double longitude) {
        checkNotNull(longitude);
        return new EntityCriteria<NavigationMarker>() {

            @Override
            public boolean isSatisfiedBy(final NavigationMarker candidate) {
                checkNotNull(candidate);
                return longitude.compareTo(candidate.getLongitude()) >= 0;
            }

            @Override
            public Predicate toPredicate(final Root<NavigationMarker> root,
                    final CriteriaBuilder criteriaBuilder) {
                checkNotNull(root);
                checkNotNull(criteriaBuilder);
                return criteriaBuilder.greaterThanOrEqualTo(
                        root.get(NavigationMarker_.longitude), longitude);
            }
        };
    }

    public static EntityCriteria<NavigationMarker> newCriteriaByLongitudeLessThan(
            final Double longitude) {
        checkNotNull(longitude);
        return new EntityCriteria<NavigationMarker>() {

            @Override
            public boolean isSatisfiedBy(final NavigationMarker candidate) {
                checkNotNull(candidate);
                return longitude.compareTo(candidate.getLongitude()) < 0;
            }

            @Override
            public Predicate toPredicate(final Root<NavigationMarker> root,
                    final CriteriaBuilder criteriaBuilder) {
                checkNotNull(root);
                checkNotNull(criteriaBuilder);
                return criteriaBuilder.lessThan(
                        root.get(NavigationMarker_.longitude), longitude);
            }
        };
    }

    public static EntityCriteria<NavigationMarker> newCriteriaByLongitudeLessThanOrEqual(
            final Double longitude) {
        checkNotNull(longitude);
        return new EntityCriteria<NavigationMarker>() {

            @Override
            public boolean isSatisfiedBy(final NavigationMarker candidate) {
                checkNotNull(candidate);
                return longitude.compareTo(candidate.getLongitude()) <= 0;
            }

            @Override
            public Predicate toPredicate(final Root<NavigationMarker> root,
                    final CriteriaBuilder criteriaBuilder) {
                checkNotNull(root);
                checkNotNull(criteriaBuilder);
                return criteriaBuilder.lessThanOrEqualTo(
                        root.get(NavigationMarker_.longitude), longitude);
            }
        };
    }

    private NavigationMarkerCriterias() {

    }
}
