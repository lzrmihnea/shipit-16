package eu.accesa.shopit.repository;

import eu.accesa.shopit.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
