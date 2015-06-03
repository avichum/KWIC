package kwic;

import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Rule;
import org.junit.Ignore;


public class KwicTest {

	kwic kwictest = new kwic();

	@Test
	public void testMainWithoutArgs() {
		
		String[] filename={};
		System.out.println("This tests main without argument passing");
		kwic.main(filename);
	
		
	}
	
	@Test
	public void testMainWithArgs() {
	
		String[] filename={"abc.txt"};
		System.out.println("This tests argument passing");
		kwic.main(filename);
		
		
		
	}


	
	@Test
	public void testKwicSelectFileExit() throws IOException{
		System.out.println("Testing Automatic Exit");
		kwictest.selectFile(5);
	}
	
	@Test(expected= NullPointerException.class)
	public void testKwicSelectFileNullPointerException() throws NullPointerException, IOException{
		System.out.println("Testing for non existing file");
		kwictest.selectFile(7);
	}
	
	@Test
	public void testProcessSearch() {
	String teststring="teststring";
System.out.println("Testing method process search");
	kwictest.processSearch(teststring);
	}
	
	@Test
	public void testReadText() throws Exception{
		System.out.println("Testing method read text");
		kwic.readText("");
	
	}
	
	@Test
	public void testDisplayIndexNotBuilt(){
	}
	
	@Test
	public void testReadTextWorking() {
		
		System.out.println("Read Text Test case passed");
		kwic.readText("abc.txt");
		
	}
	
	
	


	

}
