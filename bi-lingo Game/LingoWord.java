
import java.io.*;
import java.util.*;

public class LingoWord {
    private ArrayList<String> englishWords;
    private String[] foreignWords;
    private ArrayList<String> correctWords;
    // private ArrayList<String> usedWords = new ArrayList<String>();
    
    public LingoWord() {
        englishWords = new ArrayList<String>();
        foreignWords = new String[16];
        correctWords = new ArrayList<String>();
    }

    public void addCorrect(String word) {
        correctWords.add(word);
    }

    public void generateCustomEnglishArray(ArrayList<String> ew) {
        englishWords = ew;
    }

    public ArrayList<String> getEnglishWords() {
        return englishWords;
    }

    public String[] getForeignWords() {
        return foreignWords;
    }

    public String generateRandomWord(){
        Random rand = new Random();
        String word = englishWords.get(rand.nextInt(16));
        while (correctWords.contains(word)) {
            word = englishWords.get(rand.nextInt(16));
        }
        return word;
    }

    public String getWordAtLine(String fileName, int lineNum) throws IOException {
        FileInputStream fs= new FileInputStream(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        for(int i = 0; i < lineNum; ++i)
        br.readLine();
        String line = br.readLine();
        br.close();
        return line;
    }

    public void generateArrays(String fileName) throws IOException {
        Random rand = new Random();
        ArrayList<String> used = new ArrayList<String>();
        String foreignWord;
        String englishWord;
        int x;
        for (int i = 0; i < 16; i++) {
            x = rand.nextInt(2048);
            foreignWord = getWordAtLine(fileName, x);
            englishWord = getWordAtLine("english.txt", x);

            if (!used.contains(foreignWord)) {
                used.add(foreignWord);
                englishWords.add(englishWord);
                foreignWords[i] = foreignWord;
            }
        }
    }

}