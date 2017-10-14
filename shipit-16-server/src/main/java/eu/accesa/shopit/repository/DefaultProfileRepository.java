package eu.accesa.shopit.repository;

import eu.accesa.shopit.model.entity.DefaultProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DefaultProfileRepository extends JpaRepository<DefaultProfile, Integer> {

    Integer findByName(String name);
}
