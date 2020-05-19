<template>
  <div class="reference_detail">
    <br>
    <br>
    <div class="groupDetailcCntainer" v-loading="this.$store.state.s_group.showReferenceDetail">
      <div class="groupName">
        <h4>{{ this.$store.state.s_group.groupReferenceDetail.title }}</h4>
      </div>
      <div class="hr"></div>
      <div class="groupdetailInfo">
        <div><span>작성자</span> {{ this.$store.state.s_group.groupReferenceDetail.memberSeq }}</div>
        <div><span>작성일</span> {{ this.$store.state.s_group.groupReferenceDetail.writeDate }}</div>
        <div><span>조회수</span> {{ this.$store.state.s_group.groupReferenceDetail.readCount }}</div>
        <div><span>파일</span> <a class="el-icon-download" v-for="file in this.$store.state.s_group.groupReferenceDetailFileList" :key="file.boardSeq" :href="file.url" download>{{file.fileName}}</a></div>
      </div>
      <div class="hr"></div>
      
      <div class="groupInfoContent">
      <div><span>파일정보</span>{{ this.$store.state.s_group.groupReferenceDetail.content }}</div>
      </div>
        
      
      <div class="hr"></div>

      <div class="groupName">
        <el-button type="primary" @click="showGroupReference" round>돌아가기</el-button>
        <el-button type="primary" v-show="this.$store.state.s_group.showUpdateBtn" @click="referenceUpdate" round>수정하기</el-button>
        <el-button type="primary" v-show="this.$store.state.s_group.showDeleteBtn" @click="referenceDelete" round>삭제하기</el-button>
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
        boardSeq: ""
      }
  },
  methods:{
    getList(){
      this.$store.state.s_group.showGroupReferenceList = true
      var params = new URLSearchParams()
      params.append('groupSeq', this.$store.state.s_group.groupSeq);
      axios.post("http://localhost:9000/groupPdsList", params)
                  .then(res => {
              this.$store.state.s_group.groupReferenceList = res.data
              this.$store.state.s_group.showGroupReferenceList = false
            })
    },
    showGroupReference(){
      this.getList()
      this.$emit("showGroupReference")
    },
    showGroupReferenceWrive(){
      this.getList()
      this.$emit("showGroupReference")
    },
    referenceUpdate(){
      this.$emit("showGroupReferenceUpdate")
    },
    referenceDelete(){
      var params = new URLSearchParams()
      params.append('boardSeq', this.$store.state.s_group.groupReferenceDetail.boardSeq);
      axios.post("http://localhost:9000/groupReferenceDelete", params).then(res => {
        this.$message({ type: 'success', message:'성공적으로 삭제되었습니다' });
        //alert("자료가 삭제되었습니다.")
        this.showGroupReference()
      })
    },
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
    margin: 14px 0px 20px 0px;
    
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

a{
  
  margin-left: 10px;
  margin-right: 20px;
}
</style>