package org.example;

import java.util.List;

public class KinopoiskResponse {
    private List<Movie> docs;

    public List<Movie> getDocs() {
        return docs;
    }

    public void setDocs(List<Movie> docs) {
        this.docs = docs;
    }
}
