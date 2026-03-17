package com.example.UrlShortener.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.UrlShortener.Entity.UrlObject;

@Repository
public interface UrlRepository extends JpaRepository<UrlObject, String> {

    UrlObject findByShortCode(String shortCode);
    UrlObject findByOriginalUrl(String originalUrl);

}
