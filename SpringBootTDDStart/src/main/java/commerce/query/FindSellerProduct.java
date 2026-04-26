package commerce.query;

import java.util.UUID;

public record FindSellerProduct(UUID sellerId, UUID productId) {
}
