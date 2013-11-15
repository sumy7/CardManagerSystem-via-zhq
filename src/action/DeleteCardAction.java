package action;

import bean.Card;

import com.opensymphony.xwork2.ActionSupport;

import dao.CardDao;

public class DeleteCardAction extends ActionSupport {
	private Card card;
	private CardDao carddao;
	private Integer uid;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String execute() throws Exception {
		carddao = new CardDao();

		carddao.delete(uid);
		return "deletesuccess";
	}

}
