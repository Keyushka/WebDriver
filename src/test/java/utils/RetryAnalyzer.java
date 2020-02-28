package test.java.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    int count = 1;
    int retryCount = 2; // сколько раз будет перепрогонятся УПАВШИЙ тест

    @Override
    public boolean retry(ITestResult iTestResult) {
        // для повторного прогона теста
        if (count < retryCount){
            System.out.println("First test is failed, we skipped him and retry started");
            count++;
            return true;
        }
        return false;
    }
}
