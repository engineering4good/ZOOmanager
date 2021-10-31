package org.zoo.manager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zoo.manager.domain.persistence.FoodPot;
import org.zoo.manager.repository.FoodPotRepository;
import org.zoo.manager.service.generic.EntityServiceImpl;

@Service
public class FoodPotService extends EntityServiceImpl<FoodPot, Long> {

  private final FoodPotRepository repository;

  @Autowired
  public FoodPotService(FoodPotRepository repository) {
    super(repository);
    this.repository = repository;
  }

  public List<FoodPot> sumAllByFoodId() {
    return this.repository.findAll().stream()
            .collect(Collectors.groupingBy(foodPot -> foodPot.getFood().getId()))
            .values()
            .stream()
            .map(foodPotList -> {
              FoodPot foodPot = new FoodPot();
              foodPot.setFood(foodPotList.get(0).getFood());
              foodPot.setMeasurementUnit(foodPotList.get(0).getMeasurementUnit());
              foodPot.setStoredAmount(foodPotList.stream()
                      .mapToDouble(FoodPot::getStoredAmount)
                      .sum());
              return foodPot;
            })
            .toList();
  }

}
