package com.football.manager.controller;

import com.football.manager.model.BaseDto;
import com.football.manager.model.Player;
import com.football.manager.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping
    public ResponseEntity<Page<Player>> findAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(this.playerService.findAll(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Player> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.playerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Player> save(@RequestBody Player player) {
        Player savedPlayer = this.playerService.save(player);
        return new ResponseEntity<>(savedPlayer, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<BaseDto> deleteById(@PathVariable Long id) {
        this.playerService.deleteById(id);
        BaseDto response = BaseDto.builder().success(true).build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<Player> update(@RequestBody Player player) {
        Player updatedPlayer = this.playerService.update(player);
        return ResponseEntity.ok(updatedPlayer);
    }
}