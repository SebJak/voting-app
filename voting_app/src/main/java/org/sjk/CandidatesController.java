package org.sjk;

import java.util.Collection;

public class CandidatesController {
    private final CandidatesApi candidatesApi;

    public CandidatesController(CandidatesApi candidatesApi) {
        this.candidatesApi = candidatesApi;
    }

    public Collection<CandidatesApi.CandidateViewRaw> get() {
        return candidatesApi.get();
    }

    public Record put(String name) {
        return candidatesApi.put(name);
    }
}

