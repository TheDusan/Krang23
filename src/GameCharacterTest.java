import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class GameCharacterTest {

    private GameCharacter hero;
    private GameCharacter villain;

    @BeforeEach
    public void setUp() {
        hero = new GameCharacter("Hero", 100, 20, 3);
        villain = new GameCharacter("Villain", 80, 15, 1);
    }

    @Test
    public void testGetName() {
        assertEquals("Hero", hero.getName());
        assertEquals("Villain", villain.getName());
    }

    @Test
    public void testGetMaxHp() {
        assertEquals(100, hero.getMaxHp());
        assertEquals(80, villain.getMaxHp());
    }

    @Test
    public void testTakeDamage() {
        villain.takeDamage(20);

        assertEquals(60, villain.getHp());
    }

    @Test
    public void testUseHealingPotionWhenFull() {
        hero.useHealingPotion();

        assertEquals(100, hero.getHp());
        assertEquals(2, hero.getHealingPotions());
    }
}
