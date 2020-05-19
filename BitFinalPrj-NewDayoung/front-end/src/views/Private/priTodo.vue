<template>
  <div>
      <el-button type="text" @click="remainSelect"> TODO LIST: {{todoList.length - countDone}} </el-button>
      <el-button type="text" @click="doneSelect">완료된 할일: {{countDone}} </el-button>
      <el-button type="text" @click="allSelect">전체 TODO LIST: {{todoList.length}} </el-button>

      <ListAdd v-if="taskAdd"
      @listAdd="listAppend"
      @cancle="offAdd">
      </ListAdd>
      <el-button @click="showAdd" v-if="showBtn"><i class="el-icon-plus"></i> 할일을 추가하세요</el-button>
      <ListDoing
        v-if="task == 1"
        :list="doingList"
        @del="listDel"
        @done="listDone"
        @edit="listEdit">
      </ListDoing>
      <ListShow
        v-if="task == 2"
        :list="todoList">
        </ListShow> 
        <ListDone
        v-if="task == 3"
        :list="doneList">
        </ListDone>
     
      
  </div>
  
</template>

<script>
import ListAdd from '@/views/Private/TodoList/TodoAdd.vue'
import ListShow from "@/views/Private/TodoList/TodoShow.vue";
import ListDone from "@/views/Private/TodoList/TodoDone.vue";
import ListDoing from "@/views/Private/TodoList/TodoDoing.vue";
export default {
    components:{
      ListAdd,ListShow,ListDone,ListDoing
    },
    data(){
      return{
        task: 1,
        showBtn: true, //버튼 보여주기
        taskAdd: false, //할일추가
        add: true,
        todoList:[],
        doingList:[],
        memberSeq: ''
      }
    },
    mounted(){
         var loginData = sessionStorage.getItem("loginUser");
         var login = JSON.parse(loginData); 
         var memSeq = login.memberSeq
         this.memberSeq = memSeq
         var params = new URLSearchParams();
         params.append('memberSeq', memSeq)
         axios.post('http://localhost:9000/selectTodoList', params)
          .then(res => {
              this.todoList = res.data
         }) 

         axios.post('http://localhost:9000/TodoListDoing', params)
          .then(res => {
              this.doingList = res.data
         }) 
         
    },
    computed:{
      countDone(){
          let count = 0
          this.todoList.forEach(list =>{
            if(list.del == '1') count++
          })
          return count 
      }
    },
    methods:{
      getDoingList(memSeq){
         var params = new URLSearchParams();
         params.append('memberSeq', memSeq)
         axios.post('http://localhost:9000/TodoListDoing', params)
          .then(res => {
              this.doingList = res.data
         }) 
      },
      getAllLIst(memSeq){
         var params = new URLSearchParams();
         params.append('memberSeq', memSeq)
         axios.post('http://localhost:9000/selectTodoList', params)
          .then(res => {
              this.todoList = res.data
         }) 
      },
      showAdd(){
        //taskAdd == input Tag  //showBtn == 버튼 보여주기
        this.taskAdd = true
        this.showBtn = false
      },
       offAdd(){
         this.taskAdd = false
         this.showBtn = true
       },
       listAppend(title){
         var loginData = sessionStorage.getItem("loginUser");
         var login = JSON.parse(loginData); 
         var memSeq = login.memberSeq //addTodoList
        
         var params = new URLSearchParams();
         params.append('memberSeq', memSeq)
         params.append('title', title)
         axios.post('http://localhost:9000/selectOneList', params)
          .then(res => {
              var datas = res.data
              this.todoList.push({todoSeq: datas.todoSeq, title:datas.title, todoDate: datas.todoDate, del: datas.del})
              this.doingList.push({todoSeq: datas.todoSeq, title:datas.title, todoDate: datas.todoDate, del: datas.del})
              this.taskAdd = false
              this.showBtn = true
         }) 
       },
       listDel(memSeq){
         this.getDoingList(memSeq);
       },
       listDone(memSeq){
         this.getDoingList(memSeq)
         this.getAllLIst(memSeq)
       },
       listEdit(memSeq){
         this.getDoingList(memSeq)
       },
       //전체 보기로 
       allSelect(){
        this.task = 2
        this.showBtn = false
       },
       //완료된 할일 보여주는 곳 selectDoneTodoList
       doneSelect(){
         this.showBtn = false
         var loginData = sessionStorage.getItem("loginUser");
         var login = JSON.parse(loginData); 
         var memSeq = login.memberSeq //addTodoList
        
         var params = new URLSearchParams();
         params.append('memberSeq', memSeq)
         axios.post('http://localhost:9000/selectDoneTodoList', params)
          .then(res => {
              this.doneList= res.data
              this.task = 3
         }) 
       },
       //남은 할일 보여주는 곳
       remainSelect(){
         this.showBtn = true
         this.task = 1
       },
    }
}
</script>
