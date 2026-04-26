package commerce.api.controller;

import java.security.Principal;
import java.util.UUID;

import commerce.Seller;
import commerce.SellerRepository;
import commerce.command.ChangeContactEmailCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static commerce.UserPropertyValidator.isEmailValid;

@RestController
public record SellerChangeContactEmailController(SellerRepository repository) {

    @PostMapping("/seller/changeContactEmail")
    ResponseEntity<?> changeContactEmail(
        @RequestBody ChangeContactEmailCommand command,
        Principal user
    ) {
        if (isEmailValid(command.contactEmail()) == false) {
            return ResponseEntity.badRequest().build();
        }

        UUID id = UUID.fromString(user.getName());
        Seller seller = repository.findById(id).orElseThrow();
        seller.setContactEmail(command.contactEmail());
        repository.save(seller);

        return ResponseEntity.noContent().build();
    }
}
