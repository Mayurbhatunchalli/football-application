package com.football.api.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.football.api.entity.Country;
import com.football.api.entity.League;

import reactor.core.publisher.Mono;

@Service
public class FootballService {

	@Autowired
	private WebClient webClient;

	@Value("${api.football.key}")
	private String apiKey;

	public Mono<Country> getCountries(int countryId) {

		Mono<Country> countries = webClient.get().uri(uriBuilder -> {
			URI uri = uriBuilder.queryParam("action", "get_countries").queryParam("APIkey", apiKey).build();
			return uri;
		}).retrieve().bodyToFlux(Country.class).filter(country -> countryId == country.getCountry_id()).next();

		return countries;
	}

	public Mono<League> getLeagues(int countryId, int leagueId) {
		return webClient.get().uri(uriBuilder -> {
			URI uri = uriBuilder.queryParam("action", "get_leagues").queryParam("country_id", countryId)
					.queryParam("APIkey", apiKey).build();
			return uri;
		}).retrieve().bodyToFlux(League.class)
				.filter(league -> countryId == league.getCountry_id() && leagueId == league.getLeague_id()).next();
	}

	public Mono<String> getTeams(int leagueId) {
		return webClient.get().uri(uriBuilder -> {
			URI uri = uriBuilder.queryParam("action", "get_teams").queryParam("league_id", leagueId)
					.queryParam("APIkey", apiKey).build();

			System.out.println("URI " + uri);
			return uri;
		}).retrieve().bodyToMono(String.class);
	}
}
