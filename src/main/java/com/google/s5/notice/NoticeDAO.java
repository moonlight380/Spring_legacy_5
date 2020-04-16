package com.google.s5.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.google.s5.board.BoardDAO;
import com.google.s5.board.BoardVO;

public class NoticeDAO implements BoardDAO{
	
	@Override
	public List<BoardVO> boardList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardVO boardSelect() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//상속받은 메서드의 선언부가 같아야 함 . 즉 선언부가 같아야 함. 
	public int boardWrite(BoardVO boardVO) throws Exception {
		Connection con =null;
		String sql="insert into notice values(board_seq.nextval, ?,?,?,sysdate,0)";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, boardVO.getTitle());
		st.setString(2, boardVO.getWiter());
		st.setString(3, boardVO.getContents());

		int result=st.executeUpdate();
		st.close();
		con.close();
		
		return result;
	}


}
