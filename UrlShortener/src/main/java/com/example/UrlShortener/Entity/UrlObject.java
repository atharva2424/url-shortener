package com.example.UrlShortener.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "urlObject")
public class UrlObject {
    @Id
    String shortCode;
    @Column(unique = true)
    String originalUrl;
    LocalDateTime createdAt;
    LocalDateTime expirationAt;
    String status; 
    Long clickCount;
}
