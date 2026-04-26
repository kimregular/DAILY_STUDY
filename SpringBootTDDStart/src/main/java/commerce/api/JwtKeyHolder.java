package commerce.api;

import javax.crypto.SecretKey;

public record JwtKeyHolder(SecretKey key) {
}
