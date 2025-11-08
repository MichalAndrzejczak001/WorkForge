package spring.project.workforge.offerpreparation.service;

import spring.project.workforge.offerpreparation.model.dto.OfferCreateRequest;
import spring.project.workforge.offerpreparation.model.dto.OfferResponse;
import spring.project.workforge.offerpreparation.model.dto.OfferUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface IOfferService {
    public List<OfferResponse> findAll();

    public OfferResponse findById(Long id);
    public List<OfferResponse> findByTitle(String title);

    public OfferResponse createOffer(OfferCreateRequest offer);

    public OfferResponse updateOffer(Long id, OfferUpdateRequest offer);

    public void deleteOffer(Long id);
}
