package org.zoo.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zoo.manager.domain.FutureBalance;
import org.zoo.manager.dto.FutureBalanceDTO;
import org.zoo.manager.mapper.NestedModelMapper;
import org.zoo.manager.service.BalanceService;

@RestController
@RequestMapping("/balance")
public class FutureBalanceController {

  private final BalanceService balanceService;
  private final NestedModelMapper modelMapper;

  @Autowired
  public FutureBalanceController(BalanceService balanceService,
                                 NestedModelMapper modelMapper) {
    this.balanceService = balanceService;
    this.modelMapper = modelMapper;
  }

  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<FutureBalanceDTO> calc(@RequestParam(defaultValue = "1") Integer days) {
    List<FutureBalance> futureBalances = this.balanceService.calc(days);
    return futureBalances.stream()
            .map(this::mapToDto)
            .toList();
  }

  protected FutureBalanceDTO mapToDto(FutureBalance entity) {
    return this.modelMapper.map(entity, FutureBalanceDTO.class);
  }

}
