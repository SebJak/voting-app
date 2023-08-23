package org.sjk;

import java.time.LocalDateTime;

class VotingApi {

    private final DataAccess dataAccess;

    public VotingApi(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    VoteDetails vote(String voterName, String candidateName) {
        var vote = new Vote(voterName, candidateName);
        var voter = dataAccess.getVoterBy(vote.voter());
        var candidate = dataAccess.getCandidateBy(vote.candidate());
        var voteDetails = new VoteDetails(voter.getId(), candidate.getId());
        dataAccess.add(voteDetails);
        return voteDetails;
    }

    static record VoteDetails(String voterId, String candidateId, LocalDateTime timestamp) {
        public VoteDetails(String voterId, String candidateId) {
            this(voterId, candidateId, LocalDateTime.now());
        }

    }

    static record Vote(String voter, String candidate, LocalDateTime localDateTime) {

        public Vote(String voter, String candidate) {
            this(voter, candidate, LocalDateTime.now());
        }
        Vote {
            if (voter == null) throw new IllegalArgumentException("Voter identifier can not be empty");
            if (candidate == null) throw new IllegalArgumentException("Candidate identifier can not be empty");
        }


    }
}
