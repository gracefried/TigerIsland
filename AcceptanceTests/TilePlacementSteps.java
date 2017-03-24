import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

/**
 * Created by hugh on 3/22/17.
 */



public class TilePlacementSteps {
    @Given("^It is the first turn$")
    public void firstTurn(){
        // Add tests
        boolean firstTurn = false;
        Assert.assertTrue(firstTurn);
    }

    @And("^The board is empty$")
    public void emptyBoard(){
        // Add tests
    }

    @And("^The player has drawn a tile$")
    public void playerHasDrawn(){
        // Add tests
    }

    @When("^The player places the tile$")
    public void hasTile(){
        // Add tests
    }



    @Then("^The tile is placed on the board$")
    public void tilePlaced(){
        // Add tests
        // Below should fail
        Assert.assertTrue(true);
    }
}