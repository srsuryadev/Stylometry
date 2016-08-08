import edu.emory.mathcs.nlp.component.tokenizer.token.Token;

import java.util.*;

import edu.emory.mathcs.nlp.common.util.IOUtils;
import edu.emory.mathcs.nlp.common.util.Joiner;
import edu.emory.mathcs.nlp.component.tokenizer.EnglishTokenizer;
import edu.emory.mathcs.nlp.component.tokenizer.Tokenizer;
import edu.emory.mathcs.nlp.component.tokenizer.token.Token;

public class Paragraph {

    public ArrayList<Sentence> sentences = null;

    public Paragraph(String paragraph) {
        this.sentences = new ArrayList<Sentence>();
        Tokenizer tokenizer = new EnglishTokenizer();
        for (java.util.List<Token> tokens : tokenizer.segmentize(paragraph)) {
            String sentence = Joiner.join(tokens, " ");
            Sentence thisSentence = new Sentence(sentence);
            sentences.add(thisSentence);
        }
    }

    public int getNumLines() {
        return this.sentences.size();
    }

    public int getNumWords() {
        int answer = 0;
        for (Sentence s : this.sentences) {
            answer += s.getNumWords();
        }
        return answer;
    }

    public int getNumLetters() {
        int answer = 0;
        for (Sentence s : this.sentences) {
            answer += s.getNumLetters();
        }
        return answer;
    }

    public int getNumPunctuation() {
        int answer = 0;
        for (Sentence s : this.sentences) {
            answer += s.getNumPunctuations();
        }
        return answer;
    }

    public ArrayList<String> getWords() {
        ArrayList<String> words = new ArrayList<String>();
        for (Sentence s : this.sentences) {
            words.addAll(s.getWords());
        }
        return words;
    }

    public ArrayList<String> getNonStopWords() {
        ArrayList<String> words = new ArrayList<String>();
        for (Sentence s : this.sentences) {
            words.addAll(s.getNonStopWords());
        }
        return words;
    }

}
