import java.util.ArrayList;
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
        animals.forEach(animal -> System.out.println(animal.toString()));
    }
}
