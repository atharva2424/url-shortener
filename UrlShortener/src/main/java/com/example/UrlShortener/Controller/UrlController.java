package com.example.UrlShortener.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.UrlShortener.Service.UrlService;
import com.example.UrlShortener.Utility.Utility;

@RestController
@RequestMapping("/api/url")
public class UrlController {
    @Autowired
    private UrlService urlService;
    @PostMapping("/shorten")
    public String shorten(@RequestBody String url) {
        if(Utility.isValidURL(url)) {
            return urlService.shortenUrl(url);
        }
        else {
            return "Invalid URL";
        }
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortCode)
    {
        String longUrl = urlService.getOriginalUrl(shortCode);
        if(longUrl != null) {
            if(longUrl.equals("Link Expired"))
                return ResponseEntity.status(410).header("Message", "Linked Expired").build();
            return ResponseEntity.status(302).header("Location", longUrl).build();
        }
        else {
            return ResponseEntity.status(404).build();
        }
    }
}