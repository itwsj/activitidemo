package sql;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName WsjTaskListener
 * @Description: TODO
 * @Author wsj
 * @Date 2020/7/1
 **/
@Component
public class WsjTaskListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("getEventName"+delegateTask.getEventName());
        System.out.println("getCategory"+delegateTask.getCategory());
        System.out.println("getDelegationState"+delegateTask.getDelegationState());
    }
}
