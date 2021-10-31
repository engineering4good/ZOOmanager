package org.zoo.manager.service.generic;

import java.util.Optional;

import org.zoo.manager.domain.persistence.generic.NamedEntity;

public interface NamedEntityService<E extends NamedEntity<ID>, ID> extends EntityService<E, ID> {

  Optional<E> getByName(String name);

}
