<template>
  <div>
    <div class="slib">
      <div class="slib_info">
        <div class="tit">
          <img src='../../assets/css/images/resume.png'>
        </div>
        <div class="titup">
          <table class="table1" style="margin-left: 55px">
            <colgroup>
            <col style="width: 400px">
            <col style="width: 30%">
            </colgroup>
            <thead>
              <tr>
                <th>공고제목</th>
                <th>공고일자</th>
                <th></th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="recruitingInfo in RecruitingList" :key="recruitingInfo.memberSeq">
                <td a @click="showEmpDetail(recruitingInfo.boardSeq)">{{recruitingInfo.title}}</td>
                <td>{{recruitingInfo.writeDate}}</td>
              </tr>                   
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import logincss from '@/assets/css/member/myinfo.css'

export default {
  data(){
    return{
      RecruitingList : [],

      login1 : "",
      title : "",
      title1 : "",
      category : "",
      writeDate : ""
    }
  },
  mounted(){
    var params = new URLSearchParams();	// post 방식으로 받아야함. 
    params.append('memberSeq', this.login1.memberSeq);
    axios.post("http://localhost:9000/getOneRecruitByMemberSeq", params).then(res => { 
      this.RecruitingList = res.data
      this.$store.state.s_employment.RecruitingList = res.data
      this.title1 = this.$store.state.s_employment.RecruitingList.title
      this.writeDate = this.$store.state.s_employment.RecruitingList.writeDate
    })
    
  },
  created(){
		let sMemberSeq = sessionStorage.getItem("loginUser")
    this.login1 = JSON.parse(sMemberSeq)
  },
  methods:{
    edit(){
      this.$router.push({
        path: '/employment/' + this.login1.memberSeq
      })
    },
    showEmpDetail(seq){
      this.$router.push({
        path: "/RecruitingDetail/"+seq
        })
    },
  }

}
</script>

<style>

</style>
