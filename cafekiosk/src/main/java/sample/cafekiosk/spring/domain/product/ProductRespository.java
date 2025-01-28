package sample.cafekiosk.spring.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRespository extends JpaRepository<Product, Long> {

    List<Product> findAllBySellingStatusIn(Collection<ProductSellingStatus> sellingStatuses);
}
