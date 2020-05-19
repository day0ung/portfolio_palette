<template>
  <div>
    <div class="commentsList"  v-loading="loading">
      <ul>
        <li v-for="comment in this.$store.state.s_notice.anonymousBoardComments" 
            :key="comment.boardCommentSeq"
            :class="comment.depth>0?'depth':''"
            >
          <div v-if="comment.del == 0">
            <div class="report">
              <div v-if="loginSeq == comment.memberSeq">
                <span @click="answerUpdate(comment.boardCommentSeq, comment.content)">수정</span>
                <span @click="answerDelete(comment.boardCommentSeq)" style="margin: 0px 10px">삭제</span>
              </div>
              <div v-else>
                <span @click="answerReport(comment.boardCommentSeq)">신고</span>
              </div>
            </div>
            <div class="memberId">익 명</div>  
            <div class="writeDate">{{comment.writeDate}}</div>
            <div v-if="comment.boardCommentSeq==clicked">
              <div v-show="isShow == 1"> <!-- 답글 -->
                <span class="miniAnswer" @click="answerCancle(comment.boardCommentSeq)">답글 취소</span>
                <div class="content">{{comment.content}}</div>
                <el-input class="answerContent input-with-select" size="medium" style="width:80%"
                          v-model="subContent" placeholder="댓글을 작성해주세요.">
                <el-button size="mini" slot="append" @click="answerInsert(comment.boardCommentSeq)">등록</el-button></el-input>
              </div>
              <div v-show="isShow == 2"> <!-- 수정 -->
                <span class="miniAnswer" @click="answerCancle(comment.boardCommentSeq)">답글</span>
                <div class="content">
                  <el-input size="medium" v-model="answertxt" :value="comment.content" style="width:80%">
                  <el-button size="medium" slot="append" @click="realAnswerUpdate(comment.boardCommentSeq)">수정</el-button>
                  </el-input>
                </div>
              </div>
              <div v-show="isShow == false"><!-- 답글 취소 -->
                <span class="miniAnswer" @click="answer(comment.boardCommentSeq)" >답글</span>
                <div class="content">{{comment.content}}</div>
              </div>
            </div>
            <div v-else>
                <span class="miniAnswer" @click="answer(comment.boardCommentSeq)">답글</span>
                <div class="content">{{comment.content}}</div>
            </div>
          </div>
          <div v-else>
            <p style="color:red; margin-left:20px">-------- 작성자가 삭제한 댓글입니다 --------</p>
          </div>  
          <div class="dotline"></div>

        </li>

      </ul>
    <div class="commentInput">
      <el-input style="width:80%; float:left;" 
        type="textarea"
        :autosize="{ minRows: 2, maxRows: 4}"
        placeholder="댓글을 작성해주세요."
        v-model="content">
      </el-input>
      <el-button @click="insertComment" style="margin:10px">댓글 달기</el-button>
    </div>
    </div>

  </div>
</template>

<script>
import { loading } from 'element-ui';

export default {
name: 'Comment',
 data() {
    return {
      answertxt:'',
      updateStatus:0,
      clicked:-1,
      isShow:false,
      loginSeq:'',
      content: '',
      subContent:'',
      loading:false,
    }
  },
  methods:{
    getComments(){
        this.loading = true
        //this.boardSeq = this.$store.state.s_group.groupBoardDetail.boardSeq
        var params = new URLSearchParams();
        params.append('boardSeq', this.$store.state.s_notice.boardSeq);
        axios.post("http://localhost:9000/noticeComments", params).then(res => { 
        this.$store.state.s_notice.anonymousBoardComments = res.data
        this.loading = false
      })
    },
    insertComment(){
      alert("id:"+this.loginSeq + "/ boardSeq:"+  this.$store.state.s_notice.boardSeq +"/ content: "+this.content)
      //this.boardSeq = this.$store.state.s_group.groupBoardDetail.boardSeq
      var params = new URLSearchParams();	// post 방식으로 받아야함.
      params.append('memberSeq', this.loginSeq);
      params.append('boardSeq', this.$store.state.s_notice.boardSeq);
      params.append('content', this.content);
      axios.post("http://localhost:9000/noticeInsertComment", params)
              .then(res => {
                //alert("답글달기")
                this.content = ""
                this.getComments()
              })
    },
    answer(boardCommentSeq){
      this.subContent=""
      this.isShow = 1
      this.clicked = boardCommentSeq
      //alert(boardCommentSeq)

    },
    answerCancle(boardCommentSeq){
      this.isShow = false
      this.clicked = boardCommentSeq
      //alert(boardCommentSeq)
    },
    answerUpdate(boardCommentSeq, content){
      //alert(boardCommentSeq+"/update")
      this.answertxt= content
        if(this.isShow==2){
          this.isShow = false
        }else{
          this.isShow = 2
        }
          this.clicked=boardCommentSeq
    },
    realAnswerUpdate(boardCommentSeq){
      //alert(boardCommentSeq +"/"+this.answertxt)
      var params = new URLSearchParams();
      params.append('boardCommentSeq', boardCommentSeq);
      params.append('content', this.answertxt);
      axios.post("http://localhost:9000/noticeRealAnswerUpdate", params).then(res => { 
        this.$message({ type: 'success', message:'update completed' });
        //alert("수정완료")
          this.getComments()
          this.isShow = false
          this.clicked = -1
      })
    },
    answerDelete(boardCommentSeq){
      //alert(boardCommentSeq+"/delete")
        var params = new URLSearchParams();
        params.append('boardCommentSeq', boardCommentSeq);
        axios.post("http://localhost:9000/noticeAnswerDelete", params).then(res => { 
          this.$message({ type: 'success', message:'delete completed' });
          this.getComments()
      })
    },
    answerReport(boardCommentSeq){
      alert(boardCommentSeq+"/report")
    },
    answerInsert(boardCommentSeq){
      //alert(boardCommentSeq+"/"+this.subContent)
      var params = new URLSearchParams();
      params.append('memberSeq', this.$store.state.loginUser.memberSeq)
      params.append('boardCommentSeq', boardCommentSeq);
      params.append('boardSeq', this.$store.state.s_notice.boardSeq);
      params.append('content', this.subContent);
      axios.post("http://localhost:9000/noticeAnswerInsert", params).then(res => { 
          this.getComments()
          this.isShow = false
          this.clicked = -1
      })
    }

   
  },
  mounted(){
    this.loginSeq = this.$store.state.loginUser.memberSeq
    this.getComments() 
   
    
  }
}
</script>

<style scoped>
.commentsList{
  background-color: #f7f7f7;
  margin: 30px;
  padding: 30px;
}
.memberId {
  font-weight: bold;
  float: left;
  margin-left: 20px;
}

.writeDate{
width: 100px;
float: left;
font-size: 10px;
margin-left: 70px;
margin-bottom: 10px;
color: darkgray;
}

.content{
  
  margin: 20px 20px 0px 20px;
  /* margin-bottom: 20px; */
}

.dotline{
  height: 1px;
  overflow: hidden;
  font: 0/0 arial;
  border-bottom-width: 1px;
  border-bottom-style: dotted;
  margin-top: 20px;
  margin-bottom: 20px;
}
.report{
  float: right;
  margin-bottom: 20px;
  margin-right: 20px;
  font-size: 12px
}

span{
  cursor: pointer;
}

span.miniAnswer {
  padding-left: 10px;
  background: url(https://cafe.pstatic.net/cafe4/bu_arr.png) no-repeat 0 0;
}
.answerContent{
  width:80%;
  margin:10px 0px 0px 40px;
  padding-left: 10px; 
  background: url(https://cafe.pstatic.net/cafe4/ico_comm_re2.gif) 0 13px no-repeat
}

.depth{
  padding-left: 10px; 
  background: url(https://cafe.pstatic.net/cafe4/ico_comm_re2.gif) 0 13px no-repeat;
  margin-left: 50px;
}

.dotline > .depth{
  height: 90px;
}
</style>