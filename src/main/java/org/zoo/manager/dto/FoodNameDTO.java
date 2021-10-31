package org.zoo.manager.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FoodNameDTO {

  private Long id;
  private String name;
  private Set<FoodCategoryNameDTO> categories;

}
