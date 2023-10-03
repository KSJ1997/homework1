import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите данные в формате: Фамилия Имя Отчество Дата рождения Номер телефона Пол");

            String input = scanner.nextLine();
            
            if (input.isEmpty()) {
                System.out.println("Ошибка: Вы не ввели данные.");
                return;
            }
            
            String[] userData = input.split("\\s+");

            if (userData.length != 6) {
                System.out.println("Ошибка: Неверное количество данных.");
                return;
            }

            String lastName = userData[0];
            String firstName = userData[1];
            String patronymic = userData[2];
            String birthDate = userData[3];
            String phoneNumber = userData[4];
            String gender = userData[5];

            if (!isValidDate(birthDate)) {
                System.out.println("Ошибка: Неверный формат даты рождения. Используйте формат dd.mm.yyyy");
                return;
            }

            if (!isValidPhoneNumber(phoneNumber)) {
                System.out.println("Ошибка: Неверный формат номера телефона. Используйте только цифры.");
                return;
            }

            if (!isValidGender(gender)) {
                System.out.println("Ошибка: Неверный формат пола. Используйте 'f' или 'm'.");
                return;
            }

            String fileName = lastName + ".txt";
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                String userDataString = lastName + " " + firstName + " " + patronymic + " " + birthDate + " " + phoneNumber + " " + gender;
                writer.write(userDataString + System.lineSeparator());
                System.out.println("Данные успешно записаны в файл " + fileName);
            } catch (IOException e) {
                System.out.println("Ошибка записи данных в файл: " + e.getMessage());
            }
        }
    }

    private static boolean isValidDate(String date) {
        // Простейшая проверка на формат даты
        Pattern pattern = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        // Простейшая проверка на формат номера телефона (только цифры)
        return phoneNumber.matches("\\d+");
    }

    private static boolean isValidGender(String gender) {
        // Проверка на формат пола (допустимы только 'f' или 'm')
        return gender.equals("f") || gender.equals("m");
    }
}
