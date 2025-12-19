package spring.project.workforge.offerpreparation.offer.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spring.project.workforge.offerpreparation.offer.model.dto.SalaryRangeRequest;
import spring.project.workforge.offerpreparation.offer.model.dto.SalaryRangeResponse;
import spring.project.workforge.offerpreparation.offer.model.entity.SalaryRange;

@Component
public class SalaryRangeMapper {
    public SalaryRange toSalaryRange(SalaryRangeRequest request) {
        if (request == null) {
            return null;
        }

        SalaryRange salary = new SalaryRange();
        salary.setMin(request.min());
        salary.setMax(request.max());
        salary.setCurrency(request.currency());

        return salary;
    }

    public SalaryRangeResponse toSalaryRangeResponse(SalaryRange salary) {
        if (salary == null) {
            return null;
        }

        return new SalaryRangeResponse(
                salary.getMin(),
                salary.getMax(),
                salary.getCurrency()
        );
    }
}
