package io.javabrains.betterreads.search;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Controller
public class SearchController {

    private final WebClient webClient;

    public SearchController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://openlibrary.org/search.json")
                .build();
    }

    public String getSearchResults(@RequestParam String query) {
        Mono<String> foo = this.webClient.get()
                .uri("?q={query}", query)
                .retrieve()
                .bodyToMono(String.class);
        String result = foo.block();
        return "search";
    }
}
