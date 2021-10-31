package org.zoo.manager.domain.persistence;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;
import org.zoo.manager.domain.persistence.generic.NamedEntity;

@Setter
@Getter
@Entity
public class FoodCategory implements NamedEntity<Long> {

  public FoodCategory() {
  }

  public FoodCategory(String name) {
    this.name = name;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String name;

  @ManyToMany
  private Set<Food> foods;

  public void addFood(Food food) {
    this.foods.add(food);
    food.getCategories().add(this);
  }

  public void removeFood(Food food) {
    this.foods.remove(food);
    food.getCategories().remove(this);
  }

}
