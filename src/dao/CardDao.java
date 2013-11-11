package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;




import dbc.JdbcUtils;

import bean.Card;

public class CardDao {
	public void add(Card card)throws Exception{//�����Ƭ
		Connection conn=null;
		PreparedStatement ps=null;
		try{
			conn=JdbcUtils.getConnection();
			String sql="insert into card values(?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, card.getUid());
			ps.setString(2, card.getUname());
			ps.setString(3, card.getUposition());
			ps.setString(4, card.getUtel());
			ps.setString(5, card.getUemail());
			ps.setString(6, card.getUaddress());
			ps.executeUpdate();
		}finally{
			JdbcUtils.free1(ps, conn);
		}
	}
	public Card findById(int uid)throws Exception{//������ƬID������Ƭ
		ResultSet rs=null;
		Connection conn=null;
		PreparedStatement ps=null;
		Card card=null;
		try{
			conn=JdbcUtils.getConnection();
			String sql="select * from card where uid=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, card.getUid());
			rs=ps.executeQuery();
			if(rs.next()){
				card=new Card();
				card.setUid(rs.getInt(1));
				card.setUname(rs.getString(2));
				card.setUposition(rs.getString(3));
				card.setUtel(rs.getString(4));
				card.setUemail(rs.getString(5));
				card.setUaddress(rs.getString(6));
			}
		}finally{
			JdbcUtils.free2(rs, ps, conn);
		}
		return card;
	}
	public void update(Card card)throws Exception{//�������ݿ�
		Connection conn=null;
		PreparedStatement ps=null;
		try{
			card=new Card();
			conn=JdbcUtils.getConnection();
			String sql="update card set uid=?,uname=?,uposition=?,utel=?,uemail=?,uaddress=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, card.getUid());
			ps.setString(2, card.getUname());
			ps.setString(3, card.getUposition());
			ps.setString(4, card.getUtel());
			ps.setString(5, card.getUemail());
			ps.setString(6, card.getUaddress());
			ps.executeUpdate();
		}finally{
			JdbcUtils.free1(ps, conn);
		}
	}
	public void tdelete(int uid){//����ɾ����Ƭ���Ƶ���һ����
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Card card=null;
		try{
			conn=JdbcUtils.getConnection();
			card=findById(uid);
			String sql="insert into tcard values(?,?,?,?,?,?)"; 
			ps=conn.prepareStatement(sql);
			ps.setInt(1, uid);
			ps.setString(2, card.getUname());
			ps.setString(3, card.getUposition());
			ps.setString(4, card.getUtel());
			ps.setString(5, card.getUemail());
			ps.setString(6, card.getUaddress());
			ps.executeUpdate();
			delete(uid);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtils.free2(rs, ps, conn);
		}
	}
	public void delete(int uid)throws Exception{//����ɾ��
		Connection conn=null;
		PreparedStatement ps=null;
		try{
			conn=JdbcUtils.getConnection();
			String sql="delete from card where uid=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, uid);
			ps.executeUpdate();
		}finally{
			JdbcUtils.free1(ps, conn);
		}
	}
	public boolean hasExit(int uid)throws SQLException{//�鿴��Ƭ���Ƿ��Ѿ�����
		boolean find=false;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=JdbcUtils.getConnection();
			String sql="select * from card where uid=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, uid);
			rs=ps.executeQuery();
			if(rs.next()){
				find=true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtils.free2(rs, ps, conn);
		}
		return find;
	}
	public ArrayList<Card> selectTcard(int pageNo,int pageSize){//�鿴����վ
		ArrayList<Card> cardList=new ArrayList<Card>();
		int startReNo=(pageNo-1)*pageSize;//��ǰ��ʼ��¼���
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=JdbcUtils.getConnection();
			String sql="select * from tcard  order by uid limit ?,?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, startReNo);
			ps.setInt(2, pageSize);
			rs=ps.executeQuery();
			while(rs.next()){
				Card card=new Card();
				card.setUid(rs.getInt(1));
				card.setUname(rs.getString(2));
				card.setUposition(rs.getString(3));
				card.setUtel(rs.getString(4));
				card.setUemail(rs.getString(5));
				card.setUaddress(rs.getString(6));
				cardList.add(card);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtils.free2(rs, ps, conn);
		}
		return cardList;
	}
	public int isEmpty() throws SQLException{//�鿴���ݿ��Ƿ�Ϊ��
		int count=0;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=JdbcUtils.getConnection();
			String sql="select count(*) from card";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtils.free2(rs, ps, conn);
		}
		return count;
	}
	public int tisEmpty() throws SQLException{//�鿴����վ�Ƿ�Ϊ��
		int count=0;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=JdbcUtils.getConnection();
			String sql="select count(*) from tcard";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtils.free2(rs, ps, conn);
		}
		return count;
	}
	public int getPageCount(int pageSize)throws Exception{//��ȡ��ǰҳ��
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int recordCount=0,t1=0,t2=0;
		try{
			conn=JdbcUtils.getConnection();
			String sql="select count(*) from card";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			rs.next();
			recordCount=rs.getInt(1);
			t1=recordCount%pageSize;
			t2=recordCount/pageSize;
		}finally{
			JdbcUtils.free2(rs, ps, conn);
		}
		return t1==0?t2:t2+1;
	}
	public ArrayList<Card> findAllCard(int pageNo,int pageSize){//����������Ƭ
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int startReNo=(pageNo-1)*pageSize;
		ArrayList<Card> cardList=new ArrayList<Card>();
		try{
			conn=JdbcUtils.getConnection();
			String sql="select * from card order by uid limit ?,?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, startReNo);
			ps.setInt(2, pageSize);
			rs=ps.executeQuery();
			while(rs.next()){
				Card card=new Card();
				card.setUid(rs.getInt(1));
				card.setUname(rs.getString(2));
				card.setUposition(rs.getString(3));
				card.setUtel(rs.getString(4));
				card.setUemail(rs.getString(5));
				card.setUaddress(rs.getString(6));
				cardList.add(card);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			JdbcUtils.free2(rs, ps, conn);
		}
		return cardList;
	}
	public ArrayList<Card> findCard(String a) throws Exception{//ģ����ѯ
		
		
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 
		 ArrayList<Card> cardList=new ArrayList<Card>();
		 
		try {
				conn = JdbcUtils.getConnection();
				String sql = "select * from card where uname like ?";
				ps=conn.prepareStatement(sql);
				 ps.setString(1,"%"+a+"%");
				 
				rs=ps.executeQuery();
				System.out.println("1");
				 while(rs.next()){
					 Card card=new Card();
					 card.setUid(rs.getInt(1));
						System.out.println("2");
					 card.setUname(rs.getString(2));
					 card.setUposition(rs.getString(3));
					 card.setUtel(rs.getString(4));
						System.out.println("3");
					 card.setUemail(rs.getString(5));
					 card.setUaddress(rs.getString(6));
						System.out.println("4");
					 cardList.add(card);
						System.out.println("5");
				 }
				
			}finally {
				JdbcUtils.free2(rs, ps, conn);
			}
			 return cardList;
	}

}
