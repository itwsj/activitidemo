package sql;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName WsjActivitiEventListener
 * @Description: 全局
 * @Author wsj
 * @Date 2020/6/30
 **/
@Component
public class WsjActivitiEventListener implements ActivitiEventListener {

    @Override
    public void onEvent(ActivitiEvent activitiEvent) {
        switch (activitiEvent.getType()){
            //流程开始
            case PROCESS_STARTED:
                System.out.println("流程开始");
                break;
            //流程结束
            case PROCESS_COMPLETED:
                System.out.println("流程结束");
                break;
            //任务开始
            case TASK_CREATED:
                System.out.println("任务开始");
                break;
            //任务完成
            case TASK_COMPLETED:
                System.out.println("任务完成");
                break;
            //进程取消，删除实例
            case PROCESS_CANCELLED:
                System.out.println("进程取消，删除实例");
            default:
        }
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isFailOnException() {
        return false;
    }

}
