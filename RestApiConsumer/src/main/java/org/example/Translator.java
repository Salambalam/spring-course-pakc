package org.example;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

public class Translator {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String sentenceToTranslate = scanner.nextLine();
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://translate.api.cloud.yandex.net/translate/v2/translate";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + )


    }
}
