import java.time.LocalDate;
import java.time.Period;

public class PersonData {

    private final String name;
    private final String surname;
    private  final String patronymic;

    private final LocalDate BDay;

    public PersonData(String[] FullName, LocalDate BDay){
        surname = FullName[0];
        name = FullName[1];
        patronymic = FullName[2];
        this.BDay = BDay;
    }
    private String CorrectAge(){
        int age = Period.between(BDay,LocalDate.now()).getYears();
        if (age % 10 == 1 && age != 11) {
            String ageSuffix = "год";
            return "Возраст: " + age + " " + ageSuffix;

        } else if (age % 10 >= 2 && age % 10 <= 4 && (age < 10 || age >= 20)) {
            String ageSuffix = "года";
            return "Возраст: " + age + " " + ageSuffix;

        } else {
            String ageSuffix = "лет";
            return "Возраст: " + age + " " + ageSuffix;

        }
    }
    private String GetSex() {
        if (patronymic.endsWith("ич")) {
            return "Пол: Мужской";
        } else {
            return "Пол: Женский";
        }
    }
    private String getInitials() {
        return surname + " " + name.charAt(0) + "." + patronymic.charAt(0) + ".";
    }

        @Override
        public String toString() {
            return getInitials() + "\n" + GetSex() + "\n" + CorrectAge();
        }
    }