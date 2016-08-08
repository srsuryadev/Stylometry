
public class Main {

    public static void main(String[] args) {
        try {
            Novel azkaban = new Novel("src/test/resources/Azkaban.txt");
            System.out.println("Most frequent non-stop words: " + azkaban.mostFrequentNonStopWords(5));
            System.out.flush();
            System.out.println("Most frequent words: " + azkaban.mostFrequentWords(5));
            System.out.println("Number of sentenes: " + azkaban.getNumSentences());
            System.out.println("Average sentence length: " + azkaban.getAvgSentenceLength());
            System.out.println("Punctuation Density by paragraph: " + azkaban.getPunctuationDensityParagraph());
            System.out.println("Punctuation Density by sentence: " + azkaban.getPunctuationDensitySentence());
            System.out.println("Total punctuations: " + azkaban.getNumPunctuation());

            Novel chamberOfSecrets = new Novel("src/test/resources/ChamberOfSecrets.txt");
            System.out.println("Most frequent non-stop words: " + chamberOfSecrets.mostFrequentNonStopWords(5));
            System.out.flush();
            System.out.println("Most frequent words: " + chamberOfSecrets.mostFrequentWords(5));
            System.out.println("Number of sentenes: " + chamberOfSecrets.getNumSentences());
            System.out.println("Average sentence length: " + chamberOfSecrets.getAvgSentenceLength());
            System.out.println("Punctuation Density by paragraph: " + chamberOfSecrets.getPunctuationDensityParagraph());
            System.out.println("Punctuation Density by sentence: " + chamberOfSecrets.getPunctuationDensitySentence());
            System.out.println("Total punctuations: " + chamberOfSecrets.getNumPunctuation());
        } catch (Exception e) {
        }
    }
}
