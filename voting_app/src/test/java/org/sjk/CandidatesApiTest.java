package org.sjk;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CandidatesApiTest {

    private final CandidatesApi candidatesApi = new CandidatesApi(new DataAccess());

    @Test
    public void when_put_with_unique_name_then_should_be_returned() {
        //when
        candidatesApi.put("Pluto");
        //then
        List<CandidatesApi.CandidateViewRaw> candidates = new ArrayList<>(candidatesApi.get());
        assertEquals(1, candidates.size());
        assertEquals("Pluto", candidates.get(0).name());

    }

    @Test
    public void when_name_duplicated_then_should_throw_exception() {
        //given
        candidatesApi.put("Pluto");
        //when
        assertThrows(IllegalArgumentException.class, () -> candidatesApi.put("Pluto"));
    }

}