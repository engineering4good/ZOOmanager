package org.zoo.manager.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FutureBalanceDTO {

  private int days;
  private FoodNameDTO food;
  private MeasurementUnitDTO measurementUnit;
  private Double presentAmount;
  private Double consumptionAmount;
  private Double remainedAmount;

}
