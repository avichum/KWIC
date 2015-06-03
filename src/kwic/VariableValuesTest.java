package kwic;

import static org.junit.Assert.*;

import org.junit.Test;

public class VariableValuesTest {

	VariableValues variabletest = new VariableValues();
	KeywordData dataset = new KeywordData();
	VariableValues variabletestmultiple = new VariableValues(1,dataset);
	@Test
	public void testVariableValues() {
		
	}

	@Test
	public void testVariableValuesIntKeywordData() {
		
	}

	@Test
	public void testGetWordCount() {
		variabletest.getWordCount();
	}

	@Test
	public void testSetWordCount() {
		variabletest.setWordCount(10);
	}

	@Test
	public void testIncrementWordCount() {
		variabletest.incrementWordCount();
	}

	@Test
	public void testGetDetailList() {
		variabletest.getDetailList();
	}

}
