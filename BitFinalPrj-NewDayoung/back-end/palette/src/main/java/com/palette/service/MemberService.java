package com.palette.service;

import java.io.IOException;
import java.util.List;

import com.palette.dao.MemberDao;
import com.palette.model.BoardReferenceDto;
import com.palette.model.MemberDto;
import com.palette.model.MemberInterParam;
import com.palette.model.TodoListDto;
import com.palette.s3.ReferenceVo;
import com.palette.s3.S3Uploader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * MemberService
 */
@Service
public class MemberService {

        @Autowired
        MemberDao memberDao;
        
        @Autowired
        S3Uploader s3Uploader;

		public List<MemberDto> selectAll() {
            //System.out.println("service");
			return memberDao.selectAll();
		}

		public MemberDto getOneMember(MemberDto dto) {
			
			return memberDao.getOneMember(dto);
		}
		
		public int checkid(String memberId) {
			int is = memberDao.checkId(memberId);
			return is;
		}
		
		public boolean createMember(MemberDto dto) {
			int check = memberDao.createMember(dto);
			return check > 0? true: false;
		}
		
		public void createCompanyMember(ReferenceVo vo) throws IOException {
			 //( #{memberName}, #{pwd}, #{memberId}, #{email}, #{auth}, #{address},#{companyInfo}, #{companyLogo} ) 
			 MemberDto member = new MemberDto();
			 member.setMemberName(vo.getMemberName());
			 member.setPwd(vo.getPwd());
			 member.setMemberId(vo.getMemberId());
			 member.setEmail(vo.getEmail());
			 member.setAuth(vo.getAuth());
			 member.setAddress(vo.getAddress());
			 member.setCompanyInfo(vo.getCompanyInfo());
			 
			 memberDao.createCompanyMember(member);
			 int memberSeq = memberDao.getSeq();
			 
			 for (MultipartFile file : vo.getFiles()) {
					BoardReferenceDto dto = new BoardReferenceDto();
					dto.setFileName(file.getOriginalFilename());
					dto.setUrl(s3Uploader.upload(file, vo.getMemberId())); //접근할 수 있는  url
					dto.setMemberSeq(memberSeq);
					memberDao.companyLogo(dto);
					
					MemberDto seq = new MemberDto();
					seq.setMemberSeq(memberSeq);
					seq.setCompanyLogo(dto.getUrl());
					memberDao.updateLogo(seq);
		        }
		//	 BoardReferenceDto getUrl(BoardReferenceDto dto); //memberSeq넣고 dto  urlset
				
			//	void updateLogo(MemberDto dto);
		}
		
		
		//회원가입-내 관심분야 insert
		public void InterstingInsert(String interSmallSeqs, int memberSeq) {
			MemberInterParam param = new MemberInterParam(); 
	
			String arr[] = interSmallSeqs.split(",");
			int smallArr[] = new int[arr.length];
			int bigArr[] = new int[arr.length];
			for (int i = 0; i < arr.length; i++) {
				smallArr[i] = Integer.parseInt(arr[i]); //smallseq
				bigArr[i] = memberDao.getBigSeq(smallArr[i]);//bigarr담을변수
				param.setMemberSeq(memberSeq);
				param.setSmallSeq(smallArr[i]);
				param.setBigSeq(bigArr[i]);
				memberDao.addInter(param);
			}
		}
		
		//회원가입 내 관심지역 insert
		public void addInterArea(MemberDto dto) {
			memberDao.addInterArea(dto);
		}
		
		//myPage Info용
		public MemberDto getMyInfo(MemberDto dto) {
			MemberDto member = memberDao.getMyInfo(dto);
			return member;
		}
		
		//마이페이지 MyInterest용
		public MemberDto getDetailMember(int memberSeq) {
			return memberDao.getDetailMember(memberSeq);
		}
		
		
		//interest interBig seq얻기 위함
		public int getSeq() {
			return memberDao.getSeq();
		}
		
		//mypage 관심분야 update 하기전에 모두 지우기
		public void InterestingUpdate(String interSmallSeqs, int memberSeq) {
			System.out.println("서비스에서 멤버seq"+memberSeq);
			MemberInterParam param = new MemberInterParam(); 
			memberDao.delInter(memberSeq);
			String arr[] = interSmallSeqs.split(",");
			int smallArr[] = new int[arr.length];
			int bigArr[] = new int[arr.length];
			for (int i = 0; i < arr.length; i++) {
				smallArr[i] = Integer.parseInt(arr[i]); //smallseq
				bigArr[i] = memberDao.getBigSeq(smallArr[i]);//bigarr담을변수
				param.setMemberSeq(memberSeq);
				param.setSmallSeq(smallArr[i]);
				param.setBigSeq(bigArr[i]);
				memberDao.addInter(param);
			}
		}
		
		public boolean updateInfo(MemberDto dto) {
			int update = memberDao.updateInfo(dto);
			return update >0 ? true: false;
		}
		
		public boolean updateAddr(MemberDto dto) {
			int update = memberDao.updateAddr(dto);
			return update >0 ? true: false;
		}
		
		public int checkPass(MemberDto dto) {
			int pass = memberDao.checkPass(dto);
			return pass;
		}
		
		public boolean updatePass(MemberDto dto) {
			int update = memberDao.updatePass(dto);
			return update >0 ? true: false;
		}
		
		public boolean delUser(MemberDto dto) {
			int del = memberDao.delUser(dto);
			return del > 0 ? true:false; 
		}
		//---------------TODOLIST------------------------
		//todoList add 
		public boolean addTodoList(TodoListDto dto) {
			int add = memberDao.addTodoList(dto);
			return add > 0?  true: false;
		}
		
		public List<TodoListDto> selectTodoList(TodoListDto dto) {
			List<TodoListDto> todo = memberDao.selectTodoList(dto);
			return todo;
		}
		
		public TodoListDto selectOneList(TodoListDto dto) {
			TodoListDto todo = memberDao.selectOneList(dto);
			return todo;
		}
		
		public boolean todoDel(TodoListDto dto) {
			int del = memberDao.todoDel(dto);
			return del > 0? true: false;
		}
		
		public boolean todoDone(TodoListDto dto) {
			int done = memberDao.todoDone(dto);
			return done > 0? true: false;
		}
		
		public boolean todoEdit(TodoListDto dto) {
			int edit = memberDao.todoEdit(dto);
			return edit > 0? true: false;
		}
		
		public List<TodoListDto> selectDoneTodoList(TodoListDto dto) {
			List<TodoListDto> list = memberDao.selectDoneTodoList(dto);
			return list;
		}
		
		public List<TodoListDto> TodoListDoing(TodoListDto dto) {
			List<TodoListDto> list = memberDao.TodoListDoing(dto);
			return list;
		}
		
		public List<TodoListDto> selectDate(TodoListDto dto) {
			List<TodoListDto> list = memberDao.selectDate(dto);
			return list;
		}
		
		public List<TodoListDto> DateAndCount(TodoListDto dto){
			List<TodoListDto> list = memberDao.DateAndCount(dto);
			return list;
		}
		
		
		
    
}