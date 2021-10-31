package org.zoo.manager.repository.generic;

import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;
import org.zoo.manager.domain.persistence.generic.NamedEntity;

@NoRepositoryBean
public interface NamedEntityRepository<E extends NamedEntity<ID>, ID>
        extends EntityRepository<E, ID> {

  Optional<E> findByName(String name);

}
