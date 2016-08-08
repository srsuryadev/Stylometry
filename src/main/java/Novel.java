import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Novel {

    public List<Paragraph> paragraphs;
    public static PriorityQueue<Node> queue = null;
    public static HashMap<String, Integer> hm = null;

    public Novel() {
        hm = new HashMap<String, Integer>();
        queue = new PriorityQueue<Node>(idComparator);
    }

    public Novel(String file) throws FileNotFoundException {
        this.paragraphs = new LinkedList<Paragraph>();
        queue = new PriorityQueue<Node>(idComparator);
        hm = new HashMap<String, Integer>();
        File text = new File(file);
        Scanner scnr = new Scanner(text);
        String paragraph = "";
        while (scnr.hasNextLine()) {
            String line = scnr.nextLine();
            paragraph += " " + line;
            if (line.trim().isEmpty()) {
                paragraphs.add(new Paragraph(paragraph));
                paragraph = "";
            }
        }
    }

    public Integer getNumParagraphs() {
        return this.paragraphs.size();
    }

    public Integer getNumSentences() {
        int num = 0;
        for (Paragraph p : this.paragraphs) {
            num += p.getNumLines();
        }
        return num;
    }

    public Integer getNumLetters() {
        int num = 0;
        for (Paragraph p : this.paragraphs) {
            num += p.getNumLetters();
        }
        return num;
    }

    public Integer getNumPunctuation() {
        int num = 0;
        for (Paragraph p : this.paragraphs) {
            num += p.getNumPunctuation();
        }
        return num;
    }

    public Double getPunctuationDensityParagraph() {
        //per paragraph
        Double answer = 0.0;
        answer = ((double) (this.getNumPunctuation()) / (double) this.getNumParagraphs());
        return answer;

    }

    public Double getPunctuationDensitySentence() {
        //per paragraph
        Double answer = 0.0;
        answer = (double) (((double) this.getNumPunctuation()) / ((double) this.getNumSentences()));
        return answer;

    }

    public boolean countWords(List<String> words) {
        hm = new HashMap<String, Integer>();
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i).toLowerCase();
            if (hm.get(word) == null) {
                hm.put(word, 1);
            } else {
                int count = hm.get(word) + 1;
                hm.put(word, count);
            }
        }
        return true;
    }

    public boolean addToQueue(HashMap<String, Integer> hm) {
        for (Map.Entry m : hm.entrySet()) {
            String key = (String) m.getKey();
            Integer value = (Integer) m.getValue();
            Node n = new Node(key, value);
            queue.add(n);
        }
        return true;
    }

    public ArrayList<String> mostFrequentWords(int N, List<String> words) {
        ArrayList<String> answer = new ArrayList<String>();
        this.countWords(words);
        this.addToQueue(hm);
        // System.out.println("QUEUE SIZE:"+ queue.size());
        for (int i = 0; i < N; i++) {
            answer.add(queue.poll().word);
        }
        return (ArrayList<String>) answer;
    }


    public static Comparator<Node> idComparator = new Comparator<Node>() {
        @Override
        public int compare(Node c1, Node c2) {
            return (int) (c2.count - c1.count);
        }
    };

    public ArrayList<String> mostFrequentWords(int N) {
        return this.mostFrequentWords(N, getWords());
    }

    public ArrayList<String> mostFrequentNonStopWords(int N){
        return this.mostFrequentWords(N, getNonStopWords());
    }

    public List<String> getWords() {
        List<String> words = new LinkedList<String>();
        for (Paragraph p : this.paragraphs) {
            words.addAll(p.getWords());
        }
        return words;
    }

    public List<String> getNonStopWords() {
        List<String> words = new LinkedList<String>();
        for (Paragraph p : this.paragraphs) {
            words.addAll(p.getNonStopWords());
        }
        return words;
    }

    public Integer getNumWords() {
        return getWords().size();
    }

    public Double getAvgSentenceLength() {
        return ((double) this.getNumWords() / (double) this.getNumSentences());
    }

    public static void debug() {
        // TEST
        List<String> words = new LinkedList<String>();
        Novel n = new Novel();
        words.add("Hello");
        words.add("Hello");
        words.add("Hello");
        words.add("Hello");
        words.add("them");
        words.add("them");
        words.add("thEm");
        words.add("play");
        words.add("play");
        words.add("player");
        words = n.mostFrequentWords(2, words);
        for (String word : words) {
            System.out.println(word);
        }
    }


    public static void main(String[] args) throws FileNotFoundException, IOException {
        debug();
    }

    public class Node {
        public String word;
        public Integer count;

        public Node(String value, Integer number) {
            this.word = value;
            this.count = number;
        }
    }

}
