package org.zoo.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zoo.manager.domain.persistence.Animal;
import org.zoo.manager.repository.AnimalRepository;
import org.zoo.manager.service.generic.NamedEntityServiceImpl;

@Service
public class AnimalService extends NamedEntityServiceImpl<Animal, Long> {

  @Autowired
  public AnimalService(AnimalRepository repository) {
    super(repository);
  }

  @Override
  public void deleteById(Long id) {
    repository.findById(id)
      .ifPresent(animal -> animal
              .getCategories()
              .forEach(animal::removeCategory)
      );
    super.deleteById(id);
  }
}
