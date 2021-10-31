package org.zoo.manager.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FoodConsumptionDTO {

  private Long id;
  private AnimalDTO animal;
  private FoodDTO food;
  private MeasurementUnitDTO measurementUnit;
  private Double consumptionAmount;

}
