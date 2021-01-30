package HQueen;


import java.util.Random;

class SimulatedAnnealingState extends State {

    Random random = new Random();

    //شماره ملکه را برای هر موقعیت در صفحه بفرستید
    public SimulatedAnnealingState(int size) {
        super(size);
        for (int i = 0; i < size; i++) {
            queen[i] = new Queen(i, random.nextInt(size));
        }
    }

    //سازنده هنگام حل مشکل
    public SimulatedAnnealingState(int size, Queen q[]) {
        super(size);
        this.queen = q;
        cost = 0;
    }

    //این روش یک ملکه تصادفی را انتخاب می کند و سپس به طور تصادفی یک ستون ترسیم می کند
    //تا وقتی که با مقدار قبلی برابر نشود
    // ملکه را به روز می کند 
    //صفحه بازگشت و حالت بعدی را برمی گرداند
    @Override
    public State getNextState() {
        int i;
        Queen nextStateQueen[] = new Queen[tam_Board];
        int rand = random.nextInt(tam_Board);

        for (i = 0; i < tam_Board; i++) {
            nextStateQueen[i] = new Queen(queen[i].getX(), queen[i].getY());
            if (rand == i) {
                int temp = random.nextInt(tam_Board);
                while (temp == queen[i].getY()) {
                    temp = random.nextInt(tam_Board);
                }
                nextStateQueen[i] = new Queen(queen[i].getX(), temp);
            }
        }

        return new SimulatedAnnealingState(tam_Board, nextStateQueen);
    }
}
