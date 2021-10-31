package org.zoo.manager.controller.generic;

import java.util.Optional;

import javax.persistence.NonUniqueResultException;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.zoo.manager.domain.persistence.generic.NamedEntity;
import org.zoo.manager.mapper.NestedModelMapper;
import org.zoo.manager.service.generic.NamedEntityServiceImpl;

public abstract class NamedEntityController<E extends NamedEntity<ID>, ID, D>
        extends EntityController<E, ID, D> {

  private final NamedEntityServiceImpl<E, ID> entityService;

  public NamedEntityController(NamedEntityServiceImpl<E, ID> entityService,
                               NestedModelMapper modelMapper,
                               Class<D> dtoClass,
                               Class<E> entityClass) {
    super(entityService, modelMapper, dtoClass, entityClass);
    this.entityService = entityService;
  }

  public D getByName(String name) {
    Optional<E> entity;
    try {
      entity = this.entityService.getByName(name);
    }
    catch (NonUniqueResultException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
              String.format("Multiple entities with name=%s found", name));
    }
    if (entity.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
              String.format("Entity with name=%s not found", name));
    }
    return map(entity.get());
  }

}
