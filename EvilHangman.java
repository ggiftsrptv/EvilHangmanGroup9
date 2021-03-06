
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EvilHangman {

    public int wordLength;
    public int totalGuessRemaining;
    public int correct;
    public int incorrect;
    public String[] wordFamilies = new String[250000];
    public String SecretWord = "";
    public static String Underscore = "";
    public char LetterGuess;
    private String LetterGuessbefore = "";
    public ArrayList<String> correctGuess;
    public String[] allGuess = new String[24];
    public static int count = 0;
    public static boolean playAgain = true;
    public static boolean playing = false;
    public boolean foundLetter = false;
    String lockedWord = null;
    static String ans = "";

    public static void main(String[] args) {
        System.out.println("Welcome to Evil Hangman");
        System.out.println("please select word length what you want to play");
        while (playAgain == true) {
            EvilHangman a = new EvilHangman();
            a.lengthOfWord();
            a.setWordFamilies();
            a.updateScreen();
            //a.compareLetter('t');
            a.fillPositionLetter();
            playing = true;
            while (playing) {
                a.updateScreen();
                a.LetterGuess();
                a.check();
                System.out.println(Underscore);

                a.fillPositionLetter();
            }
            //a.againToplay();
        }
    }

    public EvilHangman() {
        wordLength = 0;
        totalGuessRemaining = 0;
        correct = 0;
        incorrect = 0;
        count = 0;
    }

    public void lengthOfWord() {//��������
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter word length: ");
        int Length = sc.nextInt();
        wordLength = Length;
        System.out.print("Enter your total guess: ");
        int guess = sc.nextInt();
        totalGuessRemaining = guess;

    }

    public void setWordFamilies() {//**��������**
        String thisLine = "dictionary.txt";
        File file = new File(thisLine);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String KeepWord;

            while ((KeepWord = br.readLine()) != null) {
                if (KeepWord.length() == wordLength) {
                    wordFamilies[count] = KeepWord;
                    count++;
                }
            }
            if (count > 0) {
                System.out.println("Words with length " + wordLength + " have " + count + " words");
                for (int i = 0; i < wordLength; i++) {
                    Underscore += "_ ";
                }
            } else {
                System.out.println("Can't find word with length " + wordLength);
                lengthOfWord();
                setWordFamilies();

            }
        } catch (StringIndexOutOfBoundsException ex) {
            Logger.getLogger(EvilHangman.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EvilHangman.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EvilHangman.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean compareLetter(char Letter) {//**��������**
        int newWord = 0;
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < wordLength; j++) {
                if (wordFamilies[i].charAt(j) == Letter) {
                    break;
                } else {
                    if (j == wordLength - 1) {
                        if (wordFamilies[i].charAt(j) != Letter) {
                            newWord++;
                        }
                    }
                }
            }
        }
        String[] temp = new String[newWord];
        int tempIndex = 0;
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < wordLength; j++) {
                if (wordFamilies[i].charAt(j) == Letter) {
                    break;
                } else {
                    if (j == wordLength - 1) {
                        if (wordFamilies[i].charAt(j) != Letter) {
                            temp[tempIndex] = wordFamilies[i];
                            tempIndex++;
                        }
                    }
                }
            }
        }
        if (newWord == 0) {
            SecretWord = wordFamilies[0];
            return false;
        } else {
            SecretWord = temp[0];
            count = newWord;
            wordFamilies = temp;
            return true;

        }
    }

    public void fillPositionLetter() {
        Scanner sc = new Scanner(System.in);
        foundLetter = false;

        System.out.print("Enter your letter : ");
        char c = sc.next().toLowerCase().charAt(0);

        LetterGuess = c;
        if (compareLetter(LetterGuess)) {
            decreaseGuess();
            System.out.println("Wrong");
            System.out.println("you can guess = " + totalGuessRemaining + " time");

            foundLetter = false;
        } else {
            if (lockedWord == null) {

            }
            correct++;
            for (int i = 0; i < SecretWord.length(); i++) {
                if (SecretWord.charAt(i) == c) {

                    ans = "";

                    for (int j = 0; j < SecretWord.length(); j++) {
                        if (SecretWord.charAt(j) == c) {
                            ans = ans + c + " ";
                        } else {
                            ans = ans + Underscore.charAt(2 * j) + Underscore.charAt(2 * j + 1);
                        }
                    }
                    Underscore = ans;

                }

            }
            foundLetter = true;
        }

        if (Underscore.replaceAll(" ", "") == SecretWord) {
            System.out.println("You Win");
            System.out.println("The answer is : " + SecretWord);
            playing = false;
            playAgain = false;
            againToplay();
        }

        if (totalGuessRemaining == 0) {
            playing = false;
            playAgain = false;
            againToplay();
        }

    }

    public int decreaseGuess() {
        totalGuessRemaining--;
        if (totalGuessRemaining == 0) {
            System.out.println("Game Over");
            //System.out.println("The answer is : "+ SecretWord);
        }
        return totalGuessRemaining;

    }

    public int increaseIncorrectGuess() {
        if (foundLetter == false) {
            incorrect++;
        }
        return incorrect;
    }

    public void updateScreen() {
        String replace = Underscore.replaceAll(" ", "");;
        System.out.print(Underscore);
        System.out.println();
        //System.out.println(SecretWord);//for show to know what word
    }

    public static boolean againToplay() {//**��������**
        Scanner sc = new Scanner(System.in);
        boolean check = false;
        while (check == false) {
            System.out.print("Do you want to play again (Y/N)?");
            String again = sc.nextLine();

            System.out.println();
            if (again.equals("Y") || (again.equals("y"))) {
                playAgain = true;
                check = true;
            } else if (again.equals("N") || (again.equals("n"))) {
                check = true;
                playAgain = false;
                System.out.println("Thank for playing ");
            }
        }

        return playAgain;
    }

    public int totalGuessRemaining() {
        return totalGuessRemaining;
    }

    public void Repeat() {
        //while(totalGuessRemaining>=0){
        //String ans = updateScreen().replaceAll(" ","");
        // }
    }

    public void check() {
        int i = 0;
        for (String x : wordFamilies) {
            i++;
        }
        System.out.println("Now you have words to guess left : " + i + " words");

    }

    public String LetterGuess() {
        LetterGuessbefore = LetterGuessbefore + " " + LetterGuess;
        System.out.println("The Letter you have already guess : " + LetterGuessbefore);
        return LetterGuessbefore;
    }
}
