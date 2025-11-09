package spring.project.workforge.offerpreparation.offer.service;

import spring.project.workforge.offerpreparation.offer.model.dto.OfferCreateRequest;
import spring.project.workforge.offerpreparation.offer.model.dto.OfferResponse;
import spring.project.workforge.offerpreparation.offer.model.dto.OfferUpdateRequest;

import java.util.List;

public interface IOfferService {
    public List<OfferResponse> findAll();

    public OfferResponse findById(Long id);
    public List<OfferResponse> findByTitle(String title);

    public OfferResponse createOffer(OfferCreateRequest request);

    public OfferResponse updateOffer(Long id, OfferUpdateRequest request);

    public void deleteOffer(Long id);
}
