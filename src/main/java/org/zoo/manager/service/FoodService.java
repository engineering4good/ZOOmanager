package org.zoo.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zoo.manager.domain.persistence.Food;
import org.zoo.manager.repository.FoodRepository;
import org.zoo.manager.service.generic.NamedEntityServiceImpl;

@Service
public class FoodService extends NamedEntityServiceImpl<Food, Long> {

  @Autowired
  public FoodService(FoodRepository repository) {
    super(repository);
  }

  @Override
  public void deleteById(Long id) {
    repository.findById(id)
            .ifPresent(food -> food
                    .getCategories()
                    .forEach(food::removeCategory)
            );
    super.deleteById(id);
  }
}

