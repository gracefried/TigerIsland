/**
 * Created by gonzalonunez on 3/16/17.
 */
public interface GameActionPerformer {
    TileCoordinate tileAction(Tile tile, Board board);
    void buildAction(Tile tile, Board board); // this is void for now but we'll probably expect some other return type
}