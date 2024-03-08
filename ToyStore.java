import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;

public class ToyStore {

    private static final int NUM_GETS = 10;

    public static void main(String[] args) {
        Toy[] toys = {
            new Toy(1, "Конструктор", 20),
            new Toy(2, "Робот", 20),
            new Toy(3, "Кукла", 60)
        };

        PriorityQueue<Toy> toyQueue = new PriorityQueue<>();
        for (Toy toy : toys) {
            toyQueue.offer(toy);
        }

        try {
            FileWriter writer = new FileWriter("output.txt");
            Random random = new Random();
            for (int i = 0; i < NUM_GETS; i++) {
                Toy toy = getRandomToy(toyQueue, random);
                writer.write(toy.getId() + "\n");
            }
            writer.close();
            System.out.println("Values written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Toy getRandomToy(PriorityQueue<Toy> toyQueue, Random random) {
        int totalWeight = 0;
        for (Toy toy : toyQueue) {
            totalWeight += toy.getWeight();
        }

        int randomNumber = random.nextInt(totalWeight);
        int cumulativeWeight = 0;
        for (Toy toy : toyQueue) {
            cumulativeWeight += toy.getWeight();
            if (randomNumber < cumulativeWeight) {
                return toy;
            }
        }
        return null;
    }
}

class Toy implements Comparable<Toy> {
    private int id;
    private String name;
    private int weight;

    public Toy(int id, String name, int weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Toy other) {
        return Integer.compare(weight, other.weight);
    }
}
