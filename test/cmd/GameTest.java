package cmd;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * test for Monopoly
 */
public class GameTest {
    private int humNum = 3;
    private int aiNum = 3;
    private String[] humanName = {};
    private String[] aiName = {"1", "2", "3", "4", "5", "6"};
    private Game game;
    private HumanPlayer player;
    /**
     *
     */
    @Before
    public void prepare(){
        game = new Game(humanName, aiName);
        player = new HumanPlayer(0, "h1");
    }

    /**
     *
     */
    @Test
    public void testGame(){
        Assert.assertEquals(game.startGame(), 1);
    }

    /**
     *
     */
    @Test
    public void testHumanPlayer(){
        //player.setPosition();
    }


}
