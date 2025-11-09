package spring.project.workforge.offerpreparation.offer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.project.workforge.offerpreparation.offer.model.entity.Offer;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long > {
    public List<Offer> findByTitle(String title);
}
