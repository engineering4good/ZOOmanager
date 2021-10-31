package org.zoo.manager.service.generic;

import java.util.List;
import java.util.Optional;

import org.zoo.manager.domain.persistence.generic.EntityId;
import org.zoo.manager.repository.generic.criteria.SearchCriteria;

public interface EntityService<E extends EntityId<ID>, ID> {

  List<E> getAll();
  Optional<E> getById(ID id);
  List<E> findByFilter(List<SearchCriteria> filter);
  E save(E entity);

}
