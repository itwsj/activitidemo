package sql;
 
 
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

@Component
public class ActivitiListenner implements TaskListener, ExecutionListener {
 
	@Override
	public void notify(DelegateExecution execution) {
		System.out.println("xml流程：" + execution.getId() + " ActivitiListenner" + this.toString());
	}
 
 
	@Override
	public void notify(DelegateTask delegateTask) {
		System.out.println("xml任务：" + delegateTask.getId() + " ActivitiListenner" + this.toString());
	}
 
 
}