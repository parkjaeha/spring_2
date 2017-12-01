package com.iu.notice;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.iu.Board.BoardDAO;
import com.iu.Board.BoardDTO;
import com.iu.util.DBConnector;
import com.iu.util.RowNum;


@Repository
public class NoticeDAO implements BoardDAO{

	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		Connection con = DBConnector.getConnect();
		String sql =  "insert into notice values(board_seq.nextval,?,?,?,sysdate,0)";
		int result = 0;
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, boardDTO.getWriter());
		st.setString(2, boardDTO.getTitle());
		st.setString(3, boardDTO.getContents());
		
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
	public List<BoardDTO> selectList(RowNum rowNum) throws Exception {
		List<BoardDTO> ar = new ArrayList<BoardDTO>();
		BoardDTO boardDTO=null;
		Connection con = DBConnector.getConnect();
		String sql ="select * from "
				+ "(select rownum R, N.* from "
				+ "(select * from notice where "+rowNum.getKind()+" like ? order by num desc) N) "
				+ "where R between ? and ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%" + rowNum.getSearch()+"%");
		st.setInt(2, rowNum.getStartRow());
		st.setInt(3, rowNum.getLastRow());
		ResultSet rs = st.executeQuery();
		while(rs.next()){
			boardDTO = new BoardDTO();
			boardDTO.setNum(rs.getInt("num"));
			boardDTO.setWriter(rs.getString("writer"));
			boardDTO.setTitle(rs.getString("title"));
			boardDTO.setContents(rs.getString("contents"));
			boardDTO.setReg_date(rs.getDate("reg_date"));
			boardDTO.setHit(rs.getInt("hit"));
			ar.add(boardDTO);
		}
		DBConnector.disConnect(rs, st, con);
		
		return ar;
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception{
		// TODO Auto-generated method stub
		Connection con = DBConnector.getConnect();
		String sql =  "select * from notice where num=?";
		
		BoardDTO boardDTO = null;
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		
		ResultSet rs = st.executeQuery();
		
		if(rs.next()){
			boardDTO = new BoardDTO();
			boardDTO.setNum(rs.getInt("num"));
			boardDTO.setTitle(rs.getString("title"));
			boardDTO.setWriter(rs.getString("writer"));
			boardDTO.setContents(rs.getString("contents"));
			boardDTO.setReg_date(rs.getDate("reg_date"));
			boardDTO.setHit(rs.getInt("hit"));
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

	@Override
	public int getTotalCount(RowNum rowNum) throws Exception {
		// TODO Auto-generated method stub
		Connection con =DBConnector.getConnect();
		String sql = "select nvl(count(num),0) from notice where "+rowNum.getKind()+" like ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+rowNum.getSearch()+"%");
		
		ResultSet rs =  st.executeQuery();
		rs.next();
		int result = rs.getInt(1);
		
		DBConnector.disConnect(rs, st, con);
		
		return result;
	}

}
