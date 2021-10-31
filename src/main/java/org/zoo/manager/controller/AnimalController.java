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
import org.zoo.manager.controller.generic.NamedEntityController;
import org.zoo.manager.domain.persistence.Animal;
import org.zoo.manager.dto.AnimalDTO;
import org.zoo.manager.mapper.AnimalModelMapper;
import org.zoo.manager.service.AnimalService;

@RestController
@RequestMapping("/animals")
public class AnimalController extends NamedEntityController<Animal, Long, AnimalDTO> {

  @Autowired
  public AnimalController(AnimalService entityService,
                          AnimalModelMapper modelMapper) {
    super(entityService, modelMapper, AnimalDTO.class, Animal.class);
  }

  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<AnimalDTO> list(@RequestParam(required = false) final Map<String, String> params) {
    return super.list(params);
  }

  @GetMapping(value = "/id{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public AnimalDTO get(@PathVariable final Long id) {
    return super.getById(id);
  }

  @DeleteMapping(value = "/id{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public void delete(@PathVariable final Long id) {
    super.deleteById(id);
  }

  @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
  public AnimalDTO get(@PathVariable final String name) {
    return super.getByName(name);
  }

  @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
  public AnimalDTO add(@RequestBody AnimalDTO dto) {
    return super.add(dto);
  }

  @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
  public AnimalDTO update(@RequestBody AnimalDTO dto) {
    Long id = dto.getId();
    return super.update(id, dto);
  }

  @PutMapping(value = "/id{id}/update", produces = MediaType.APPLICATION_JSON_VALUE)
  public AnimalDTO update(@PathVariable final Long id, @RequestBody AnimalDTO dto) {
    return super.update(id, dto);
  }

}
