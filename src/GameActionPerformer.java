/**
 * Created by gonzalonunez on 3/16/17.
 */
public interface GameActionPerformer {
    Coordinate tileAction(Tile tile, Board board);
    BuildAction buildAction(Board board);
}
