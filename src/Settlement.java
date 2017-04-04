import java.io.*;
import java.util.*;


public class Settlement {
    private Board board;
    private int numOfHexes;
    private LinkedList<Integer> adjacencyList[];


    board(int hexes)
    {
        numOfHexes = hexes;
        adjacencyList = new LinkedList[hexes];
        for (int i=0; i<hexes; ++i)
            adj[i] = new LinkedList();
    }


    void addSettlement(int hexes,int newHex)
    {
        adjacencyList[hexes].add(newHex);
    }


    void BFS(int settlement) {

        boolean visited[] = new boolean[numOfHexes];

        LinkedList<Integer> queue = new LinkedList<Integer>();

        visited[settlement]=true;
        //if meeple/piece is not on the hex, then we add it to the settlement. we also need to check terrains
        queue.add(settlement);

        while (queue.size() != 0) {

            settlement = queue.poll();
            System.out.print(settlement+" ");

            Iterator<Integer> i = adjacencyList[settlement].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }


    public List<Node> settlementArrayList(){

        Graph newSettlements = new Graph();
        for (int i=0; i< numOfHexes; i++){

       // g.addEdge(0, 1);


        //g.BFS(currentHex);

    }}

    }
}