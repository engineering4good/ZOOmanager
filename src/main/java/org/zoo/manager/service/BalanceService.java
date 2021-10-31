package org.zoo.manager.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zoo.manager.domain.FutureBalance;
import org.zoo.manager.domain.persistence.Food;
import org.zoo.manager.domain.persistence.FoodConsumption;
import org.zoo.manager.domain.persistence.FoodPot;

@Service
public class BalanceService {

  @Autowired
  FoodConsumptionService foodConsumptionService;
  @Autowired
  FoodPotService foodPotService;


  public List<FutureBalance> calc(int days) {

    Map<Food, FoodConsumption> foodConsumptions
            = this.foodConsumptionService.calculateWithFilter(null, days).stream()
                .collect(Collectors.toMap(FoodConsumption::getFood, Function.identity()));

    Map<Food, FoodPot> foodPots = this.foodPotService.sumAllByFoodId().stream()
            .collect(Collectors.toMap(FoodPot::getFood, Function.identity()));

    Set<Food> foods = new HashSet<>(foodConsumptions.keySet());
    foods.addAll(foodConsumptions.keySet());

    return foods.stream()
            .map(food -> new FutureBalance(foodConsumptions.getOrDefault(food, null),
                                           foodPots.getOrDefault(food, null),
                                           days))
            .toList();
  }

}
