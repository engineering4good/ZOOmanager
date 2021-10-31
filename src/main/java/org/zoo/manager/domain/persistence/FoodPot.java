package org.zoo.manager.domain.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import org.zoo.manager.domain.persistence.generic.EntityId;

@Setter
@Getter
@Entity
public class FoodPot implements EntityId<Long> {

  public FoodPot() {
  }

  public FoodPot(Food food, MeasurementUnit measurementUnit, Double storedAmount) {
    this.food = food;
    this.measurementUnit = measurementUnit;
    this.storedAmount = storedAmount;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "fk_food", nullable = false)
  private Food food;

  @ManyToOne
  @JoinColumn(name = "fk_measurement_unit", nullable = false)
  private MeasurementUnit measurementUnit;

  private Double storedAmount;

}
