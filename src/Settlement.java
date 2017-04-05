import java.awt.*;
import java.util.ArrayList;

/**
 * Created by patrickwert on 4/4/17.
 */
public class Settlement {
    private Board board;
    ArrayList<Point> OccupiedHexes;



    public Settlement() {
        this.board = board;
        OccupiedHexes = new ArrayList();
    }

    public int getSettlementSize(){
        return OccupiedHexes.size();
    }
}
