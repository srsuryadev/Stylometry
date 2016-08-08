import java.util.List;
import java.util.ArrayList;

public class Paragraph {
	
	public ArrayList<Sentence> sentences = null;
	
	public Paragraph(String paragraph){
		this.sentences = new ArrayList<Sentence>();
		String sentence = "";
		for(char ch: paragraph.toCharArray()){
			sentence += ch;
			if(ch=='\n'){
				this.sentences.add(new Sentence(sentence));
				sentence = "";
			}
		}
	}
	
	public int getNumLines(){
		return this.sentences.size();
	}
	
	public int getNumWords(){
		int answer = 0;
		for(Sentence s: this.sentences){
			answer += s.getNoWords();
		}
		return answer;
	}
	
	public int getNumLetters(){
		int answer = 0;
		for(Sentence s: this.sentences){
			answer += s.getNumLetter();
		}
	}
	
	public int getNoPunctuation(){
		int answer = 0;
		for(Sentence s : this.sentences){
			answer+=s.getNoPunctuation();
		}
		return answer;
	}
	
}
