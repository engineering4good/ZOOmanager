package org.zoo.manager.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FoodPotDTO {

  private Long id;
  private FoodNameDTO food;
  private MeasurementUnitDTO measurementUnit;
  private Double storedAmount;

}
