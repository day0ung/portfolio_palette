package com.palette.controller;

import java.util.List;
import java.util.Map;


import com.palette.model.MemberDto;
import com.palette.service.AdminStatisticsService;
import com.palette.util.ListANDMapConvertToJSON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONArray;

@CrossOrigin(origins = "*")
@RestController
public class Admin_StatisticsController {
    @Autowired
    AdminStatisticsService adminstatisticsservice;

    // @PostMapping(value = "/changeAuthCompanyMember")
    // public boolean changeAuthCompanyMemberStatus(MemberDto dto) {
    // 	System.out.println("changeAuthCompanyMemberStatus Method execute Now..");
    	
    // 	boolean isS = adminCompanyService.changeAUTHforCompanyMember(dto);
    // 	System.out.println("changeAuthCompanyMemberStatus RESULT : \n" + isS );
    // 	return isS;
    // }
    

    // getMemberGenderInfo

    //남여 성비를 산출해내는 로직.
    @PostMapping(value = "/getMemberGenderInfo")
    public List<MemberDto> getMemberGenderInfo() {
    	System.out.println("getMemberGenderInfo Method execute Now..");
    	
    	List<MemberDto> genderInfoList = adminstatisticsservice.getMemberGenderInfo();
        System.out.println("getMemberGenderInfo RESULT : \n" + genderInfoList );
        
        
    	return genderInfoList;
    }

    //대분류 카운트 산출
    @PostMapping(value = "/getGroupInfo_InterBigCount")
    public JSONArray getGroupInfo_InterBigCount() {
        //adminStaticsdao.receiveinterBigCountAll();
        ListANDMapConvertToJSON convertjson = new ListANDMapConvertToJSON();
        List<Map<String, Object>> listMap = adminstatisticsservice.receiveinterBigCountAll();
        JSONArray res_jsonobj = convertjson.getJsonArrayFromList(listMap);
        
        System.out.print("change list to json array = ");
        System.out.println(res_jsonobj);
        System.out.println("data fin");
        
    	return res_jsonobj;
    }

 

	//소분류 카운트 산출 
    @PostMapping(value = "/getGroupInfo_InterSmallCount")
    public JSONArray getGroupInfo_InterSmallCount() {
        System.out.println("getGroupInfo_InterSmallCount 실행됨.");


        // getJsonArrayFromListInList

        ListANDMapConvertToJSON convertjson = new ListANDMapConvertToJSON();

        JSONArray allResult = adminstatisticsservice.receiveinterSmall_All();

        System.out.println(allResult);

        // JSONArray res_ListJsonArray = convertjson.getJsonArrayFromListInList(allResult);

        
        System.out.println("\n\n");
        // System.out.println(res_ListJsonArray);
        System.out.println("\n\n");



        
    	return allResult;
    }






}//end of  Admin_StatisticsController class


    
    


