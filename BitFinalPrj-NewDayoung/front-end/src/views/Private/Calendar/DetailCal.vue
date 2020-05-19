<template>
   <transition name="modal">
           <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">

          <div class="modal-header">
            <slot name="header" v-if="updateNum==1">일정 수정하기</slot>
            <slot name="header" v-else>일정 보기</slot>
          </div>

          <div class="modal-body">
            <slot name="body">
                    
                <!-- input box -->
              <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
                <el-form-item label="시작일" prop="startDate">
                  <el-date-picker v-if="updateNum==1" 
                                  v-model="ruleForm.startDate" type="datetime" placeholder="시작일"></el-date-picker>
                  <el-date-picker v-else
                                  v-model="ruleForm.startDate" type="datetime" placeholder="시작일" readonly></el-date-picker>
                </el-form-item>
                <el-form-item label="종료일" prop="endDate">
                  <el-date-picker v-if="updateNum==1"  
                    v-model="ruleForm.endDate" type="datetime" placeholder="마감일"></el-date-picker>
                    <el-date-picker v-else 
                    v-model="ruleForm.endDate" type="datetime" placeholder="마감일" readonly></el-date-picker>
                </el-form-item>
                <el-form-item label="title" prop="title">
                  <el-input v-if="updateNum==1"  v-model="ruleForm.title" size="mini"></el-input>
                  <el-input v-else v-model="ruleForm.title" size="mini" readonly></el-input>
                </el-form-item>
                
                <el-form-item label="content" prop="content">
                  <el-input v-if="updateNum==1" type="textarea" v-model="ruleForm.content"></el-input>
                  <el-input v-else type="textarea" v-model="ruleForm.content" readonly></el-input>
                </el-form-item>
                <el-form-item label="배경" prop="backColor" >
                  <el-select v-if="updateNum==1" v-model="ruleForm.backColor" placeholder="배경" >
                    <el-option label="노랑색" value="#ffff00" style="background-color:#ffff00;"></el-option>
                    <el-option label="주황색" value="#ffa94d" style="background-color:#ffa94d; color:#fff"></el-option>
                    <el-option label="파란색" value="#74c0fc" style="background-color:#74c0fc; color:#fff"></el-option>
                    <el-option label="빨간색" value="#f06595" style="background-color:#f06595; color:#fff"></el-option>
                    <el-option label="초록색" value="#a9e34b" style="background-color:#a9e34b; color:#fff"></el-option>
                  </el-select>
                  <el-select v-else v-model="ruleForm.backColor" placeholder="배경">
                    <el-option label="노랑색" value="#ffff00" disabled=true></el-option>
                    <el-option label="주황색" value="#ffa94d" disabled=true></el-option>
                    <el-option label="파란색" value="#74c0fc" disabled=true></el-option>
                    <el-option label="빨간색" value="#f06595" disabled=true></el-option>
                    <el-option label="초록색" value="#a9e34b" disabled=true></el-option>
                  </el-select>
                </el-form-item>
                
                <el-form-item>
                  <el-button type="primary" v-if="updateNum == 0" @click="updateNum = 1">수정하기</el-button>
                  <el-button type="primary" v-if="updateNum == 1" @click="updateCalendar('ruleForm')">수정하기</el-button>
                  <el-button v-if="updateNum == 1" @click="resetForm('ruleForm')">Reset</el-button>
                  <el-button type="primary" @click="deleteCal">삭제하기</el-button>
                </el-form-item>
              </el-form>
           
            </slot>
          </div>

          <div class="modal-footer">
            <slot name="footer">
              <button class="modal-default-button" @click="exit">
                      CLOSE
              </button>
            </slot>
          </div>
        </div>
      </div>
    </div>
   </transition>
</template>

<script>
export default {
    data(){
        return{
                updateNum: 0,
                ruleForm: {
                    calendarSeq:'',
                    startDate: '',
                    endDate: '',
                    title: '',
                    content: '',
                    backColor:''
                },
                  rules: {
                    title: [
                        { required: true, message: '제목을 입력해주세요', trigger: 'blur' },
                        { min: 3, message: '최소 3글자 이상 작성해주세요', trigger: 'blur' }
                    ],
                    startDate: [
                        { required: true, message: 'Please pick a date', trigger: 'change' }
                    ],
                    endDate: [
                        { required: true, message: 'Please pick a date', trigger: 'change' }
                    ],
                    content: [
                        { required: true, message: 'Please input activity form', trigger: 'blur' }
                    ],
                    backColor:[
                        { required: true, message: 'Please input activity form', trigger: 'blur' }
                    ]
                }
        }
    },
    methods:{
         deleteCal(){
            this.$confirm('정말 삭제하시겠습니까?', 'Warning', {
            confirmButtonText: 'OK',
            cancelButtonText: 'Cancel',
            type: 'warning'
            }).then(() => {
            this.$store.state.s_group.showGroupCalendar = true
            let params = new URLSearchParams()	
            params.append('seq', this.ruleForm.calendarSeq)
            axios.post("http://localhost:9000/deleteGroupCalendar", params)
            .then(res => {
                if(res.data === ""){
                this.$message({ type: 'success', message:' Delete completed' });
                
                    let params = new URLSearchParams()	
                    let groupSeq = this.$store.state.s_group.groupSeq
                    params.append('groupInfoSeq', groupSeq)
                    axios.post("http://localhost:9000/getGroupCalendar", params)
                    .then(res => {
                        //console.log(JSON.stringify(res.data))
                        let e = (JSON.stringify(res.data))
                        this.$store.state.s_group.groupCalendar = JSON.parse(e)
                        this.$store.state.s_group.showGroupCalendar = false
                    })
                }
            })

            this.$emit('close')
        
                }).catch(() => {
                this.$message({
                    type: 'info',
                    message: 'Delete canceled'
                });          
                });
        },
        updateCalendar(formName){
            this.$refs[formName].validate((valid) => {
            if (valid) {

                axios.get("http://localhost:9000/updateMemberCalendar",{
                params:{
                calendarSeq : this.ruleForm.calendarSeq,
                title: this.ruleForm.title,
                content: this.ruleForm.content,
                start : this.$moment(this.ruleForm.startDate).format('YYYY.MM.DD HH:mm:ss'),
                end : this.$moment(this.ruleForm.endDate).format('YYYY.MM.DD HH:mm:ss'),
                color : this.ruleForm.backColor
                
                }
                }).then(res =>{
                if(res.data === ""){
                  this.$message({ type: 'success', message:'성공적으로 수정되었습니다.'})
                    //alert("성공적으로 수정되었습니다.")
                    this.$emit('close')
                }
                else {
                    alert("실패했습니다. 다시 확인해주시기 바랍니다.")
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
            },
        exit(){
            this.$emit('close')
        },
    },
    mounted(){
    //this.$moment(this.$store.state.s_group.groupCalendarStartDate).format('YYYY.MM.DD HH:mm:ss')
        this.ruleForm.calendarSeq = this.$store.state.s_private.memberCalendarDetail.calendarSeq
        this.ruleForm.startDate = this.$moment(this.$store.state.s_private.memberCalendarDetail.start).format('YYYY.MM.DD HH:mm:ss')
        if(this.$store.state.s_private.memberCalendarDetail.end === null){
        this.ruleForm.endDate = this.$moment(this.$store.state.s_private.memberCalendarDetail.start).format('YYYY.MM.DD HH:mm:ss')
        }
        this.ruleForm.endDate = this.$moment(this.$store.state.s_private.memberCalendarDetail.end).format('YYYY.MM.DD HH:mm:ss')
        this.ruleForm.title = this.$store.state.s_private.memberCalendarDetail.title
        this.ruleForm.content = this.$store.state.s_private.memberCalendarDetail.content
        this.ruleForm.backColor = this.$store.state.s_private.memberCalendarDetail.color
    }
}
</script>

<style>

</style>