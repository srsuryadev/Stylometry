import java.io.FileNotFoundException;

public class Main {
    
	public final static String BASE_PATH = "src/test/resources/";
	public final static int THRESHOLD = 1;
   
    public static void getStats(String fileName) {

        try {
            Novel novel = new Novel(BASE_PATH + fileName + ".txt");
            System.out.println("Novel: " + fileName);
            System.out.println("Most frequent non-stop words: " + novel.mostFrequentNonStopWords(5));
            System.out.flush();
            System.out.println("Most frequent words: " + novel.mostFrequentWords(5));
            System.out.println("Number of sentenes: " + novel.getNumSentences());
            System.out.println("Average sentence length: " + novel.getAvgSentenceLength());
            System.out.println("Punctuation Density by paragraph: " + novel.getPunctuationDensityParagraph());
            System.out.println("Punctuation Density by sentence: " + novel.getPunctuationDensitySentence());
            System.out.println("Total punctuations: " + novel.getNumPunctuation());
        } catch (Exception e) {

        }
    }
    
    public static String getFileName(String name){
    	return BASE_PATH + name + ".txt";
    }
    
    public static boolean isSimilarAuthor(Novel unknown, Novel known){
    	
    	Double unPunct = unknown.getPunctuationDensityParagraph();
    	Double knPunct = unknown.getPunctuationDensityParagraph();
    	
    	Double unWordsSentence = unknown.getAvgSentenceLength();
    	Double knWordsSentence = known.getAvgSentenceLength();
    	
    	if(Math.abs(knPunct - unPunct) <=THRESHOLD && Math.abs(unWordsSentence- knWordsSentence)<=THRESHOLD){
    		return true;
    	}
    	return false;
    	
    }
    
    

    public static void main(String[] args) throws FileNotFoundException {
        String[] files = { "Hamlet", "Azkaban", "ChamberOfSecrets", "GobletOfFire", "SorcerersStone", "test"};
       /* for (String fileName : files) {
            getStats(fileName);
            System.out.println("\n");
        }
        */
        System.out.println("TEST 1 : "+isSimilarAuthor(new Novel(getFileName("Azkaban")), new Novel(getFileName("SorcerersStone"))));
        System.out.println("TEST 2 : "+isSimilarAuthor(new Novel(getFileName("Azkaban")), new Novel(getFileName("Hamlet"))));
    }
}
