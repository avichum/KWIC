package kwic;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;

import org.junit.Test;

public class kwicSearchTest {

	KwicSearch searchtest = new KwicSearch();
	KwicSearch searchtextfile = new KwicSearch("abc.txt");
	KwicSearch willy = new KwicSearch("willy.txt");
	KwicSearch hello = new KwicSearch("hellosir.txt");
	@Test
	public void testKwicSearch() {
		//Implemented above
	}

	@Test
	public void testKwicSearchString() {
		//Implemented above
	}

	@Test
	public void testProcessQueryKeyword() {
		searchtextfile.processQueryKeyword("Descent");
		//Will be covered in All tests
	}
	
	@Test
	public void testProcessQueryKeywordElsecase() {
		searchtextfile.processQueryKeyword("WillyWonka");
	}

	@Test
	public void testStartIndexWorker() {
	
		searchtest.startIndexWorker();
	}

	@Test
	public void testIsIndexDone() {
		
		searchtest.isIndexDone();
	}

	@Test
	public void testSetIndexDone() {
		searchtest.setIndexDone(true);
	}
	
	@Test
	public void testBuildIndex() throws Exception{
	searchtest.buildIndex();
	}

	@Test
	public void testBuildIndexExceededMaxWords() throws Exception{
	willy.buildIndex(); // Implements a document with more than ten words.
	}
	
	
	@Test
	public void testCleanupFirstLine() {
		searchtextfile.cleanupFirstLine("abc");

	}

}
