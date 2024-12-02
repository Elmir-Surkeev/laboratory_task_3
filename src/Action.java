import java.util.*;
//Если использовать два языка при вводе на сканере выходи ошибка InputMisMatchException
public class Action {
    static List<Animal> animals = new ArrayList<Animal>();
    public static void main() {
        Scanner sc = new Scanner(System.in);
        Animal cat1 = new Animal("Laika");
        Animal cat2 = new Animal("Pushok");
        Animal cat3 = new Animal("Chirick");
        animals.add(cat1);
        animals.add(cat2);
        animals.add(cat3);

        while (true){
            try {
                System.out.println(Animal.printTableHeader());
                Collections.sort(animals, (a1, a2) -> Integer.compare(a1.getAverageLevel(), a2.getAverageLevel()));
                animals.forEach(animal -> System.out.println(animal.toString()));
                System.out.println("Enter 0 if you want close program\n" +
                        "Enter 1 if you want to add cat");
                int choice = sc.nextInt();
                switch (choice){
                    case 0:
                        return;
                    case 1:
                        addAnimal(sc);
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            }catch (InputMismatchException e) {
                System.out.println("You enter string in field for number");
                main();
            }catch (Exception e){
                main();
                System.out.println(e.getMessage());
            }
        }
    }

    public static void addAnimal(Scanner sc) {
        try {
            System.out.println(Animal.printTableHeader());
            System.out.println("Enter name");
            String name = sc.next();
            if (animals.stream().anyMatch(animal -> animal.getName().equals(name))) {
                System.out.println("Animal is already exist");
                System.out.println("Please enter a another name, this name - " + name + " have in our base date");
            }else {
                System.out.println("Enter age");
                int age = sc.nextInt();
                if (age > 0 && age < 20) {
                    Animal animal = new Animal(name, age);
                    animals.add(animal);
                } else {
                    System.out.println("Invalid age \n" +
                            "Enter age range 1-20");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("You enter string in field for number");
            main();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
