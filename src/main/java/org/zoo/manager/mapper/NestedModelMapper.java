package org.zoo.manager.mapper;

import org.modelmapper.ModelMapper;

public class NestedModelMapper extends ModelMapper {
  public NestedModelMapper() {
    super();
    _init();
    init();
  }

  private void _init() {
    getConfiguration().setPreferNestedProperties(false);
  }

  protected void init() {
  }
}
