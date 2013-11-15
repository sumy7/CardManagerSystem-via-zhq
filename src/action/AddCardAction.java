package action;

import bean.Card;

import com.opensymphony.xwork2.ActionSupport;

import dao.CardDao;

public class AddCardAction extends ActionSupport {
	private Card card;
	private CardDao carddao;

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public String execute() throws Exception {

		carddao = new CardDao();
		carddao.add(card);
		return "addsuccess";

	}

}
