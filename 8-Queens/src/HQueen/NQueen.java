package HQueen;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//  این کلاس انتزاعی ارائه راه حل ها است
 

abstract class NQueen {

    protected int size = 8;
    protected State current_status, prox_state;
    protected int tolerance;

    public NQueen(int Tolerance) {
        this.tolerance = Tolerance;
    }

    abstract public void solve();

//این روش هر دو ترمینال و FXML را نشان می دهد که ملکه ها روی صفحه پراکنده اند
    public void showInicialPosition(ImageView tabuleiro[][]) {
        Image img = new Image("Imagens/crown.png");
        System.out.println("Current position:");
        int counter = 0;
        Queen q[] = current_status.getQueens();
        boolean queen = false;
        System.out.println();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    if (i == q[k].getX() && j == q[k].getY()) {
                        queen = true;
                        counter = k;
                        break;

                    }
                }
                System.out.print("|");
                if (queen) {
                    tabuleiro[i][j].setImage(img);
                    System.out.print(" " + counter + " ");
                    queen = false;
                } else {
                    System.out.print(" * ");
                }
                System.out.print("");
            }

            System.out.println("|");
        }
    }

    public void show(ImageView tabuleiro[][]) {
        Image img = new Image("Imagens/crown.png");
        int counter = 0;
        Queen q[] = current_status.getQueens();
        boolean queen = false;
        System.out.println();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    if (i == q[k].getX() && j == q[k].getY()) {
                        queen = true;
                        counter = k;
                        break;
                    }
                }
                System.out.print("|");
                if (queen) {
                    tabuleiro[i][j].setImage(img);
                    System.out.print(" " + counter + " ");
                    queen = false;
                } else {
                    System.out.print(" * ");
                }
                System.out.print("");
            }

            System.out.println("|");
        }
    }

    protected boolean isSolvedPossition(State s) {
        if (s.getCost() <= tolerance) {
            return true;
        }
        return false;
    }
}
