import java.util.Random;

public class Animal {
    private int id = 1;
    private String name;
    private int age;
    //Как лучше satietyLevel | levelSatiety
    private int satietyLevel;
    private int moodLevel;
    private int healthLevel;
    private int averageLevel;
    Random rnd = new Random();
    //Где лучше всего было бы расположить, средний уровень

    public Animal(String name) {
        this.id = id+1;
        this.name = name;
        this.age = 1 + rnd.nextInt(17);
        this.satietyLevel = 20 + rnd.nextInt(61);
        this.moodLevel = 20 + rnd.nextInt(61);
        this.healthLevel = 20 + rnd.nextInt(61);
        this.averageLevel = (satietyLevel+moodLevel+healthLevel)/3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSatietyLevel() {
        return satietyLevel;
    }

    public void setSatietyLevel(int satietyLevel) {
        this.satietyLevel = satietyLevel;
    }

    public int getMoodLevel() {
        return moodLevel;
    }

    public void setMoodLevel(int moodLevel) {
        this.moodLevel = moodLevel;
    }

    public int getHealthLevel() {
        return healthLevel;
    }

    public void setHealthLevel(int healthLevel) {
        this.healthLevel = healthLevel;
    }

    public Random getRnd() {
        return rnd;
    }

    public void setRnd(Random rnd) {
        this.rnd = rnd;
    }

    public int getAverageLevel() {
        return averageLevel;
    }

    public void setAverageLevel(int agerageLevel) {
        this.averageLevel = agerageLevel;

    }
    public static String printTableHeader() {
        return String.format("+----+--------+--------+----------+------------+--------+---------------+\n" +
                "| #  | ИМЯ        | ВОЗРАСТ| ЗДОРОВЬЕ | НАСТРОЕНИЕ | СЫТОСТЬ| СРЕДНИЙ УРОВЕНЬ |\n" +
                "+----+--------+--------+----------+------------+--------+---------------+");
    }
    @Override
    public String toString() {
        return String.format("| %-2d | %-10s | %-6d | %-8d | %-10d | %-6d | %-13d |",
                id, name.length() > 10 ? name.substring(0, 10) : name, age, healthLevel, moodLevel, satietyLevel, getAverageLevel());
    }
}
