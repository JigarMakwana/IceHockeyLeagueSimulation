package csci5308;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectSetupTest {
    ProjectSetup p = new ProjectSetup("ice-hockey");
    @Test
    public void getGameTest() throws Exception{
        assertEquals("ice-hockey", p.getGame());
    }

    @Test
    public void isSetupTest() throws Exception{
        assertTrue(p.isSetup());
    }
}
