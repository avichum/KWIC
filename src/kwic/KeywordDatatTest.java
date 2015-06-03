package kwic;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Ignore;

public class KeywordDatatTest {
KeywordData datatest = new KeywordData();
KeywordData datatestintestring = new KeywordData(1,"string");

@Test
	public void testKeywordData() {
	
	}

	@Test
	public void testKeywordDataIntString() {
		
	}

	@Test
	public void testGetLineNubmer() {
		
		datatest.getLineNubmer();
	}

	@Test
	public void testSetLineNumber() {
		
		datatest.setLineNumber(10);
	}

	@Test
	public void testGetContextStr() {
		datatest.getContextStr();
	}

}
