package ua.khpi.oop.malokhvii07.navigation.component.schema;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ua.khpi.oop.malokhvii05.common.repository.EntityCriteria;

public class NavigationSchemaCriterias {

    public static EntityCriteria<NavigationSchema> newCriteriaByDirectedFalse() {
        return new EntityCriteria<NavigationSchema>() {

            @Override
            public boolean isSatisfiedBy(final NavigationSchema candidate) {
                checkNotNull(candidate);
                return !candidate.getDirected();
            }

            @Override
            public Predicate toPredicate(final Root<NavigationSchema> root,
                    final CriteriaBuilder criteriaBuilder) {
                checkNotNull(root);
                checkNotNull(criteriaBuilder);
                return criteriaBuilder
                        .equal(root.get(NavigationSchema_.directed), false);
            }
        };
    }

    public static EntityCriteria<NavigationSchema> newCriteriaByDirectedTrue() {
        return new EntityCriteria<NavigationSchema>() {

            @Override
            public boolean isSatisfiedBy(final NavigationSchema candidate) {
                checkNotNull(candidate);
                return candidate.getDirected();
            }

            @Override
            public Predicate toPredicate(final Root<NavigationSchema> root,
                    final CriteriaBuilder criteriaBuilder) {
                checkNotNull(root);
                checkNotNull(criteriaBuilder);
                return criteriaBuilder
                        .equal(root.get(NavigationSchema_.directed), true);
            }
        };
    }

    public static EntityCriteria<NavigationSchema> newCriteriaByLabel(
            final String label) {
        checkNotNull(label);
        return new EntityCriteria<NavigationSchema>() {

            @Override
            public boolean isSatisfiedBy(final NavigationSchema candidate) {
                checkNotNull(candidate);
                return label.equals(candidate.getLabel());
            }

            @Override
            public Predicate toPredicate(final Root<NavigationSchema> root,
                    final CriteriaBuilder criteriaBuilder) {
                checkNotNull(root);
                checkNotNull(criteriaBuilder);
                return criteriaBuilder.equal(root.get(NavigationSchema_.label),
                        label);
            }
        };
    }

    public static EntityCriteria<NavigationSchema> newCriteriaByMutableFalse() {
        return new EntityCriteria<NavigationSchema>() {

            @Override
            public boolean isSatisfiedBy(final NavigationSchema candidate) {
                checkNotNull(candidate);
                return !candidate.getMutable();
            }

            @Override
            public Predicate toPredicate(final Root<NavigationSchema> root,
                    final CriteriaBuilder criteriaBuilder) {
                checkNotNull(root);
                checkNotNull(criteriaBuilder);
                return criteriaBuilder
                        .equal(root.get(NavigationSchema_.mutable), false);
            }
        };
    }

    public static EntityCriteria<NavigationSchema> newCriteriaByMutableTrue() {
        return new EntityCriteria<NavigationSchema>() {

            @Override
            public boolean isSatisfiedBy(final NavigationSchema candidate) {
                checkNotNull(candidate);
                return candidate.getMutable();
            }

            @Override
            public Predicate toPredicate(final Root<NavigationSchema> root,
                    final CriteriaBuilder criteriaBuilder) {
                checkNotNull(root);
                checkNotNull(criteriaBuilder);
                return criteriaBuilder
                        .equal(root.get(NavigationSchema_.mutable), true);
            }
        };
    }

    public static EntityCriteria<NavigationSchema> newCriteriaBySelfLoopsFalse() {
        return new EntityCriteria<NavigationSchema>() {

            @Override
            public boolean isSatisfiedBy(final NavigationSchema candidate) {
                checkNotNull(candidate);
                return !candidate.getSelfLoops();
            }

            @Override
            public Predicate toPredicate(final Root<NavigationSchema> root,
                    final CriteriaBuilder criteriaBuilder) {
                checkNotNull(root);
                checkNotNull(criteriaBuilder);
                return criteriaBuilder
                        .equal(root.get(NavigationSchema_.selfLoops), false);
            }
        };
    }

    public static EntityCriteria<NavigationSchema> newCriteriaBySelfLoopsTrue() {
        return new EntityCriteria<NavigationSchema>() {

            @Override
            public boolean isSatisfiedBy(final NavigationSchema candidate) {
                checkNotNull(candidate);
                return candidate.getSelfLoops();
            }

            @Override
            public Predicate toPredicate(final Root<NavigationSchema> root,
                    final CriteriaBuilder criteriaBuilder) {
                checkNotNull(root);
                checkNotNull(criteriaBuilder);
                return criteriaBuilder
                        .equal(root.get(NavigationSchema_.selfLoops), true);
            }
        };
    }

    private NavigationSchemaCriterias() {

    }
}
