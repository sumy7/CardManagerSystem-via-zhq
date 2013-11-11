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
	public void setCrtd(Card card) {
		this.card = card;
	}
	public CardDao getCarddao() {
		return carddao;
	}
	public void setCarddao(CardDao carddao) {
		this.carddao = carddao;
	}
	public String execute()throws Exception{
		if(carddao.hasExit(card.getUid())==true){
			return "addfail";
		}
		else{
			carddao.add(card);
			return "addsuccess";
		}
	}
	

}
