package com.iu.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.iu.Board.BoardDAO;
import com.iu.Board.BoardDTO;
import com.iu.util.DBConnector;

public class NoticeDAO implements BoardDAO{

	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		Connection con = DBConnector.getConnect();
		String sql =  "insert into notice values(board_seq.nextval,?,?,?,?,?)";
		int result = 0;
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, boardDTO.getWriter());
		st.setString(2, boardDTO.getTitle());
		st.setString(3, boardDTO.getContents());
		st.setDate(4, boardDTO.getReg_date());
		st.setInt(5, boardDTO.getHit());
		
		result = st.executeUpdate();
		
		if(result>0){
			System.out.println("insert success");
		}
		
		DBConnector.disConnect(st, con);
		
		return result;
	}

	@Override
	public int update(BoardDTO boardDTO)  throws Exception{
		// TODO Auto-generated method stub
		Connection con = DBConnector.getConnect();
		String sql =  "update notice set writer=?,title=?,contents=?,reg_date=?,hit=? where num=?";
		int result = 0;
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, boardDTO.getWriter());
		st.setString(2, boardDTO.getTitle());
		st.setString(3, boardDTO.getContents());
		st.setDate(4, boardDTO.getReg_date());
		st.setInt(5, boardDTO.getHit());
		st.setInt(6, boardDTO.getNum());
	
		result = st.executeUpdate();
		
		if(result>0){
			System.out.println("update success");
		}
		
		DBConnector.disConnect(st, con);
		
		return result;
	}

	@Override
	public int delete(BoardDTO boardDTO)  throws Exception{
		// TODO Auto-generated method stub
		Connection con = DBConnector.getConnect();
		String sql =  "delete notice where num=?";
		int result = 0;
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, boardDTO.getNum());
	
		result = st.executeUpdate();
		
		if(result>0){
			System.out.println("delete success");
		}
		
		DBConnector.disConnect(st, con);
		
		return result;
	}

	@Override
	public List<BoardDTO> selectList(BoardDTO boardDTO)  throws Exception{
		// TODO Auto-generated method stub
				Connection con = DBConnector.getConnect();
				String sql =  "select * from notice order by num desc";
				List<BoardDTO> ar = new ArrayList<BoardDTO>();
	
				PreparedStatement st = con.prepareStatement(sql);
				
				ResultSet rs = st.executeQuery();
				
				while(rs.next()){
					BoardDTO boardDTO2 = new BoardDTO();
					boardDTO2.getNum();
					boardDTO2.getWriter();
					boardDTO2.getTitle();
					boardDTO2.getContents();
					boardDTO2.getReg_date();
					boardDTO2.getHit();
					ar.add(boardDTO2);
				}
				
				DBConnector.disConnect(st, con);
				
				return ar;
	}

	@Override
	public BoardDTO selectOne(int num)  throws Exception{
		// TODO Auto-generated method stub
		Connection con = DBConnector.getConnect();
		String sql =  "select * from notice where num=?";
		
		BoardDTO boardDTO = null;
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		
		ResultSet rs = st.executeQuery();
		
		if(rs.next()){
			boardDTO = new BoardDTO();
			boardDTO.getNum();
			boardDTO.getWriter();
			boardDTO.getTitle();
			boardDTO.getContents();
			boardDTO.getReg_date();
			boardDTO.getHit();
			
		}
		
		DBConnector.disConnect(st, con);
		
		return boardDTO;
	}

	@Override
	public int hit(int num) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql ="update notice set hit=hit+1 where num=?";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		int result = st.executeUpdate();
		
		DBConnector.disConnect(st, con);
		return result;
	}

}
