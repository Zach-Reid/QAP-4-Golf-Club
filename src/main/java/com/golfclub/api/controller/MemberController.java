package com.golfclub.api.controller;

import com.golfclub.api.model.Member;
import com.golfclub.api.service.MemberService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService mService;

    public MemberController(MemberService mService) {
        this.mService = mService;
    }

    // --- Create a new member ---
    @PostMapping(consumes = "application/json")
    public Member add(@RequestBody Member m) {
        return mService.add(m);
    }

    // --- Get member by ID ---
    @GetMapping("/{id}")
    public Member get(@PathVariable Long id) {
        Member m = mService.get(id);
        if (m == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found");
        }
        return m;
    }

    // --- Search members ---
    @GetMapping("/search")
    public List<Member> search(@RequestParam(required = false) String name,
                               @RequestParam(required = false) String phone,
                               @RequestParam(required = false) LocalDate membershipStart) {
        return mService.search(name, phone, membershipStart);
    }

    // --- Get all members ---
    @GetMapping
    public List<Member> getAll() {
        return mService.getAll();
    }
}
