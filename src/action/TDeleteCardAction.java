package action;

import bean.Card;

import com.opensymphony.xwork2.ActionSupport;

import dao.CardDao;

public class TDeleteCardAction extends ActionSupport {
	private Card card;
	private CardDao carddao;
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public CardDao getCarddao() {
		return carddao;
	}
	public void setCarddao(CardDao carddao) {
		this.carddao = carddao;
	}
	public String execute()throws Exception{
		carddao.tdelete(card.getUid());
		return "tdeletesuccess";
	}
	

}
