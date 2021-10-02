package com.metea.moneyanalysis.dto;


import com.metea.moneyanalysis.domain.OperationMaster;
import com.metea.moneyanalysis.util.SearchCriteria;
import com.metea.moneyanalysis.util.SearchCriteriaOptions;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode()
public class OperationMasterSearchCriteriaDTO {
    private Long id;
    private BigDecimal totalAmount;
    private Long userId;
    private String nameSurname;

    public SearchCriteriaOptions<OperationMaster> OperationMasterSearchCriteriaFieldMapper(OperationMasterSearchCriteriaDTO filter) {
        final var searchCriteriaOptions = new SearchCriteriaOptions<OperationMaster>();
        searchCriteriaOptions.add(new SearchCriteria("id", filter.getId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("totalAmount", filter.getTotalAmount(), SearchCriteria.SearchOperation.LESS_THAN_EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("userDetail/id", filter.getUserId(), SearchCriteria.SearchOperation.LESS_THAN_EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("userDetail/nameSurname", filter.getNameSurname(), SearchCriteria.SearchOperation.LIKE));
        return searchCriteriaOptions;
    }

}
