<template>
  <transition name="modal">
    <div class="modal-mask">
        <div class="modal-container">
            <div class="modal-body">
        <div> 
         <el-form :model="ruleForm" label-position="top" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
                <!-- 상세분야 -->
                <el-form-item label="관심분야" prop="inter" >
                  <p style="color: #82CF1E; margin-bottom:-10px" >대학생/취업</p>
                   <div style="border-bottom: solid 1px; color: #cecece; padding-bottom: 10px">
                    <el-checkbox-group v-model="ruleForm.inter" size="medium">
                        <el-checkbox label="1"><label for="inter1">공기업</label></el-checkbox>
                        <el-checkbox label="2"><label for="inter1">자격증</label></el-checkbox>
                        <el-checkbox label="3"><label for="inter1">자소서</label></el-checkbox>
                        <el-checkbox label="4"><label for="inter1">면접</label></el-checkbox>
                        <el-checkbox label="5"><label for="inter1">경영/사무</label></el-checkbox>
                        <el-checkbox label="6"><label for="inter1">디자인</label></el-checkbox>
                        <el-checkbox label="7"><label for="inter1">전문직</label></el-checkbox>
                        <el-checkbox label="8"><label for="inter1">IT/Internet</label></el-checkbox>
                        <el-checkbox label="9"><label for="inter1">유통/무역</label></el-checkbox>
                        <el-checkbox label="10"><label for="inter1">서비스</label></el-checkbox>
                    </el-checkbox-group> 
                   </div>
                     <p style="color: #82CF1E; margin-bottom:-10px" >공무원/임용</p>
                   <div style="border-bottom: solid 1px; color: #cecece; padding-bottom: 10px">
                    <el-checkbox-group v-model="ruleForm.inter" size="medium">
                        <el-checkbox label="11"><label for="inter1">일반행정</label></el-checkbox>
                        <el-checkbox label="12"><label for="inter1">세무</label></el-checkbox>
                        <el-checkbox label="13"><label for="inter1">교육행정</label></el-checkbox>
                        <el-checkbox label="14"><label for="inter1">교정</label></el-checkbox>
                        <el-checkbox label="15"><label for="inter1">군무원</label></el-checkbox>
                        <el-checkbox label="16"><label for="inter1">사회복지</label></el-checkbox>
                        <el-checkbox label="17"><label for="inter1">검찰사무</label></el-checkbox>
                        <el-checkbox label="18"><label for="inter1">소방</label></el-checkbox>
                        <el-checkbox label="19"><label for="inter1">경찰</label></el-checkbox>
                    </el-checkbox-group>
                     </div> 
                     <p style="color: #82CF1E; margin-bottom:-10px" >어학/회화</p>
                   <div style="border-bottom: solid 1px; color: #cecece; padding-bottom: 10px">
                    <el-checkbox-group v-model="ruleForm.inter" size="medium">
                        <el-checkbox label="20"><label for="inter1">토익</label></el-checkbox>
                        <el-checkbox label="21"><label for="inter1">토스/오픽</label></el-checkbox>
                        <el-checkbox label="22"><label for="inter1">기초영어회화</label></el-checkbox>
                        <el-checkbox label="23"><label for="inter1">일본어</label></el-checkbox>
                        <el-checkbox label="24"><label for="inter1">중국어</label></el-checkbox>
                        <el-checkbox label="25"><label for="inter1">제2외국어</label></el-checkbox>
                    </el-checkbox-group>
                     </div> 
                     <p style="color: #82CF1E; margin-bottom:-10px" >라이프/취미</p>
                   <div style="border-bottom: solid 1px; color: #cecece; padding-bottom: 10px">
                    <el-checkbox-group v-model="ruleForm.inter" size="medium">
                        <el-checkbox label="26"><label for="inter1">여행/아웃도어</label></el-checkbox>
                        <el-checkbox label="27"><label for="inter1">운동</label></el-checkbox>
                        <el-checkbox label="28"><label for="inter1">봉사활동</label></el-checkbox>
                        <el-checkbox label="29"><label for="inter1">공예/만들기</label></el-checkbox>
                        <el-checkbox label="30"><label for="inter1">댄스/무용</label></el-checkbox>
                        <el-checkbox label="31"><label for="inter1">요리/제조</label></el-checkbox>
                        <el-checkbox label="32"><label for="inter1">자유</label></el-checkbox>
                    </el-checkbox-group>
                     </div> 
               </el-form-item> 

                <!-- 서브밋버튼 -->
                <div class="submitBtnDiv">
                    <el-form-item>
                        <el-button type="primary" @click="submitForm('ruleForm')">수정하기</el-button>
                        <el-button @click="resetForm('ruleForm')">취소</el-button>
                    </el-form-item>
                </div>
            </el-form>
                </div>
             </div>
        </div>
     </div>
  </transition>
</template>

<script>
export default {
    props:["memSeq"], 
    data(){
        return{
          ruleForm: {
              inter: [],
          },
          rules: {
              inter:[ { required: true, message: '관심분야를 선택해주세요', trigger: 'change' } ]
          },
        }
    },
    methods:{
         submitForm(formName) {
            this.$refs[formName].validate((valid) => {
            if (valid) {
                var interArea= this.ruleForm.address + this.ruleForm.extraAddress
                var params = new URLSearchParams();
                params.append('memberSeq', this.memSeq)
                //alert(this.memSeq)
                params.append('interSmallSeqs', this.ruleForm.inter)
                axios.post('http://localhost:9000/InterestingUpdate', params).then(
                res => {
                    if(res.data == 'perfect'){
                        this.$message({ type: 'success', message:'수정이 완료 되었습니다'})
                        //alert('작성이 완료 되었습니다')
                         this.$emit('update', this.memSeq)   
                    }
                }) 

            } else {
                console.log('error submit!!');
                return false;
            }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
             this.$emit('close')   
        },
        
    }
}
</script>

<style scoped>
.modal-container{
    margin-left: 600px;
    margin-top: 12px;
    width: 500px;
    padding: 0px 19px;
    background-color: #fff;
    border-radius: 2px;
    -webkit-box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
    box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
    -webkit-transition: all .3s ease;
    transition: all .3s ease; 
   
}

.modal-body {
 margin: 0px 0; 
 position: relative; 
 flex:  none; 
}
.submitBtnDiv{
    margin-left: 20px
}
</style>
