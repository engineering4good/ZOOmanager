package org.zoo.manager.domain.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;
import org.zoo.manager.domain.persistence.generic.EntityId;

@Setter
@Getter
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueAnimalAndFood",
                                               columnNames = { "fk_animal", "fk_food" }) })
public class FoodConsumption implements EntityId<Long> {

  public FoodConsumption() {
  }

  public FoodConsumption(Animal animal, Food food, MeasurementUnit measurementUnit, Double consumptionAmount) {
    this.animal = animal;
    this.food = food;
    this.measurementUnit = measurementUnit;
    this.consumptionAmount = consumptionAmount;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "fk_animal", nullable = false)
  private Animal animal;

  @ManyToOne
  @JoinColumn(name = "fk_food", nullable = false)
  private Food food;

  @ManyToOne
  @JoinColumn(name = "fk_measurement_unit", nullable = false)
  private MeasurementUnit measurementUnit;

  private Double consumptionAmount;

}
