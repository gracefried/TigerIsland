/**
 * Created by gonzalonunez on 3/16/17.
 */
public class GameAction {
    public interface TileAction {
        TileCoordinate action(Tile tile, Board board);
    }

    public interface BuildAction {
        void action(Tile tile, Board board); // this is void for now but we'll probably expect some other return type
    }

    public TileAction tileAction;
    public BuildAction buildAction;

    public GameAction(TileAction tileAction, BuildAction buildAction) {
        this.tileAction = tileAction;
        this.buildAction = buildAction;
    }
}