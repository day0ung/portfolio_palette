<template>
  <div>
    <el-card shadow="hover" v-for="(task, index) in list" :key="index" style="margin:10px 5px 10px 5px;">
     <div class="default">
      <div v-bind:class="{'done': task.del == 1}" > 
       <p>{{task.todoDate}}</p>
       <h4>{{task.title}}</h4>
       <p v-if="task.del ==1" style="margin-top: 12px; flaot:left">완료 됨</p>
       <p v-else style="margin-top: 12px; flaot:left">진행 중</p>
      </div>
     </div> <!-- default -->
    </el-card> 
 
  </div>
</template>

<script>
export default {
    props:["list"], 
    data(){
      return{
        isShow: false, //수정 input 보여주기
        clicked: 0, //수정버튼값과 div값이 같을때
        status: true, //수정버튼 눌렀을때
        editList: '', //수정내용
        editBtn: false //수정버튼 보여주기
      }
    },
    methods:{
        del(todoSeq){
         var loginData = sessionStorage.getItem("loginUser");
         var login = JSON.parse(loginData); 
         var memSeq = login.memberSeq 
        
         var params = new URLSearchParams();
         params.append('memberSeq', memSeq)
         params.append('todoSeq', todoSeq)
         axios.post('http://localhost:9000/todoDel', params)
          .then(res => {
            this.$emit("del", memSeq)
         }) 
       },
       done(todoSeq){
         var loginData = sessionStorage.getItem("loginUser");
         var login = JSON.parse(loginData); 
         var memSeq = login.memberSeq 
        
         var params = new URLSearchParams();
         params.append('memberSeq', memSeq)
         params.append('todoSeq', todoSeq)
         axios.post('http://localhost:9000/todoDone', params)
          .then(res => {
            this.$emit("done", memSeq)
            }) 
       },
       edit(todoSeq, index, title){
        this.isShow = !this.isShow
        this.clicked = index
        this.editList = title
       },
       editVal(){
        this.editList == ''? 
        this.editBtn = false
        :this.editBtn = true
       },
       editReal(todoSeq){
         //alert(this.editList)
         //alert(todoSeq) title, memberseq, todoseq
         var loginData = sessionStorage.getItem("loginUser");
         var login = JSON.parse(loginData); 
         var memSeq = login.memberSeq 
        
         var params = new URLSearchParams();
         params.append('memberSeq', memSeq)
         params.append('todoSeq', todoSeq)
         params.append('title', this.editList)
         axios.post('http://localhost:9000/todoEdit', params)
          .then(res => {
            this.$emit("edit", memSeq)
            this.editList = ''
            this.isShow = false
            }) 

       }
    }
}
</script>

<style scoped>

.done h4{
 text-decoration: line-through;
  color: #cecece;
}
.done p{
  color: #cecece;
}
.default{
  margin: 10px;
}
.default p{
 margin: 0px 0px 10px;
}
</style>
