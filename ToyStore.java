package ToyGiveaway;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ToyStore {
    private List<Toy> toys = new ArrayList<Toy>();

    static Random choice = new Random();
    private int toysCount = 0;

    public void addToy(Toy toy) {
        this.toys.add(toy);
        this.toysCount++;
        this.updateWeights();
    }

    public int getAllToysCount() {
        int allToysCount = 0;
        for (Toy toy : this.toys) {
            allToysCount += toy.getCount();
        }
        return allToysCount;
    }

    private void updateWeights() {
        int allToysCount = this.getAllToysCount();
        for (Toy toy : this.toys) {
            toy.setWeight(100 * toy.getCount() / allToysCount);
        }
    }

    public Toy raffle() {
        int index = 0;
        for (int choice = ToyStore.choice.nextInt(100); index < toysCount; index++) {
            if (choice < toys.get(index).getWeight()) {
                break;
            }
            choice -= toys.get(index).getWeight();
        }
        Toy chosenToy = this.toys.get(index).getToy();
        this.updateWeights();
        return chosenToy;
    }
}
