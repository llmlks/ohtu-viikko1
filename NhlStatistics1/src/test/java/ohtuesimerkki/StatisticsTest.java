/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author llmlks
 */
public class StatisticsTest {

    Statistics stats;
    Reader readerStub = () -> {
        ArrayList<Player> players = new ArrayList<>();

        players.add(new Player("Semenko", "EDM", 4, 12));
        players.add(new Player("Lemieux", "PIT", 45, 54));
        players.add(new Player("Kurri", "EDM", 37, 53));
        players.add(new Player("Yzerman", "DET", 42, 56));
        players.add(new Player("Gretzky", "EDM", 35, 89));

        return players;
    };

    public StatisticsTest() {
    }

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @Test
    public void searchLoytaaListallaOlevanPelaajan() {
        assertFalse(stats.search("Yzerman") == null);
    }

    @Test
    public void searchPalauttaaNullJosListaltaEiLoydyHaettua() {
        assertEquals(stats.search("Heino"), null);
    }

    @Test
    public void teamLoytaaOikeatPelaajatJoukkueelle() {
        assertEquals(stats.team("EDM").size(), 3);
    }

    @Test
    public void teamPalauttaaTyhjanListanJosJoukkeelleEiLoydyPelaajia() {
        assertTrue(stats.team("HAWKS").isEmpty());
    }

    @Test (expected = NoSuchElementException.class)
    public void topScorersHeittaaPoikkeuksenLiianSuurellaParametrilla() {
        stats.topScorers(10).size();
    }

    @Test
    public void topScorersPalauttaaTyhjanListanNegatiivisellaParametrilla() {
        assertTrue(stats.topScorers(-1).isEmpty());
    }

    @Test
    public void topScorersPalauttaaOikeanMaaranPelaajia() {
        assertEquals(stats.topScorers(2).size(), 3);
    }

    @Test
    public void topScorersPalauttaaOikeanPelaajanEnsimmaisena() {
        assertEquals(stats.topScorers(0).get(0).getName(), "Gretzky");
    }
}
