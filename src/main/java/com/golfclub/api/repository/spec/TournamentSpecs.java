package com.golfclub.api.repository.spec;

import com.golfclub.api.model.Tournament;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;

public class TournamentSpecs {

    public static Specification<Tournament> hasStartDate(LocalDate date) {
        return (root, query, cb) -> cb.equal(root.get("startDate"), date);
    }

    public static Specification<Tournament> hasLocation(String location) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("location")), "%" + location.toLowerCase() + "%");
    }
}
