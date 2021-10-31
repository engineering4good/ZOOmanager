package org.zoo.manager.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zoo.manager.domain.persistence.FoodConsumption;
import org.zoo.manager.repository.FoodConsumptionRepository;
import org.zoo.manager.repository.generic.criteria.SearchCriteria;
import org.zoo.manager.service.generic.EntityServiceImpl;

@Service
public class FoodConsumptionService extends EntityServiceImpl<FoodConsumption, Long> {

  @Autowired
  public FoodConsumptionService(FoodConsumptionRepository repository) {
    super(repository);
  }

  public Optional<FoodConsumption> calculate(long id, int days) {
    Optional<FoodConsumption> foodConsumption = getById(id);
    foodConsumption.ifPresent(consumption -> multiply(consumption, days));
    return foodConsumption;
  }

  public List<FoodConsumption> calculate(List<Long> foodConsumptionIds, int days) {
    return foodConsumptionIds.stream()
            .map(this::getById)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .peek(consumption -> multiply(consumption, days))
            .toList();
  }

  public List<FoodConsumption> calculateWithFilter(List<SearchCriteria> searchCriteriaList, int days) {
    return calculateWithFilter(searchCriteriaList, days, true);
  }

  public List<FoodConsumption> calculateWithFilter(List<SearchCriteria> searchCriteriaList,
                                                   int days,
                                                   boolean aggregateByFood) {
    List<FoodConsumption> foodConsumptions = findByFilter(searchCriteriaList).stream()
                                                .peek(consumption -> multiply(consumption, days))
                                                .toList();
    if (aggregateByFood) {
      foodConsumptions = foodConsumptions.stream()
              .collect(Collectors.groupingBy(foodConsumption -> foodConsumption.getFood().getId()))
              .values()
              .stream()
              .map(consumptions -> {
                FoodConsumption foodConsumption = new FoodConsumption();
                foodConsumption.setFood(consumptions.get(0).getFood());
                foodConsumption.setMeasurementUnit(consumptions.get(0).getMeasurementUnit());
                foodConsumption.setConsumptionAmount(consumptions.stream()
                        .mapToDouble(FoodConsumption::getConsumptionAmount)
                        .sum());
                return foodConsumption;
              }).toList();
    }
    return foodConsumptions;
  }

  private void multiply(FoodConsumption foodConsumption, int days) {
    Double consumptionAmount = foodConsumption.getConsumptionAmount();
    if (Objects.nonNull(consumptionAmount)) {
      foodConsumption.setConsumptionAmount(consumptionAmount * days);
    }
  }
}

