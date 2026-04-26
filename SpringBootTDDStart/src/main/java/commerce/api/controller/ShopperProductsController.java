package commerce.api.controller;

import commerce.query.GetProductPage;
import commerce.querymodel.GetProductPageQueryProcessor;
import commerce.result.PageCarrier;
import commerce.view.ProductView;
import jakarta.persistence.EntityManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public record ShopperProductsController(EntityManager entityManager) {

    @GetMapping("/shopper/products")
    PageCarrier<ProductView> getProducts(
        @RequestParam(required = false) String continuationToken
    ) {
        var processor = new GetProductPageQueryProcessor(entityManager);
        var query = new GetProductPage(continuationToken);
        return processor.process(query);
    }
}
