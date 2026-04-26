package commerce.querymodel;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import commerce.Product;
import commerce.query.GetSellerProducts;
import commerce.result.ArrayCarrier;
import commerce.view.SellerProductView;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;

public class GetSellerProductsQueryProcessor {

    private final Function<UUID, List<Product>> getProductsOfSeller;

    public GetSellerProductsQueryProcessor(
        Function<UUID, List<Product>> getProductsOfSeller
    ) {
        this.getProductsOfSeller = getProductsOfSeller;
    }

    public ArrayCarrier<SellerProductView> process(GetSellerProducts query) {
        SellerProductView[] items = getProductsOfSeller
            .apply(query.sellerId())
            .stream()
            .sorted(comparing(Product::getRegisteredTimeUtc, reverseOrder()))
            .map(ProductMapper::convertToViewForSeller)
            .toArray(SellerProductView[]::new);
        return new ArrayCarrier<>(items);
    }
}
