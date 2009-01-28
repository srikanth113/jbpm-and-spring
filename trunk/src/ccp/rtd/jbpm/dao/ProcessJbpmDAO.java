package ccp.rtd.jbpm.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.jbpm.JbpmContext;
import org.jbpm.JbpmException;
import org.jbpm.context.exe.ContextInstance;
import org.jbpm.db.GraphSession;
import org.jbpm.graph.def.Node;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.springmodules.workflow.jbpm31.JbpmCallback;
import org.springmodules.workflow.jbpm31.JbpmTemplate;

import java.util.Collection;
import java.util.List;

public class ProcessJbpmDAO {
	private static final Log log = LogFactory.getLog(ProcessJbpmDAO.class);

	public ProcessJbpmDAO() {
		log.debug("ProcessJbpmDAO");
	}

	private JbpmTemplate jbpmTemplate;

	public JbpmTemplate getJbpmTemplate() {
		return jbpmTemplate;
	}

	public void setJbpmTemplate(JbpmTemplate jbpmTemplate) {
		this.jbpmTemplate = jbpmTemplate;
	}

	public List<ProcessInstance> getProcessInstances() {
		return this.jbpmTemplate.findProcessInstances();
	}

	public ProcessDefinition findLatestProcessDefinition(
			final String processName) {
		return (ProcessDefinition) this.jbpmTemplate
				.execute(new JbpmCallback() {
					public Object doInJbpm(JbpmContext context) {
						GraphSession graphSession = context.getGraphSession();
						ProcessDefinition processDefinition = graphSession
								.findLatestProcessDefinition(processName);
						Hibernate.initialize(processDefinition.getNodes());
						return processDefinition;
					}
				});
	}

	public ProcessInstance getNewProcessInstance() {
		ProcessInstance pi = this.jbpmTemplate.getProcessDefinition()
				.createProcessInstance();
		Token token = pi.getRootToken();
		token.signal();
		this.jbpmTemplate.saveProcessInstance(pi);
		return pi;
	}

	public Long saveProcessInstance(ProcessInstance pi) {
		return this.jbpmTemplate.saveProcessInstance(pi);
	}

	public Collection findTaskInstances(final long processInstanceId) {
		return (Collection) this.jbpmTemplate.execute(new JbpmCallback() {
			public Object doInJbpm(JbpmContext context) {
				GraphSession graphSession = context.getGraphSession();
				ProcessInstance processInstance = graphSession
						.getProcessInstance(processInstanceId);
				Collection taskInstances = processInstance
						.getTaskMgmtInstance().getTaskInstances();
				Hibernate.initialize(taskInstances);
				for (Object taskInstanceObj : taskInstances) {
					TaskInstance taskInstance = (TaskInstance) taskInstanceObj;
					Hibernate.initialize(taskInstance);
					
				}
				return taskInstances;
			}
		});
	}

	public ProcessInstance findProcessInstance(final long processInstanceId) {
		return (ProcessInstance) this.jbpmTemplate.execute(new JbpmCallback() {
			public Object doInJbpm(JbpmContext context) {
				GraphSession graphSession = context.getGraphSession();
				ProcessInstance processInstance = graphSession
						.getProcessInstance(processInstanceId);
				if (processInstance != null) {
					Hibernate.initialize(processInstance);
					Hibernate.initialize(processInstance.getRootToken());
					Hibernate.initialize(processInstance.findAllTokens());
					Hibernate.initialize(processInstance.getInstances());
					for (Object tokenObj : processInstance.findAllTokens()) {
						Token token = (Token) tokenObj;
						Hibernate.initialize(token);
						Hibernate.initialize(token.getChildren());
					}
					for (Object nodeObj : processInstance
							.getProcessDefinition().getNodes()) {
						Node node = (Node) nodeObj;
						Hibernate.initialize(node.getLeavingTransitions());
					}
				}
				return processInstance;
			}
		});
	}
	

	public void signalToken(final ProcessInstance processInstance,
			final String tokenName) {
		this.jbpmTemplate.execute(new JbpmCallback() {
			public Object doInJbpm(JbpmContext context) {
				GraphSession graphSession = context.getGraphSession();
				ProcessInstance signalProcessInstance = graphSession
						.getProcessInstance(processInstance.getId());
				Token token = signalProcessInstance.getRootToken().findToken(
						tokenName);
				if (token == null)
					throw new JbpmException("token '" + tokenName
							+ "' was not found in this processInstance (id="
							+ processInstance.getId() + ")");

				token.signal();
				context.save(signalProcessInstance);
				return null;
			}
		});
	}

	public void saveVariable(final Long processInstanceId, final String key,
			final Object value) {
		this.jbpmTemplate.execute(new JbpmCallback() {
			public Object doInJbpm(JbpmContext context) {
				GraphSession graphSession = context.getGraphSession();
				ProcessInstance processInstance = graphSession
						.getProcessInstance(processInstanceId);
				processInstance.getContextInstance().setVariable(key, value);
				context.save(processInstance);
				return null;
			}
		});
	}
	
	public void saveVariable(final Long processInstanceId, final String key,
			final String value) {
		this.jbpmTemplate.execute(new JbpmCallback() {
			public Object doInJbpm(JbpmContext context) {
				GraphSession graphSession = context.getGraphSession();
				ProcessInstance processInstance = graphSession
						.getProcessInstance(processInstanceId);
				processInstance.getContextInstance().setVariable(key, value);
				context.save(processInstance);
				return null;
			}
		});
	}

	public void saveVariable(final ProcessInstance processInstance,
			final String key, final Object value) {
		ContextInstance ctxI = processInstance.getContextInstance();
		ctxI.setVariable(key, value);
		jbpmTemplate.saveProcessInstance(processInstance);
	}
	
	public Object fetchVariableByKey(final long processInstanceId,final String name) {
		 return (Object) this.jbpmTemplate.execute(new JbpmCallback() {
             public Object doInJbpm(JbpmContext context) {
                     GraphSession graphSession = context.getGraphSession();
                     ProcessInstance processInstance = graphSession
                                     .getProcessInstance(processInstanceId);
                     ContextInstance ctx = processInstance.getContextInstance();
                     return ctx.getVariable(name);
             }
     });
    }
	
	public void signalProcessInstance(final long processInstanceId) {
        this.jbpmTemplate.execute(new JbpmCallback() {
                public Object doInJbpm(JbpmContext context) {
                        GraphSession graphSession = context.getGraphSession();
                        ProcessInstance processInstance = graphSession
                                        .getProcessInstance(processInstanceId);
                        processInstance.signal();
                        context.save(processInstance);
                        return null;
                }
        });
	}

	public boolean hasProcessInstanceEnded(final long processInstanceId) {
		return (Boolean) this.jbpmTemplate.execute(new JbpmCallback() {
			public Object doInJbpm(JbpmContext context) {
				ProcessInstance initializedProcessInstance = findProcessInstance(processInstanceId);
				return initializedProcessInstance.hasEnded();
			}
		});
	}
}
