package org.sjk;

import java.util.Collection;
import java.util.Objects;

public class VotersApi {

   private final DataAccess dataAccess;

    public VotersApi(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    public Record put(String name) {
        var figure = new DataAccess.Figure(name, DataAccess.Figure.Type.VOTER);
        var isDuplicated = dataAccess.exists(figure);

        if(isDuplicated) {
            throw new IllegalArgumentException("Duplicated");
        }

        return dataAccess.add(figure);
    }

    public Collection<VoterViewRaw> get() {
        //we need to merge our votes with figures to get current data

        return dataAccess.getVoters();
    }

    record VoterViewRaw(String name, boolean hasVoted) {
        VoterViewRaw {
            if (name == null || name.isEmpty()) throw new IllegalArgumentException("Figure name can not be empty");
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
            if (!(other instanceof VoterViewRaw candidate)) {
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