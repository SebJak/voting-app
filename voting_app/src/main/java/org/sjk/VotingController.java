package org.sjk;

import java.time.LocalDateTime;

public class VotingController {

    private final VotingApi votingApi;

    public VotingController(VotingApi votes) {
        this.votingApi = votes;
    }

    public VotingApi.VoteDetails vote(String voter, String candidate) {
        return votingApi.vote(voter, candidate);
    }
}


