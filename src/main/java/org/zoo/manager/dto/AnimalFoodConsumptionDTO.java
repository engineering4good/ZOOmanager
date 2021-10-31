package org.zoo.manager.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AnimalFoodConsumptionDTO {

  private Long id;
  private FoodDTO food;
  private MeasurementUnitDTO measurementUnit;
  private Double consumptionAmount;

}
