import java.io.*;
import java.util.*;

public class CharacterWordFinder {

    private TreeMap <String, HashSet<String>> wordMap;
    private boolean byProportion;
    private char[] keys;
    private int numOfWords;

    public static void main (String args[]) throws IOException {
        Scanner console = new Scanner(System.in);
        System.out.println("Would you like to receive results based on proportion (\"p\") or count (\"c\")?");
        String pOrC = console.next();
        if(!pOrC.equals("p") && !pOrC.equals("c")){
            throw new IllegalArgumentException("A \"p\" or \"c\" must be entered.");
        }
        System.out.println("Please enter all characters to be included.  It is not necessary to separate them with a space.");
        String chars = console.next();
        chars.replaceAll(" ", "");
        if(chars.equals("")){
            throw new IllegalArgumentException("At least one character must be entered.");
        }
        System.out.println("How many words would you like to receive?");
        int wordCount = console.nextInt();

        CharacterWordFinder cwf = new CharacterWordFinder();
        cwf.byProportion = pOrC.equals("p") ? true : false;
        cwf.keys = chars.toCharArray();
        cwf.numOfWords = wordCount;
        cwf.wordSorter();
    }

    public void wordSorter() throws IOException {
        Set<String> wordSet = new HashSet<String>();
        File file = new File("./WordList.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String word;
        while ((word = br.readLine()) != null){
            wordSet.add(word);
        }
        wordMap = new TreeMap<>(comparatorGen());
        for(String curWord : wordSet){
            char charArray[] = curWord.toCharArray();
            Arrays.sort(charArray);
            String sortedWord = new String(charArray);
            HashSet<String> prevWords = wordMap.getOrDefault(sortedWord, new HashSet<String>());
            prevWords.add(curWord);
            wordMap.put(sortedWord, prevWords);
        }
        this.output();
    }

    public Comparator<String> comparatorGen(){
        Comparator<String> byChars = (String s1, String s2)-> {
            double s1Count = 0, s2Count = 0;
            for(Character c : keys){
                s1Count += (double) s1.chars().filter(ch -> ch == c).count();
                s2Count += (double) s2.chars().filter(ch -> ch == c).count();
            }
            return byProportion ? (Double.valueOf((s1.length()/s1Count)).compareTo(Double.valueOf(s2.length()/s2Count))) : (Double.valueOf(s2Count).compareTo(Double.valueOf(s1Count)));
        };
        return byChars;
    }

    public void output(){
        int resCount = 0;
        Iterator<String> iter = wordMap.navigableKeySet().iterator();
        while(true){
            String nextKey = iter.next();
            if(wordMap.get(nextKey).size() + resCount <= this.numOfWords){
                resCount += wordMap.get(nextKey).size();
                for(String word : wordMap.get(nextKey)){
                    System.out.println(word);
                }
            }
            else{
                Iterator<String> valuesIter = wordMap.get(nextKey).iterator();
                for(int i = 0; i < this.numOfWords - resCount; i++){
                    System.out.println(valuesIter.next());
                }
                break;
            }
        }

    }

}
