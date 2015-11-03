import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
  public static void main(String[] args) {
    Result result = JUnitCore.runClasses(
    					TranslateTest.class,
    					AddCreditsTest.class,
    					GetValueTest.class,
    					GetCreditTest.class,
    					MissingTranslationTest.class,
    					InvalidTranslateTest.class,
    					InvalidCreditTest.class,
    					MissingCommodityTest.class,
    					WoodchuckTest.class
    				);
    for (Failure failure : result.getFailures()) {
      System.err.println( failure.toString() );
    }
  }
} 