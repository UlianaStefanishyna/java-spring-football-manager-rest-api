package com.football.manager.service.impl;

import com.football.manager.model.Player;
import com.football.manager.model.Team;
import com.football.manager.repository.TeamRepository;
import com.football.manager.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.football.manager.exception.*;

import javax.persistence.PersistenceException;

import static java.util.Optional.ofNullable;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Override
    public Team save(Team team) {
        return ofNullable(this.teamRepository.save(team))
                .orElseThrow(() -> new PersistenceException("Exception occurred while trying to save new team data"));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Team> findAll(Pageable pageable) {
        return this.teamRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Team findById(Long teamId) {
        return this.teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team with id '" + teamId + "' was not found"));
    }

    @Override
    public Team update(Team team) {
        return null;
    }

    @Override
    public void deleteById(Long teamId) {
        this.teamRepository.deleteById(teamId);
    }

    @Override
    public Team addPlayer(Long teamId, Player player) {
        Team team = this.teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team with id '" + teamId + "' was not found"));
        team.addPlayer(player);
        return team;
    }

    @Override
    public Team removePlayer(Long teamId, Player player) {
        Team team = this.teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team with id '" + teamId + "' was not found"));
        team.removePlayer(player);
        return team;
    }

    @Override
    public Team assignCaptain(Player player) {
        return null;
    }
}