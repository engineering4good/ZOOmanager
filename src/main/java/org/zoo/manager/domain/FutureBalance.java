package org.zoo.manager.domain;

import java.util.Objects;

import lombok.Data;
import org.zoo.manager.domain.persistence.Food;
import org.zoo.manager.domain.persistence.FoodConsumption;
import org.zoo.manager.domain.persistence.FoodPot;
import org.zoo.manager.domain.persistence.MeasurementUnit;

@Data
public class FutureBalance {

  private Integer days;
  private Food food;
  private MeasurementUnit measurementUnit;
  private Double presentAmount;
  private Double consumptionAmount;
  private Double remainedAmount;

  public FutureBalance(FoodConsumption foodConsumption, FoodPot foodPot, Integer days) {
    if (Objects.nonNull(foodConsumption) && Objects.nonNull(foodPot)) {
      if (!foodConsumption.getFood().getId().equals(foodPot.getFood().getId())) {
        throw new IllegalStateException("Foods can't be merged.");
      }
      if (!foodConsumption.getMeasurementUnit().getId().equals(foodPot.getMeasurementUnit().getId())) {
        throw new IllegalStateException("Can't convert measurement units.");
      }
    }

    this.days = days;
    if (Objects.nonNull(foodConsumption)) {
      this.food = foodConsumption.getFood();
      this.measurementUnit = foodConsumption.getMeasurementUnit();
      this.consumptionAmount = foodConsumption.getConsumptionAmount();
    }
    if (Objects.nonNull(foodPot)) {
      this.food = foodPot.getFood();
      this.measurementUnit = foodPot.getMeasurementUnit();
      this.presentAmount = foodPot.getStoredAmount();
    }
    if (Objects.isNull(this.consumptionAmount)) {
      this.consumptionAmount = 0.0;
    }
    if (Objects.isNull(this.presentAmount)) {
      this.presentAmount = 0.0;
    }
    this.remainedAmount = this.presentAmount - this.consumptionAmount;
  }
}
