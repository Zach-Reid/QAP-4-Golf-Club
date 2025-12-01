package com.golfclub.api.service;

import com.golfclub.api.model.Member;
import com.golfclub.api.model.Tournament;
import com.golfclub.api.repository.MemberRepository;
import com.golfclub.api.repository.TournamentRepository;
import com.golfclub.api.repository.spec.TournamentSpecs;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class TournamentService {
    private final TournamentRepository repo;
    private final MemberRepository memberRepo;

    public TournamentService(TournamentRepository repo, MemberRepository memberRepo) {
        this.repo = repo;
        this.memberRepo = memberRepo;
    }

    public Tournament add(Tournament t) {
        return repo.save(t);
    }

    public Tournament get(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Tournament> search(LocalDate start, String location) {
        Specification<Tournament> spec = Specification.where(null);
        if (start != null) spec = spec.and(TournamentSpecs.hasStartDate(start));
        if (location != null) spec = spec.and(TournamentSpecs.hasLocation(location));
        return repo.findAll(spec);
    }

    public Set<Member> getParticipants(Long id) {
        return repo.findById(id).map(Tournament::getParticipants).orElse(null);
    }

    // --- Corrected register method ---
    public Tournament registerMember(Long tournamentId, Long memberId) {
        Tournament tournament = repo.findById(tournamentId).orElse(null);
        Member member = memberRepo.findById(memberId).orElse(null);

        if (tournament == null || member == null) return null;

        // Keep both sides of the relationship in sync
        tournament.getParticipants().add(member);
        member.getTournaments().add(tournament);

        return repo.save(tournament);
    }
}
