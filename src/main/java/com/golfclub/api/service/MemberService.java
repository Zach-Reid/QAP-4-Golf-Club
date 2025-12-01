package com.golfclub.api.service;

import com.golfclub.api.model.Member;
import com.golfclub.api.repository.MemberRepository;
import com.golfclub.api.repository.spec.MemberSpecs;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository repo;

    public MemberService(MemberRepository repo) {
        this.repo = repo;
    }

    public Member add(Member m) {
        return repo.save(m);
    }

    public Member get(Long id) {
        return repo.findById(id).orElse(null);
    }

    // --- Fixed search method ---
    public List<Member> search(String name, String phone, LocalDate membershipStart) {
        Specification<Member> spec = Specification.where(null);
        if (name != null && !name.isEmpty()) {
            spec = spec.and(MemberSpecs.hasName(name));
        }
        if (phone != null && !phone.isEmpty()) {
            spec = spec.and(MemberSpecs.hasPhone(phone));
        }
        if (membershipStart != null) {
            spec = spec.and(MemberSpecs.hasMembershipStart(membershipStart));
        }
        return repo.findAll(spec);
    }

    // --- Added getAll method ---
    public List<Member> getAll() {
        return repo.findAll();
    }
}
