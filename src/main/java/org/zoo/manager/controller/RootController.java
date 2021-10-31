package org.zoo.manager.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class RootController {

  @Value("${spring.application.name}")
  private String appName;
  private final static String documentationUrl = "/swagger-ui/index.html?url=";
  private final static String openApiSpecificationUrl = "/v3/api-docs/";
  private final static String h2ConsoleUrl = "/h2-console";
  private final static String apiWelcome = "Welcome to %s!<br/>" +
          "Explore <a href=\"%s%s\">API documentation</a>.<br/>" +
          "<a href=\"%s\">OpenAPI 3.0 specification</a><br/>" +
          "Debug <a href=\"%s\">H2 database</a>.";

  @GetMapping("*")
  public ResponseEntity<?> root() {
    return new ResponseEntity<>(String.format(apiWelcome,
                                              this.appName,
                                              documentationUrl,
                                              openApiSpecificationUrl,
                                              openApiSpecificationUrl,
                                              h2ConsoleUrl),
                                HttpStatus.OK);
  }
}
