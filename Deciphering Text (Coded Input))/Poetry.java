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

//      we initialize a string of vowels to check against for decoding while maintaining casing
        String vowels = "aeiouAEIOU";

/*      we will be using regular expressions and checking for a match against each letter
        we initialize our first pattern that will be used to check if a letter is in the range [a-o] */
        Pattern firstPartOfAlphabet = Pattern.compile("[a-o]", Pattern.CASE_INSENSITIVE);

//      we initialize our second pattern that will be used to check if a letter is in the range [p-z]
        Pattern secondPartOfAlphabet = Pattern.compile("[p-z]", Pattern.CASE_INSENSITIVE);

//      begin try/catch block to catch FileNotFoundException
        try {

//          open the scanner that points to our file that we want to decode
            Scanner fileInput = new Scanner(new File("resources/capitalVowels.txt"));

//          initialize our output file
            Formatter fileOutput = new Formatter("resources/reversePoem.txt");

//          iterate through each line
            while (fileInput.hasNext()) {

/*              read the line to decode from capitalVowels.txt
                treat line as a list of letters and map each character to its decoded value
                if letter doesn't match either of these patterns, then just map the original letter */
                List<String> letters = Arrays.asList(fileInput.nextLine().split("")).stream().map((letter) ->

/*                      if character exists in the range [a-o] (case-insensitive) -
                        add 11 to its corresponding ASCII value and return the character at that position on the ASCII
                        table */
                        firstPartOfAlphabet.matcher(letter).find() ?

//                              if letter is a vowel then lowercase it else just return the usual decoded value
                                (vowels.contains(letter) ?
                                        String.valueOf((char)((int)letter.charAt(0) + 11)).toLowerCase() :
                                            String.valueOf((char)((int)letter.charAt(0) + 11))) :

/*                      if character exists in the range [p-z] (case-insensitive) -
                        subtract 15 from its corresponding ASCII value and return the character at that position
                        on the ASCII table */
                        secondPartOfAlphabet.matcher(letter).find() ?

//                              if letter is a vowel then lowercase it else just return the usual decoded value
                                (vowels.contains(letter) ?
                                        String.valueOf((char)((int)letter.charAt(0) - 15)).toLowerCase() :
                                            String.valueOf((char)((int)letter.charAt(0) - 15))) : letter).collect(Collectors.toList());

/*              we force the first letter of the line to be uppercase for readability 
                if we are encoding capitalVowels.txt in isolation of the original poem.txt and *ALL* vowels get
                capitalized as was prescribed in the previous task, there is unfortunately no way to know whether
                the original letter that was encoded to a vowel was a capital or not. The best we can do is assume
                that all first letters in each line of a poem are capitalized */
                letters.set(0, letters.get(0).toUpperCase());

//              we reverse the order of the list returned
                Collections.reverse(letters);

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
