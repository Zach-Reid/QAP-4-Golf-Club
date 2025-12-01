package com.golfclub.api.repository.spec;

import com.golfclub.api.model.Member;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;

public class MemberSpecs {

    public static Specification<Member> hasName(String name) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Member> hasPhone(String phone) {
        return (root, query, cb) -> cb.like(root.get("phone"), "%" + phone + "%");
    }

    public static Specification<Member> hasMembershipStart(LocalDate start) {
        return (root, query, cb) -> cb.equal(root.get("membershipStart"), start);
    }

    public static Specification<Member> hasMembershipDuration(int duration) {
        return (root, query, cb) -> cb.equal(root.get("durationMonths"), duration);
    }

    public static Specification<Member> participatingTournamentStart(LocalDate tournamentStart) {
        return (root, query, cb) -> {
            query.distinct(true);
            return cb.equal(root.join("tournaments").get("startDate"), tournamentStart);
        };
    }
}
