package com.metea.moneyanalysis.util;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.metea.moneyanalysis.util.SearchCriteria.SearchOperation;


public class SearchCriteriaOptions<T> implements Specification<T> {
    private static final long serialVersionUID = 1900581010229669687L;

    private final List<SearchCriteria> list;

    public SearchCriteriaOptions() {
        this.list = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        if (Objects.nonNull(criteria.getValue())) {
            list.add(criteria);
        }
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria criteria : list) {
            if (findForeignKey(criteria)) {
                var splitKey = criteria.getKey().split("/");
                if (criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {
                    predicates.add(builder.greaterThan(
                            root.join(splitKey[0]).get(splitKey[1]), criteria.getValue().toString()));
                } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
                    predicates.add(builder.lessThan(
                            root.join(splitKey[0]).get(splitKey[1]), criteria.getValue().toString()));
                } else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
                    predicates.add(builder.greaterThanOrEqualTo(
                            root.join(splitKey[0]).get(splitKey[1]), criteria.getValue().toString()));
                } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
                    predicates.add(builder.lessThanOrEqualTo(
                            root.join(splitKey[0]).get(splitKey[1]), criteria.getValue().toString()));
                } else if (criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
                    predicates.add(builder.notEqual(
                            root.join(splitKey[0]).get(splitKey[1]), criteria.getValue()));
                } else if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
                    predicates.add(builder.equal(
                            root.join(splitKey[0]).get(splitKey[1]), criteria.getValue()));
                } else if (criteria.getOperation().equals(SearchOperation.LIKE)) {
                    predicates.add(builder.like(
                            builder.lower(root.join(splitKey[0]).get(splitKey[1])),
                            "%" + criteria.getValue().toString().toLowerCase() + "%"));
                } else if (criteria.getOperation().equals(SearchOperation.MATCH_END)) {
                    predicates.add(builder.like(
                            builder.lower(root.join(splitKey[0]).get(splitKey[1])),
                            criteria.getValue().toString().toLowerCase() + "%"));
                }
            } else {
                if (criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {
                    predicates.add(builder.greaterThan(
                            root.get(criteria.getKey()), criteria.getValue().toString()));
                } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
                    predicates.add(builder.lessThan(
                            root.get(criteria.getKey()), criteria.getValue().toString()));
                } else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
                    predicates.add(builder.greaterThanOrEqualTo(
                            root.get(criteria.getKey()), criteria.getValue().toString()));
                } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
                    predicates.add(builder.lessThanOrEqualTo(
                            root.get(criteria.getKey()), criteria.getValue().toString()));
                } else if (criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
                    predicates.add(builder.notEqual(
                            root.get(criteria.getKey()), criteria.getValue()));
                } else if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
                    predicates.add(builder.equal(
                            root.get(criteria.getKey()), criteria.getValue()));
                } else if (criteria.getOperation().equals(SearchOperation.LIKE)) {
                    predicates.add(builder.like(
                            builder.lower(root.get(criteria.getKey())),
                            "%" + criteria.getValue().toString().toLowerCase() + "%"));
                } else if (criteria.getOperation().equals(SearchOperation.MATCH_END)) {
                    predicates.add(builder.like(
                            builder.lower(root.get(criteria.getKey())),
                            criteria.getValue().toString().toLowerCase() + "%"));
                }
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }

    public boolean findForeignKey(SearchCriteria criteria) {
        return criteria.getKey().indexOf('/') != -1;
    }

}
