package kwic;

//Helper class containing methods

public class KeywordData {
	private int lineNumber;
	private String contextStr;
	
	// Default Constructor
	protected KeywordData(){
		this.lineNumber = 0;
		this.contextStr = null;
	}
	
	// Constructor that sets both fields
	protected KeywordData(int line,String context){
		this.lineNumber = line;
		this.contextStr = context;
	}
	
	// get lineNumer
	protected int getLineNubmer(){
		return lineNumber;
	}
	
	// set lineNumber
	protected void setLineNumber(int line){
		this.lineNumber = line;
	}
	
	// get context String
	protected String getContextStr(){
		return contextStr;
	}
	
}
