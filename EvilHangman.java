import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EvilHangman{
  public int wordLength;
  public int totalGuessRemaining;
  public int correct;
  public int incorrect;
  public boolean foundLetter;
  public String[]wordFamilies = new String[250000];
  public String Underscore = "";
  public ArrayList<String>correctGuess;
  public ArrayList<String>allGuess;
  public static int count = 0;
  public static boolean playAgain = true;
    
  public static void main(String[]args){
    while(playAgain == true){
   EvilHangman a = new EvilHangman();
   a.lengthOfWord();
   a.setWordFamilies();
   a.updateScreen();
   a.againToplay();
   }
  }
  public EvilHangman(){
   wordLength = 0;
   totalGuessRemaining = 0;
   correct = 0;
   incorrect = 0;
   count = 0;
  }
  public void lengthOfWord(){
   Scanner sc = new Scanner(System.in);
   System.out.print("Enter word length: ");
   int Length = sc.nextInt();
   wordLength = Length;
  }
  public void setWordFamilies(){
   String thisLine = "C:\\Users\\USER\\Desktop\\Code\\New folder\\EvilHangmanGroup9\\dictionary.txt";
   File file = new File(thisLine);
   try{
        BufferedReader br = new BufferedReader(new FileReader(file));
        String KeepWord;
     while((KeepWord = br.readLine()) != null ){//add word into array
       if(KeepWord.length() == wordLength){
        wordFamilies[count] = KeepWord;
        count++;
       }
     }
     if(count>0){
     System.out.println("Words with length " + wordLength + " have " + count + " words");
     for(int i = 0; i < wordLength ; i++){
       Underscore += "_ "; 
     }
    }else{
      System.out.println("Can't find word with length "+wordLength); 
     }
   }
   catch(StringIndexOutOfBoundsException ex){
     Logger.getLogger(EvilHangman.class.getName()).log(Level.SEVERE,null,ex);
   }
   catch(FileNotFoundException ex){
     Logger.getLogger(EvilHangman.class.getName()).log(Level.SEVERE,null,ex);
   }
   catch(IOException ex){
     Logger.getLogger(EvilHangman.class.getName()).log(Level.SEVERE,null,ex); 
   }
  }
   public boolean compareLetter(){
    for(int i = 0; i<count ;i++){
      for(int j = 0 ;j<wordLength ;j++){
        /*if(wordFamilies[i].charAt(j) == ){
          
        }*/
      }
    }
    return foundLetter;
  }
   public void fillPositionLetter(){
    Scanner sc = new Scanner(System.in);
    String input = sc.next();
  }
  public int decreaseGuess(){
    
    return totalGuessRemaining;
  }
  public int increaseIncorrectGuess(){
    if(foundLetter == false){
     
      incorrect++;
    }
    return incorrect; 
  }
  public boolean checkNumberBlankPosition(){
    
    return foundLetter;
  }
  public void updateScreen(){
    System.out.print(Underscore);
    System.out.println();
  }
   public static boolean againToplay(){
   Scanner sc = new Scanner(System.in);
   System.out.print("Do you want to play again (Y/N)?");
   String again = sc.nextLine();
   System.out.println();
   if(again.equals("Y")){
    playAgain = true; 
   }else if(again.equals("N")){
    playAgain = false;
   }
   return playAgain;
 }
}