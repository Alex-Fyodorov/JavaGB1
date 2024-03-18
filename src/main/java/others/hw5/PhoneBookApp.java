package others.hw5;

public class PhoneBookApp {
    public static void main(String[] args) {
        PhoneBook book = new PhoneBook();
        book.addNote("Горбачёв Михаил Сергеевич", "11111");
        book.addNote("Горбачёв Михаил Сергеевич", "77777");
        book.addNote("Брежнев Леонид Ильич", "22222");
        book.addNote("Брежнев Леонид Ильич", "99999");
        book.addNote("Брежнев Леонид Ильич", "00000");
        book.addNote("Брежнев Леонид Ильич", "54545");
        book.addNote("Хрущёв Никита Сергеевич", "33333");
        book.addNote("Хрущёв Никита Сергеевич", "55555");
        book.addNote("Хрущёв Никита Сергеевич", "66666");
        book.addNote("Андропов Юрий Владимирович", "44444");
        book.addNote("Черненко Константин Устинович", "88888");

        book.printOne("5555"); // Номер не найден
        book.printOne("55555");

        book.printAll();

        book.deleteNumber("4444"); // Номер не найден
        book.deleteNumber("44444"); // Удаляется вся запись
        book.deleteNumber("66666");

        book.printAll();
    }
}
