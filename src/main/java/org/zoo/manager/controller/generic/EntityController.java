package org.zoo.manager.controller.generic;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.zoo.manager.domain.persistence.generic.EntityId;
import org.zoo.manager.mapper.NestedModelMapper;
import org.zoo.manager.repository.generic.criteria.SearchCriteria;
import org.zoo.manager.service.generic.EntityServiceImpl;

public abstract class EntityController<E extends EntityId<ID>, ID, D> {

  private final EntityServiceImpl<E, ID> entityService;
  protected final Class<D> dtoClass;
  protected final Class<E> entityClass;
  protected final NestedModelMapper modelMapper;

  public EntityController(EntityServiceImpl<E, ID> entityService,
                          NestedModelMapper modelMapper, Class<D> dtoClass,
                          Class<E> entityClass) {
    this.entityService = entityService;
    this.dtoClass = dtoClass;
    this.entityClass = entityClass;
    this.modelMapper = modelMapper;
  }

  public D getById(ID id) {
    Optional<E> entity = this.entityService.getById(id);
    if (entity.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
              String.format("Entity with id=%s not found", id));
    }
    return map(entity.get());
  }

  public List<D> list(@RequestParam final Map<String, String> params) {
    return map(getByFilter(params));
  }

  protected List<E> getByFilter(@RequestParam final Map<String, String> params) {
    List<E> entityList;
    if (params.isEmpty()) {
      entityList = this.entityService.getAll();
    }
    else {
      entityList = this.entityService.findByFilter(parseFilter(params));
    }
    return entityList;
  }

  protected List<SearchCriteria> parseFilter(final Map<String, String> params) {
    if (Objects.isNull(params)) {
      return Collections.emptyList();
    }
    return params.entrySet().stream()
            .map(entry ->
                    new SearchCriteria(entry.getKey(),
                            ":",
                            entry.getValue()))
            .toList();
  }

  public D add(D dto) {
    return map(this.entityService.save(map(dto)));
  }

  public D update(ID id, D dto) {
    E entity = map(dto);
    if (Objects.nonNull(this.entityService.getById(id))) {
      entity.setId(id);
      return map(this.entityService.save(entity));
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
            String.format("Entity with id=%s not found.", id));
  }

  public void deleteById(ID id) {
    this.entityService.deleteById(id);
  }

  protected D map(E entity) {
    return this.modelMapper.map(entity, this.dtoClass);
  }

  protected E map(D dto) {
    return this.modelMapper.map(dto, this.entityClass);
  }

  protected List<D> map(Collection<E> entities) {
    return entities.stream()
            .map(entity -> this.modelMapper.map(entity, this.dtoClass))
            .toList();
  }

}
