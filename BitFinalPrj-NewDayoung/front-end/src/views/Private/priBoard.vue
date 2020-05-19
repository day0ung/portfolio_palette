<template>
  <div>
    <div v-show="this.$store.state.s_private.list">
     <el-button type="plain" style="float: right; margin-right: 20px" round @click="writeBoard">글 쓰기</el-button>
       <el-table 
          :data="this.$store.state.s_private.BoardData"
          stripe
          style="width: 100%; cursor:pointer"
          @row-click="gotoClick"
        >
        <el-table-column
          prop="rowNum"
          label="글번호"
          >
        </el-table-column>
        <el-table-column
          prop="title"
          label="글제목"
          >
        </el-table-column>
    
        <el-table-column
          prop="writeDate"
          label="작성일"
          >
        </el-table-column>
      </el-table>
    </div>
      <Write v-show="this.$store.state.s_private.write"
      @upload="uploadData">
      </Write>
      <Detail v-show="this.$store.state.s_private.detail"
      @delete="deleteData">
      </Detail>
      <Update v-show="this.$store.state.s_private.update"
      @update='updateData'>
      </Update>
  </div>
</template>

<script>
import Write from '@/views/Private/Board/WriteBoard.vue'
import Detail from '@/views/Private/Board/DetailBoard.vue'
import Update from '@/views/Private/Board/UpdateBoard.vue'
export default {
    components:{
      Write,Detail, Update
    },
    data(){
        return{
          
        }
    }, 
    methods:{
      writeBoard(){
        this.$store.state.s_private.list = false
        this.$store.state.s_private.write = true
       },
       gotoClick(row, column, event){
         var loginData = sessionStorage.getItem("loginUser");
         var login = JSON.parse(loginData); 
         var memSeq = login.memberSeq 
         
         //게시판
         axios.get("http://localhost:9000/detailLibarary",{ params:{ boardSeq: row.boardSeq} }  )
              .then(res => {
                this.$store.state.s_private.boardDetail = res.data
              })

          //파일
          var params = new URLSearchParams()
          params.append('memberSeq', memSeq)
          params.append('boardSeq', row.boardSeq);
          axios.post("http://localhost:9000/detailFile", params)
              .then(res => {
                this.$store.state.s_private.fileDetail = res.data
                //alert("파일"+JSON.stringify(this.$store.state.s_private.fileDetail))
              })

          this.$store.state.s_private.list = false //list 끄기
          this.$store.state.s_private.detail = true  //detail보여주기

       },
      getBoard(memSeq){
         let params = new URLSearchParams()
         params.append('memberSeq', memSeq)
         axios.post('http://localhost:9000/getMemberBoard', params)
         .then(res => {
            this.$store.state.s_private.BoardData= res.data
            //alert(this.$store.state.s_private.BoardData)
           }) 
       },
       uploadData(memSeq){
         this.getBoard(memSeq)
         this.$store.state.s_private.list = true
       },
       updateData(memSeq){
          this.getBoard(memSeq)
       },
       deleteData(memSeq){
         this.getBoard(memSeq)
         this.$store.state.s_private.list = true
       }

    },
    mounted(){
         /*var loginData = sessionStorage.getItem("loginUser");
         var login = JSON.parse(loginData); 
         var memSeq = login.memberSeq
         var params = new URLSearchParams();
         params.append('memberSeq', memSeq)
         axios.post('http://localhost:9000/getMemberBoard', params)
         .then(res => {
            this.$store.state.s_private.BoardData = res.data
           })*/ 
    },
}
</script>

<style>

</style>