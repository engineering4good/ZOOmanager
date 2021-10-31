package org.zoo.manager.domain.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import org.zoo.manager.domain.persistence.generic.NamedEntity;

@Setter
@Getter
@Entity
public class MeasurementUnit implements NamedEntity<Long> {

  public MeasurementUnit() {
  }

  public MeasurementUnit(String name) {
    this.name = name;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String name;

}
