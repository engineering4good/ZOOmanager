package org.zoo.manager.domain.persistence;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.zoo.manager.domain.persistence.generic.NamedEntity;

@Setter
@Getter
@Entity
public class AnimalCategory implements NamedEntity<Long> {

  public AnimalCategory() {
  }

  public AnimalCategory(String name) {
    this.name = name;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String name;

  @JsonIgnore
  @ManyToMany
  private Set<Animal> animals;

  public void addAnimal(Animal animal) {
    this.animals.add(animal);
    animal.getCategories().add(this);
  }

  public void removeAnimal(Animal animal) {
    this.animals.remove(animal);
    animal.getCategories().remove(this);
  }

}
