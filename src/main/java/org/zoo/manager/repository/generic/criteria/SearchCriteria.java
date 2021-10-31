package org.zoo.manager.repository.generic.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SearchCriteria {

  private final String key;
  private final String operation;
  private final Object value;

}
