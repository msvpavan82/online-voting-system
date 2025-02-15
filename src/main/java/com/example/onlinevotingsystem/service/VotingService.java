package com.example.onlinevotingsystem.service;

import com.example.onlinevotingsystem.model.Result;
import com.example.onlinevotingsystem.model.User;
import com.example.onlinevotingsystem.repository.VotingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotingService {
    private VotingRepository votingRepository;

    public VotingService(VotingRepository votingRepository) {
        this.votingRepository = votingRepository;
    }

    public boolean getVoteStatus(int userId) {
        List<User> users = votingRepository.getUsers();
        boolean status = false;
        for (User user : users) {
            if(user.getId() == userId) {
                status = votingRepository.getVoteStatus(userId);
                break;
            }
        }
        return status;
    }

    public String castVote(int userId, int destinationId) {
        int voteStatus = votingRepository.updateUserVoteStatus(userId);
        int voteCountUpdate = votingRepository.updateVoteForDestination(destinationId);

        if(voteStatus != 0 && voteCountUpdate != 0)
            return "Vote Casted Successfully";
        return "Vote Failed";
    }

    public List<Result> displayResults() {
        return votingRepository.displayResults();
    }
}
