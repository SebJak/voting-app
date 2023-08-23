package org.sjk;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VotersApiTest {

    final DataAccess dataAccess = new DataAccess();
    final VotersApi votersApi = new VotersApi(dataAccess);

    @Test
    public void when_put_with_new_name_then_should_be_returned() {
        //when
        votersApi.put("Pluto");
        //then
        List<VotersApi.VoterViewRaw> voters = new ArrayList<>(votersApi.get());
        assertEquals(1, voters.size());
        assertEquals("Pluto", voters.get(0).name());

    }

    @Test
    public void when_name_duplicated_then_should_throw_exception() {
        //given
        votersApi.put("Pluto");
        //when
        assertThrows(IllegalArgumentException.class, () -> votersApi.put("Pluto"));
    }

}