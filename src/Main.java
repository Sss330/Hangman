import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class Gallow {
    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<String> WORDS = new ArrayList<>();
    private static String CHOSEN_WORD;
    private static int ATTEMPTS = 6;
    private static final String GAME_IS_OVER = "игра закончена";
    private static final String CHOSEN_CORRECT_LETTER = "выбрана правильная буква";
    private static final String CHOSEN_INCORRECT_LETTER = "выбрана неправильная буква";
    private static char[] maskedWord;

    public static void main(String[] args) {
        loadWords();
        chooseRandomWord();

        while (ATTEMPTS > 0) {
            printGallow();
            createMask();
            playerGuess();
            if (isWordGuessed()) {
                System.out.println("Поздравляем! Вы угадали слово: " + CHOSEN_WORD);
                break;
            }
        }

        if (ATTEMPTS == 0) {
            System.out.println(GAME_IS_OVER + "загаднное слово было: "+ CHOSEN_WORD);
        }
    }

    public static void loadWords() {
        File filePath = new File("C:\\Users\\podvo\\IdeaProjects\\Gallow\\out\\production\\Gallow\\words.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                WORDS.add(line);
            }
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static void chooseRandomWord() {
        int randomIndex = random.nextInt(WORDS.size());
        CHOSEN_WORD = WORDS.get(randomIndex);
        maskedWord = new char[CHOSEN_WORD.length()];
        Arrays.fill(maskedWord, '*');
        System.out.println("Случайное слово выбрано: " + CHOSEN_WORD); // Для отладки
    }

    public static void createMask() {
        System.out.println(maskedWord);
    }

    public static void playerGuess() {
        System.out.print("Введите букву: ");
        char guessLetter = scanner.next().charAt(0);
        boolean correctGuess = false;

        for (int i = 0; i < CHOSEN_WORD.length(); i++) {
            if (CHOSEN_WORD.charAt(i) == guessLetter) {
                maskedWord[i] = guessLetter;
                correctGuess = true;
            }
        }

        if (correctGuess) {
            System.out.println(CHOSEN_CORRECT_LETTER);
        } else {
            System.out.println(CHOSEN_INCORRECT_LETTER);
            ATTEMPTS--;
        }
    }

    public static void printGallow() {
        switch (ATTEMPTS) {
            case 6 -> System.out.println("""
                    ---------
                    |/      |
                    |      \s
                    |
                    |
                    |
                    |__________|
                   \s""");
            case 5 -> System.out.println("""
                    ---------
                    |/      |
                    |       O
                    |
                    |
                    |
                    |__________|
                    """);
            case 4 -> System.out.println("""
                    ---------
                    |/      |
                    |       O
                    |       |
                    |      \s
                    |
                    |__________|
                   \s""");
            case 3 -> System.out.println("""
                    ---------
                    |/      |
                    |       O
                    |      /|
                    |        \s
                    |
                    |__________|
                   \s""");
            case 2 -> System.out.println("""
                    ---------
                    |/      |
                    |       O
                    |      /|\\
                    |
                    |
                    |__________|
                    """);
            case 1 -> System.out.println("""
                    ---------
                    |/      |
                    |       O
                    |      /|\\
                    |      /
                    |
                    |__________|
                    """);
            case 0 -> System.out.println("""
                    ---------
                    |/      |
                    |       O
                    |      /|\\
                    |      / \\
                    |
                    |__________|
                    """);
        }
    }

    public static boolean isWordGuessed() {
        return new String(maskedWord).equals(CHOSEN_WORD);
    }
}
