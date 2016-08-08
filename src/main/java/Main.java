import org.jfree.ui.RefineryUtilities;

import java.util.ArrayList;

public class Main {
    public final static String BASE_PATH = "src/test/resources/";
    public final static int THRESHOLD = 1;

    static ArrayList<Double> metrics = new ArrayList<Double>();

    public static void getStats(String fileName) {

        try {
            Novel novel = new Novel(BASE_PATH + fileName + ".txt");
            double noOfSentences = novel.getNumSentences();
            double avgSentenceLength = novel.getAvgSentenceLength();
            double punctuationDensityParagraph = novel.getPunctuationDensityParagraph();
            double punctuationDensitySentence = novel.getPunctuationDensitySentence();
            metrics.add(noOfSentences);
            metrics.add(avgSentenceLength);
            metrics.add(punctuationDensityParagraph);
            metrics.add(punctuationDensitySentence);
            System.out.println("Novel: " + fileName);
            System.out.println("Most frequent non-stop words: " + novel.mostFrequentNonStopWords(5));
            System.out.flush();
            System.out.println("Most frequent words: " + novel.mostFrequentWords(5));
            System.out.println("Number of sentenes: " + noOfSentences);
            System.out.println("Average sentence length: " + avgSentenceLength);
            System.out.println("Punctuation Density by paragraph: " + punctuationDensityParagraph);
            System.out.println("Punctuation Density by sentence: " + punctuationDensitySentence);
            System.out.println("Total punctuations: " + novel.getNumPunctuation());
        } catch (Exception e) {

        }
    }

    public static String getFilePath(String name){
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


    public static void main(String[] args) {
        String[] files = {"Azkaban", "ChamberOfSecrets"};
        for (String fileName : files) {
            getStats(fileName);
            System.out.println("\n");
        }
        Chart m= new Chart(metrics.get(1),metrics.get(5),metrics.get(2),metrics.get(6),metrics.get(3),metrics.get(7));
        m.pack( );
        RefineryUtilities.centerFrameOnScreen(m);
        m.setVisible( true );
        try {
            System.out.println("TEST 1 : " + isSimilarAuthor(new Novel(getFilePath("Azkaban")), new Novel(getFilePath("SorcerersStone"))));
            System.out.println("TEST 2 : " + isSimilarAuthor(new Novel(getFilePath("Azkaban")), new Novel(getFilePath("Hamlet"))));
        }
        catch(Exception e) {

        }


    }
}
