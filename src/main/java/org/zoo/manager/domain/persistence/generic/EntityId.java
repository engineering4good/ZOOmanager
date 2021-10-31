package org.zoo.manager.domain.persistence.generic;

public interface EntityId<ID> {

  ID getId();
  void setId(ID id);

}
