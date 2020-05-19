<template>
    <el-form>
        <h3>새 이력서</h3><br>
		<el-form :model="ruleForm" label-position="top" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
			<el-form-item label="이름">
				<el-input readonly="readonly" >{{login1.memberName}}</el-input>
			</el-form-item>
			<el-form-item label="제목"  prop="title">
				<el-input v-model="ruleForm.title"></el-input>
			</el-form-item>
            <el-form-item label="지원분야"  prop="category">
				<el-input v-model="ruleForm.category"></el-input>
			</el-form-item>
			<el-form-item label="이력서 올리기" prop="fileList">
                <el-upload
                    action=""
                    list-type="picture-card"
                    accept=".jpg, .jpeg, .png, .bmp, .txt, .ppt, .pptx, .hwp"
                    multiple
                    v-model="ruleForm.fileList"
                    :limit="3"
                    :auto-upload="false"
                    :on-change="handleChange"
                    :on-exceed="handleExceed"
                    :on-preview="handlePictureCardPreview"
                    :on-remove="handleRemove">
                    <i class="el-icon-plus"></i>
                </el-upload>
                <el-dialog :visible.sync="dialogVisible">
                    <img width="100%" :src="ruleForm.dialogImageUrl" alt="">
                </el-dialog>
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
		  dialogVisible: false,
        ruleForm: {
          title: '',
          category: '',
		  login1: [],
		  dialogImageUrl: "",
		  fileList: []
		},
        rules: {
		  title: [
			{ required: true, message: '제목을 입력하세요', trigger: 'blur' },
          ],
          category: [
			{ required: true, message: '지원분야를 입력하세요', trigger: 'blur' },
		  ],
		  fileList: [
            { required: true, message: '이력서를 올려주세요', trigger: 'change' },
        	]
        }
      };
    },
    methods: {
		submitForm(formName) {
			this.$refs[formName].validate((valid) => {
			if (valid) {
				let formData = new FormData();
				formData.append('memberSeq', this.login1.memberSeq)
				formData.append('memberId', this.login1.memberId)
				formData.append('title', this.ruleForm.title)
				formData.append('category', this.ruleForm.category)
				this.ruleForm.fileList.forEach(function(element){
                    formData.append('files', element)
                })
                for (let key of formData.entries()){
                    console.log(`${key}`)
                }
				axios.post("http://localhost:9000/insertCV", formData, {
					headers:{
                        'Content-Type' : 'multipart/form-data'
					}
				}).then(res =>{
					this.$message({ type: 'success', message:'이력서가 성공적으로 업로드 되었습니다.'})
					//alert("이력서가 성공적으로 업로드 되었습니다.")
					var params = new URLSearchParams();	// post 방식으로 받아야함. 
					params.append('memberSeq', this.login1.memberSeq);
					axios.post("http://localhost:9000/oneMember", params).then(res => { 
       					this.$store.state.s_employment.oneMember = res.data
      				})
					
					this.$emit("showCVMain")

				})
			} 
			});
		},
		resetForm(formName) {
			this.$refs[formName].resetFields();
		},
		handleChange(file, fileList){
            this.ruleForm.fileList.push(file.raw) 
            console.log("addList")
            console.log(this.ruleForm.fileList)
        },
        handleRemove(file, fileList) {
            this.ruleForm.fileList = this.ruleForm.fileList.filter(data => data.name !== file.raw.name)
            console.log("removeList")
            console.log(this.ruleForm.fileList)
        },
        handlePictureCardPreview(file) {
            this.ruleForm.dialogImageUrl = file.url;
            this.dialogVisible = true;
        },
        handleExceed(files, fileList){
            this.$message({
                showClose: true,
                center: true,
                message: '자료는 한번에 2개만 업로드가능합니다.',
                type: 'error'  
            })
        },

	},
	created(){
		let sMemberSeq = sessionStorage.getItem("loginUser")
		this.login1 = JSON.parse(sMemberSeq)
		// this.memberSeq = this.$store.state.loginUser.memberSeq
	}
	
  }
</script>