/**
 * Участок трассы
 */
public abstract class Stage {
    protected int length; //длина участка трассы
    protected String description; //описание участка трассы

    //метод получения описания участка трассы
    public String getDescription() {
        return description;
    }

    //абстрактный метод вождения машины
    public abstract void go(Car c);
}
