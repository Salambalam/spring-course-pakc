package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class MovieFounder {
    public static void main(String[] args) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://api.kinopoisk.dev/v1.3/movie?sortField=year&sortType=1&page=1&limit=3&name=Человек паук";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("X-API-KEY", "PCS47X6-DH7MC14-PMZ1EM6-GN8Q50N");

        HttpEntity<?> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<KinopoiskResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, KinopoiskResponse.class);
        KinopoiskResponse response = responseEntity.getBody();

        for(Movie movie: response.getDocs()){
            System.out.println(movie.getName() + " - " + movie.getYear() + " - " + movie.getType());
        }


    }
}
