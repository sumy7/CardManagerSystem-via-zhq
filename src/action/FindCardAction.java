package action;

import java.util.ArrayList;

import bean.Card;

import com.opensymphony.xwork2.ActionSupport;

import dao.CardDao;

public class FindCardAction extends ActionSupport {
	private ArrayList<Card> cardlist;
	private CardDao carddao = null;
	private String keyword;
	private String searchkey;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSearchkey() {
		return searchkey;
	}

	public void setSearchkey(String searchkey) {
		this.searchkey = searchkey;
	}

	public ArrayList<Card> getCardlist() {
		return cardlist;
	}

	public void setCardlist(ArrayList<Card> cardlist) {
		this.cardlist = cardlist;
	}

	public String execute() throws Exception {
		carddao = new CardDao();
		System.out.println("FIND:" + searchkey + "=" + keyword);
		cardlist = carddao.findCard(searchkey, keyword);
		// System.out.println("RESULT:"+cardlist.toString());
		if (cardlist == null)
			System.out.println("null");
		return "findcard";
	}

}
