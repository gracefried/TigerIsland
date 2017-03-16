/**
 * Created by gonzalonunez on 3/16/17.
 */
public class GameActionPerformer {
    public interface TileAction {
        int[] action(Tile tile, Board board); // expects a board coordinate back aka: [x, y]
    }

    public interface BuildAction {
        void action(Tile tile, Board board); // this is void for now but we'll probably expect some other return type
    }

    public TileAction tileAction;
    public BuildAction buildAction;

    public GameActionPerformer(TileAction tileAction, BuildAction buildAction) {
        this.tileAction = tileAction;
        this.buildAction = buildAction;
    }
}