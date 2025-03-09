package net.xdclass.stress;

import lombok.extern.slf4j.Slf4j;
import org.apache.jmeter.assertions.AssertionResult;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.samplers.SampleEvent;

/**
 *
 **/
@Slf4j
public class TestResultCollector extends ResultCollector {

    public TestResultCollector() {
        super();
    }

    public TestResultCollector(Summariser summariser) {
        super(summariser);
    }

    @Override
    public void sampleOccurred(SampleEvent event) {
        super.sampleOccurred(event);
        System.out.println("label="+ event.getResult().getSampleLabel());
        System.out.println("getRequestHeaders="+ event.getResult().getRequestHeaders());
        System.out.println("getResponseHeaders="+ event.getResult().getResponseHeaders());
        System.out.println("ThreadGroup="+event.getThreadGroup());
        System.out.println("ResponseCode="+event.getResult().getResponseCode());
        System.out.println("Hostname="+event.getHostname());
        System.out.println("getResponseDataAsString="+event.getResult().getResponseDataAsString());

        AssertionResult[] assertionResults = event.getResult().getAssertionResults();
        for (AssertionResult assertionResult : assertionResults) {
            System.out.println("AssertionResult="+assertionResult.getName()+",FailureMessage="+assertionResult.getFailureMessage());
        }

    }
}
