package testssss;

import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ActivitiConfig
 * @Description: TODO
 * @Author wsj
 * @Date 2020/6/30
 **/
@Component
public class ActivitiConfig implements ProcessEngineConfigurationConfigurer {

    @Autowired
    private WsjActivitiEventListener wsjActivitiEventListener;

    @Override
    public void configure(SpringProcessEngineConfiguration processEngineConfiguration) {
        List<ActivitiEventListener> activitiEventListener=new ArrayList<ActivitiEventListener>();

        activitiEventListener.add(wsjActivitiEventListener);//配置全局监听器
        System.out.println("注册监听");
        processEngineConfiguration.setEventListeners(activitiEventListener);
    }
}
