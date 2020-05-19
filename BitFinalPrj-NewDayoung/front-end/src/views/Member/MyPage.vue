<template>
  <div class="myPageWrap">
			<h4 style="margin-left: 29px; padding-top: 5px;" > <strong>{{name}}</strong>님</h4>
		<br>
	  <div class="mymenu">
			<el-menu
			class="el-menu-vertical-demo"
			active-text-color="#ff674b"
			default-active="5">
			<el-menu-item index="1"  v-if="auth == '1' | auth == '0' " @click="studyClick(memSeq)" >
				<i class="el-icon-edit"></i>
				<span>내 스터디</span>
			</el-menu-item>
			<el-menu-item index="2" v-if="auth == '1' | auth == '0' "  @click="scheduleClick(memSeq)">
				<i class="el-icon-postcard"></i>
				<span>내 일정</span>
			</el-menu-item>
			<el-menu-item index="6"  v-if="auth == '1' | auth == '0' "  @click="referenceClick(memSeq)">
				<i class="el-icon-takeaway-box"></i>
				<span>내 자료</span>
			</el-menu-item>
			<el-menu-item index="3" v-model="auth" v-if="auth == '1' | auth == '0' "  @click="resumeClick(memSeq)">
				<i class="el-icon-document"></i>
				<span>내 이력서</span>
			</el-menu-item>
			<el-menu-item index="3" v-model="auth" v-else @click="recruitClick(memSeq)">
				<i class="el-icon-document"></i>
				<span>내 공고</span>
			</el-menu-item>
			<el-menu-item index="4" v-model="auth" v-if="auth == '1' | auth == '0' "  @click="interclick(memSeq)">
				<i class="el-icon-star-off"></i>
				<span>내 찜목록</span>
			</el-menu-item><!-- $router.push({name:'MyInfo', params: { seq: memSeq }}), show = false" -->
			<el-menu-item index="5"   @click="infoClick(memSeq)" >
				<i class="el-icon-setting"></i>
				<span>내 정보수정</span>
			</el-menu-item>
			</el-menu>
	 </div>
	 <div class="myContent" >
		<MyStudy v-if="study"/>
		<MySchedule v-if="schedule"/>
		<MyReference v-if="reference"/>
		<MyResume v-if="resume"/>
		<MyRecruit v-if="recruit"/>
		<MyInter v-if="inter"
		:memSeq="memSeq"
		:myinter="myinter"
		:mylike="mylike"
		:myBig="myBig"
		:mySmall="mySmall"
		@updateInterArea="updateInterArea"/>
		<MyInfo v-if="info"
		:memSeq="memSeq"
		:myinfo="myinfo"
		@emailUpdate="emailUpdate"
		@addrUpdate="addrUpdate"
		@passUpdate="passUpdate"/>
	 </div> 

  </div>
</template>

<script>
import MyStudy from '@/views/MyPage/myStudy.vue'
import MySchedule from '@/views/MyPage/mySchedule.vue'
import MyResume from '@/views/MyPage/myResume.vue'
import MyRecruit from '@/views/MyPage/myRecruit.vue'
import MyInter from '@/views/MyPage/myInter.vue'
import MyInfo from '@/views/MyPage/myInfo.vue'
import MyReference from '@/views/MyPage/reference.vue'

export default {
	components:{
		MyStudy, MySchedule, MyResume, MyInter, MyInfo, MyReference, MyRecruit
	},
	data(){
		return{
			study: false,
			schedule:false,
			reference: false,
			resume:false,
			recruit:false,
			inter:false,
			info:true,
			mylike: [],
			myinfo: [],
			myinter:[],
         	myBig: [],
          	mySmall: [],
			name: '',
			memSeq: '',
			auth: '',
		}
	}, 
	 mounted(){
		 var loginData = sessionStorage.getItem("loginUser");
		 var login = JSON.parse(loginData); 
		 this.name = login.memberName
		 this.memSeq = login.memberSeq
		 this.auth = login.auth
		 this.getInfomation(this.memSeq)
		 this.getMylikeList(this.memSeq)
	}, 

	methods:{
		getMylikeList(memSeq){
			let params = new URLSearchParams();
			params.append('memberSeq', memSeq)
			axios.post('http://localhost:9000/getMylikeList', params).then(res => {
				this.mylike = res.data
				this.$store.state.s_member.MyPageInterLikeList = res.data
				console.log(res.data)
			})
		},
		getInfomation(memSeq){
			var params = new URLSearchParams();
			params.append('memberSeq', memSeq)
			axios.post('http://localhost:9000/myPageInfomation', params)
			.then(res => {
				this.myinfo = res.data
			})
		},
		studyClick(){
			this.study = true
			this.schedule = false
			this.resume = false
			this.recruit = false
			this.inter = false
			this.info = false
			this.reference = false
		},
		scheduleClick(){
			this.study = false
			this.schedule = true
			this.resume = false
			this.recruit = false
			this.inter = false
			this.info = false
			this.reference = false
		},
		referenceClick(){
			this.study = false
			this.schedule = false
			this.reference = true
			this.resume = false
			this.recruit = false
			this.inter = false
			this.info = false
		},
		resumeClick(){
			this.study = false
			this.schedule = false
			this.resume = true
			this.recruit = false
			this.inter = false
			this.info = false
			this.reference = false
		},
		interclick(memSeq){
			this.study = false
			this.schedule = false
			this.resume = false
			this.recruit = false
			this.inter = true
			this.info = false
			this.reference = false
			var params = new URLSearchParams();
			params.append('memberSeq', memSeq)
			axios.post('http://localhost:9000/myPageInter', params)
			.then(res => {
				var smyinfo = res.data
				this.myinter = smyinfo
				
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
		},
		infoClick(memSeq){
			this.study = false
			this.schedule = false
			this.resume = false
			this.recruit = false
			this.inter = false
			this.info = true	
			this.reference = false	
			var params = new URLSearchParams();
			params.append('memberSeq', memSeq)
			axios.post('http://localhost:9000/myPageInfomation', params)
			.then(res => {
			this.myinfo = res.data
			 }) 
		},
		recruitClick(){
			this.study = false
			this.schedule = false
			this.reference = false
			this.resume = false
			this.recruit = true
			this.inter = false
			this.info = false
		},

		emailUpdate(memSeq){
			this.getInfomation(memSeq)
		},
		addrUpdate(memSeq){
			this.getInfomation(memSeq)
		},
		passUpdate(memSeq){
			this.getInfomation(memSeq)
		},
		updateInterArea(memSeq){
			var params = new URLSearchParams();
			params.append('memberSeq', memSeq)
			axios.post('http://localhost:9000/myPageInter', params)
			.then(res => {
				this.myinter = res.data
			})  
		},
	}
}
</script>

<style scoped>
.myPageWrap{
	min-height: 120vh;
}

.mymenu{
	float: left;
	width: 15%;
}
.myContent{
	float: left;
	width: 85%;
	padding-left: 25px;
}
.el-menu-item:hover{
	background-color: #fbfbfb;
}
.el-menu-item:focus{
	background-color: #fbfbfb;
}

</style>