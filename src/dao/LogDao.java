package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dbc.JdbcUtils;

import bean.Log;

public class LogDao {
	public void add(Log log)throws Exception{
		Connection conn=null;
		PreparedStatement ps=null;
		try{
			conn=JdbcUtils.getConnection();
			String sql="insert into logg values(?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, log.getLogid());
			ps.setString(2, log.getLogpwd());
			ps.executeUpdate();
		}finally{
			JdbcUtils.free1(ps, conn);
		}
	}
	public Log findByLogId(String logid)throws Exception{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Log log=null;
		try{
			conn=JdbcUtils.getConnection();
			String sql="select * from logg where logid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, logid);
			rs=ps.executeQuery();
			if(rs.next()){
				log=new Log();
				log.setLogid(rs.getString("logid"));
				log.setLogpwd(rs.getString("logpwd"));
			}
		}finally{
			JdbcUtils.free2(rs, ps, conn);
		}
		return log;
	}
	public boolean findLog(String logid,String logpwd)throws Exception{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=JdbcUtils.getConnection();
			String sql="select * from logg where logid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, logid);
			rs=ps.executeQuery();
			if(rs.next()){
				if(logid.equals(rs.getString("logid")) && logpwd.equals(rs.getString("logpwd"))){
					return true;
				}
				
			}
			return false;
		}finally{
			JdbcUtils.free2(rs, ps, conn);
		}
	}

}
