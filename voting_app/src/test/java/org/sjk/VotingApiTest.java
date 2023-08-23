package org.sjk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VotingApiTest {

    private final DataAccess dataAccess = new DataAccess();
    private final VotingApi votingApi = new VotingApi(dataAccess);

    @Test
    public void when_make_vote_for_existing_figures_then_result_is_ok() {
        //given
        dataAccess.add(new DataAccess.Figure("Pluto", DataAccess.Figure.Type.VOTER));
        dataAccess.add(new DataAccess.Figure("Peppa", DataAccess.Figure.Type.CANDIDATE));
        //when
        votingApi.vote("Pluto", "Peppa");
        //then
        assertEquals(1, dataAccess.getVotes().size());
    }

    @Test
    public void when_make_vote_for_unexisting_figures_then_result_is_ok() {
        //when
        assertThrows(IllegalArgumentException.class, () -> votingApi.vote("Unknown", "Peppa"));
    }


}