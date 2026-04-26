package commerce.result;

public record PageCarrier<T>(T[] items, String continuationToken) {
}
