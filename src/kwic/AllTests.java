package kwic;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ KeywordDatatTest.class, KwicTest.class,
		VariableValuesTest.class, kwicSearchTest.class })
public class AllTests {
}
