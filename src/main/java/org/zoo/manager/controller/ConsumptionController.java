package org.zoo.manager.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zoo.manager.controller.generic.EntityController;
import org.zoo.manager.domain.persistence.FoodConsumption;
import org.zoo.manager.dto.FoodConsumptionDTO;
import org.zoo.manager.mapper.NestedModelMapper;
import org.zoo.manager.service.FoodConsumptionService;

@RestController
@RequestMapping("/consumptions")
public class ConsumptionController extends EntityController<FoodConsumption, Long, FoodConsumptionDTO> {


  @Autowired
  public ConsumptionController(FoodConsumptionService foodConsumptionService,
                               NestedModelMapper modelMapper) {
    super(foodConsumptionService, modelMapper, FoodConsumptionDTO.class, FoodConsumption.class);
  }

  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<FoodConsumptionDTO> list(@RequestParam(required = false) final Map<String, String> params) {
    return super.list(params);
  }

  @GetMapping(value = "/id{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public FoodConsumptionDTO get(@PathVariable final Long id) {
    return super.getById(id);
  }

  @DeleteMapping(value = "/id{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public void delete(@PathVariable final Long id) {
    super.deleteById(id);
  }

  @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
  public FoodConsumptionDTO add(@RequestBody FoodConsumptionDTO dto) {
    return super.add(dto);
  }

  @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
  public FoodConsumptionDTO update(@RequestBody FoodConsumptionDTO dto) {
    Long id = dto.getId();
    return super.update(id, dto);
  }

  @PutMapping(value = "/id{id}/update", produces = MediaType.APPLICATION_JSON_VALUE)
  public FoodConsumptionDTO update(@PathVariable final Long id, @RequestBody FoodConsumptionDTO dto) {
    return super.update(id, dto);
  }

}
