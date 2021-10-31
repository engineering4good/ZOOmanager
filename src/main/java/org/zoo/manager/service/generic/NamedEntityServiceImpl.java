package org.zoo.manager.service.generic;

import java.util.Optional;

import org.zoo.manager.domain.persistence.generic.NamedEntity;
import org.zoo.manager.repository.generic.NamedEntityRepository;

public abstract class NamedEntityServiceImpl<E extends NamedEntity<ID>, ID>
        extends EntityServiceImpl<E, ID>
        implements NamedEntityService<E, ID> {

  protected final NamedEntityRepository<E, ID> repository;

  public NamedEntityServiceImpl(NamedEntityRepository<E, ID> repository) {
    super(repository);
    this.repository = repository;
  }

  public Optional<E> getByName(String name) {
    return this.repository.findByName(name);
  }

}
