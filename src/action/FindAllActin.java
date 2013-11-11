package action;


import java.util.ArrayList;

import bean.Card;

import com.opensymphony.xwork2.ActionSupport;

import dao.CardDao;

public class FindAllActin extends ActionSupport {
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
	ArrayList<Card> cardList=carddao.findAllCard(1, 10);
	public ArrayList<Card> getCardList() {
		return cardList;
	}
	public void setCardList(ArrayList<Card> cardList) {
		this.cardList = cardList;
	}
	public String execute()throws Exception{
		if(carddao.isEmpty()!=0){
			cardList=carddao.findAllCard(1, 10);
			return "findallcard";
		}
		else{
			return"notfindallcard";
		}
	}

}
