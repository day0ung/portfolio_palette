<template>
	<el-form>
		<el-form :model="ruleForm" label-position="top" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
			<el-form-item label="기업명">
				<el-input readonly="readonly" :value="login1.memberName"></el-input>
			</el-form-item>
			<el-form-item label="제목"  prop="title">
				<el-input v-model="ruleForm.title"></el-input>
			</el-form-item>
			<el-form-item label="접수기간">
				<el-date-picker
					v-model="ruleForm.cvStartDate"
					type="datetime"
					placeholder="시작일">
				</el-date-picker>
				-
				<el-date-picker
					v-model="ruleForm.cvEndDate"
					type="datetime"
					placeholder="마감일">
				</el-date-picker>
			</el-form-item>
			<el-form-item label="경력"  prop="career">
				<el-input v-model="ruleForm.career"></el-input>
			</el-form-item>
			<el-form-item label="학력" prop="education">
				<el-input v-model="ruleForm.education"></el-input>
			</el-form-item>
			<el-form-item label="근무형태" prop="workingType">
				<el-input v-model="ruleForm.workingType"></el-input>
			</el-form-item>
			<el-form-item label="급여" prop="salary">
				<el-input v-model="ruleForm.salary"></el-input>
			</el-form-item>
			<el-form-item label="직급" prop="position">
				<el-input v-model="ruleForm.position"></el-input>
			</el-form-item>
			<el-form-item label="근무지" prop="workingLocation">
				<el-input v-model="ruleForm.workingLocation"></el-input>
			</el-form-item>
			<el-form-item label="지원홈페이지 주소"  prop="webUrl">
				<el-input v-model="ruleForm.webUrl" placeholder="https://는 생략하고 입력해주세요"></el-input>
			</el-form-item>
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

Vue.use(VueMomentJS, moment)

export default {
	components:{
        quillexamplesnow
    },
	 data() {
      return {
        ruleForm: {
		  title: '',
          career: '',
          education: '',
          workingType: '',
		  salary: '',
		  cvStartDate: '',
          position: '',
          workingLocation: '',
		  dialogImageUrl: "",
		  webUrl : ''
		
		},
		content : "",
		login1: [],
		title1 : "",
		files: [],
		galleryDatas: [],
		
        rules: {
		  title: [
			{ required: true, message: '제목을 입력하세요', trigger: 'blur' },
		  ],
		  career: [
			{ required: true, message: '경력을 입력하세요', trigger: 'blur' },
		  ],
		  period: [
			{ required: true, message: '접수기간을 입력하세요', trigger: 'blur' },
		  ],
		  education: [
			{ required: true, message: '학력을 입력하세요', trigger: 'blur' },
		  ],
		  workingType: [
            { required: true, message: '근무형태를 입력하세요', trigger: 'blur' },
		  ],
		  salary: [
            { required: true, message: '급여를 입력하세요', trigger: 'blur' },
		  ],
		  position: [
            { required: true, message: '직책을 입력하세요', trigger: 'blur' },
		  ],
		  workingLocation: [
            { required: true, message: '근무지를 입력하세요', trigger: 'blur' },
		  ],
		  webUrl: [
            { required: true, message: '지원홈페이지 주소를 입력하세요', trigger: 'blur' },
		  ]
        }
      };
    },
    methods: {
		qContent(value){
			this.content = value
		},
		submitFile() {
			for (i = 0; i < this.files.length; i++) {
				let formData = new FormData();
				formData.append('title', this.title);
				formData.append('files', this.files[i]);
				axios.post('http://localhost:9000/api/gallery/upload',
						formData, {
							headers: {
								'Content-Type': 'multipart/form-data'
							}
						}
					).then(function() {
						console.log('SUCCESS!!');
					})
					.catch(function() {
						console.og('FAILURE!!');
					});
			}
		},
		submitForm(formName) {
			this.$refs[formName].validate((valid) => {
			if (valid) {
			
				axios.get("http://localhost:9000/insertRecruit",{
					params:{
						memberSeq: this.login1.memberSeq,
						title: this.ruleForm.title,
						career: this.ruleForm.career,
						education: this.ruleForm.education,
						workingType: this.ruleForm.workingType,
						cvStartDate : this.$moment(this.ruleForm.cvStartDate).format('YYYY.MM.DD HH:mm:ss'),
						cvEndDate : this.$moment(this.ruleForm.cvEndDate).format('YYYY.MM.DD HH:mm:ss'),
						salary: this.ruleForm.salary,
						position: this.ruleForm.position,
						workingLocation: this.ruleForm.workingLocation,
						webUrl: this.ruleForm.webUrl,
						content:this.content

					}
				}).then(res =>{
					this.$message({ type: 'success', message:'성공적으로 적용되었습니다.' });
					//alert("성공적으로 적용되었습니다.")
					this.$router.push({
						name: 'recruiting'
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
		}
	
	},
	created(){
		let sMemberSeq = sessionStorage.getItem("loginUser")
		this.login1 = JSON.parse(sMemberSeq)
		
		
		// this.memberSeq = this.$store.state.loginUser.memberSeq
	}
	// mounted() {
	// 		axios.get('http://localhost:9000/getImageList')
	// 			.then(res => {
	// 				console.log(res.data);
	// 				this.galleryDatas = res.data
	// 			})
	// 			.catch(error => console.log(error));
	// 	},
  }
</script>