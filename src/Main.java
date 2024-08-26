import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

class Gallow {
    private static final Random RANDOM = new Random();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ArrayList<String> WORDS = new ArrayList<>();
    private static String CHOSEN_WORD;
    private static int ATTEMPTS = 6;
    private static final String GAME_IS_OVER = "игра закончена";
    private static final String CHOSEN_CORRECT_LETTER = "выбрана правильная буква";
    private static final String CHOSEN_INCORRECT_LETTER = "выбрана неправильная буква";
    private static char[] maskedWord;
    private static char GUESSLETTER;
    private static final HashSet<Character> guessedLetters = new HashSet<>(); // Для хранения угаданных букв

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
            System.out.println(GAME_IS_OVER + ". Загаданное слово было: " + CHOSEN_WORD);
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
        int randomIndex = RANDOM.nextInt(WORDS.size());
        CHOSEN_WORD = WORDS.get(randomIndex);
        maskedWord = new char[CHOSEN_WORD.length()];
        Arrays.fill(maskedWord, '*');
        System.out.println("Случайное слово выбрано: " + CHOSEN_WORD); 
    }

    public static void createMask() {
        System.out.print("Текущее состояние слова: ");
        for (int i = 0; i < maskedWord.length; i++) {
            System.out.print(maskedWord[i]);
        }
        System.out.println();
        System.out.print("Угаданные буквы: " + guessedLetters + "\n");
    }

    public static void playerGuess() {
        System.out.print("Введите букву: ");
        GUESSLETTER = SCANNER.next().charAt(0);
        guessedLetters.add(GUESSLETTER); 
        boolean correctGuess = false;

        for (int i = 0; i < CHOSEN_WORD.length(); i++) {
            if (CHOSEN_WORD.charAt(i) == GUESSLETTER) {
                maskedWord[i] = GUESSLETTER;
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
