public class Player {
    public String playerName;
    private boolean isTurn;

    public Player() {
        this.isTurn = true;
    }

    public Player(String playerName) {
        this.playerName = playerName;
        this.isTurn = true;
    }

    public void setTurn() {
        this.isTurn = !this.isTurn;
    }

    public boolean getTurn() {
        return this.isTurn;
    }

    public String getPlayerName() {
        return this.playerName;
    }
}
