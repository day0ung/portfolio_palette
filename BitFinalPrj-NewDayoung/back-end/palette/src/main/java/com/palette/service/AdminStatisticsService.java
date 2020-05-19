package com.palette.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.palette.dao.AdminStatisticsDao;
import com.palette.model.MemberDto;
import com.palette.util.ListANDMapConvertToJSON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.minidev.json.JSONArray;



@Service
@Transactional
public class AdminStatisticsService {
	@Autowired
	AdminStatisticsDao adminStaticsdao;



	public List<MemberDto> getMemberGenderInfo(){
		return adminStaticsdao.getMemberGenderInfo();
	}
	

	public List<Map<String, Object>> receiveinterBigCountAll(){

		return adminStaticsdao.receiveinterBigCountAll();
	}




	public JSONArray receiveinterSmall_All(){
		/*
		List<List<Map<String, Object>>>
						대분류묶음
							대분류명
								<MAP<소분류명, 소분류명의 총갯수>>

*/
		//utilclass내 method 사용.
		ListANDMapConvertToJSON convetJSON = new ListANDMapConvertToJSON();
		
		JSONArray totalInterSmall = new JSONArray();
		
		Object[] inputData = {
			//List<map<String, Object>> 형태. 
			adminStaticsdao.receiveinterSmall_1(), 
			adminStaticsdao.receiveinterSmall_2(), 
			adminStaticsdao.receiveinterSmall_3(), 
			adminStaticsdao.receiveinterSmall_4()
		};
		JSONArray jsonArray1 = new JSONArray();
		

		//1. 변환해서
		//2. change to listmap to JSON

		System.out.println("변환전 데이터 체크 시작.... ");
		System.out.println(inputData[0]);
		System.out.println("ListMap형태를 JSON으로 변환한다.");
		System.out.println(convetJSON.getJsonStringFromList((List<Map<String, Object>>)inputData[0]));
		System.out.println(convetJSON.getJsonStringFromList((List<Map<String, Object>>)inputData[1]));
		System.out.println("변환전 데이터 체크 끝.... ");

		for(int i=0; i<inputData.length; i++){
			System.out.println("\n\nreceiveinterSmall_All 시작");
			System.out.println((List<Map<String, Object>>)inputData[i]);
			System.out.println("\n\n");
			// jsonArray.add(convetJSON.getJsonStringFromList((List<Map<String, Object>>)inputData[i]));
			//JSON으로 변환하지말고 배열에 넣어서 넣은것을 변환하자.
			jsonArray1.add((List<Map<String, Object>>)inputData[i]);

			System.out.println(jsonArray1);

			System.out.println("receiveinterSmall_All 끝\n\n");
		}
		System.out.println("jsonarray 출력");
		System.out.println(jsonArray1);
		System.out.println("jsonarray 출력끝.");

		return jsonArray1;
	}




	
	
	

}// end of AdminStatisticsService class
