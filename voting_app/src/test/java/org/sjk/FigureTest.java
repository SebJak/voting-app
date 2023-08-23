package org.sjk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FigureTest {

    @Test
    public void when_all_data_provided_the_figure_created() {
        //when
        var figure = new DataAccess.Figure("name", DataAccess.Figure.Type.CANDIDATE);
        //then
        assertEquals("name", figure.name());
        assertEquals("name", figure.getId());
        assertEquals(DataAccess.Figure.Type.CANDIDATE, figure.type());
    }

    @Test
    public void when_name_is_null_then_exception() {
        //when
        assertThrows(IllegalArgumentException.class, () -> new DataAccess.Figure(null, DataAccess.Figure.Type.CANDIDATE));
    }

    @Test
    public void when_name_is_empty_then_exception() {
        //when
        assertThrows(IllegalArgumentException.class, () -> new DataAccess.Figure("", DataAccess.Figure.Type.CANDIDATE));
    }

    @Test
    public void when_name_is_blank_then_exception() {
        //when
        assertThrows(IllegalArgumentException.class, () -> new DataAccess.Figure(" ", DataAccess.Figure.Type.CANDIDATE));
    }

    @Test
    public void when_type_is_null_then_exception() {
        //when
        assertThrows(IllegalArgumentException.class, () -> new DataAccess.Figure("name", null));
    }

}