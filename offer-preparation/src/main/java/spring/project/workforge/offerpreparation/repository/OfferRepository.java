package spring.project.workforge.offerpreparation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.project.workforge.offerpreparation.model.entity.Offer;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long > {
    public List<Offer> findByTitle(String title);
}
