package kr.co.shortenurlservice.domain;

import java.util.List;

public interface ShortenUrlRepository {
    void saveShortenUrl(ShortenUrl shortenUrl);
    ShortenUrl findShortenUrlByShortenUrlKey(String shortenUrlKey);

    List<ShortenUrl> findAll();
}
