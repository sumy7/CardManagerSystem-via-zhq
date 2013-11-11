package action;

import java.util.ArrayList;

import bean.Card;

import com.opensymphony.xwork2.ActionSupport;

import dao.CardDao;

public class FindCardAction extends ActionSupport {
	private ArrayList<Card> cardlist;
	private CardDao carddao = null;
	private String searchword;

	public ArrayList<Card> getCardlist() {
		return cardlist;
	}

	public void setCardlist(ArrayList<Card> cardlist) {
		this.cardlist = cardlist;
	}

	public String getSearchword() {
		return searchword;
	}

	public void setSearchword(String searchword) {
		this.searchword = searchword;
	}

	public String execute()throws Exception{
		carddao = new CardDao();
		System.out.println("FIND:"+searchword);
		cardlist=carddao.findCard(searchword);
		//System.out.println("RESULT:"+cardlist.toString());
		if(cardlist==null) System.out.println("null");
		return "findcard";
	}

}
