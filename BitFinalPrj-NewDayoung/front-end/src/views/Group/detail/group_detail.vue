<template>
  <div class="group_detail">
    <br>
    <br>
    <div class="groupDetailcCntainer" v-loading="this.$store.state.s_group.groupDetailLoading">
      <div class="groupImage">
        <img :src="groupOne.image" />
      </div>
      <div class="groupName">
        <h1>{{ groupOne.groupName }}</h1>
      </div>
      <div class="hr"></div>
      <div class="groupInfoTitle">
        <h5>스터디 소개</h5>
      </div>
      <div class="groupInfoContent">
        {{ groupOne.info }}
      </div>
      <div class="hr"></div>
      <div class="groupInfoTitle">
        <h5><span>상세 정보</span></h5>
      </div>
      <div class="groupInfoContent">
        <div><span>분류</span> {{ groupOne.interBigDto.bigName }} > {{groupOne.interSmallDto.smallName}}</div>
        <div><span>지역</span> {{ groupOne.groupLocation }}</div>
        <div><span>인원</span> {{ groupOne.currMember}} / {{ groupOne.maxMember}}</div>
        <div><span>일정</span></div>
        <div class="schedules" style="line-height: 12px;">
            <div v-if="groupOne.groupSchedule.monday == 0" class="schedule">월</div>
            <div v-else class="schedule_check">월</div>
            <div v-if="groupOne.groupSchedule.tuesday == 0" class="schedule">화</div>
            <div v-else class="schedule_check">화</div>
            <div v-if="groupOne.groupSchedule.wednesday == 0" class="schedule">수</div>
            <div v-else class="schedule_check">수</div>
            <div v-if="groupOne.groupSchedule.thursday == 0" class="schedule">목</div>
            <div v-else class="schedule_check">목</div>
            <div v-if="groupOne.groupSchedule.friday == 0" class="schedule">금</div>
            <div v-else class="schedule_check">금</div>
            <div v-if="groupOne.groupSchedule.saturday == 0" class="schedule">토</div>
            <div v-else class="schedule_check">토</div>
            <div v-if="groupOne.groupSchedule.sunday == 0" class="schedule">일</div>
            <div v-else class="schedule_check">일</div>
        </div>
        
        <div><span>시작일</span> {{ groupOne.startDate }}</div>
        <div><span>완료일</span> {{ groupOne.endDate }}</div>
      </div>
      <div class="hr"></div>
      <div class="groupName">
        <el-button type="primary" @click="joinGroup" round>그룹 스터디 참여신청</el-button>
        <el-button type="primary" @click="likeAdd" round>♥</el-button>
      </div>
    </div>
    <br>
    <br>
  </div>
  

</template>

<script>
import { loading } from 'element-ui';
export default {
  data(){
    return{
      groupInfoSeq: "",
      groupOne: [],
      loading: true,
    }
  },
  methods:{
    likeAdd(){
      this.$store.state.s_group.groupDetailLoading = true
      let params = new URLSearchParams();
      params.append("groupInfoSeq", this.$route.params.contentId)
      params.append("memberSeq", this.$store.state.loginUser.memberSeq)
      axios.post("http://localhost:9000/likeGroupAdd", params).then(res =>{
        this.$message({ type: 'success', message: this.groupOne.groupName + " 그룹이 찜목록에 추가되었습니다. 마이페이지에서 확인해주세요."})
        //alert(this.groupOne.groupName + " 그룹이 찜목록에 추가되었습니다.\n마이페이지에서 확인해주세요.")
        this.$store.state.s_group.groupDetailLoading = false
      })
    },
    joinGroup(){
      this.$store.state.s_group.groupDetailLoading = true
      let params = new URLSearchParams();
      params.append("groupInfoSeq", this.$route.params.contentId)
      params.append("memberSeq", this.$store.state.loginUser.memberSeq)
      axios.post("http://localhost:9000/joinGroupMemberRegistrationRequest", params).then(res =>{
        this.$message({ type: 'success', message:"참여신청이 완료. 마이페이지에서 확인해주세요."})
        //alert("참여신청이 완료되었습니다.\n마이페이지 내 그룹에서 확인해주세요.")
        this.$store.state.s_group.groupDetailLoading = false
        this.$router.push({name : "Group"})
      })
    }
  },
  mounted(){
    this.$store.state.s_group.groupDetailLoading = true
    this.groupInfoSeq = this.$route.params.contentId
    var params = new URLSearchParams();	// post 방식으로 받아야함.
    params.append('groupInfoSeq', this.groupInfoSeq);
    axios.post("http://localhost:9000/getOneGroup", params)
                .then(res => {
            this.groupOne = res.data
            this.$store.state.s_group.groupDetailLoading = false
          })
  },
  create(){

  }
}
</script>

<style scoped>

@import url("https://fonts.googleapis.com/css?family=Lato:400,700");
/* center container in the middle */
.group_detail{
  width: 950px;
  margin: auto;
}

.groupDetailcCntainer {
	 position: relative;
	 margin: auto;
	 overflow: hidden;
   height: auto;
	 background: #f7f7f7;
}

.groupImage img{
  display: table;
  margin: auto;
  height: 250px;
}

.groupName{
  text-align: center;
  padding: 35px;
}
.groupInfoTitle{
  float: left;
  padding: 60px 0px 0px 100px;
}
.groupInfoTitle > h5 > span{
  color: #727272;
}
.groupInfoContent{
  float: right;
  padding: 60px 100px 60px 0px;
  width: 600px;
  text-align: initial;
}
.groupInfoContent > div{
  margin: 14px 0px;
  line-height: 24px;
}

.groupInfoContent > div > span{
  display: list-item;
  color: #727272;
}

.hr{
  display: flex;
  margin: auto;
  margin-top: 15px;
  margin-bottom: 5px;
  background: #c1c1c1;
  height: 1px;
  width: 80%;
}

.schedules {
	 display: grid;
	 color: #4e4e4e;
	 grid-template-columns: repeat(auto-fill, 30px);
	 grid-gap: 4px;
}
.schedule {
	 padding: 8px;
	 border: 1px solid #4e4e4e;
	 font-size: 0.7em;
	 text-align: center;
}
.schedule_check{
  padding: 8px;
  border: 1px solid #4e4e4e;
  font-size: 0.7em;
  text-align: center;
  background: #FF6C00;
  color: #f5f5f5;
  transition: all 0.4s ease-in-out;
}

</style>