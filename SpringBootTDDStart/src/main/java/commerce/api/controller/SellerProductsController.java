package commerce.api.controller;

import java.net.URI;
import java.security.Principal;
import java.util.UUID;

import commerce.ProductRepository;
import commerce.command.RegisterProductCommand;
import commerce.commandmodel.RegisterProductCommandExecutor;
import commerce.query.FindSellerProduct;
import commerce.query.GetSellerProducts;
import commerce.querymodel.FindSellerProductQueryProcessor;
import commerce.querymodel.GetSellerProductsQueryProcessor;
import commerce.result.ArrayCarrier;
import commerce.view.SellerProductView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public record SellerProductsController(ProductRepository repository) {

    @PostMapping("/seller/products")
    ResponseEntity<?> registerProduct(
        @RequestBody RegisterProductCommand command,
        Principal user
    ) {
        UUID id = UUID.randomUUID();
        var executor = new RegisterProductCommandExecutor(repository::save);
        executor.execute(id, UUID.fromString(user.getName()), command);
        URI location = URI.create("/seller/products/" + id);
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/seller/products/{id}")
    ResponseEntity<?> findProduct(@PathVariable UUID id, Principal user) {
        var processor = new FindSellerProductQueryProcessor(repository::findById);
        var query = new FindSellerProduct(UUID.fromString(user.getName()), id);
        return ResponseEntity.of(processor.process(query));
    }

    @GetMapping("/seller/products")
    ArrayCarrier<SellerProductView> getProducts(Principal user) {
        var processor = new GetSellerProductsQueryProcessor(repository::findBySellerId);
        var query = new GetSellerProducts(UUID.fromString(user.getName()));
        return processor.process(query);
    }
}
