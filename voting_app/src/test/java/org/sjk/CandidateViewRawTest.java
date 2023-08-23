package org.sjk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CandidateViewRawTest {

    @Test
    public void when_all_attributes_correct_then_create_message() {
        //when
        var candidateView = new CandidatesApi.CandidateViewRaw("name", 0);
        //then
        assertEquals("name", candidateView.name());
        assertEquals("name", candidateView.getId());
        assertEquals(0, candidateView.votes());
    }

    @Test
    public void when_name_is_null_then_exception() {
        //when
        assertThrows(IllegalArgumentException.class, () -> new CandidatesApi.CandidateViewRaw(null, 0));
    }

    @Test
    public void when_name_is_empty_then_exception() {
        //when
        assertThrows(IllegalArgumentException.class, () -> new CandidatesApi.CandidateViewRaw("", 0));
    }

    @Test
    public void when_name_is_blank_then_exception() {
        //when
        assertThrows(IllegalArgumentException.class, () -> new DataAccess.Figure(" ", DataAccess.Figure.Type.CANDIDATE));
    }

    @Test
    public void when_votes_are_lower_than_zero_then_exception() {
        //when
        assertThrows(IllegalArgumentException.class, () -> new CandidatesApi.CandidateViewRaw("name", -1));
    }
}