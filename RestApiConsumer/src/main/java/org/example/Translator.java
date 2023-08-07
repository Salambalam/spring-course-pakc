package org.example;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class Translator {
    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://api.kinopoisk.dev/v1.3/movie?sortField=year&sortType=1&page=1&limit=3&name=Человек паук";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("X-API-KEY", "PCS47X6-DH7MC14-PMZ1EM6-GN8Q50N");

        HttpEntity<?> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        String response = responseEntity.toString();

        System.out.println(response);
    }
}
