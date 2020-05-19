<template>
  <div>
    <!-- 상엽 찜목록 작업 -->
    <div class="slib">
      <div class="slib_info">
          <div class="tit">
            <img src='../../assets/css/images/like.png'>
          </div>
          <div class="titup">
              <table class="table1" style="margin-left: 55px" v-loading="this.$store.state.s_member.MyPageInterLikeListLoading">
                <colgroup>
              <col style="width: 270px">
              <col style="width: 30px">
              <col style="width: 50px">
              </colgroup>
                <thead>
                  <tr>
                    <th style="text-align: center;">찜목록</th>
                    <th></th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="groupOne in this.$store.state.s_member.MyPageInterLikeList" :key="groupOne.groupInfoSeq">
                    <td colspan="3">{{groupOne.groupName}}</td>
                    <td><i class="el-icon-paperclip" style="color: #ff5151"></i> </td>
                    <td style="width: 100px; text-align: left;">
                      <el-button v-if="groupOne.del === 0" type="text" style="color: #ff5151; font-size: 16px">가입된 멤버</el-button>
                      <el-button v-else-if="groupOne.del === 2" type="text" style="color: #ff5151; font-size: 16px">가입 대기중</el-button>
                      <el-button v-else type="text" @click="joinGroup(groupOne.groupInfoSeq)" style="color: #ff5151; font-size: 16px">가입 신청하기</el-button>
                    </td>
                    <td style="width: 100px; text-align: left;">
                      <el-button v-if="groupOne.del === 0" type="text" style="color: #ff5151; font-size: 16px" @click="likeDelete(groupOne.groupInfoSeq)">| 삭제</el-button>
                      <el-button v-else-if="groupOne.del === 2" type="text" style="color: #ff5151; font-size: 16px" @click="waitingDelete(groupOne.groupInfoSeq)">| 취소</el-button>
                      <el-button v-else type="text" style="color: #ff5151; font-size: 16px" @click="likeDelete(groupOne.groupInfoSeq)">| 삭제</el-button>
                    </td>
                  </tr>                   
                </tbody>
              </table>
           
          </div>
      </div>
    </div>
    <div class="slib">
            <div class="slib_info">
                <div class="tit">
                  <img src='../../assets/css/images/area.png'>
                </div>
                <div class="titup">
                   <table class="table1" style="margin-left: 55px">
                     <colgroup>
                    <col style="width: 270px">
                    <col style="width: 30px">
                    <col style="width: 50px">
                    </colgroup>
                      <thead>
                        <tr>
                          <th style="text-align: center;">관심지역</th>
                          <th></th>
                          <th></th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr>
                          <td colspan="3">{{myinter.interArea}}</td>
                          <td><i class="el-icon-paperclip" style="color: #ff5151"></i> </td>
                          <td>
                            <el-button type="text" @click="editAddr" style="color: #ff5151; font-size: 16px">수정하기</el-button>
                          </td>
                        </tr>                   
                      </tbody>
                    </table>
                    <table class="table1" v-if="addr">
                      <tbody>
                        <tr>
                             <el-button type="info" plain style="width: 100px; padding: 11px;" @click="execDaumPostcode">우편번호 찾기</el-button>
                        </tr>
                        <tr>
                          <td>
                            <el-input  placeholder="주소" readonly="readonly" v-model="address"></el-input>
                          </td>
                          <td>
                            <el-input  placeholder="상세주소" style="width: 300px" v-model="extraAddress"></el-input>
                          </td>
                          <td style="width: 14px"></td>
                          <td><i class="el-icon-s-tools" style="color: #d77f4a"></i> </td>
                            <td>
                            <el-button type="text" @click="editArea" style="color: #d77f4a; font-size: 16px">수정하기</el-button>
                          </td>
                        </tr>                   
                      </tbody>
                    </table>
                </div>
            </div>
        </div>
   <div class="slib">
            <div class="slib_info">
                <div class="tit">
                  <img src='../../assets/css/images/puzzle.png'>
                </div>
                <div class="titup">
                  <div class="inter">
                      <p style="color: #909090">관심분야</p>
                      <h5 v-for="big in myBig" :key="big.seq">
                        {{big.bigName}}
                      </h5>
                    <br>
                  <p style="color: #909090">상세분야</p>
                    <h5 v-for="small in mySmall" :key="small.seq">
                      {{small.smallName}}
                    </h5>

                  </div>
                  <div class="interEdit">
                  <i class="el-icon-paperclip" style="color: #ff5151"></i> 
                    <el-button @click="showshow" type="text"  style="color: #ff5151; font-size: 16px">수정하기</el-button>
                  </div>
                <Inter 
                v-if="showInter"
                :memSeq="memSeq"
                @close="showInter = false"
                @update="updateInter">
                </Inter>
                </div>
            </div>
        </div>
  </div>
</template>

<script>
import logincss from '@/assets/css/member/myinfo.css'
import Inter from "@/views/MyPage/interestUpdate.vue"
import { loading } from 'element-ui';

export default {
  props:['myinter','myBig','mySmall','memSeq', 'mylike'],
  components:{
      Inter,
    },
  data(){
      return{
          addr: false,
          showInter: false,
          address: '',
          extraAddress: '',
          postcode: '',
      }
    },
     methods: {
       likeListUpdate(){
        this.$store.state.s_member.MyPageInterLikeListLoading = true
        let params = new URLSearchParams();
        params.append('memberSeq', this.$store.state.loginUser.memberSeq)
        axios.post('http://localhost:9000/getMylikeList', params).then(res => {
          this.$store.state.s_member.MyPageInterLikeList = res.data
          console.log(res.data)
          this.$store.state.s_member.MyPageInterLikeListLoading = false
        })
       },
       likeDelete(groupInfoSeq){
        this.$store.state.s_member.MyPageInterLikeListLoading = true
        // like table로 가야함
        let params = new URLSearchParams();
        params.append("groupInfoSeq", groupInfoSeq)
        params.append("memberSeq", this.$store.state.loginUser.memberSeq)
        axios.post("http://localhost:9000/likeGroupDelete", params).then(res =>{
          console.log(res.data)
          this.likeListUpdate()
          this.$message({ type: 'info', message:'찜목록에서 제외되었습니다.'})
          //alert("찜목록에서 제외되었습니다.")
          this.$store.state.s_member.MyPageInterLikeListLoading = false
        })
       },
       waitingDelete(groupInfoSeq){
         this.$store.state.s_member.MyPageInterLikeListLoading = true
        //group member table 로 가야함
        let params = new URLSearchParams();
        params.append("groupInfoSeq", groupInfoSeq)
        params.append("memberSeq", this.$store.state.loginUser.memberSeq)
        axios.post("http://localhost:9000/groupWaitingDelete", params).then(res =>{
          console.log(res.data)
          this.likeListUpdate()
          this.$message({ type: 'info', message:'가입신청이 취소되었습니다.'})
          //alert("가입신청이 취소되었습니다.")
          this.$store.state.s_member.MyPageInterLikeListLoading = false
        })
       },
       joinGroup(groupInfoSeq){
        this.$store.state.s_member.MyPageInterLikeListLoading = true
        let params = new URLSearchParams();
        params.append("groupInfoSeq", groupInfoSeq)
        params.append("memberSeq", this.$store.state.loginUser.memberSeq)
        axios.post("http://localhost:9000/joinGroupMemberRegistrationRequest", params).then(res =>{
          if (res.data === "success"){
            this.likeListUpdate()
            this.$message({ type: 'success', message:'가입신청 완료. 그룹장이 승인하면 가입이완료됩니다.'})
            //alert("가입신청이 완료되었습니다.\n그룹장이 승인하면 가입이완료됩니다.")
            this.$store.state.s_member.MyPageInterLikeListLoading = false
          }else if(res.data === "fail"){
            this.likeListUpdate()
            this.$message({ type: 'info', message:'이미 가입된 그룹입니다'})
            //alert("이미 가입된 그룹입니다")
            this.$store.state.s_member.MyPageInterLikeListLoading = false
          }else if(res.data === "waiting"){
            this.likeListUpdate()
            this.$message({ type: 'info', message:'가입대기중인 그룹입니다.'})
            //alert("가입대기중인 그룹입니다.")
            this.$store.state.s_member.MyPageInterLikeListLoading = false
          }
        })
       },
       editAddr(){
         this.addr = true;
       },
      editArea(){
        if(this.address =='' || this.extraAddress ==''){
          this.$message({ type: 'info', message:'주소를 입력해주세요'})
          //alert('주소를 입력해주세요')
         }else{
          var params = new URLSearchParams();
          var realAddr = this.address + this.extraAddress
          params.append('memberSeq', this.memSeq)
          params.append('interArea', realAddr)
         axios.post('http://localhost:9000/updateInterArea', params)
            .then(res => {
              this.$message({ type: 'success', message:'정보수정이 완료되었습니다'})
              //alert('정보수정이 완료되었습니다')
              this.addr = false
              this.$emit('updateInterArea', this.memSeq)
            })
         }
      },
       execDaumPostcode() {
            new daum.Postcode({
            onComplete: (data) => {
            if (data.userSelectedType === 'R') {
              this.address = data.roadAddress;
            } else {
              this.address = data.jibunAddress;
            }
            if (data.userSelectedType === 'R') {
              if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
              this.extraAddress += data.bname;
              }
              if (data.buildingName !== '' && data.apartment === 'Y') {
              this.extraAddress +=
                this.extraAddress !== ''
                ? `, ${data.buildingName}`
                : data.buildingName;
              }
              if (this.extraAddress !== '') {
              this.extraAddress = ` (${this.extraAddress})`;
              }
            } else {
              this.extraAddress = '';
            }
            },
          }).open(); 
        },
       showshow(){
         this.showInter = true
       },
       updateInter(memSeq){
         this.showInter = false
           var params = new URLSearchParams();
		      params.append('memberSeq', memSeq)
          axios.post('http://localhost:9000/myPageInter', params)
          .then(res => {
            var smyinfo = res.data
            this.myinfo = smyinfo
            
            var bg = smyinfo.interBigDto
            var sm = smyinfo.interSmallDto
        
            var big;
            big = JSON.stringify(bg)
            big = JSON.parse(big)
            this.myBig = big

            var small;
            small = JSON.stringify(sm)
            small = JSON.parse(small)
            this.mySmall = small
          })
       }
  }
}
</script>

<style scoped>
.inter{
  padding: 23px 44px 0px 54px;
    margin-left: 88px;
}
.interEdit{
   padding:12px 44px 0px 51px;
   margin-top: 32px;
}
</style>