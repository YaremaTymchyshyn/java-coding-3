import java.util.Scanner;

public class Main2
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть послідовність слів, розділених комами: ");
        String input = scanner.nextLine();

        String[] words = input.split(",");
        StringBuilder result = new StringBuilder();

        for (String word : words)
        {
            char lastLetter = word.charAt(word.length() - 1);
            String transformedWord = removePreviousOccurrences(word, lastLetter);
            result.append(transformedWord).append(" ");
        }

        System.out.println("Результат: " + result.toString().trim());
    }

    private static String removePreviousOccurrences(String word, char lastLetter)
    {
        StringBuilder transformedWord = new StringBuilder();
        boolean lastLetterFound = false;

        for (int i = word.length() - 1; i >= 0; i--)
        {
            if (word.charAt(i) == lastLetter && !lastLetterFound)
            {
                lastLetterFound = true;
                transformedWord.insert(0, lastLetter);
            }
            else if (word.charAt(i) != lastLetter)
            {
                transformedWord.insert(0, word.charAt(i));
            }
        }
        return transformedWord.toString();
    }
}