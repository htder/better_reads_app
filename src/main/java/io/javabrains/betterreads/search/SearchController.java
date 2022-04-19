package io.javabrains.betterreads.search;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SearchController {

    private final WebClient webClient;

    public SearchController(WebClient.Builder webClientBuilder) {
        this.webClient =
                webClientBuilder
                        .exchangeStrategies(
                                ExchangeStrategies.builder()
                                        .codecs(configurer ->
                                                configurer
                                                        .defaultCodecs()
                                                        .maxInMemorySize(16 * 1024 * 1024))
                                        .build())
                        .baseUrl("https://openlibrary.org/search.json")
                        .build();
    }

    @GetMapping(value = "/search")
    public String getSearchResults(@RequestParam String query, Model model) {
        Mono<SearchResult> resultsMono =
                this.webClient
                        .get()
                        .uri("?q={query}", query)
                        .retrieve()
                        .bodyToMono(SearchResult.class);
        SearchResult result = resultsMono.block();
        List<SearchResultBook> books = result.getDocs()
                                    .stream()
                                    .limit(10)
                                    .collect(Collectors.toList());
        model.addAttribute("searchResult", books);
        return "search";
    }
}
