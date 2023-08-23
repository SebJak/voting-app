package org.sjk;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class DataAccess {

    private final Set<VotingApi.VoteDetails> votes;
    private final Set<CandidatesApi.CandidateViewRaw> candidateView;
    private final Set<VotersApi.VoterViewRaw> voterView;
    private final Set<Figure> figures;

    public DataAccess() {
        votes = new HashSet<>();
        candidateView = new HashSet<>();
        voterView = new HashSet<>();
        figures = new HashSet<>();
    }

    public Record add(Figure figure) {
        figures.add(figure);
        if (figure.type().equals(Figure.Type.CANDIDATE)) {
            var candidate = new CandidatesApi.CandidateViewRaw(figure.name(), 0);
            candidateView.add(candidate);
            return candidate;
        }
        if (figure.type().equals(Figure.Type.VOTER)) {
            var voter = new VotersApi.VoterViewRaw(figure.name(), false);
            voterView.add(voter);
            return voter;
        }
        return null;
    }

    public void add(VotingApi.VoteDetails voteDetails) {
        votes.add(voteDetails);

        Optional<VotersApi.VoterViewRaw> first = voterView.stream().filter(v -> v.getId().equals(voteDetails.voterId())).findFirst();
        first.ifPresent(voterViewRaw -> {
            voterView.remove(voterViewRaw);
            voterView.add(new VotersApi.VoterViewRaw(voterViewRaw.name(), true));
        });

        Optional<CandidatesApi.CandidateViewRaw> candidateViewRaw = candidateView.stream().filter(v -> v.getId().equals(voteDetails.candidateId())).findFirst();
        candidateViewRaw.ifPresent(viewRaw -> {
            candidateView.remove(viewRaw);
            candidateView.add(new CandidatesApi.CandidateViewRaw(viewRaw.name(), viewRaw.votes() + 1));

        });

    }

    public boolean exists(Figure figure) {
        return figures.contains(figure);
    }

    public Collection<VotingApi.VoteDetails> getVotes() {
        return votes;
    }

    public Collection<VotersApi.VoterViewRaw> getVoters() {
        return voterView;
    }

    public VotersApi.VoterViewRaw getVoterBy(String name) {
        return voterView.stream().filter(v -> v.getId().equals(name)).findFirst().orElseThrow(() -> {
            throw new IllegalArgumentException("Not found voter");
        });
    }

    public CandidatesApi.CandidateViewRaw getCandidateBy(String candidate) {
        return candidateView.stream().filter(v -> v.getId().equals(candidate)).findFirst().orElseThrow(() -> {
            throw new IllegalArgumentException("Not found candidate");
        });
    }

    public Collection<CandidatesApi.CandidateViewRaw> getCandidates() {
        return candidateView;
    }

    record Figure(String name, Type type) {
        Figure {
            if (name == null || name.isBlank()) throw new IllegalArgumentException("Figure name can not be empty");
            if (type == null) throw new IllegalArgumentException("Figure type can not be null");
        }

        public String getId() {
            return name;
        }

        enum Type {
            CANDIDATE,
            VOTER;
        }
    }
}
