<template>
    <el-form>
        <h3>이력서 수정</h3><br>
		<el-form v-if="login1 == null"></el-form>
		<el-form v-else :model="ruleForm" label-position="top" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
			<el-form-item label="이름">
				<el-input readonly="readonly" :value="login1.memberName"></el-input>
			</el-form-item>
			<el-form-item label="제목"  prop="title">
				<el-input v-model="ruleForm.title" :value="this.$store.state.s_employment.cvDetail.title"></el-input>
			</el-form-item>
            <el-form-item label="지원분야"  prop="category">
				<el-input v-model="ruleForm.category"></el-input>
			</el-form-item>
			
			
			<el-form-item>
				<el-button type="primary" @click="submitForm('ruleForm')">적용</el-button>
				<el-button @click="resetForm('ruleForm')">취소</el-button>
			</el-form-item>
		</el-form>
	</el-form>
</template>

<script>
import 'element-ui/lib/theme-chalk/index.css';
import Vue from "vue"
import moment from "moment"
import VueMomentJS from "vue-momentjs"

Vue.use(VueMomentJS, moment)

export default {
	 data() {
      return {
        ruleForm: {
          title: '',
          category: '',
		  login1: [],
		  dialogImageUrl: ""
		},
		memberName : '',
		file : "",
        rules: {
		  title: [
			{ required: true, message: '제목을 입력하세요', trigger: 'blur' },
          ],
          category: [
			{ required: true, message: '지원분야를 입력하세요', trigger: 'blur' },
		  ]
        }
      };
    },
    methods: {
		submitForm(formName) {
			this.$refs[formName].validate((valid) => {
				if (valid) {
					axios.get("http://localhost:9000/updateCV",{
						params:{
							memberSeq: this.login1.memberSeq,
							title: this.ruleForm.title,
							category: this.ruleForm.category
						}
					}).then(res =>{
						this.$message({ type: 'success', message:'이력서가 성공적으로 수정 되었습니다.' })
						//alert("이력서가 성공적으로 수정 되었습니다.")
						var params = new URLSearchParams();	// post 방식으로 받아야함. 
						params.append('memberSeq', this.login1.memberSeq);
						axios.post("http://localhost:9000/oneMember", params).then(res => { 
							this.$store.state.s_employment.oneMember = res.data
						})
						this.$emit("showCVMain")
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
        // this.memberSeq = this.$store.state.loginUser.memberSeq
        
    },
    mounted(){
		if(this.login1 == null){

		} else {
			var params = new URLSearchParams();	// post 방식으로 받아야함. 
			params.append('memberSeq', this.login1.memberSeq);
			axios.post("http://localhost:9000/getOneCVByMemberSeq", params).then(res => { 
				this.$store.state.s_employment.cvDetail = res.data
				this.ruleForm.title = this.$store.state.s_employment.cvDetail.title
				this.ruleForm.category = this.$store.state.s_employment.cvDetail.category
			})
		}
    }
	
  }
</script>