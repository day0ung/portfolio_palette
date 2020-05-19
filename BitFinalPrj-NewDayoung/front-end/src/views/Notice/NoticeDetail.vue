<template>
  <div class="boardDetail">
    <br>
    <br>
    <div class="groupDetailcCntainer" v-loading="loading">
      <div class="groupName">
        <h4>{{ boardOne.title }}</h4>
      </div>
      <div class="hr"></div>
      <div class="groupdetailInfo">
        <div><span>작성일</span> {{ boardOne.writeDate }}</div>
        <!-- <div><span>작성자</span> {{ this.$store.state.s_group.groupBoardDetail.memberSeq }}</div> -->
        <div><span>조회수</span> {{ boardOne.readCount }}</div>
      </div>
      <div class="hr"></div>
      <div class="groupInfoContent" v-html="boardOne.content"></div>
      <div class="hr"></div>
   
    
      <div class="buttons">
        <el-button type="primary" @click="showList" round>돌아가기</el-button>
        <el-button v-if="boardOne.memberSeq == loginSeq" type="primary" @click="boardOneUpdate" round >수정하기</el-button>
        <el-button v-if="boardOne.memberSeq == loginSeq" type="primary" @click="boardOneDelete" round >삭제하기</el-button>
      </div>

      <div class="comment">
        <Comments></Comments>
      </div>

    </div>
    <br>
    <br>
  </div>
</template>

<script>
import Comments from '@/views/Notice/Notice_comment.vue';
import { loading } from 'element-ui';
export default {
components: { Comments },
data(){
        return{
            loading:false,
            boardOne: "",
            boardSeq: 0,
            loginSeq:0,
        }
    },
    methods:{
        showList(){
           this.$router.push({ name : "Notice" })
        },
        boardOneUpdate(){
          
        },
        boardOneDelete(){
          var params = new URLSearchParams();	// post 방식으로 받아야함.
          params.append('boardSeq', this.boardSeq);
          axios.post("http://localhost:9000/anonymousBoardDelete", params)
                  .then(res => {
                    this.$message({ type: 'success', message:'게시글이 정상적으로 삭제 되었습니다.'})
                    //alert("게시글이 정상적으로 삭제 되었습니다.")
                    this.$router.push({ name : "Notice" })
          })
        },

  
    },
    mounted(){
        this.loading = true;
        this.loginSeq = this.$store.state.loginUser.memberSeq
        this.boardSeq = this.$route.params.ContentId
        var params = new URLSearchParams();
        params.append('boardSeq', this.boardSeq);
        axios.post("http://localhost:9000/anonymousBoardDetail", params)
                .then(res => {
            this.boardOne = res.data
            this.loading = false;
          })
        this.$store.state.s_notice.boardSeq = this.boardSeq
        axios.post("http://localhost:9000/noticeComments", params)
                .then(res => {
            this.$store.state.s_notice.anonymousBoardComments = res.data
            this.loading = false;
          })

   

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
.buttons{
  text-align: center;
}
.groupInfoTitle{
    float: left;
    padding: 60px 0px 0px 100px;
}
.groupInfoTitle > h5 > span{
    color: #727272;
}
.groupdetailInfo{
    padding: 0px 10px 0px 100px;
    width: 600px;
    text-align: initial;
}
.groupdetailInfo > div{
    float: left;
    margin: 14px 0px;
    margin-right: 50px;
    line-height: 24px;
}
.groupdetailInfo > div > span{
    margin-right: 10px;
    color: #727272;
}
.groupInfoContent{
    /* float: left; */
    padding: 10px 100px 60px 100px;
    width: auto;
    text-align: initial;
}
.groupInfoContent > div{
    float: left;
    margin: 14px 0px 50px 0px;
    line-height: 24px;
}

.groupInfoContent > div > span{
    margin-right: 10px;
    color: #727272;
}

.hr{
    display: flex;
    margin: auto;
    margin-top: 5px;
    margin-bottom: 5px;
    background: #c1c1c1;
    height: 1px;
    width: 80%;
}

.schedules {
    display: grid;
    color: #4e4e4e;
    grid-template-columns: repeat(auto-fill, 30px);
    grid-gap: 4px;
}
.schedule {
    padding: 8px;
    border: 1px solid #4e4e4e;
    font-size: 0.7em;
    text-align: center;
}
.schedule_check{
    padding: 8px;
    border: 1px solid #4e4e4e;
    font-size: 0.7em;
    text-align: center;
    background: #FF6C00;
    color: #f5f5f5;
    transition: all 0.4s ease-in-out;
}

</style>