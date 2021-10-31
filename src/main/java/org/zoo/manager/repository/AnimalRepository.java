package org.zoo.manager.repository;

import org.zoo.manager.domain.persistence.Animal;
import org.zoo.manager.repository.generic.NamedEntityRepository;

public interface AnimalRepository
        extends NamedEntityRepository<Animal, Long> {
}
