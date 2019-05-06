package com.football.manager.service;

import com.football.manager.model.Player;
import com.football.manager.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeamService {

    Team save(Team team);

    Page<Team> findAll(Pageable pageable);

    Team findById(Long teamId);

    Team update(Team team);

    void deleteById(Long teamId);

    Team addPlayer(Long teamId, Player player);

    Team removePlayer(Long teamId, Long playerId);

    Team assignCaptain(Long teamId, Long captainId);
}