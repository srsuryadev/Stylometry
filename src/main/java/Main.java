
public class Main {
    public final static String BASE_PATH = "src/test/resources/";

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

    public static void main(String[] args) {
        String[] files = {"test", "Hamlet", "Azkaban", "ChamberOfSecrets", "GobletOfFire", "SorcerersStone"};
        for (String fileName : files) {
            getStats(fileName);
            System.out.println("\n");
        }
    }
}
