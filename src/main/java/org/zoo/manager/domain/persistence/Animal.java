package org.zoo.manager.domain.persistence;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import org.zoo.manager.domain.persistence.generic.NamedEntity;

@Setter
@Getter
@Entity
public class Animal implements NamedEntity<Long> {

  public Animal() {
  }

  public Animal(String name) {
    this.name = name;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @ManyToMany(mappedBy = "animals")
  private Set<AnimalCategory> categories;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "animal", orphanRemoval = true)
  private Set<FoodConsumption> foodConsumptions;

  public void addCategory(AnimalCategory category) {
    this.categories.add(category);
    category.getAnimals().add(this);
  }

  public void removeCategory(AnimalCategory category) {
    this.categories.remove(category);
    category.getAnimals().remove(this);
  }

}
