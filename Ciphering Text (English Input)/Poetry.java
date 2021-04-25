//import our io packages
import java.io.*;

//import our util packages
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

//our Poetry class
public class Poetry {

//  our main method
    public static void main(String[] args) {

/*      we will be using regular expressions and checking for a match against each letter
        we initialize our first pattern that will be used to check if a letter is in the range [a-k] */
        Pattern firstPartOfAlphabet = Pattern.compile("[a-k]", Pattern.CASE_INSENSITIVE);

//      we initialize our second pattern that will be used to check if a letter is in the range [l-z]
        Pattern secondPartOfAlphabet = Pattern.compile("[l-z]", Pattern.CASE_INSENSITIVE);

//      begin try/catch block to catch FileNotFoundException
        try {

//          open the scanner that points to our file that we want to encode
            Scanner fileInput = new Scanner(new File("resources/poem.txt"));

//          initialize our output file
            Formatter fileOutput = new Formatter("resources/encodedPoem.txt");

//          iterate through each line
            while (fileInput.hasNext()) {

/*              read the line to encode from poem.txt
                treat line as a list of letters and map each character to its new value
                if letter doesn't match either of these patterns, then just map the original letter */
                List<String> letters = Arrays.asList(fileInput.nextLine().split("")).stream().map((letter) ->

/*                      if character exists in the range [a-k] (case-insensitive) -
                        add 15 to its corresponding ASCII value and return the character at that position on the ASCII
                        table */
                        firstPartOfAlphabet.matcher(letter).find() ? String.valueOf((char)((int)letter.charAt(0) + 15)) :

/*                      if character exists in the range [l-z] (case-insensitive) -
                        add (15-26) *or 11* to its corresponding ASCII value and return the character at that position
                        on the ASCII table */
                        secondPartOfAlphabet.matcher(letter).find() ? String.valueOf((char)((int)letter.charAt(0) - 11)) : letter).collect(Collectors.toList());

/*              we convert the collection of letters that was returned from our manipulated list into a string and
                write it to our output file */
                fileOutput.format("%s", String.join("", letters) + "\r\n");
            }

//          we close our input stream
            fileInput.close();

//          we close our output stream
            fileOutput.close();
        }
        catch (FileNotFoundException e) {

//          leave a meaningful message if the exception is thrown
            System.out.println("The file you were looking for was not found.\nPlease make sure your file exists.");
        }
    }
}
