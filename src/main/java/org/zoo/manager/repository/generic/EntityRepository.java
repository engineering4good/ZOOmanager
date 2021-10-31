package org.zoo.manager.repository.generic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.zoo.manager.domain.persistence.generic.EntityId;

@NoRepositoryBean
public interface EntityRepository<E extends EntityId<ID>, ID>
        extends JpaRepository<E, ID>,
                JpaSpecificationExecutor<E> {
}
