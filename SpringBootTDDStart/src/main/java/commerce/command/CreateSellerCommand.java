package commerce.command;

public record CreateSellerCommand(
    String email,
    String username,
    String password,
    String contactEmail
) {
}
