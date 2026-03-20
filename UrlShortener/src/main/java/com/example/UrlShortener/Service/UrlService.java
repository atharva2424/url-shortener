package com.example.UrlShortener.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UrlShortener.Entity.UrlObject;
import com.example.UrlShortener.Repository.UrlRepository;
import com.example.UrlShortener.Utility.Utility;

@Service
public class UrlService {
    
    @Autowired
    private UrlRepository urlRepository;

    public String shortenUrl(String originalUrl)
    {
        UrlObject existingObject = urlRepository.findByOriginalUrl(originalUrl);
        if(existingObject !=null)
        {
            LocalDateTime now = LocalDateTime.now();
            if(existingObject.getExpirationAt().isAfter(now))
            {
                return existingObject.getShortCode();
            }
            else
            {
                existingObject.setStatus("Expired");
                urlRepository.save(existingObject);
            }
        }
        String shortCode = Utility.generateShortCode();
        LocalDateTime now = LocalDateTime.now();
        // LocalDateTime expirationTime = now.plusDays(30);
        LocalDateTime expirationTime = now.plusMinutes(5);
        UrlObject object = new UrlObject(shortCode, originalUrl, now, expirationTime, "active", 0L);
        urlRepository.save(object);
        return shortCode;
    }

    public String getOriginalUrl(String shortCode) {
        UrlObject object = urlRepository.findByShortCode(shortCode);
        if(object != null) 
            {
                LocalDateTime now = LocalDateTime.now();
                if(object.getExpirationAt().isAfter(now))
                {
                    object.setClickCount(object.getClickCount() + 1);
                    urlRepository.save(object);
                    return object.getOriginalUrl();
                } 
                else
                {
                    object.setStatus("Expired");
                    urlRepository.save(object);
                    return "Link Expired";
                }
            }
            else
                return null;
    }
}
