package com.dashboard.ipldashboard.controller;

import com.dashboard.ipldashboard.model.Team;
import com.dashboard.ipldashboard.repository.MatchRepository;
import com.dashboard.ipldashboard.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TeamController {

  private TeamRepository teamRepository;
  private MatchRepository matchRepository;

  @Autowired
  public TeamController(
    TeamRepository teamRepository,
    MatchRepository matchRepository
  ) {
    this.teamRepository = teamRepository;
    this.matchRepository = matchRepository;
  }

  @GetMapping("/team/{teamName}")
  public Team getTeam(@PathVariable String teamName) {
    Team team = this.teamRepository.findByTeamName(teamName);
    team.setMatches(this.matchRepository.findLatestMatchesByTeam(teamName, 4));
    return team;
  }
}
