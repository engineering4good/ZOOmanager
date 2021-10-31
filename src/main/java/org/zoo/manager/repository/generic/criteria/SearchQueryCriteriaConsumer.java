package org.zoo.manager.repository.generic.criteria;

import java.util.function.Consumer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class SearchQueryCriteriaConsumer<E> implements Consumer<SearchCriteria> {

  private Predicate predicate;
  private CriteriaBuilder builder;
  private Root<E> r;

  @Override
  public void accept(SearchCriteria param) {
    if (param.getOperation().equalsIgnoreCase(">")) {
      this.predicate = this.builder
              .and(this.predicate,
                      this.builder
                      .greaterThanOrEqualTo(this.r.get(param.getKey()),
                                            param.getValue().toString()));
    }
    else if (param.getOperation().equalsIgnoreCase("<")) {
      this.predicate = this.builder
              .and(this.predicate,
                   this.builder.lessThanOrEqualTo(this.r.get(param.getKey()),
                                                  param.getValue().toString()));
    }
    else if (param.getOperation().equalsIgnoreCase(":")) {
      if (this.r.get(param.getKey()).getJavaType() == String.class) {
        this.predicate = this.builder
                .and(this.predicate,
                     this.builder.like(this.r.get(param.getKey()),
                                "%" + param.getValue() + "%"));
      }
      else {
        this.predicate = this.builder
                .and(this.predicate,
                     this.builder.equal(this.r.get(param.getKey()),
                                        param.getValue()));
      }
    }
  }

}
