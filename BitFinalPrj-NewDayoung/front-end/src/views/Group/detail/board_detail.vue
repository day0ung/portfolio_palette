<template>
  <div class="boardDetail">
    <br>
    <br>
    <div class="groupDetailcCntainer" v-loading="this.$store.state.s_group.showBoardDetail">
      <div class="groupName">
        <h4>{{ this.$store.state.s_group.groupBoardDetail.title }}</h4>
      </div>
      <div class="hr"></div>
      <div class="groupdetailInfo">
        <div><span>작성일</span> {{ this.$store.state.s_group.groupBoardDetail.writeDate }}</div>
        <div><span>작성자</span> {{ this.$store.state.s_group.groupBoardDetail.memberSeq }}</div>
        <div><span>조회수</span> {{ this.$store.state.s_group.groupBoardDetail.readCount }}</div>
      </div>
      <div class="hr"></div>
      <div class="groupInfoContent" v-html="this.$store.state.s_group.groupBoardDetail.content"></div>
      <div class="hr"></div>
   
    
      
      <div class="groupName">
        <el-button type="primary" @click="showList" round>돌아가기</el-button>
        <el-button type="primary" v-show="this.$store.state.s_group.showUpdateBtn" @click="boardOneUpdate" round>수정하기</el-button>
        <el-button type="primary" v-show="this.$store.state.s_group.showDeleteBtn" @click="boardOneDelete" round>삭제하기</el-button>
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
import Comments from '@/views/Group/detail/board_comment.vue';
import { loading } from 'element-ui';
export default {
components: { Comments },
data(){
        return{
            boardOne: "",
            boardSeq: 0,
        }
    },
    methods:{
        showList(){
          this.allList()
          this.$emit("showBoard")
        },
        boardOneUpdate(){
          this.$emit("showUpdate")
        },
        boardOneDelete(){
          this.$confirm('정말 삭제하시겠습니까?', 'Warning', {
            confirmButtonText: 'OK',
            cancelButtonText: 'Cancel',
            type: 'warning'
          }).then(() => {
            var params = new URLSearchParams();	// post 방식으로 받아야함.
            params.append('boardSeq', this.$store.state.s_group.groupBoardDetail.boardSeq);
            axios.post("http://localhost:9000/groupBoardDelete", params)
                    .then(res => {
                      this.$message({ type: 'success', message:' Delete completed' });
                      this.showList()
            })
              }).catch(() => {
            this.$message({
              type: 'info',
              message: 'Delete canceled'
            });          
          });
        },
        allList(){
          this.$store.state.s_group.showBoardList = true
          this.$store.state.s_group.s_keyWord=''
          this.$store.state.s_group.searchWord=''
          var params = new URLSearchParams();
          params.append('page', 1);
          params.append('limit', this.$store.state.s_group.listQuery.limit);
          params.append('groupSeq', this.$store.state.s_group.groupSeq);
          params.append('keyWord', this.$store.state.s_group.s_keyWord);
          params.append('searchWord', this.$store.state.s_group.searchWord);
          axios.post("http://localhost:9000/groupPagingList", params)
                    .then(res => {
                this.$store.state.s_group.groupBoardList = res.data
                this.getTotal()
                this.$store.state.s_group.showBoardList = false
              })
        },
        getTotal(){
                var params = new URLSearchParams()
                params.append('groupSeq', this.$store.state.s_group.groupSeq);
                params.append('keyWord', this.$store.state.s_group.s_keyWord);
                params.append('searchWord', this.$store.state.s_group.searchWord);
                axios.post("http://localhost:9000/groupBoardTotal", params)
                      .then(res => {
                        this.$store.state.s_group.total = res.data
                      })
        }
    },
    mounted(){

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