package testssss;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

public class MyExecutionListener implements ExecutionListener {
    public void notify(DelegateExecution execution) {
        String eventName = execution.getEventName();
//start
        if ("start".equals(eventName)) {
            System.out.println("start=========");
        } else if ("end".equals(eventName)) {
            System.out.println("end=========");
        }
    }
}