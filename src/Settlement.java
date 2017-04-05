import java.awt.*;
import java.util.ArrayList;

/**
 * Created by patrickwert on 4/4/17.
 */
public class Settlement {
    private Board board;
    ArrayList<Point> occupiedHexes;

    public Settlement() {
        this.board = board;
        occupiedHexes = new ArrayList();
    }

    public int getSettlementSize(){
        return occupiedHexes.size();
    }
}
