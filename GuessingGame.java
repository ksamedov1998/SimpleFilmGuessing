package guessinggame;

/*
sozde boyuk herf olanda onu kicikle yazsan bele cixartmir
boyuk ile yazanda diger kicikler de boyuk ile yazilirlar
sozlerin tekrarlanmasinin qarsini almaq lazimdi 
soz sayi bitende oyun bitdi yazmalidi
 */
import java.util.ArrayList;
import java.lang.StringBuffer;
import java.util.Scanner;
import java.util.Random;

public class GuessingGame {

    public static void main(String[] args) {
        //this string contains film names
        String[] filmNames = {"Star Wars", "Avangers:Infinity War", "Aquaman", "96",
            "Black Panther", "A star is born", "The Shawshank Redemption", "The Godfather",
            "The Dark Knight", "Schindler's list", "The Lord of The Rings",
            "Samurai", "The Silence of The Lambs"};
        StringBuffer modifiedWord;
        int randomNumber;
        char tkch;
        byte checker = 1; // check if all words are used
        int mistakes = 0;
        ArrayList typedSymbols = new ArrayList();

        System.out.println("Oyun bashladi!");
        while (true) {
            randomNumber = getRandom(filmNames);
            if (filmNames[randomNumber].equals("None1")) {

                for (int i = 0; i < filmNames.length; i++) {
                    if (filmNames[i].equals("None1")) {
                        ++checker;
                    }
                }
                if (checker == filmNames.length) {
                    System.out.println("Butun sozleri tapdiniz,tebrikler!");
                    break;
                }

            }

            String S = filmNames[randomNumber];

            modifiedWord = new StringBuffer(filmNames[randomNumber]);

            modifiedWord = new StringBuffer(encoding(modifiedWord.toString()));
            System.out.println(modifiedWord);

            while (modifiedWord.toString().toLowerCase().indexOf("_") != -1 || modifiedWord.toString().toUpperCase().indexOf("_") != -1) {
                System.out.println("Simvol daxil edin-  ");
                tkch = getCharfromUser();
                int chChecker = charchecker(tkch, filmNames[randomNumber]);

                if (chChecker == -1 || chChecker == 4) {
                    System.out.println("Daxil etdiyiniz simvol yoxdur!");
                    ++mistakes;
                    System.out.println((10 - mistakes) + " - sansiniz qaldi");
                    System.out.println(modifiedWord);
                    if (10 - mistakes < 1) {
                        System.out.println("Oyun bitdi!");
                        break;
                    }
                    System.out.println("");
                } else if (chChecker == 1) {
                    if (typedSymbols.contains(tkch) == true || typedSymbols.contains((char) tkch + 32) == true) {
                        System.out.println("Bu simvol evvelceden daxil edilib!");
                        continue;
                    } else {
                        typedSymbols.add(tkch);
                        typedSymbols.add((char) (tkch + 32));

                    }
                    // fromuppercase(filmNames[randomNumber]);                  // boyuk simvoldur!
                    modifiedWord = fromuppercase(S, modifiedWord, tkch);
                    for (int i = 0; i < modifiedWord.length(); i++) {
                        if (modifiedWord.charAt(i) != '_') {
                            if (filmNames[randomNumber].charAt(i) != modifiedWord.charAt(i)) {
                                modifiedWord.setCharAt(i, filmNames[randomNumber].charAt(i));

                            }
                        }
                    }
                    System.out.println(modifiedWord);
                } else if (chChecker == 2) {
                    if (typedSymbols.contains(tkch) == true || typedSymbols.contains((char) tkch - 32) == true) {
                        System.out.println("Bu simvol evvelceden daxil edilib!");
                        continue;
                    } else {
                        typedSymbols.add(tkch);
                        typedSymbols.add((char) (tkch - 32));
                    }
                    modifiedWord = fromlowercase(S, modifiedWord, tkch);   // kicik simvoldur!
                    for (int i = 0; i < modifiedWord.length(); i++) {
                        if (modifiedWord.charAt(i) != '_') {
                            if (filmNames[randomNumber].charAt(i) != modifiedWord.charAt(i)) {
                                modifiedWord.setCharAt(i, filmNames[randomNumber].charAt(i));

                            }
                        }
                    }
                    System.out.println(modifiedWord);
                } else {
                    if (typedSymbols.contains(tkch) == true) {
                        System.out.println("Bu simvol evvelceden daxil edilib!");
                        continue;
                    } else {
                        typedSymbols.add(tkch);
                    }
                    modifiedWord = new StringBuffer(fromnumbercase(S, modifiedWord, tkch));         // reqemdir simvoldur! 
                    System.out.println(modifiedWord);
                }
            }
            typedSymbols.clear();

            if (10 - mistakes < 1) {
                break;
            }
            filmNames[randomNumber] = "None1";
            // the word has been taken in this point

        }
    }

    ///////////////////////////////////////
    static int getRandom(String[] filmlist) {
        Random rndm = new Random();
        return rndm.nextInt(filmlist.length);
    }

    ///////////////////////////////////
    static char getCharfromUser() {
        Scanner usrch = new Scanner(System.in);
        return usrch.next().charAt(0);
    }

    ////////////////////////////////////////
    static int charchecker(char chr, String word) {

        if (word.toLowerCase().indexOf(chr) != -1 || word.toUpperCase().indexOf(chr) != -1) {
            if (chr >= (char) 65 && chr <= (char) 90) {        // boyuk simvol olduguna baxsin
                return 1;
            } else if (chr >= (char) 97 && chr <= (char) 122) { // kicik simvol olduunu yoxla
                return 2;
            } else if (chr >= (char) 48 && chr <= (char) 57) { // reqemdirse
                return 3;
            } else // reqem boyuk kicik simvol deyilse
            {
                return 4;
            }
        } else {
            return -1;
        }

    }
    ////////////////////////////////////////////////////////////// 

    static String encoding(String encodedWord) {
        char chr;
        for (int i = 0; i <= encodedWord.length() - 1; i++) {
            chr = encodedWord.charAt(i);
            if (((chr >= 65 && chr <= 90) || (chr >= 48 && chr <= 57) || (chr >= 97 && chr <= 122))) {
                encodedWord = encodedWord.replace(chr, '_');

            }
        }

        return encodedWord;
    }

    ///////////////////////////////////////////////////////////////
    static StringBuffer fromuppercase(String fullword, StringBuffer word, char ch) {
        fullword = fullword.toUpperCase();

        for (int i = 0; i < word.length(); ++i) {
            if (fullword.charAt(i) == ch) {
                word.setCharAt(i, fullword.charAt(i));
            }
        }

        return word;
    }

    ///////////////////////////////////////
    static StringBuffer fromlowercase(String fullword, StringBuffer word, char ch) {
        fullword = fullword.toLowerCase();
        for (int i = 0; i < word.length(); i++) {
            if (fullword.charAt(i) == ch) {
                word.setCharAt(i, fullword.charAt(i));
            }
        }

        return word;
    }

    ///////////////////////////////////////
    static StringBuffer fromnumbercase(String fullword, StringBuffer word, char ch) {
        for (int i = 0; i < word.length(); i++) {
            if (fullword.charAt(i) == ch) {
                word.setCharAt(i, fullword.charAt(i));
            }
        }

        return word;
    }

    //////////////////////////////////////
}
