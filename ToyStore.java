package ToyGiveaway;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ToyStore {
    private ArrayList<Toy> toys;

    static Random choice = new Random();
    private int toysCount;

    public ToyStore() {
        this.toys = new ArrayList<Toy>();
        this.toysCount = 0;
    }

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

    public void showToys() {
        System.out.printf("Всего %d видов игрушек: \n", this.toysCount);
        int index = 1;
        for (Toy toy : this.toys) {
            System.out.printf("%d) %s (%d шт)\n", index++, toy.getName(), toy.getCount());
        }
        System.out.println("\n");
    }

    private void updateWeights() {
        int allToysCount = this.getAllToysCount();
        Iterator<Toy> iterator = this.toys.iterator();
        while (iterator.hasNext()) {
            Toy toy = iterator.next();
            if (toy.getCount() == 0) {
                iterator.remove();
                this.toysCount--;
            } else {
                toy.setWeight(100 * toy.getCount() / allToysCount);
            }
        }
    }

    public Toy raffle() {
        int index = 0;
        for (int choice = ToyStore.choice.nextInt(this.getAllToysCount()); index < this.toysCount; index++) {
            if (choice < toys.get(index).getCount()) {
                break;
            }
            choice -= toys.get(index).getCount();
        }
        Toy chosenToy = this.toys.get(index).getToy();
        this.updateWeights();
        return chosenToy;
    }
}
