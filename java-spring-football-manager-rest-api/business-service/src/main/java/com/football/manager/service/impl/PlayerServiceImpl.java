package com.football.manager.service.impl;

import com.football.manager.model.Player;
import com.football.manager.repository.PlayerRepository;
import com.football.manager.service.PlayerService;
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
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public Player save(Player player) {
        return ofNullable(this.playerRepository.save(player))
                .orElseThrow(() -> new PersistenceException("Exception occurred while trying to save new player data"));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Player> findAll(Pageable pageable) {
        return this.playerRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Player findById(Long playerId) {
        return this.playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Player with id '" + playerId + "' was not found"));
    }

    @Override
    public Player update(Player player) {
        return null;
    }

    @Override
    public void deleteById(Long playerId) {
        this.playerRepository.deleteById(playerId);
    }
}