/**
 * Created by user on 3/27/2017.
 */
import java.util.ArrayList;
public class Inventory {

    ArrayList<Pieces> meepleList = new ArrayList<>(20);
    ArrayList<Pieces> totoroList = new ArrayList<>(3);
    ArrayList<Pieces> tigerList = new ArrayList<>(2);

    public Inventory (){
        initMeepleList();
        initTotoroList();
        initTigerList();
    }

    private void initMeepleList() {
        for (int i = 0; i < 20; i++) {
            meepleList.add(Pieces.MEEPLE);
        }
    }

    private void initTotoroList(){
        for (int i =0; i<3; i++){
            totoroList.add(Pieces.TOTORO);
        }
    }

    private void initTigerList(){
        for (int i = 0; i < 2; i++) {
            tigerList.add(Pieces.TIGER);
        }

    }
    public int getMeepleSize(){
       return meepleList.size();
    }
    public int getTotoroSize(){
        return totoroList.size();
    }
    public int getTigerSize(){
        return tigerList.size();
    }
    public Pieces getMeepleObject(){
        return meepleList.get(5);
    }
    public Pieces getTotoroObject(){
        return totoroList.get(1);
    }
    public Pieces getTigerObject(){
        return tigerList.get(1);
    }
    public void removeMeeplePiece(){
        meepleList.remove(Pieces.MEEPLE);
    }
    public void removeTotoroPiece(){
        totoroList.remove(Pieces.TOTORO);
    }
    public void removeTigerPiece(){
        tigerList.remove(Pieces.TIGER);
    }
    public boolean isInventoryEmpty() {
        if(getMeepleSize() == 0 && getTotoroSize() == 0 && getTigerSize() == 0) {
            return true;
        }
        else {
            return false;
        }
    }
}
