package com.football.manager.controller;

import com.football.manager.model.Team;
import com.football.manager.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/teams")
@Transactional
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public ResponseEntity<Page<Team>> findAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(this.teamService.findAll(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Team> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.teamService.findById(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.teamService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Team> save(@RequestBody Team team) {
        Team savedTeam = this.teamService.save(team);
        return new ResponseEntity<>(savedTeam, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Team> update(@RequestBody Team team) {
        Team updatedTeam = this.teamService.update(team);
        return ResponseEntity.ok(updatedTeam);
    }

    @DeleteMapping(value = "/{teamId}/remove-player")
    public ResponseEntity<Team> removePlayer(@PathVariable Long teamId, @RequestParam Long playerId) {
        Team team = this.teamService.removePlayer(teamId, playerId);
        return ResponseEntity.ok(team);
    }

    @PutMapping(value = "/{teamId}/assign-captain")
    public ResponseEntity<Team> assignCaptain(@PathVariable Long teamId, @RequestParam Long captainId) {
        Team team = this.teamService.assignCaptain(teamId, captainId);
        return ResponseEntity.ok(team);
    }
}