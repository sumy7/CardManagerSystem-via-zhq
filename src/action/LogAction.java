package action;

import bean.Log;

import com.opensymphony.xwork2.ActionSupport;

import dao.LogDao;

public class LogAction extends ActionSupport {
	private Log log;
	private LogDao logdao;
	private String message;
	public String execute()throws Exception{
		logdao=new LogDao();
		if(logdao.findLog(log.getLogid(), log.getLogpwd())){
			return "success";
		}
		else if(logdao.findByLogId(log.getLogid())==null){
			message="�û��������ڣ�����ע�ᣡ";
			return "register";
		}
		else{
			message="�û�����������������µ�¼��";
			return "error";
		}
	}
	public Log getLog() {
		return log;
	}
	public void setLog(Log log) {
		this.log = log;
	}
	public LogDao getLogdao() {
		return logdao;
	}
	public void setLogdao(LogDao logdao) {
		this.logdao = logdao;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
