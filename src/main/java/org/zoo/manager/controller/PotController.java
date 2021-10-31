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
import org.zoo.manager.domain.persistence.FoodPot;
import org.zoo.manager.dto.FoodPotDTO;
import org.zoo.manager.mapper.NestedModelMapper;
import org.zoo.manager.service.FoodPotService;

@RestController
@RequestMapping("/pots")
public class PotController extends EntityController<FoodPot, Long, FoodPotDTO> {


  @Autowired
  public PotController(FoodPotService foodPotService,
                       NestedModelMapper modelMapper) {
    super(foodPotService, modelMapper, FoodPotDTO.class, FoodPot.class);
  }

  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<FoodPotDTO> list(@RequestParam(required = false) final Map<String, String> params) {
    return super.list(params);
  }

  @GetMapping(value = "/id{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public FoodPotDTO get(@PathVariable final Long id) {
    return super.getById(id);
  }

  @DeleteMapping(value = "/id{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public void delete(@PathVariable final Long id) {
    super.deleteById(id);
  }

  @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
  public FoodPotDTO add(@RequestBody FoodPotDTO dto) {
    return super.add(dto);
  }

  @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
  public FoodPotDTO update(@RequestBody FoodPotDTO dto) {
    Long id = dto.getId();
    return super.update(id, dto);
  }

  @PutMapping(value = "/id{id}/update", produces = MediaType.APPLICATION_JSON_VALUE)
  public FoodPotDTO update(@PathVariable final Long id, @RequestBody FoodPotDTO dto) {
    return super.update(id, dto);
  }

}
