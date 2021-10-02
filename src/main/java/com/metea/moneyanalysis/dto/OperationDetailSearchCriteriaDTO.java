package com.metea.moneyanalysis.dto;

import com.metea.moneyanalysis.domain.OperationDetail;
import com.metea.moneyanalysis.util.SearchCriteria;
import com.metea.moneyanalysis.util.SearchCriteriaOptions;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode()
public class OperationDetailSearchCriteriaDTO extends OperationDetailReadDTO {

    public SearchCriteriaOptions<OperationDetail> OperationMasterSearchCriteriaFieldMapper(OperationDetailSearchCriteriaDTO filter) {
        final var searchCriteriaOptions = new SearchCriteriaOptions<OperationDetail>();
        searchCriteriaOptions.add(new SearchCriteria("value", filter.getValue(), SearchCriteria.SearchOperation.LESS_THAN_EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("operationType", filter.getOperationType(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("description", filter.getDescription(), SearchCriteria.SearchOperation.LIKE));
        searchCriteriaOptions.add(new SearchCriteria("operationMaster/id", filter.getMasterId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("id", filter.getId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("operationMaster/totalAmount", filter.getTotalAmount(), SearchCriteria.SearchOperation.LESS_THAN_EQUAL));

        return searchCriteriaOptions;
    }
}
