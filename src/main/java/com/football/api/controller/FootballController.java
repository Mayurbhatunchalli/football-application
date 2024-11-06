package com.football.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.football.api.entity.Country;
import com.football.api.entity.League;
import com.football.api.service.FootballService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/football")
public class FootballController {

    @Autowired
    private FootballService footballService;

    @GetMapping("/countries/{countryId}")
    public Country getCountries(@PathVariable int countryId) {
    	
    	Mono<Country> countries = footballService.getCountries(countryId);
        return countries.block();
    }

    @GetMapping("/leagues/{country}/{league}")
    public League getLeagues(@PathVariable int country, @PathVariable int league ) {
        
    	Mono<League> leagues = footballService.getLeagues(country, league);
    	
    	return leagues.block();
    }

    @GetMapping("/teams/{leagueId}")
    public String getTeams(@PathVariable int leagueId) {
        
    	Mono<String> teams = footballService.getTeams(leagueId);
    	
    	return teams.block();
    }
}

