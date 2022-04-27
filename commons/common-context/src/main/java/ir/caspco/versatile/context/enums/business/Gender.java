package ir.caspco.versatile.context.enums.business;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */
public enum Gender {

    Male('1'),
    Female('2');

    private final Character value;

    Gender(Character value) {
        this.value = value;
    }

    public Character value() {
        return value;
    }

}
