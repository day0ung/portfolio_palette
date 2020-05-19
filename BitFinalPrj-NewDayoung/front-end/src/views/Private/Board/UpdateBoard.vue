<template>
   <div class="reference_write">
        <br>
        <br>
        <el-button @click="showGroupReference" round>목록으로</el-button>
        <br>
        <br>
        <el-form :model="ruleForm" label-position="top" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
            <el-form-item label="자료 올리기" prop="fileList">
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

            <el-form-item label="자료 제목"  prop="title">
                <el-input v-model="ruleForm.title"></el-input>
            </el-form-item>

            <el-form-item label="자료 간략내용" style="margin-bottom: 10px;">
                <el-input v-model="ruleForm.content"></el-input>
            </el-form-item>
                <br>
                <br>
                <br>
            <div class="writeButton" style="display: table; margin: auto;">
                <el-form-item>
                    <el-button @click="submitForm('ruleForm')" round>작성완료</el-button>
                </el-form-item>
            </div>
        </el-form>
  </div>
</template>

<script>
export default {
    data(){
        return{
            dialogVisible: false,
            ruleForm:{
                title: '',
                content: '',
                dialogImageUrl: '',
                fileList: [],
            },
            rules: {
                title: [
                    { required: true, message: '자료 제목을 입력하세요', trigger: 'blur' },
                ],
                content: [
                    { required: true, message: '간략하게 자료에 대한 설명을해주세요', trigger: 'blur' },
                ],
                fileList: [
                    { required: true, message: '자료를 올려주세요', trigger: 'change' },
                ]
            }
        }
    },
    methods:{
        submitForm(formName) {
			this.$refs[formName].validate((valid) => {
			if (valid) {
                
                 let formData = new FormData();
                 formData.append('memberSeq', this.$store.state.loginUser.memberSeq)
                 formData.append('title',this.ruleForm.title)
                 formData.append('content',this.ruleForm.content)
                 formData.append('boardSeq', this.$store.state.s_private.boardDetail.boardSeq)
                 this.ruleForm.fileList.forEach(function(element){
                     formData.append('files', element)
                 })
                 // for (let key of formData.entries()){
                 // //     console.log(`${key}`)
                 // }
				 axios.post("http://localhost:9000/updateLibarary", formData ,{
				 	headers:{
                         'Content-Type' : 'multipart/form-data'
				 	}
				 }).then(res =>{
                     alert("자료가 업로드 되었습니다.")
                      this.$store.state.s_private.list = true
                      this.$store.state.s_private.write = false
                      this.$store.state.s_private.update = false
                      this.ruleForm.title = ''
                      this.ruleForm.content =''
                      this.ruleForm.dialogImageUrl =''
                      this.ruleForm.fileList.splice(0)
                      this.dialogVisible = false
                      this.$emit('update',this.$store.state.loginUser.memberSeq )
				 })
				
			} else {
				console.log('error submit!!');
				return false;
			}
            });
        },
        showGroupReference(){
             this.ruleForm.title = ''
             this.ruleForm.content =''
             this.ruleForm.dialogImageUrl =''
             this.ruleForm.fileList = []
             this.$store.state.s_private.list = true
             this.$store.state.s_private.write = false
             this.$store.state.s_private.update = false
        },
        handleChange(file, fileList){
            this.ruleForm.fileList.push(file.raw) 
        },
        handleRemove(file, fileList) {
            this.ruleForm.fileList = this.ruleForm.fileList.filter(data => data.name !== file.raw.name)
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
        }
    },
    mounted(){
        
    }

}
</script>

<style>

</style>