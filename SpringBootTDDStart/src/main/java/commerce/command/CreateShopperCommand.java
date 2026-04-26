package commerce.command;

public record CreateShopperCommand(
    String email,
    String username,
    String password
) {
}
