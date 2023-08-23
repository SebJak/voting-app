package org.sjk;

import java.util.Collection;

public class VotersController {

    private final VotersApi votersApi;

    public VotersController(VotersApi votersApi) {
        this.votersApi = votersApi;
    }

    public Record put(String name) {
        return votersApi.put(name);
    }

    public Collection<VotersApi.VoterViewRaw> get() {
        return votersApi.get();
    }
}
