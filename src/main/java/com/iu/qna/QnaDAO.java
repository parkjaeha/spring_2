package com.iu.qna;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.iu.Board.BoardDAO;
import com.iu.Board.BoardDTO;
import com.iu.util.DBConnector;
import com.iu.util.ListData;
import com.iu.util.RowNum;

import oracle.jdbc.proxy.annotation.Pre;
@Repository
public class QnaDAO implements BoardDAO{

	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql =  "insert into qna values(board_seq.nextval,?,?,?,sysdate,0,board_seq.currval,0,0)";
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
	public int update(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardDTO> selectList(RowNum rowNum) throws Exception {
		Connection con =DBConnector.getConnect();
		String sql ="select * from "
				+ "(select rownum R, Q.* from "
				+ "(select * from qna where "+rowNum.getKind()+" like ? order by num desc) Q) "
				+ "where R between ? and ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%" + rowNum.getSearch()+"%");
		st.setInt(2, rowNum.getStartRow());
		st.setInt(3, rowNum.getLastRow());
		
		ResultSet rs =  st.executeQuery();
		List<BoardDTO> ar = new ArrayList<BoardDTO>();
		QnaDTO qnaDTO = null;
		while(rs.next()){
			qnaDTO = new QnaDTO();
			qnaDTO.setNum(rs.getInt("num"));
			qnaDTO.setTitle(rs.getString("title"));
			qnaDTO.setWriter(rs.getString("writer"));
			qnaDTO.setContents(rs.getString("contents"));
			qnaDTO.setReg_date(rs.getDate("reg_date"));
			qnaDTO.setHit(rs.getInt("hit"));
			qnaDTO.setRef(rs.getInt("ref"));
			qnaDTO.setStep(rs.getInt("step"));
			qnaDTO.setDepth(rs.getInt("depth"));
			ar.add(qnaDTO);
		}
		
		return ar;
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql =  "select * from qna where num=?";
		
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		
		ResultSet rs = st.executeQuery();
			QnaDTO qnaDTO= null;
		if(rs.next()){
			qnaDTO = new QnaDTO();
			qnaDTO.setNum(rs.getInt("num"));
			qnaDTO.setTitle(rs.getString("title"));
			qnaDTO.setWriter(rs.getString("writer"));
			qnaDTO.setContents(rs.getString("contents"));
			qnaDTO.setReg_date(rs.getDate("reg_date"));
			qnaDTO.setHit(rs.getInt("hit"));
			qnaDTO.setRef(rs.getInt("ref"));
			qnaDTO.setStep(rs.getInt("step"));
			qnaDTO.setDepth(rs.getInt("step"));
		}
		
		DBConnector.disConnect(st, con);
		
		return (BoardDTO)qnaDTO;
	}

	@Override
	public int hit(int num) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql ="update qna set hit=hit+1 where num=?";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		int result = st.executeUpdate();
		
		DBConnector.disConnect(st, con);
		return result;
	}

	@Override
	public int getTotalCount(RowNum rowNum) throws Exception {
		// TODO Auto-generated method stub
		Connection con  = DBConnector.getConnect();
		String sql = "select nvl(count(num),0) from qna where "+rowNum.getKind() + " like ?";
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, "%"+rowNum.getSearch()+"%");
		
		ResultSet rs = st.executeQuery();
		
		rs.next();
		int result = rs.getInt(1);
		
		DBConnector.disConnect(rs, st, con);
		
		return result;
	}

}
