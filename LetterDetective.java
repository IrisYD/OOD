import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * CS5004 Assignment 10: Problem 2 - LetterDetective.
 * This program is used to check if it is possible to use the letters and symbols in magazine.txt
 * to write an anonymous letter.
 *
 * @author Yi Deng
 * @since 2020-08-01
 */
public class LetterDetective {
    public static void main(String[] args) throws IOException {
//        for(int i = 0; i<args.length; i++) {
//            System.out.println("args[" + i + "]: " + args[i]);
//        }
        
        //Initializing.
        Scanner fileScan;
        //Two string builders for magazine and letter.
        StringBuilder magazine = new StringBuilder();
        StringBuilder letter = new StringBuilder();
        //Make result as a boolean.
        boolean result = true;

        //create a hash map for magazine.txt.
        HashMap<Character, Integer> mapForMag = new HashMap<>();

        //this block is for importing magazine.txt.
        try {
            fileScan = new Scanner(new FileInputStream("magazine.txt"));
            //append the string in magazine.txt to the string builder.
            while (fileScan.hasNext()) {
                magazine.append(fileScan.next());
            }
            fileScan.close();
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, file can not be found.");
            System.exit(0);
        }

        //this block is for adding letters in magazine to the hashmap.
        for (int i = 0; i < magazine.length(); i++) {
            char c = magazine.charAt(i);
            //if this char is already in the hash map, make the value += 1.
            if (mapForMag.containsKey(c)) {
                mapForMag.replace(c, mapForMag.get(c) + 1);
            } else { // if not in the hash map, make the value to be 1.
                mapForMag.put(c, 1);
            }
        }

//        System.out.println(mapForMag);

        //this block is for importing letters.txt
        try {
            fileScan = new Scanner(new FileInputStream("letter.txt"));
            //append the string in letter.txt to the string builder.
            while (fileScan.hasNext()) {
                letter.append(fileScan.next());
            }
            fileScan.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(0);
        }
//        System.out.println(letter);

        //this block is for checking if the letter we need in the magazine(map).
        for (int i = 0; i < letter.length(); i++ ) {
            char c = letter.charAt(i);
            //if the letter we need in the map, once we meet it, we make the value of it -= 1.
            if (mapForMag.containsKey(c)) {
                mapForMag.replace(c, mapForMag.get(c) - 1);
            } else { // Once we find one the letter we need NOT in the map,
                // make the result to be false and break directly.
                result = false;
                break;
            }
        }
        //after going over all the letters, if the result is true, which means that the letters we need are all in the magazine.
        // then we need to check if there is a value in map is negative.
        if (result) {
            for (Integer v : mapForMag.values()) {
                //if there is a value is negative, it means that the number of letter in magazine is not enough.
                if (v < 0) {
                    //make the result to be false and break directly.
                    result = false;
                    break;
                }
            }
        }
        //after going over all the map, there is no negative value, keep the result to be true.
        //print out the result.
        System.out.println(result);

    }
}
