
public class Main {

    public static void main(String[] args) {
        try {
            Novel novel = new Novel("src/test/resources/test.txt");
            System.out.println("Most frequent words: " + novel.mostFrequentWords(20));
            System.out.println("Number of sentenes: " + novel.getNumSentences());
            System.out.println("Average sentence length: " + novel.getAvgSentenceLength());
            System.out.println("Punctuation Density by paragraph: " + novel.getPunctuationDensityParagraph());
            System.out.println("Punctuation Density by sentence: " + novel.getPunctuationDensitySentence());
            System.out.println("Total punctuations: " + novel.getNumPunctuation());
        } catch (Exception e) {
        }
    }
}
