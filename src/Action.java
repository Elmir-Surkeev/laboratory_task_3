import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Action {
    public static void main() {
        List<Animal> animals = new ArrayList<Animal>();
        Animal dog = new Animal("Laika");
        Animal cat = new Animal("Pushok");
        Animal parrot = new Animal("Chirick");
        animals.add(dog);
        animals.add(cat);
        animals.add(parrot);
        System.out.println(Animal.printTableHeader());

        Collections.sort(animals, (a1, a2) -> Integer.compare(a1.getAverageLevel(), a2.getAverageLevel()));
        animals.forEach(animal -> System.out.println(animal.toString()));
    }
}
