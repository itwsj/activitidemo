package testssss;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

public class ActivitiTest {
    @Test
    public void test111(){
        for (int i = 0; i < 6; i++) {
            String formKey = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            System.out.println(formKey);
        }
       /* 80c7b3d54ad44bc1a6d1edf8be397dc1
        5d931ad68fad4e5187faac013efa902c
        14f311aeddbc4653a7667eb40877b235
        60df00a12b194364a264950b2c46cf2d
        eaf049cfc5e34208a0bab5588b97f93a
        f8bd31fcf8354ea1a7adddce21a25a85*/
    }

    @Test
    public void creatSql() {
        //动态创建数据库
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        ProcessEngine processEngine = configuration.buildProcessEngine();
        System.out.println(processEngine);
    }
    ProcessEngine pe;
    @Before
    public  void init(){
        //默认方式拿到流程引擎，配置文件名字必须是"activiti.cfg.xml。。
        pe = ProcessEngines.getDefaultProcessEngine();
        System.out.println("初始化了");
    }


    @Test
    public void test1() {
        //部署流程，把画好的流程部署到数据库
        DeploymentBuilder deploymentBuilder = pe.getRepositoryService().createDeployment();
        deploymentBuilder.addClasspathResource("diagram/pro1.bpmn");
        deploymentBuilder.addClasspathResource("diagram/pro1.png");
        Deployment deployment = deploymentBuilder.deploy();
        System.out.println(deployment.getId());
    }

    @Test
    public void test2() {
        // 部署查询对象----查询部署表act_re_deployment，也就是流程列表。。
        ProcessDefinitionQuery query = pe.getRepositoryService()
                .createProcessDefinitionQuery();
//        query.orderByDeploymenTime().desc();
        query.latestVersion();
        List<ProcessDefinition> list = query.list();
        for (ProcessDefinition processDefinition : list) {

            System.out.println(processDefinition.getId()+"-------"+processDefinition.getName()+"---"+processDefinition.getKey());
        }
    }

    @Test
    public void test3() {
        //开启流程
        String processDefinitionId = "qjlc:1:4";
        ProcessInstance pi = pe.getRuntimeService().startProcessInstanceById(processDefinitionId);
//        ProcessInstance pi = pe.getRuntimeService().startProcessInstanceByKey(); //多种方式请求
        System.out.println(pi.getId());

    }
    @Test
    public void test33(){
        // 根据流程定义的key启动一个流程实例
        String businessKey = "hello";
        ProcessInstance processInstance = pe.getRuntimeService()
                .startProcessInstanceByKey("qjlc", businessKey);
    }
    @Test
    public void test4() {
        //查询指派人下的任务
        TaskQuery taskQuery = pe.getTaskService().createTaskQuery();
//        String assignee = "张三";  指定人就是当前人下的任务，不指定人就是所有任务
//        taskQuery.taskAssignee(assignee);

        taskQuery.orderByTaskCreateTime().desc();
        List<Task> list = taskQuery.list();
        for (Task task : list) {
            String id = task.getId();
            String name = task.getName();
            String assignee2 = task.getAssignee();
            System.out.println(id + "---" + name + "----" + assignee2);
        }
    }

    @Test
    public void test5() {
        //执行任务
        String taskId = "5002";
        pe.getTaskService().complete(taskId);
    }
    @Test
    public void testHistoric01(){
        HistoryService historyService = pe.getHistoryService();
        HistoricActivityInstanceQuery query = historyService.createHistoricActivityInstanceQuery();
//        query.processInstanceId("2501");可以根据id筛选。。
        List<HistoricActivityInstance> list = query.list();
        for(HistoricActivityInstance ai :list){
            System.out.println(ai.getActivityId());
            System.out.println(ai.getActivityName());
            System.out.println(ai.getProcessDefinitionId());
            System.out.println(ai.getProcessInstanceId());
            System.out.println("==============================");
        }
    }
    @Test
    public void deleteDeployment() {
        // 流程部署id
        String deploymentId = "1";
        // 通过流程引擎获取repositoryService
         RepositoryService repositoryService =pe.getRepositoryService();
        //删除流程定义，如果该流程定义已有流程实例启动则删除时出错
        repositoryService.deleteDeployment(deploymentId);
        //设置true 级联删除流程定义，即使该流程有流程实例启动也可以删除，设置为false非级别删除方式，如果流程
        // repositoryService.deleteDeployment(deploymentId, true);
    }
}
