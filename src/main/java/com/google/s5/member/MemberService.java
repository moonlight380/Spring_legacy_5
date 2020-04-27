package com.google.s5.member;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.s5.board.BoardVO;
import com.google.s5.member.memberFile.MemberFileDAO;
import com.google.s5.member.memberFile.MemberFileVO;
import com.google.s5.util.FileSaver;
import com.google.s5.util.Pager;


@Service
public class MemberService {
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private FileSaver fileSaver;
	@Autowired
	private MemberFileDAO memberFileDAO;
	
	// memberIdCheck	
	public MemberVO memberIdCheck(MemberVO memberVO)throws Exception{
		return memberDAO.memberIdCheck(memberVO);
	}
	
	
	//LIST
	public List<MemberVO> memberList(Pager mp) throws Exception {
		
		
		mp.makeRow();
		
		//--------------------------------------------
		long totalCount= memberDAO.memberCount(mp);
		mp.makePage(totalCount);
		//리턴을 안해도 totalCount가 controller에 가는데 왜 그럴까?
		//참조변수는 주소를 남기는데 이미 주소 안에  totalCount가 있기 때문
		return memberDAO.memberList(mp);
	}

//*	//MEMBER JOIN
	public int memberJoin(MemberVO memberVO,MultipartFile avatar,HttpSession session) throws Exception{
		//HDD에 저장 resources/memberUpload/
		//1. 파일을 HDD 에 저장
		
		String path=session.getServletContext().getRealPath("/resources/memberUpload"); //파일 경로
		System.out.println(path);	
		String fileName=fileSaver.saveByUtils(avatar, path);
		
		MemberFileVO memberFileVO = new MemberFileVO();
		memberFileVO.setId(memberVO.getId());
		memberFileVO.setFileName(fileName);
		memberFileVO.setOriName(avatar.getOriginalFilename());
		
		//2.파일명을 DB에 저장
		int result=memberDAO.memberJoin(memberVO);
		result=memberFileDAO.fileInsert(memberFileVO);
		return result;
		//return memberDAO.memberJoin(memberVO);
	}

	

	//MEMBER LOGIN
	public MemberVO memberLogin(MemberVO memberVO) throws Exception{
		
		return memberDAO.memberLogin(memberVO);
	}
	
		
	//Member Update
		public int memberUpdate(MemberVO memberVO)throws Exception{
			return memberDAO.memberUpdate(memberVO);
		}
		
		public int memberDelete(MemberVO memberVO) throws Exception{
			return memberDAO.memberDelete(memberVO);
		}
		
		
//		로그인과정에 담겨있어서 더이상 필요 없음
//	//fileSelect
//		public MemberFileVO fileSelect(String id) throws Exception{
//			return memberFileDAO.fileSelect(id);
//		}
		
		
	//fileDelete	
		public int fileDelete(String id,HttpSession session) throws Exception{
			MemberFileVO memberFileVO =memberFileDAO.fileSelect(id);
			int result= memberFileDAO.fileDelete(id);
			if(result>0) {
				String path= session.getServletContext().getRealPath("/resources/memberUpload");
				result=fileSaver.deleteFile(memberFileVO.getFileName(), path);
			}
			return result;
		}
		
	//memberDeletes
		public int memberDeletes(List<String> list) throws Exception{
			return memberDAO.memberDeletes(list);
		}
		
}
