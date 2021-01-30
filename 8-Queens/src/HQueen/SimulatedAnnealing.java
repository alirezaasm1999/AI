package HQueen;


public class SimulatedAnnealing extends NQueen {

    double t;

    //سازنده
    SimulatedAnnealing(int Tolerance, double temperature) {
        super(Tolerance);
        t = temperature;
        current_status = new SimulatedAnnealingState(8);
    }

    @Override
    public void solve() {
        while (!isSolvedPossition(current_status)) {
            double temperatura;
            double custoMovimento;
            double prob; 
            double rand;

            
            for (temperatura = t; (temperatura > 0) && (current_status.getCost() != 0); temperatura--) {
                prox_state = current_status.getNextState();
                custoMovimento = current_status.getCost() - prox_state.getCost();
                prob = Math.exp(custoMovimento / temperatura);
                rand = Math.random();
               System.out.println(prob + "prob");
                if (custoMovimento > 0) {
                    current_status = prox_state;
                } else if (rand <= prob) {
                    current_status = prox_state;
                }
            }
        }
    }
}
