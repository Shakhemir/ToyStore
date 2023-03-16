package ToyGiveaway;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class Main {
    private static ToyStore kidsWorldStore = new ToyStore(); // магазин игрушек
    private static Deque<String> toysPrizes = new ArrayDeque<>();  // Список призовых игрушек

    public static void main(String[] args) throws FileNotFoundException {
        Toy teddy = new Toy("мишка Тедди", 20);
        Toy car = new Toy("модель автомобиля", 17);
        Toy rcCar = new Toy("авто на пульте", 11);
        Toy babyDoll = new Toy("кукла пупс", 43);
        Toy lolDoll = new Toy("кукла LOL", 80);
        Toy helicopter = new Toy("вертолет", 15);
        // заполним магазин игрушками
        kidsWorldStore.addToy(teddy);
        kidsWorldStore.addToy(car);
        kidsWorldStore.addToy(rcCar);
        kidsWorldStore.addToy(babyDoll);
        kidsWorldStore.addToy(lolDoll);
        kidsWorldStore.addToy(helicopter);
        System.out.println("Добро пожаловать в магазин игрушек «Мир детства»!");
        System.out.println();
        System.out.printf("У нас в наличие %d игрушек\n\n", kidsWorldStore.getAllToysCount());
        raffle(5);
        savePrizesToFile("toysprizes.txt");
    }

    private static void raffle(int prizesCount) {
        System.out.printf("Проведем розыгрыш %d игушек\n", prizesCount);
        for (int i = 0; i < prizesCount; i++) {
            String prize = kidsWorldStore.raffle().getName();
            System.out.printf("Приз №%d: %s\n", i, prize);
            toysPrizes.add(prize);
        }
    }

    private static void savePrizesToFile(String fileName) {
        Path path = Paths.get(fileName);
        String textToFile = new String();
        int size = toysPrizes.size();
        for (int i = 0; i < size; i++) {
            System.out.printf("%d/%d: ", i, toysPrizes.size());
            String toyName = toysPrizes.pollLast();
            textToFile += toyName + "\n";
            System.out.println(toyName);
        }
        try {
            Files.writeString(path, textToFile, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

