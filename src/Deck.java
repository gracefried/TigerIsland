import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.lang.ArrayIndexOutOfBoundsException;

/**
 * Created by patrickwert on 3/20/17.
 */
public class Deck {
    private ArrayList<Tile> gameDeck;

    public Deck(){
        Tile[] tilePile = new Tile [48];

        //LAKES FIRST:
        //LAKE LAKE VOLCANO
        tilePile[0] = new Tile(TerrainType.LAKE, TerrainType.LAKE);
        tilePile[1]= new Tile(TerrainType.LAKE, TerrainType.LAKE);
        tilePile[2]= new Tile(TerrainType.LAKE, TerrainType.LAKE);

        //LAKE JUNGLE VOLCANO
        tilePile[3]= new Tile(TerrainType.LAKE, TerrainType.JUNGLE);
        tilePile[4]= new Tile(TerrainType.LAKE, TerrainType.JUNGLE);
        tilePile[5]= new Tile(TerrainType.LAKE, TerrainType.JUNGLE);

        //LAKE GRASSLANDS VOLCANO
        tilePile[6]= new Tile(TerrainType.LAKE, TerrainType.GRASSLANDS);
        tilePile[7]= new Tile(TerrainType.LAKE, TerrainType.GRASSLANDS);
        tilePile[8]= new Tile(TerrainType.LAKE, TerrainType.GRASSLANDS);

        //LAKE ROCKY VOLCANO
        tilePile[9]= new Tile(TerrainType.LAKE, TerrainType.ROCKY);
        tilePile[10]= new Tile(TerrainType.LAKE, TerrainType.ROCKY);
        tilePile[11]= new Tile(TerrainType.LAKE, TerrainType.ROCKY);

        //JUNGLES FIRST
        //JUNGLE LAKE VOLCANO
        tilePile[12]= new Tile(TerrainType.JUNGLE, TerrainType.LAKE);
        tilePile[13]= new Tile(TerrainType.JUNGLE, TerrainType.LAKE);
        tilePile[14]= new Tile(TerrainType.JUNGLE, TerrainType.LAKE);

        //JUNGLE JUNGLE VOLCANO
        tilePile[15]= new Tile(TerrainType.JUNGLE, TerrainType.JUNGLE);
        tilePile[16]= new Tile(TerrainType.JUNGLE, TerrainType.JUNGLE);
        tilePile[17]= new Tile(TerrainType.JUNGLE, TerrainType.JUNGLE);

        //JUNGLE GRASSLANDS VOLCANO
        tilePile[18]= new Tile(TerrainType.JUNGLE, TerrainType.GRASSLANDS);
        tilePile[19]= new Tile(TerrainType.JUNGLE, TerrainType.GRASSLANDS);
        tilePile[20]= new Tile(TerrainType.JUNGLE, TerrainType.GRASSLANDS);

        //JUNGLE ROCKY VOLCANO
        tilePile[21]= new Tile(TerrainType.JUNGLE, TerrainType.ROCKY);
        tilePile[22]= new Tile(TerrainType.JUNGLE, TerrainType.ROCKY);
        tilePile[23]= new Tile(TerrainType.JUNGLE, TerrainType.ROCKY);

        //GRASSLANDS FIRST
        //GRASSLANDS LAKE VOLCANO
        tilePile[24]= new Tile(TerrainType.GRASSLANDS, TerrainType.LAKE);
        tilePile[25]= new Tile(TerrainType.GRASSLANDS, TerrainType.LAKE);
        tilePile[26]= new Tile(TerrainType.GRASSLANDS, TerrainType.LAKE);

        //GRASSLANDS JUNGLE VOLCANO
        tilePile[27]= new Tile(TerrainType.GRASSLANDS, TerrainType.JUNGLE);
        tilePile[28]= new Tile(TerrainType.GRASSLANDS, TerrainType.JUNGLE);
        tilePile[29]= new Tile(TerrainType.GRASSLANDS, TerrainType.JUNGLE);

        //GRASSLANDS GRASSLANDS VOLCANO
        tilePile[30]= new Tile(TerrainType.GRASSLANDS, TerrainType.GRASSLANDS);
        tilePile[31]= new Tile(TerrainType.GRASSLANDS, TerrainType.GRASSLANDS);
        tilePile[32]= new Tile(TerrainType.GRASSLANDS, TerrainType.GRASSLANDS);

        //GRASSLANDS ROCKY VOLCANO
        tilePile[33]= new Tile(TerrainType.GRASSLANDS, TerrainType.ROCKY);
        tilePile[34]= new Tile(TerrainType.GRASSLANDS, TerrainType.ROCKY);
        tilePile[35]= new Tile(TerrainType.GRASSLANDS, TerrainType.ROCKY);

        //ROCKY FIRST
        //ROCKY LAKE VOLCANO
        tilePile[36]= new Tile(TerrainType.ROCKY, TerrainType.LAKE);
        tilePile[37]= new Tile(TerrainType.ROCKY, TerrainType.LAKE);
        tilePile[38]= new Tile(TerrainType.ROCKY, TerrainType.LAKE);

        //ROCKY JUNGLE VOLCANO
        tilePile[39]= new Tile(TerrainType.ROCKY, TerrainType.JUNGLE);
        tilePile[40]= new Tile(TerrainType.ROCKY, TerrainType.JUNGLE);
        tilePile[41]= new Tile(TerrainType.ROCKY, TerrainType.JUNGLE);

        //ROCKY GRASSLANDS VOLCANO
        tilePile[42]= new Tile(TerrainType.ROCKY, TerrainType.GRASSLANDS);
        tilePile[43]= new Tile(TerrainType.ROCKY, TerrainType.GRASSLANDS);
        tilePile[44]= new Tile(TerrainType.ROCKY, TerrainType.GRASSLANDS);

        //ROCKY ROCKY VOLCANO
        tilePile[45]= new Tile(TerrainType.ROCKY, TerrainType.ROCKY);
        tilePile[46]= new Tile(TerrainType.ROCKY, TerrainType.ROCKY);
        tilePile[47]= new Tile(TerrainType.ROCKY, TerrainType.ROCKY);

        //Randomize tilePile and instantiate gameDeck
        tilePile = randomizeTilePile(tilePile);
        gameDeck = new ArrayList<Tile>(Arrays.asList(tilePile));
    }

    public Tile drawTile() throws ArrayIndexOutOfBoundsException {
        if (gameDeck.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("No more tiles remaining in the Deck");
        }
        return gameDeck.remove(0);
    }

    public int size() {
        return gameDeck.size();
    }

    public boolean isEmpty() {
        return gameDeck.isEmpty();
    }

    private Tile[] randomizeTilePile(Tile[] tiles){
        Random randomNumberGenerator = new Random();
        for (int i = 0; i<48; i++){
            int randomPosition = randomNumberGenerator.nextInt(48);
            Tile temp = tiles[i];
            tiles[i] = tiles[randomPosition];
            tiles[randomPosition] = temp;
        }
        return tiles;
    }

}

