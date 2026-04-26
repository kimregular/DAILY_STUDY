package commerce.querymodel;

import commerce.Product;
import commerce.Seller;
import commerce.view.ProductView;
import commerce.view.SellerView;

record ProductSellerTuple(Product product, Seller seller) {

    ProductView toView() {
        return new ProductView(
            product().getId(),
            new SellerView(
                seller().getId(),
                seller().getUsername(),
                seller().getContactEmail()
            ),
            product().getName(),
            product().getImageUri(),
            product().getDescription(),
            product().getPriceAmount(),
            product().getStockQuantity()
        );
    }
}
