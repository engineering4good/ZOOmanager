package org.zoo.manager.repository.generic.criteria;

import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class SearchSpec<E> implements Specification<E> {

  List<SearchCriteria> params;

  public SearchSpec(List<SearchCriteria> params) {
    this.params = params;
  }

  @Override
  public Predicate toPredicate(Root<E> root,
                               CriteriaQuery<?> query,
                               CriteriaBuilder criteriaBuilder) {
    Predicate compositePredicate = null;
    for (SearchCriteria criteria : this.params) {
      String operation = criteria.getOperation();
      Predicate predicate;
      if (operation.equals(":")) {
        predicate = criteriaBuilder.equal(parsePath(root, criteria.getKey()), criteria.getValue());
      }
      else {
        throw new UnsupportedOperationException(String.format("Operation %s not implemented", operation));
      }
      if (Objects.isNull(compositePredicate)) {
        compositePredicate = predicate;
      }
      else {
        compositePredicate = criteriaBuilder.and(compositePredicate, predicate);
      }
    }
    return compositePredicate;
  }

  private Path<E> parsePath(Path<E> root, String pathStr) {
    String[] parts = pathStr.split("\\.");
    Path<E> path = root;
    for (String part : parts) {
      path = path.get(part);
    }
    return path;
  }
}
