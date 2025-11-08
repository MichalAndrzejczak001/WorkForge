package spring.project.workforge.offerpreparation.model.excepitons;


import spring.project.workforge.offerpreparation.model.entity.Offer;

public class OfferNotFoundException extends RuntimeException {
    public OfferNotFoundException(String message) {
        super(message);
    }
}
