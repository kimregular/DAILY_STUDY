package commerce.querymodel;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import commerce.Product;
import commerce.query.FindSellerProduct;
import commerce.view.SellerProductView;

public class FindSellerProductQueryProcessor {

    private final Function<UUID, Optional<Product>> findProduct;

    public FindSellerProductQueryProcessor(
        Function<UUID, Optional<Product>> findProduct
    ) {
        this.findProduct = findProduct;
    }

    public Optional<SellerProductView> process(FindSellerProduct query) {
        return findProduct
            .apply(query.productId())
            .filter(product -> product.getSellerId().equals(query.sellerId()))
            .map(ProductMapper::convertToViewForSeller);
    }
}
