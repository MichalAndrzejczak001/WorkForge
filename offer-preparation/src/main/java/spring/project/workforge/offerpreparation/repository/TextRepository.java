package spring.project.workforge.offerpreparation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.project.workforge.offerpreparation.model.Text;

public interface TextRepository extends JpaRepository<Text, Long> {
}
