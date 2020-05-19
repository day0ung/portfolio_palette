
<template>
  <div class="boardDetail">
    <br>

      <div class="groupdetailInfo">
               <table class="table1">
                    <tbody>
                      <tr>
                        <td style="width: 113px;"><h5>제목</h5> </td>
                        <td>
                         <h5>{{ this.$store.state.s_private.boardDetail.title }}</h5>
                        </td>
                      </tr>
                    </tbody>
                  </table>
         <br><br><br>
              <table class="table1">
                    <tbody>
                      <tr>
                        <td style="width: 113px;"><h5>내용</h5> </td>
                        <td>
                         <h5> {{ this.$store.state.s_private.boardDetail.content }}</h5>
                        </td>
                      </tr>
                    </tbody>
              </table>
         <br><br><br>
          <table class="table1">
            <tbody>
                <tr>
                <td style="width: 113px;"><h5>파일</h5> </td>
                <td>
                    <a v-for="file in this.$store.state.s_private.fileDetail " :key="file.boardSeq" :href="file.url" download>  {{file.fileName}} ||</a>
                </td>
                </tr>
            </tbody>
          </table>
      </div>

      <div class="groupName">
        <el-button  plain @click="showList" round>돌아가기</el-button>
        <el-button type="success" plain  @click="boardOneUpdate" round>수정하기</el-button>
        <el-button type="warning" plain  @click="boardOneDelete" round>삭제하기</el-button>
      </div>
  </div>
</template>

<script>
import logincss from '@/assets/css/member/myinfo.css'
export default {
  data(){
    return{
    
      }
  },
  methods:{
      showList(){
          this.$store.state.s_private.detail = false
          this.$store.state.s_private.update = false
          this.$store.state.s_private.list = true
          
      },
      boardOneUpdate(){
          this.$store.state.s_private.detail = false
          this.$store.state.s_private.update =true
      },
      boardOneDelete(){
        
         axios.get('http://localhost:9000/memberBoardDel', { params:{ boardSeq: this.$store.state.s_private.boardDetail.boardSeq} })
         .then(res => {
            this.$store.state.s_private.BoardData = res.data
            this.$store.state.s_private.detail = false
            var loginData = sessionStorage.getItem("loginUser");
            var login = JSON.parse(loginData); 
            var memSeq = login.memberSeq 
            this.$emit('delete', memSeq )
           }) 
      }

  },


}
</script>

<style scoped>

.group_detail{
    width: 950px;
    margin: auto;
}

.groupDetailcCntainer {
    position: relative;
    margin: auto;
    overflow: hidden;
    height: auto;
   
}

.groupImage img{
    display: table;
    margin: auto;
    height: 250px;
}

.groupName{
    text-align: center;
    padding: 10px;
}

.groupdetailInfo > h5{
    color: #727272;
}
.groupdetailInfo{
    padding: 20px 10px 0px 55px;
    width: 600px;
    height: 500px;
    background-color: #f9f9f9;
    margin-left: 196px
}
.title{
    float: left;
}
.groupdetailInfo .title{
    float: left;
}

</style>