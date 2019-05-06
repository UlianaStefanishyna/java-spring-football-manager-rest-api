package com.football.manager.service.impl;

import com.football.manager.model.Player;
import com.football.manager.model.Team;
import com.football.manager.repository.PlayerRepository;
import com.football.manager.repository.TeamRepository;
import com.football.manager.service.TeamService;
import com.football.manager.util.Validator;
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
    private final PlayerRepository playerRepository;

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
        Validator.validateId(teamId);
        return this.teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team with id '" + teamId + "' was not found"));
    }

    @Override
    public Team update(Team team) {
        return ofNullable(this.teamRepository.save(team))
                .orElseThrow(() -> new PersistenceException("Exception occurred while trying to update team data"));
    }

    @Override
    public void deleteById(Long teamId) {
        Validator.validateId(teamId);
        this.teamRepository.deleteById(teamId);
    }

    @Override
    public Team addPlayer(Long teamId, Player player) {
        Validator.validateId(teamId);
        Team team = this.teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team with id '" + teamId + "' was not found"));
        team.addPlayer(player);
        return team;
    }

    @Override
    public Team removePlayer(Long teamId, Long playerId) {
        Team team = this.teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team with id '" + teamId + "' was not found"));
        Player player = this.playerRepository.getOne(playerId);
        team.removePlayer(player);
        return team;
    }

    @Override
    public Team assignCaptain(Long teamId, Long captainId) {
        Team team = this.teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team with id '" + teamId + "' was not found"));
        Player captain = this.playerRepository.findById(captainId)
                .orElseThrow(() -> new ResourceNotFoundException("Player with id '" + captainId + "' was not found"));
        team.setCaptain(captain);
        captain.setTeam(team);
        return team;
    }
}