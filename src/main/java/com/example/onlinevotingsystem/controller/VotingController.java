package com.example.onlinevotingsystem.controller;

import com.example.onlinevotingsystem.model.Result;
import com.example.onlinevotingsystem.service.VotingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class VotingController {
    private VotingService votingService;

    public VotingController(VotingService votingService) {
        this.votingService = votingService;
    }

    @RequestMapping("castvote/{userId}/{destinationId}")
    public String castVote(@PathVariable int userId, @PathVariable int destinationId) {
        if(votingService.getVoteStatus(userId))
            return "you already voted";
        return votingService.castVote(userId, destinationId);
    }

    @GetMapping("/display-results")
    public List<Result> displayResults() {
        return votingService.displayResults();
    }
}
