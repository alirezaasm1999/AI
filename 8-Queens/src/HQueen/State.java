package HQueen;

abstract class State {

    int tam_Board;
    int cost;
    protected Queen[] queen;

    public State(int boardSize) {
        this.tam_Board = boardSize;
        queen = new Queen[boardSize];

        cost = 0;
    }

    abstract public State getNextState();

    public void calculateCost() {
        int i, j;
        cost = 0;

        for (i = 0; i < tam_Board; i++) {
            for (j = 0; j < tam_Board; j++) {
                if (i == j) {
                    continue;
                }
                if (queen[i].getX() == queen[j].getX()
                        || queen[i].getY() == queen[j].getY() 
                        || (queen[i].getX() - queen[j].getX() == queen[i].getY() - queen[j].getY()) 
                        || (queen[i].getX() - queen[j].getX() == queen[j].getY() - queen[i].getY())) { 
                    cost++;
                }
            }
        }
        cost = cost / 2;
    }

    public int getCost() {
        calculateCost();
        return cost;
    }

    public Queen[] getQueens() {
        return queen;
    }
}
