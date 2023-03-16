package ToyGiveaway;

public class Toy {
    private int toyId;
    private String name;
    private int count;
    private int weight;
    private static int toysCount = 0;

    public Toy(String name, int count) {
        this.name = name;
        this.toyId = toysCount++;
        this.count = count;
    }

    public String getName() {
        return this.name;
    }

    public void addToy() {
        this.count++;
    }

    public Toy getToy() {
        this.count--;
        return this;
    }

    public int getCount() {
        return this.count;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return this.weight;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
