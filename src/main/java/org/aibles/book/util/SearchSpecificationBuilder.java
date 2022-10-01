package org.aibles.book.util;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.aibles.book.component.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor
public class SearchSpecificationBuilder<T> {
  private final List<SearchCriteria> searchCriteriaList;

  public Specification<T> build() {
    if (searchCriteriaList.size() == 0) {
      return null;
    }

    List<Specification<T>> specifications =
        searchCriteriaList.stream()
            .map(variable -> new SearchSpecification<T>(variable))
            .collect(Collectors.toList());

    Specification<T> result = specifications.get(0);
    for (int i = 1; i < searchCriteriaList.size(); i++) {
      result = Specification
          .where(result)
          .and(specifications.get(i));
    }
    return result;
  }
}
