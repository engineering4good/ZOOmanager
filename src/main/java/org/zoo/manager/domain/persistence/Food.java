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
public class Food implements NamedEntity<Long> {

  public Food() {
  }

  public Food(String name) {
    this.name = name;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String name;

  @ManyToMany(mappedBy = "foods")
  private Set<FoodCategory> categories;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "food", orphanRemoval = true)
  private Set<FoodConsumption> foodConsumptions;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "food", orphanRemoval = true)
  private Set<FoodPot> foodPots;


  public void addCategory(FoodCategory category) {
    this.categories.add(category);
    category.getFoods().add(this);
  }

  public void removeCategory(FoodCategory category) {
    this.categories.remove(category);
    category.getFoods().remove(this);
  }

}
