package org.zoo.manager.mapper;

import java.util.Objects;
import java.util.Set;

import org.modelmapper.Converter;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import org.zoo.manager.domain.persistence.Animal;
import org.zoo.manager.domain.persistence.Food;
import org.zoo.manager.domain.persistence.FoodCategory;
import org.zoo.manager.domain.persistence.FoodConsumption;
import org.zoo.manager.dto.AnimalDTO;

@Service
public class AnimalModelMapper extends NestedModelMapper {
  private static final String MEAT_STRING = "мясо";

  protected void init() {
    TypeMap<Animal, AnimalDTO> typeMap = createTypeMap(Animal.class, AnimalDTO.class);
    typeMap.setPostConverter(new AnimalPostConverter());
  }

  private static class AnimalPostConverter implements Converter<Animal, AnimalDTO> {

    @Override
    public AnimalDTO convert(MappingContext<Animal, AnimalDTO> context) {
      AnimalDTO destination = context.getDestination();
      destination.setPredator(isPredator(context.getSource()));
      return destination;
    }

    private boolean isPredator(Animal entity) {
      if (Objects.isNull(entity)) {
        return false;
      }
      Set<FoodConsumption> foodConsumptions = entity.getFoodConsumptions();
      if (Objects.isNull(foodConsumptions) || foodConsumptions.isEmpty()) {
        return false;
      }
      for (FoodConsumption foodConsumption : foodConsumptions) {
        Food food = foodConsumption.getFood();
        if (Objects.nonNull(food)) {
          Set<FoodCategory> categories = food.getCategories();
          if (Objects.nonNull(categories) && !categories.isEmpty()) {
            for (FoodCategory category : categories) {
              String categoryName = category.getName();
              if (Objects.nonNull(categoryName) && categoryName.equals(MEAT_STRING)) {
                return true;
              }
            }
          }
        }
      }
      return false;
    }

  }
}
