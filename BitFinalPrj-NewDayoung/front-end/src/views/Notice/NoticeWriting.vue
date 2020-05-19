<template>
	<el-form>
		<h3>익명게시판 글쓰기</h3><br><br>
		<el-form :model="ruleForm" label-position="top" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
			<el-form-item label="기업명" hidden>
				<el-input readonly="readonly" :value="login1.memberName"></el-input>
			</el-form-item>
			<el-form-item label="제목"  prop="title">
				<el-input v-model="ruleForm.title"></el-input>
			</el-form-item>
			<!-- </el-form-item> -->
			<!-- <el-form-item label="내용" prop="content">
				<el-input type="textarea" v-model="ruleForm.content"></el-input>
			</el-form-item> -->
			
				<div class="board_write">
					<quillexamplesnow
						@contentS="qContent"
					/>
				</div>
			
			<el-form-item>
				<el-button type="primary" @click="submitForm('ruleForm')">적용</el-button>
				<el-button @click="resetForm('ruleForm')">취소</el-button>
			</el-form-item>
		</el-form>
	</el-form>
</template>

<script>
import quillexamplesnow from '@/components/Qilledit'
import 'element-ui/lib/theme-chalk/index.css';
import Vue from "vue"
import moment from "moment"
import VueMomentJS from "vue-momentjs"
import NoticeMain from '@/views/Notice/Notice.vue'

Vue.use(VueMomentJS, moment)

export default {

	components:{
        quillexamplesnow, NoticeMain
    },
	 data() {
      return {
        ruleForm: {
		  title: '',
		},
		content : "",
		login1: [],
        rules: {
		  title: [
			{ required: true, message: '제목을 입력하세요', trigger: 'blur' },
		  ],
		},
		tableData: [],
		total: 0,
		listQuery:{
				page: 1,
				limit: 10,
				title: "",
				searchWord: "",
				s_keyWord: ""
			},
			searchWord:'',
			
			s_keyWord:'',
			loading: true,
      };
    },
    methods: {
		qContent(value){
			this.content = value
		},
		
		submitForm(formName) {
			this.$refs[formName].validate((valid) => {
			if (valid) {
			
				axios.get("http://localhost:9000/insertAnonymousBoard",{
					params:{
						memberSeq: this.login1.memberSeq,
						title: this.ruleForm.title,
						content:this.content

					}
				}).then(res =>{
					this.$message({ type: 'success', message:'성공적으로 적용되었습니다.'})
					//alert("성공적으로 적용되었습니다.")
     				this.$router.push({
						 name : "Notice"
					 })
				})
				
			} else {
				console.log('error submit!!');
				return false;
			}
			});
		},
		resetForm(formName) {
			this.$refs[formName].resetFields();
		},
	},
	created(){
		let sMemberSeq = sessionStorage.getItem("loginUser")
		this.login1 = JSON.parse(sMemberSeq)
	}
  }
</script>