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
        caseOne();
//        caseTwo();
    }

    private static void caseOne() {
        Toy teddy = new Toy("мишка Тедди", 20);
        Toy car = new Toy("модель автомобиля", 15);
        Toy rcCar = new Toy("авто на пульте", 55);
        Toy babyDoll = new Toy("кукла пупс", 40);
        Toy lolDoll = new Toy("кукла LOL", 28);
        Toy helicopter = new Toy("вертолет", 12);
        // заполним магазин игрушками
        kidsWorldStore.addToy(teddy);
        kidsWorldStore.addToy(car);
        kidsWorldStore.addToy(rcCar);
        kidsWorldStore.addToy(babyDoll);
        kidsWorldStore.addToy(lolDoll);
        kidsWorldStore.addToy(helicopter);
        System.out.println("Добро пожаловать в магазин игрушек «Мир детства»!");
        System.out.println();
        System.out.printf("У нас в наличие %d игрушек. \n\n", kidsWorldStore.getAllToysCount());
//        kidsWorldStore.showToys();
        raffle(5);
        savePrizesToFile("toysprizes.txt");
//        kidsWorldStore.showToys();
    }
    private static void caseTwo() {
//       Для проверки как правильно ли делается выборка усложняем условия.
//       Общее количество игрушек 16. А разыгрываем 15.
//       Выводим сколько каких игрушек было до и после розыгрыша

        Toy teddy = new Toy("мишка Тедди", 2);
        Toy car = new Toy("модель автомобиля", 1);
        Toy rcCar = new Toy("авто на пульте", 5);
        Toy babyDoll = new Toy("кукла пупс", 4);
        Toy lolDoll = new Toy("кукла LOL", 2);
        Toy helicopter = new Toy("вертолет", 2);
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
        kidsWorldStore.showToys();
        raffle(15);
        savePrizesToFile("toysprizes.txt");
        kidsWorldStore.showToys();
    }

    private static void raffle(int prizesCount) {
        System.out.printf("Проведем розыгрыш %d игушек:\n\n", prizesCount);
        for (int i = 0; i < prizesCount; i++) {
            String prize = kidsWorldStore.raffle().getName();
            System.out.printf("Приз №%d: %s\n", i + 1, prize);
            toysPrizes.add(prize);
        }
        System.out.println();
    }

    private static void savePrizesToFile(String fileName) {
        Path path = Paths.get(fileName);
        String textToFile = new String();
        int size = toysPrizes.size();
        for (int i = 0; i < size; i++) {
            String toyName = toysPrizes.pollLast();
            textToFile += toyName + "\n";
        }
        try {
            Files.writeString(path, textToFile, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

