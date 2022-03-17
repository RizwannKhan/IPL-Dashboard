package com.dashboard.ipldashboard.repository;

import com.dashboard.ipldashboard.model.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long> {
  Team findByTeamName(String teamName);
}
