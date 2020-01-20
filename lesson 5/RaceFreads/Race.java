import java.util.ArrayList;
import java.util.Arrays;

/**
 * Трасса
 */
public class Race {
    private ArrayList<Stage> stages; //участки дороги трассы

    //метод получения участок дорог трассы
    public ArrayList<Stage> getStages() { return stages; }

    //конструктор
    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}