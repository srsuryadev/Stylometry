import java.io.File;
import java.io.FileNotFoundException;
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

    private List<Paragraph> paragraphs;
    private  PriorityQueue<Node> queue = null;
    private  HashMap<String, Integer> hm = null;
    private List<String> nonStopWords = null;
    private List<String> words = null;
    private int numSen = 0;
    private int numPara = 0;
    private int numPunct = 0;
    private int numLet = 0;
    
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
        if(numSen == 0){
        	for (Paragraph p : this.paragraphs) {
        		numSen += p.getNumSentences();
        	}
        }
        return numSen;
    }

    public Integer getNumLetters() {
        if(numLet == 0){
        	for (Paragraph p : this.paragraphs) {
        		numLet += p.getNumLetters();
        	}
        }
        return numLet;
    }

    public Integer getNumPunctuation() {
        if( numPunct == 0)
        	for (Paragraph p : this.paragraphs) {
        		numPunct += p.getNumPunctuation();
        	}
        return numPunct;
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
        public int compare(Node c1, Node c2) {
            return (int) (c2.count - c1.count);
        }
    };

    public ArrayList<String> mostFrequentWords(int N) {
        return this.mostFrequentWords(N, getWords());
    }

    public ArrayList<String> mostFrequentNonStopWords(int N) {
        return this.mostFrequentWords(N, getNonStopWords());
    }

    public List<String> getWords() {
        if(words==null){
        	words = new LinkedList<String>();
        	for (Paragraph p : this.paragraphs) {
        		words.addAll(p.getWords());
        	}
        }
        return words;
    }

    public List<String> getNonStopWords() {
       if(nonStopWords==null){
    	   nonStopWords = new LinkedList<String>();
    	   for (Paragraph p : this.paragraphs) {
    		   nonStopWords.addAll(p.getNonStopWords());
    	   }
       }
        return nonStopWords;
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
