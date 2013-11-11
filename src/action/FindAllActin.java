package action;

import java.util.ArrayList;

import bean.Card;

import com.opensymphony.xwork2.ActionSupport;

import dao.CardDao;

public class FindAllActin extends ActionSupport {

	private CardDao carddao = new CardDao();
	ArrayList<Card> cardlist;
	private Integer pageCount;
	private Integer pageNo;
	private Integer totalCard;
	private Integer countperPage;

	public ArrayList<Card> getCardlist() {
		return cardlist;
	}

	public void setCardlist(ArrayList<Card> cardlist) {
		this.cardlist = cardlist;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getCountperPage() {
		return countperPage;
	}

	public void setCountperPage(Integer countperPage) {
		this.countperPage = countperPage;
	}

	public String execute() throws Exception {
		totalCard = carddao.isEmpty();
		if (countperPage == null || countperPage < 10)
			countperPage = 10;
		pageCount = totalCard / countperPage + 1;
		if (pageNo == null)
			pageNo = 1;
		if (pageNo > pageCount)
			pageNo = pageCount;
		System.out.println("totalcard:" + totalCard + "countperPage"
				+ countperPage + "pageCount" + pageCount + "pageNo" + pageNo);
		if (totalCard != 0) {
			cardlist = carddao.findAllCard(pageNo, countperPage);
			return "findallcard";
		} else {
			return "notfindallcard";
		}
	}

}
