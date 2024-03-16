import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        System.out.println(new PersonData(CorrectName(),CorrectBDay()));
    }
    private static String[] CorrectName(){
        boolean isNameCorrect = false;
        String[] fullName = new String[3];
        while (!isNameCorrect) {
            System.out.print("Введите ФИО: ");
            fullName = scanner.nextLine().split(" ");
            if (fullName.length == 3) {
                for (String name : fullName) {
                    if (!name.matches("[а-яА-Я]+")) {
                        System.out.println("Ошибка: при вводе нужно использовать только буквы.");
                        break;
                    }
                    isNameCorrect = true;
                }
            } else {
                System.out.println("Ошибка: необходимо указать ФАМИЛИЮ, ИМЯ и ОТЧЕСТВО.");
            }
        }
        return fullName;
    }
    private static LocalDate CorrectBDay(){
        boolean isDateCorrect = false;
        LocalDate birthDate = null;
        while (!isDateCorrect) {
            System.out.print("Введите дату рождения в формате ДД:ММ:ГГГГ:");
            String birthDateString = scanner.nextLine();
            try {
                birthDate = LocalDate.parse(birthDateString, DateTimeFormatter
                        .ofPattern("dd:MM:uuuu")
                        .withResolverStyle(ResolverStyle.STRICT));

                isDateCorrect = true;
            } catch (DateTimeParseException e) {
                System.out.println("Ошибка: некорректная дата.");
            }
        }
        return birthDate;
    }
}