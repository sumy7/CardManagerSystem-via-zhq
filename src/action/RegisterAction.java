package action;

import bean.Log;

import com.opensymphony.xwork2.ActionSupport;

import dao.LogDao;

public class RegisterAction extends ActionSupport {
	private Log log;
	private LogDao logdao;
	private String message;
	public String execute()throws Exception{
		if(log.getLogid()!=null && !log.getLogid().equals("")&& log.getLogpwd()!=null && !log.getLogpwd().equals("")){
			logdao.add(log);
			message="ע��ɹ������¼��";
			return "register-success";
		}
		else{
			message="ע��ʧ�ܣ�������ע�ᣡ";
			return "register-error";
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
