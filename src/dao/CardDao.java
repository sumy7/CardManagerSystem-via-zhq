package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;




import dbc.JdbcUtils;

import bean.Card;

public class CardDao {
	public void add(Card card)throws Exception{//添加名片
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
	public Card findById(int uid)throws Exception{//根据名片ID查找名片
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
	public void update(Card card)throws Exception{//更新数据库
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
	public void tdelete(int uid){//将被删除名片复制到另一表中
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
	public void delete(int uid)throws Exception{//永久删除
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
	public boolean hasExit(int uid)throws SQLException{//查看名片名是否已经存在
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
	public ArrayList<Card> selectTcard(int pageNo,int pageSize){//查看回收站
		ArrayList<Card> cardList=new ArrayList<Card>();
		int startReNo=(pageNo-1)*pageSize;//当前开始记录编号
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
	public int isEmpty() throws SQLException{//查看数据库是否为空
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
	public int tisEmpty() throws SQLException{//查看回收站是否为空
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
	public int getPageCount(int pageSize)throws Exception{//获取当前页数
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
	public ArrayList<Card> findAllCard(int pageNo,int pageSize){//查找所有名片
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
	public ArrayList<Card> findCard(String a) throws Exception{//模糊查询
		
		
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
