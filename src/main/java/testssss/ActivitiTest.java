package testssss;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        deploymentBuilder.addClasspathResource("diagram/callbpmn.bpmn");
        deploymentBuilder.addClasspathResource("diagram/callbpmn.png");
        Deployment deployment = deploymentBuilder.deploy();
        System.out.println(deployment.getId());
    }

    @Test
    public void test2() {
        // 部署查询对象----查询部署表act_re_deployment，也就是流程定义列表。。
        ProcessDefinitionQuery query = pe.getRepositoryService()
                .createProcessDefinitionQuery();
//        query.orderByDeploymenTime().desc();
        query.latestVersion();
        List<ProcessDefinition> list = query.list();
        for (ProcessDefinition processDefinition : list) {

            System.out.println(processDefinition.getId()+"-------"+processDefinition.getName()+"---"+processDefinition.getKey());
        }

//        qjlc:1:17504-------请假流程---qjlc
//        zhuliucheng:3:132504-------null---zhuliucheng
//        zhuliuchenge:1:135004-------null---zhuliuchenge
//        ziliucheng:2:42504-------null---ziliucheng
//        ziliuchenge:2:45004-------null---ziliuchenge
//        ziliuchengs:2:47504-------null---ziliuchengs
    }


    @Test
    public void test3() {

            //开启流程
            String processDefinitionId = "zhuliuchenge";
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("call","ziliucheng");
            map.put("calle","ziliuchenge");
            map.put("calls","ziliuchengs");

//        ProcessInstance pi = pe.getRuntimeService().startProcessInstanceById(processDefinitionId);
            ProcessInstance pi = pe.getRuntimeService().startProcessInstanceByKey(processDefinitionId,map);
//        ProcessInstance pi = pe.getRuntimeService().startProcessInstanceByKey(processDefinitionId); //多种方式请求
//        ProcessInstance pi  =  pe.getRuntimeService().startProcessInstanceById(processDefinitionId,m);
            System.out.println(pi.getId()+"--"+pi.getProcessDefinitionId()+"--"+pi.getName()+"---"+pi.getStartUserId());

    }

    @Test
    public void test4() {
        //查询指派人下的任务
        TaskQuery taskQuery = pe.getTaskService().createTaskQuery();
//        String assignee = "张三";  //指定人就是当前人下的任务，不指定人就是所有任务
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
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("call","ziliucheng");
        //执行任务
        String taskId = "160003";
        pe.getTaskService().complete(taskId,map);
//        pe.getTaskService().complete(taskId);
//        pe.getRuntimeService().add


    }
    @Test
    public void saveTask(){
        Task task = pe.getTaskService().newTask("11222");
        task.setAssignee("张三");
        task.setName("审核");
        pe.getTaskService().saveTask(task);
        System.out.println(task.getId()+"------"+task.getAssignee());
    }
    @Test
    public void getvar(){
        Map<String, Object> variables = pe.getTaskService().getVariables("47508");
        System.out.println(variables);
        ProcessInstanceQuery processInstanceQuery = pe.getRuntimeService().createProcessInstanceQuery().includeProcessVariables();
    }
    @Test
    public void delTask() {
        //执行任务 //运行中的无法删除，回报错。。。。
        String taskId = "5002";
        pe.getTaskService().deleteTask(taskId);
    }

    @Test
    public void queryProcessInstance() {
        //查询流程定义数据
        // 流程定义key
        String processDefinitionKey = "qjlc";
        // 获取RunTimeService
        RuntimeService runtimeService = pe.getRuntimeService();
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery()
                .processDefinitionKey(processDefinitionKey).list();
        for (ProcessInstance processInstance : list) {
            System.out.println("----------------------------");
//            System.out.println("流程实例id：" + processInstance.);
            System.out.println("流程实例id：" + processInstance.getProcessInstanceId());
            System.out.println("所属流程定义id：" + processInstance.getProcessDefinitionId());
            System.out.println("是否执行完成：" + processInstance.isEnded());
            System.out.println("是否暂停：" + processInstance.isSuspended());
            System.out.println("当前活动标识：" + processInstance.getActivityId());
        }

    }
    @Test
    public void stopTask() {
        //终止流程
        String processId = "10001";
        String deleteReason = "手动终止";//原因
        //终止流程
        pe.getRuntimeService().deleteProcessInstance(processId,deleteReason);
//        //激活
//        pe.getRuntimeService().activateProcessInstanceById(processId);
//        //挂起
//        pe.getRuntimeService().suspendProcessInstanceById(processId);
    }
    @Test
    public void testHistoric01(){
        //历史记录
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
    public void unfinish(){
        HistoricActivityInstance historicActivityInstance = pe.getHistoryService().createHistoricActivityInstanceQuery()
                .processInstanceId("22501")
                .unfinished()//未完成的活动(任务)
                .singleResult();
    }
    @Test
    public void deleteDeployment() {
        //删除流程
        // 流程部署id
        String deploymentId = "1";
        // 通过流程引擎获取repositoryService
         RepositoryService repositoryService =pe.getRepositoryService();
        //删除流程定义，如果该流程定义已有流程实例启动则删除时出错
        repositoryService.deleteDeployment(deploymentId);
        //设置true 级联删除流程定义，即使该流程有流程实例启动也可以删除，设置为false非级别删除方式，如果流程
        // repositoryService.deleteDeployment(deploymentId, true);
    }

    @Test
    public void suspendOrActivateProcessDefinition() {
        // 挂起激活流程定义--全部流程实例
        String processDefinitionId = "qjlc";
        RepositoryService repositoryService = pe.getRepositoryService();
        // 获得流程定义
        ProcessDefinition processDefinition = repositoryService .createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId).singleResult();
        //是否暂停
        boolean suspend = processDefinition.isSuspended();
        if(suspend){
            //如果暂停则激活，这里将流程定义下的所有流程实例全部激活
            repositoryService.activateProcessDefinitionById(processDefinitionId, true, null);
            System.out.println("流程定义："+processDefinitionId+"激活");
        }else{
            //如果激活则挂起，这里将流程定义下的所有流程实例全部挂起
            repositoryService.suspendProcessDefinitionById(processDefinitionId, true, null);
            System.out.println("流程定义："+processDefinitionId+"挂起");
        }
    }

    @Test
    public void suspendOrActiveProcessInstance() {
        // 流程实例id
        String processInstanceId = "";
        // 获取RunTimeService
        RuntimeService runtimeService = pe.getRuntimeService();
        //根据流程实例id查询流程实例
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
        boolean suspend = processInstance.isSuspended();
        if(suspend){
            //如果暂停则激活
            runtimeService.activateProcessInstanceById(processInstanceId);
            System.out.println("流程实例："+processInstanceId+"激活");
        }else{
            //如果激活则挂起
            runtimeService.suspendProcessInstanceById(processInstanceId);
            System.out.println("流程实例："+processInstanceId+"挂起");
        }
    }

    @Test
    public void setAssi() {
        //设置执行人
       pe.getTaskService().setAssignee("taskid","userId");

    }
    @Test
    public void claimTask(){
        TaskService taskService = pe.getTaskService();
        //要拾取的任务id
        String taskId = "15002";
        //任务候选人id
        String userId = "zhangsan";
        //拾取任务
        // 即使该用户不是候选人也能拾取(建议拾取时校验是否有资格)
        // 校验该用户有没有拾取任务的资格
        Task task = taskService.createTaskQuery().taskId(taskId)
                .taskCandidateUser(userId)//根据候选人查询
                .singleResult();
        if(task!=null){
            taskService.claim(taskId, userId);
            System.out.println("任务拾取成功");
        }
    }
    // 归还组任务，由个人任务变为组任务，还可以进行任务交接
    @Test
    public void setAssigneeToGroupTask() {
        // 查询任务使用
        TaskService taskService = pe.getTaskService();
        // 当前待办任务
        String taskId = "15002";
        // 任务负责人
        String userId = "zhangsan";
        // 校验userId是否是taskId的负责人，如果是负责人才可以归还组任务
        Task task = taskService.createTaskQuery().taskId(taskId)
                .taskAssignee(userId).singleResult();
        if (task != null) {
            // 如果设置为null，归还组任务,该 任务没有负责人
            taskService.setAssignee(taskId, null);
        }
    }

    @Test
    public void test333() {
        //开启流程
        String processDefinitionId = "qjlc:1:4";
        String variables = "流程变量";
        Map m = new HashMap();
        m.put("variables",variables);
        //在任务中设置流程变量
        pe.getTaskService().setVariables("taskid",m);

    }
    @Test
    public void test3333() {
        //开启流程
        String processDefinitionId = "qjlc:1:4";
        String variables = "流程变量";
        Map m = new HashMap();
        m.put("variables",variables);
        //在任务中设置流程变量
        pe.getRuntimeService().setVariables("executionID",m);

    }
    @Test
    public void test33(){
        // 根据流程定义的key启动一个流程实例
        String businessKey = "hello";
        ProcessInstance processInstance = pe.getRuntimeService()
                .startProcessInstanceByKey("qjlc", businessKey);

        //获取businessKey
        String businessKey1 = processInstance.getBusinessKey();
        System.out.println(businessKey1);
    }
}
