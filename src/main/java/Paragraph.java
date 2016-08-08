import edu.emory.mathcs.nlp.component.tokenizer.token.Token;

import java.util.*;

import edu.emory.mathcs.nlp.common.util.IOUtils;
import edu.emory.mathcs.nlp.common.util.Joiner;
import edu.emory.mathcs.nlp.component.tokenizer.EnglishTokenizer;
import edu.emory.mathcs.nlp.component.tokenizer.Tokenizer;
import edu.emory.mathcs.nlp.component.tokenizer.token.Token;

public class Paragraph {

    public ArrayList<Sentence> sentences = null;
    private ArrayList<String> words = null;
    private ArrayList<String> nonStopWords = null;
    private int numWords = 0;
    private int numLetters = 0;
    private int numPunctuations = 0;

    public Paragraph(String paragraph) {
        this.sentences = new ArrayList<Sentence>();
        Tokenizer tokenizer = new EnglishTokenizer();
        for (java.util.List<Token> tokens : tokenizer.segmentize(paragraph)) {
            String sentence = Joiner.join(tokens, " ");
            Sentence thisSentence = new Sentence(sentence);
            sentences.add(thisSentence);
        }
    }

    public int getNumSentences() {
        return this.sentences.size();
    }

    public int getNumWords() {
        if (numWords == 0) {
            for (Sentence s : this.sentences) {
                numWords += s.getNumWords();
            }
        }
        return numWords;
    }

    public int getNumLetters() {
        if (numLetters == 0) {
            for (Sentence s : this.sentences) {
                numLetters += s.getNumLetters();
            }
        }
        return numLetters;
    }

    public int getNumPunctuation() {
        if (numPunctuations == 0) {
            for (Sentence s : this.sentences) {
                numPunctuations += s.getNumPunctuations();
            }
        }
        return numPunctuations;
    }

    public ArrayList<String> getWords() {
        if (words == null) {
            words = new ArrayList<String>();
            for (Sentence s : this.sentences) {
                words.addAll(s.getWords());
            }
        }
        return words;
    }

    public ArrayList<String> getNonStopWords() {
        if (nonStopWords == null) {
            nonStopWords = new ArrayList<String>();
            for (Sentence s : this.sentences) {
                nonStopWords.addAll(s.getNonStopWords());
            }
        }
        return nonStopWords;
    }

}
