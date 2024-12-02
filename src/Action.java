import java.util.*;
////Если использовать два языка при вводе на сканере выходи ошибка InputMisMatchException
//Очистка буфера
public class Action {
    static List<Animal> animals = new ArrayList<>();

    public static void main() {
        Scanner sc = new Scanner(System.in);

        Animal cat1 = new Animal("Laika");
        Animal cat2 = new Animal("Pushok");
        Animal cat3 = new Animal("Chirick");
        animals.add(cat1);
        animals.add(cat2);
        animals.add(cat3);

        while (true) {
            try {
                System.out.println(Animal.printTableHeader());
                Collections.sort(animals, Comparator.comparingInt(Animal::getAverageLevel));
                animals.forEach(System.out::println);
                System.out.println("Enter 0 to close program\n" +
                        "Enter 1 to add cat\n" +
                        "Enter 2 to feed cat\n" +
                        "Enter 3 to play with cat\n" +
                        "Enter 4 to go to the vet");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 0:
                        return;
                    case 1:
                        addAnimal(sc);
                        break;
                    case 2:
                        feedCat(sc);
                        break;
                    case 3:
                        playWithCat(sc);
                        break;
                    case 4:
                        goToVet(sc);
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void addAnimal(Scanner sc) {
        try {
            System.out.println("Enter name:");
            String name = sc.nextLine();
            if (animals.stream().anyMatch(animal -> animal.getName().equals(name))) {
                System.out.println("Animal already exists. Enter another name.");
                return;
            }
            System.out.println("Enter age:");
            if (sc.hasNextInt()) {
                int age = sc.nextInt();
                sc.nextLine();
                if (age > 0 && age < 20) {
                    animals.add(new Animal(name, age));
                } else {
                    System.out.println("Invalid age. Enter age in range 1-20.");
                }
            } else {
                System.out.println("Invalid input. Age must be a number.");
                sc.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            sc.nextLine();
        }
    }

    public static void feedCat(Scanner sc) {
        System.out.println("Enter name of the cat:");
        String name = sc.nextLine();
        try {
            animals.stream()
                    //find first это использовать что 1-ый элемент да
                    .filter(animal -> animal.getName().equals(name))
                    .findFirst()
                    .ifPresentOrElse(animal -> {
                        if (animal.getAge() > 1 && animal.getAge() < 5){
                            animal.setSatietyLevel(animal.getSatietyLevel() + 7);
                            animal.setMoodLevel(animal.getMoodLevel() + 7);
                        }else if (animal.getAge()>6 && animal.getAge() < 10){
                            animal.setSatietyLevel(animal.getSatietyLevel() + 5);
                            animal.setMoodLevel(animal.getMoodLevel() + 5);
                        }else if (animal.getAge()> 10){
                            animal.setSatietyLevel(animal.getSatietyLevel() + 4);
                            animal.setMoodLevel(animal.getMoodLevel() + 4);
                        }else {
                            System.out.println("Eror");
                        }},
                        ()-> System.out.println("Error")
                        );
        } catch (Exception e) {
            System.out.println("Error feeding cat: " + e.getMessage());
        }
    }
    public static void playWithCat(Scanner sc) {
        System.out.println("Enter name of the cat:");
        String name = sc.nextLine();
        try {
            animals.stream()
                    .filter(animal -> animal.getName().equals(name))
                    .findFirst()
                    .ifPresentOrElse(animal -> {
                                if (animal.getAge() > 1 && animal.getAge() < 5){
                                    animal.setSatietyLevel(animal.getSatietyLevel() -3);
                                    animal.setMoodLevel(animal.getMoodLevel() + 7);
                                    animal.setHealthLevel(animal.getHealthLevel()+7);
                                }else if (animal.getAge()>6 && animal.getAge() < 10){
                                    animal.setSatietyLevel(animal.getSatietyLevel() - 5);
                                    animal.setMoodLevel(animal.getMoodLevel() + 5);
                                    animal.setHealthLevel(animal.getHealthLevel()+5);
                                }else if (animal.getAge()> 10){
                                    animal.setSatietyLevel(animal.getSatietyLevel() - 6);
                                    animal.setMoodLevel(animal.getMoodLevel() + 4);
                                    animal.setHealthLevel(animal.getHealthLevel()+4);
                                }else {
                                    System.out.println("Error");
                                }},
                            ()-> System.out.println("Error")
                    );
        } catch (Exception e) {
            System.out.println("Error play with cat: " + e.getMessage());
        }
    }
    public static void goToVet(Scanner sc) {
        System.out.println("Enter name of the cat:");
        String name = sc.nextLine();
        try {
            animals.stream()
                    .filter(animal -> animal.getName().equals(name))
                    .findFirst()
                    .ifPresentOrElse(animal -> {
                                if (animal.getAge() > 1 && animal.getAge() < 5){
                                    animal.setSatietyLevel(animal.getSatietyLevel() -3);
                                    animal.setMoodLevel(animal.getMoodLevel() -3);
                                    animal.setHealthLevel(animal.getHealthLevel()+7);
                                }else if (animal.getAge()>6 && animal.getAge() < 10){
                                    animal.setSatietyLevel(animal.getSatietyLevel() - 5);
                                    animal.setMoodLevel(animal.getMoodLevel() - 5);
                                    animal.setHealthLevel(animal.getHealthLevel()+5);
                                }else if (animal.getAge()> 10){
                                    animal.setSatietyLevel(animal.getSatietyLevel() - 4);
                                    animal.setMoodLevel(animal.getMoodLevel() - 4);
                                    animal.setHealthLevel(animal.getHealthLevel()+4);
                                }else {
                                    System.out.println("Error");
                                }},
                            ()-> System.out.println("Error")
                    );
        } catch (Exception e) {
            System.out.println("Error play with cat: " + e.getMessage());
        }
    }
}
