package kwic;

import java.util.ArrayList;
import java.util.List;

// //Helper class containing variables

public class VariableValues {
	// Counter of instances found for this keyword
	private int wordCount;
	// List of Keyword instances with details
	private List<KeywordData> detailList = new ArrayList<KeywordData>();
	
	// Basic Constructor
	protected VariableValues(){
		this.wordCount = 0;
	}
	
	// Constructor that sets wordCount and adds to detailList
	protected VariableValues(int count,KeywordData details){
		this.wordCount = count;
		detailList.add(details);
	}
	
	// get wordCount
	protected int getWordCount(){
		return wordCount;
	}
	
	// set wordCount
	protected void setWordCount(int count){
		this.wordCount = count;
	}
	
	// Increment word count
	protected void incrementWordCount(){
		wordCount++;
	}
	
	// get detailList
	protected List<KeywordData> getDetailList(){
		return detailList;
	}
}
