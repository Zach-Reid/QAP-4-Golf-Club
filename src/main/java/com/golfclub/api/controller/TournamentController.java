package com.golfclub.api.controller;

import com.golfclub.api.model.*;
import com.golfclub.api.service.TournamentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    private final TournamentService tService;

    public TournamentController(TournamentService tService) {
        this.tService = tService;
    }

    @PostMapping
    public Tournament add(@RequestBody Tournament t) {
        return tService.add(t);
    }

    @PostMapping("/{tournamentId}/register/{memberId}")
    public Tournament register(@PathVariable Long tournamentId, @PathVariable Long memberId) {
        Tournament t = tService.registerMember(tournamentId, memberId);
        if (t == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tournament or member not found");
        }
        return t;
    }

    @GetMapping("/{id}/members")
    public Set<Member> participants(@PathVariable Long id) {
        return tService.getParticipants(id);
    }

    @GetMapping("/search")
    public List<Tournament> search(@RequestParam(required = false) LocalDate start,
                                   @RequestParam(required = false) String location) {
        return tService.search(start, location);
    }

    @GetMapping("/{id}")
    public Tournament get(@PathVariable Long id) {
        Tournament t = tService.get(id);
        if (t == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tournament not found");
        }
        return t;
    }
}
