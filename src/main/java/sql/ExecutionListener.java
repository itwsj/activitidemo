package sql;

import org.activiti.engine.delegate.DelegateExecution;

import java.io.Serializable;

public interface ExecutionListener extends Serializable {
  String EVENTNAME_START = "start";
  String EVENTNAME_END = "end";
  String EVENTNAME_TAKE = "take";
  void notify(DelegateExecution execution) throws Exception;
}