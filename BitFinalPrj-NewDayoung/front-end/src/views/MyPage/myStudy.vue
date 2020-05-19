<template>
  <div>
    <button class="btn btn-primary" @click="createGroup" style="float: right;">그룹개설 신청</button>
    <!-- 개설신청한 그룹 -->
    <div class="slib" v-show="this.$store.state.s_member.MyPageCreatGroupListLength">
      <div class="slib_info">
          <div class="tit">
            <img src='../../assets/css/images/group_submit.png'>
          </div>
          <div class="titup">
              <table class="table1" style="margin-left: 55px" v-loading="this.$store.state.s_member.MyPageCreatGroupListLoading">
                <colgroup>
              <col style="width: 270px">
              <col style="width: 30px">
              <col style="width: 50px">
              </colgroup>
                <thead>
                  <tr>
                    <th style="text-align: center;">개설신청한 그룹</th>
                    <th></th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="groupOne in this.$store.state.s_member.MyPageCreatGroupList" :key="groupOne.groupInfoSeq">
                    <td colspan="3">{{groupOne.groupName}}</td>
                    <td><i class="el-icon-paperclip" style="color: #ff5151"></i> </td>
                    <td style="width: 50px; text-align: left;">
                      <el-button type="text" style="color: #ff5151; font-size: 16px" @click="groupCreateCancle(groupOne.groupInfoSeq, groupOne.memberSeq)">개설신청 취소</el-button>
                    </td>
                  </tr>                   
                </tbody>
              </table>
          </div>
      </div>
    </div>
    <!-- 가입된 스터디 그룹 -->
    <div class="slib">
      <div class="slib_info">
          <div class="tit">
            <img src='../../assets/css/images/networking.png'>
          </div>
          <div class="titup">
              <table class="table1" style="margin-left: 55px" v-loading="this.$store.state.s_member.MyPageStudyListLoading">
                <colgroup>
              <col style="width: 270px">
              <col style="width: 30px">
              <col style="width: 50px">
              </colgroup>
                <thead>
                  <tr>
                    <th style="text-align: center;">가입한 스터디 그룹</th>
                    <th></th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="groupOne in this.$store.state.s_member.MyPageGroupList" :key="groupOne.groupInfoSeq">
                    <td colspan="3">{{groupOne.groupName}}</td>
                    <td><i class="el-icon-paperclip" style="color: #ff5151"></i> </td>
                    <td style="width: 50px; text-align: left;">
                      <el-button v-if="groupOne.memberSeq === loginSeq" type="text" style="color: #ff5151; font-size: 16px">그룹장</el-button>
                      <el-button v-else="" type="text" style="color: #ff5151; font-size: 16px">멤버</el-button>
                    </td>
                    <td style="width: 80px; text-align: left;">
                      <el-button v-if="groupOne.memberSeq === loginSeq" type="text" style="color: #ff5151; font-size: 16px" @click="gotoDetail(groupOne.groupInfoSeq)">| 관리하기</el-button>
                      <el-button v-else="" type="text" style="color: #ff5151; font-size: 16px" @click="gotoDetail(groupOne.groupInfoSeq)">| 활동하기</el-button>
                    </td>
                    <td style="width: 100px; text-align: left;">
                      <el-button v-if="groupOne.memberSeq === loginSeq" type="text" style="color: #ff5151; font-size: 16px"></el-button>
                      <el-button v-else="" type="text" style="color: #ff5151; font-size: 16px" @click="groupMemberDelete(loginSeq, groupOne.groupName, groupOne.groupInfoSeq)">| 탈퇴하기</el-button>
                    </td>
                  </tr>                   
                </tbody>
              </table>
          </div>
      </div>
    </div>
    <!-- 가입신청한 스터디 그룹 -->
    <div class="slib">
      <div class="slib_info">
          <div class="tit">
            <img src='../../assets/css/images/permission_user.png'>
          </div>
          <div class="titup">
              <table class="table1" style="margin-left: 55px" v-loading="this.$store.state.s_member.MyPagePermissionListLoading">
              <colgroup>
                <col style="width: 270px">
                <col style="width: 30px">
                <col style="width: 50px">
              </colgroup>
                <thead>
                  <tr>
                    <th style="text-align: center;">가입신청 한 스터디 그룹</th>
                    <th></th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="groupOne in this.$store.state.s_member.MyPagePermissionList" :key="groupOne.groupInfoSeq">
                    <td colspan="3">{{groupOne.groupName}}</td>
                    <td><i class="el-icon-paperclip" style="color: #ff5151"></i> </td>
                    <td style="width: 50px; text-align: left;">
                      <el-button type="text" style="color: #ff5151; font-size: 16px" @click="waitingDelete(groupOne.groupInfoSeq)">가입신청 취소</el-button>
                    </td>
                  </tr>                   
                </tbody>
              </table>
          </div>
      </div>
    </div>

  </div>
</template>

<script>
import { loading } from 'element-ui';

export default {
  data(){
    return{
      loginSeq: 0,
    }
  },
    methods:{
      groupCreateCancle(groupInfoSeq, memberSeq){
        this.$store.state.s_member.MyPageCreatGroupListLoading = true
        let params = new URLSearchParams();
        params.append("groupInfoSeq", groupInfoSeq)
        params.append("memberSeq", memberSeq)
        axios.post("http://localhost:9000/groupCreateCancle", params).then(res => {
          this.getMyPageCreatGroupList()
          this.$store.state.s_member.MyPageCreatGroupListLoading = false
        })
      },
      waitingDelete(groupInfoSeq){
        this.$store.state.s_member.MyPagePermissionListLoading = true
        //group member table 로 가야함
        let params = new URLSearchParams();
        params.append("groupInfoSeq", groupInfoSeq)
        params.append("memberSeq", this.$store.state.loginUser.memberSeq)
        axios.post("http://localhost:9000/groupWaitingDelete", params).then(res =>{
          this.getMyPremissionGroup()
          alert("가입신청이 취소되었습니다.")
          this.$store.state.s_member.MyPagePermissionListLoading = false
        })
      },
      groupMemberDelete(memberSeq, groupName, groupInfoSeq){
        this.$confirm(groupName + '그룹에서 정말 탈퇴하시겠습니까?', 'Warning', {
          confirmButtonText: '예',
          cancelButtonText: '아니오',
          type: 'warning'
        }).then(() => {
          this.$message({ type: 'warning', message: groupName +'그룹에서 탈퇴하였습니다.' });
          console.log("탈퇴" + memberSeq)
          this.$store.state.s_member.MyPageStudyListLoading = true
          let params = new URLSearchParams();
          params.append("memberSeq", memberSeq)
          params.append("groupInfoSeq", groupInfoSeq)
          axios.post("http://localhost:9000/groupMemberDelete", params).then(res => {
            console.log("groupMemberDelete()")
            this.getList()
            this.$store.state.s_member.MyPageStudyListLoading = false
          })
        })
      },
      createGroup(){
        this.$router.push({name :"Create"})
      },
      gotoDetail(groupSeq){
        this.$router.push('/group/menu/'+ groupSeq)
      },
      getList(){
        this.$store.state.s_member.MyPageStudyListLoading = true
        this.loginSeq = this.$store.state.loginUser.memberSeq
        let params = new URLSearchParams();
        params.append("memberSeq", this.$store.state.loginUser.memberSeq)
        axios.post("http://localhost:9000/getMyGroup", params).then(res => {
          this.$store.state.s_member.MyPageGroupList = res.data
          this.$store.state.s_member.MyPageStudyListLoading = false
        })
      },
      getMyPremissionGroup(){
        this.$store.state.s_member.MyPagePermissionListLoading = true
        let params = new URLSearchParams();
        params.append("memberSeq", this.$store.state.loginUser.memberSeq)
        axios.post("http://localhost:9000/getMyPremissionGroup", params).then(res => {
          this.$store.state.s_member.MyPagePermissionList = res.data
          this.$store.state.s_member.MyPagePermissionListLoading = false
        })
      },
      getMyPageCreatGroupList(){
        this.$store.state.s_member.MyPageCreatGroupListLoading = true
        let params = new URLSearchParams();
        params.append("memberSeq", this.$store.state.loginUser.memberSeq)
        axios.post("http://localhost:9000/getMyPageCreatGroupList", params).then(res => {
          if(res.data.length === 0){
            this.$store.state.s_member.MyPageCreatGroupListLength = false
          }else{
            this.$store.state.s_member.MyPageCreatGroupListLength = true
          }
          this.$store.state.s_member.MyPageCreatGroupList = res.data
          this.$store.state.s_member.MyPageCreatGroupListLoading = false
        })
      }
    },
    mounted(){
      this.getList()
      this.getMyPremissionGroup()
      this.getMyPageCreatGroupList()
    }
}
</script>

<style>

</style>