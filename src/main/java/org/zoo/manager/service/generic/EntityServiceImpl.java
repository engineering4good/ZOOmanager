package org.zoo.manager.service.generic;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.zoo.manager.domain.persistence.generic.EntityId;
import org.zoo.manager.repository.generic.EntityRepository;
import org.zoo.manager.repository.generic.criteria.SearchCriteria;
import org.zoo.manager.repository.generic.criteria.SearchSpec;

public abstract class EntityServiceImpl<E extends EntityId<ID>, ID>
        implements EntityService<E, ID> {

  private final EntityRepository<E, ID> repository;

  public EntityServiceImpl(EntityRepository<E, ID> repository) {
    this.repository = repository;
  }

  public List<E> getAll() {
    return this.repository.findAll();
  }

  public Optional<E> getById(ID id) {
    return this.repository.findById(id);
  }

  public List<E> findByFilter(SearchCriteria criteria) {
    return findByFilter(Collections.singletonList(criteria));
  }

  public List<E> findByFilter(List<SearchCriteria> params) {
    if (Objects.isNull(params) || params.isEmpty()) {
      return this.repository.findAll();
    }
    SearchSpec<E> searchSpec = new SearchSpec<>(params);
    return this.repository.findAll(searchSpec);
  }

  public E save(E entity) {
    return this.repository.save(entity);
  }

  public void deleteById(ID id) {
    this.repository.deleteById(id);
  }
}
