package org.sjk;

import java.util.Collection;
import java.util.Objects;

public class CandidatesApi {

    private final DataAccess dataAccess;

    public CandidatesApi(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    public Record put(String name) {
        var figure = new DataAccess.Figure(name, DataAccess.Figure.Type.CANDIDATE);
        var isDuplicated = dataAccess.exists(figure);

        if (isDuplicated) {
            throw new IllegalArgumentException("Duplicated");
        }

        return dataAccess.add(figure);
    }

    public Collection<CandidateViewRaw> get() {
        return dataAccess.getCandidates();
    }

    record CandidateViewRaw(String name, int votes) {
        CandidateViewRaw {
            if (name == null || name.isEmpty()) throw new IllegalArgumentException("Figure name can not be empty");
            if (votes < 0) throw new IllegalArgumentException("Votes can not be lower than zero");
        }

        public String getId() {
            return name;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other == null) {
                return false;
            }
            if (!(other instanceof CandidateViewRaw candidate)) {
                return false;
            }
            return candidate.name.equals(this.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

}