import java.util.*;
////Если использовать два языка при вводе на сканере выходи ошибка InputMisMatchException
//Очистка буфера
//Почему у меня средний не обновляется
public class Action {
    static List<Animal> animals = new ArrayList<>();

    public static void main() {
        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();
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
                        "Enter 4 to go to the vet\n" +
                        "Enter 5 to for go nextDay");
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
                    case 5:
                        nextDay(rnd);
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
//        if (animal.getAge() > 1 && animal.getAge() < 5){
//            animal.setSatietyLevel(animal.getSatietyLevel() + 7);
//            animal.setMoodLevel(animal.getMoodLevel() + 7);
        try {
            System.out.println("Enter name:");
            String name = sc.nextLine();
            if (animals.stream().anyMatch(animal ->animal.getName().equalsIgnoreCase(name))) {
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
        String name = sc.next();
        try {
            animals.stream()
                    //Как лучше в фильтре отбрасывать, или по условию проверить и Exception вывести
                    //.filter(animal -> (animal.getName().equals(name) || Integer.parseInt(name) == animal.getId()) && animal.getCheckToDo() == 1)
                    .filter(animal -> (animal.getName().equalsIgnoreCase(name) || Integer.parseInt(name) == animal.getId()))
                    //find first это использовать что 1-ый элемент да
                    .findFirst()
                    .ifPresentOrElse(animal -> {
                        if (animal.getCheckToDo() == 0){
                            throw new RuntimeException("This cat toDo today");
                        }
                        else if (animal.getAge() > 1 && animal.getAge() < 5){
                            animal.setSatietyLevel(animal.getSatietyLevel() + 7);
                            animal.setMoodLevel(animal.getMoodLevel() + 7);
                            animal.setCheckToDo(animal.getCheckToDo()-1);
                        }else if (animal.getAge()>6 && animal.getAge() < 10){
                            animal.setSatietyLevel(animal.getSatietyLevel() + 5);
                            animal.setMoodLevel(animal.getMoodLevel() + 5);
                            animal.setCheckToDo(animal.getCheckToDo()-1);
                        }else if (animal.getAge()> 10){
                            animal.setSatietyLevel(animal.getSatietyLevel() + 4);
                            animal.setMoodLevel(animal.getMoodLevel() + 4);
                            animal.setCheckToDo(animal.getCheckToDo()-1);
                        }else {
                            System.out.println("Error");
                        }},
                        ()-> System.out.println("Error")
                        );
        } catch (Exception e) {
            System.out.println("Error feeding cat: " + e.getMessage());
        }
    }
    public static void playWithCat(Scanner sc) {
        System.out.println("Enter name of the cat:");
        String name = sc.next();
        try {
            animals.stream()
                    .filter(animal -> animal.getName().equalsIgnoreCase(name) || Integer.parseInt(name) == animal.getId())
                    .findFirst()
                    .ifPresentOrElse(animal -> {
                                if (animal.getCheckToDo() == 0){
                                    throw new RuntimeException("This cat toDo today");
                                }
                                else if (animal.getAge() > 1 && animal.getAge() < 5){
                                    animal.setSatietyLevel(animal.getSatietyLevel() -3);
                                    animal.setMoodLevel(animal.getMoodLevel() + 7);
                                    animal.setHealthLevel(animal.getHealthLevel()+7);
                                    animal.setCheckToDo(animal.getCheckToDo()-1);
                                }else if (animal.getAge()>6 && animal.getAge() < 10){
                                    animal.setSatietyLevel(animal.getSatietyLevel() - 5);
                                    animal.setMoodLevel(animal.getMoodLevel() + 5);
                                    animal.setHealthLevel(animal.getHealthLevel()+5);
                                    animal.setCheckToDo(animal.getCheckToDo()-1);
                                }else if (animal.getAge()> 10){
                                    animal.setSatietyLevel(animal.getSatietyLevel() - 6);
                                    animal.setMoodLevel(animal.getMoodLevel() + 4);
                                    animal.setHealthLevel(animal.getHealthLevel()+4);
                                    animal.setCheckToDo(animal.getCheckToDo()-1);
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
        String name = sc.next();
        try {
            animals.stream()
                    .filter(animal -> animal.getName().equalsIgnoreCase(name) || Integer.parseInt(name) == animal.getId())
                    .findFirst()
                    .ifPresentOrElse(animal -> {
                                if (animal.getCheckToDo() == 0){
                                    throw new RuntimeException("This cat toDo today");
                                }
                                else if (animal.getAge() > 1 && animal.getAge() < 5){
                                    animal.setSatietyLevel(animal.getSatietyLevel() -3);
                                    animal.setMoodLevel(animal.getMoodLevel() -3);
                                    animal.setHealthLevel(animal.getHealthLevel()+7);
                                    animal.setCheckToDo(animal.getCheckToDo()-1);
                                }else if (animal.getAge()>6 && animal.getAge() < 10){
                                    animal.setSatietyLevel(animal.getSatietyLevel() - 5);
                                    animal.setMoodLevel(animal.getMoodLevel() - 5);
                                    animal.setHealthLevel(animal.getHealthLevel()+5);
                                    animal.setCheckToDo(animal.getCheckToDo()-1);
                                }else if (animal.getAge()> 10){
                                    animal.setSatietyLevel(animal.getSatietyLevel() - 4);
                                    animal.setMoodLevel(animal.getMoodLevel() - 4);
                                    animal.setHealthLevel(animal.getHealthLevel()+4);
                                    animal.setCheckToDo(animal.getCheckToDo()-1);
                                }else {
                                    System.out.println("Error");
                                }},
                            ()-> System.out.println("Error")
                    );
        } catch (Exception e) {
            System.out.println("Error play with cat: " + e.getMessage());
        }
    }
    public static void nextDay(Random rnd){
        System.out.println("You are there tomorrow");
        animals.stream()
                .forEach(a -> {
                    a.setSatietyLevel(a.getSatietyLevel()- rnd.nextInt(5)+1);
                    a.setMoodLevel(a.getMoodLevel()+ rnd.nextInt(7)-3);
                    a.setHealthLevel(a.getHealthLevel()- rnd.nextInt(7)-3);
                    a.setCheckToDo(1);
                });
    }
}
