package com.football.manager.service;

import com.football.manager.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlayerService {

    Player save(Player player);

    Page<Player> findAll(Pageable pageable);

    Player findById(Long playerId);

    Player update(Player player);

    void deleteById(Long playerId);
}