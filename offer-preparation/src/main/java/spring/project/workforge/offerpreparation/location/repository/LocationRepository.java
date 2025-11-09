package spring.project.workforge.offerpreparation.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.project.workforge.offerpreparation.location.model.entity.Location;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByLatitudeAndLongitude(double latitude, double longitude);
}
