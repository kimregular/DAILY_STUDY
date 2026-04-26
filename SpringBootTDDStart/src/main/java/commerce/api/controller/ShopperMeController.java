package commerce.api.controller;

import java.security.Principal;
import java.util.UUID;

import commerce.Shopper;
import commerce.ShopperRepository;
import commerce.view.ShopperMeView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public record ShopperMeController(ShopperRepository repository) {

    @GetMapping("/shopper/me")
    ShopperMeView me(Principal user) {
        UUID id = UUID.fromString(user.getName());
        Shopper shopper = repository.findById(id).orElseThrow();
        return new ShopperMeView(id, shopper.getEmail(), shopper.getUsername());
    }
}
