package sample.cafekiosk.spring.api.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRespository;
import sample.cafekiosk.spring.domain.product.ProductSellingType;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRespository productRespository;

    public List<ProductResponse> getSellingProducts() {
        List<Product> products = productRespository.findAllBySellingTypeIn(ProductSellingType.forDisplay());
        return products.stream().map(ProductResponse::of).toList();
    }
}
