package com.dashboard.ipldashboard.controller;

import com.dashboard.ipldashboard.model.Match;
import com.dashboard.ipldashboard.model.Team;
import com.dashboard.ipldashboard.repository.MatchRepository;
import com.dashboard.ipldashboard.repository.TeamRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TeamController {

	private TeamRepository teamRepository;
	private MatchRepository matchRepository;

	@Autowired
	public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
		this.teamRepository = teamRepository;
		this.matchRepository = matchRepository;
	}

	@GetMapping("/teams")
	public Iterable<Team> getAllTeam() {
		return this.teamRepository.findAll();
	}

	@GetMapping("/teams/{teamName}")
	public Team getTeam(@PathVariable String teamName) {
		Team team = this.teamRepository.findByTeamName(teamName);
		team.setMatches(this.matchRepository.findLatestMatchesByTeam(teamName, 4));
		return team;
	}

	@GetMapping("/teams/{teamName}/matches")
	public List<Match> getTeamMatches(@PathVariable String teamName, @RequestParam int year) {
		LocalDate startDate = LocalDate.of(year, 1, 1);
		LocalDate endDate = LocalDate.of(year + 1, 1, 1);
		return this.matchRepository.getMatchesByTeamBetweenDates(teamName, startDate, endDate);
	}
}
