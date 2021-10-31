package org.zoo.manager.repository;

import org.zoo.manager.domain.persistence.Food;
import org.zoo.manager.repository.generic.NamedEntityRepository;

public interface FoodRepository
        extends NamedEntityRepository<Food, Long> {
}
