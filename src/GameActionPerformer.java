/**
 * Created by gonzalonunez on 3/16/17.
 */
public interface GameActionPerformer {
    Coordinate tileAction(Tile tile, Board board);
    BuildAction buildAction(Board board); // this is void for now but we'll probably expect some other return type
}