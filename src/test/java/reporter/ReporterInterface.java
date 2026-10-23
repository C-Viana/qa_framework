package reporter;

import com.aventstack.extentreports.Status;

public interface ReporterInterface {
    void setResultPath(String scenarioname);
    void setEnvironmentValue(String environment);
    void startReport();
    void startTest(String testName);
    void endReport();
    void setStep(Status statusValue, String stepLog);
    void setPass(String stepLog);
    void setFail(String stepLog);
    void setInfo(String stepLog);
    void setPass(String stepLog, boolean screenshot);
    void setFail(String stepLog, boolean screenshot);
    void setInfo(String stepLog, boolean screenshot);
}
