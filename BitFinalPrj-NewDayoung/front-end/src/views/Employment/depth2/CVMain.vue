<template>
  <div class="CV">
    <div v-if="login1 == null">로그인 후에 사용가능합니다.</div>
    <!-- 일반 회원 / CV 없을 때 -->
    <div v-else-if="(login1.auth === 0 | login1.auth === 1) & this.$store.state.s_employment.oneMember.cv === 0" class="writeNewCV">
    <div class="slib">
      <div class="slib_info">
        <div class="tit">
          <img src='@/assets/css/images/resume.png'>
        </div>
        <div class="titup">
          <table class="table1" style="margin-left: 55px">
            <colgroup>
              <col style="width: 600px">
              
            </colgroup>
            <thead>
              <tr>
                <th>이력서 샘플</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td><a href="https://bit-palette.s3.ap-northeast-2.amazonaws.com/112/%E1%84%8B%E1%85%B5%E1%84%85%E1%85%A7%E1%86%A8%E1%84%89%E1%85%A5_%E1%84%8C%E1%85%A1%E1%84%89%E1%85%A9%E1%84%89%E1%85%A5%20%E1%84%89%E1%85%A2%E1%86%B7%E1%84%91%E1%85%B3%E1%86%AF-2020-05-01T12%3A39%3A38.108.hwp" download>이력서 샘플 다운로드</a></td>
                <td>{{category}}</td>
              </tr>
              <br><br>
              <tr>
                <td colspan="2">
                  <el-button type="text" @click="writeCV" style="color: #ff5151; font-size: 16px">내 이력서 작성하기</el-button>
                </td>
              </tr>               
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
    <!-- <div v-else-if="(login1.auth === 0 | login1.auth === 1) & this.$store.state.s_employment.oneMember.cv === 0" class="writeNewCV">
      <div class="cvCntainer">
        <div class="cvInfoTitle">
          <h5>이력서 샘플</h5>
        </div>
        <div class="cvInfoContent" style="text-align:center">
          <a href="https://bit-palette.s3.ap-northeast-2.amazonaws.com/112/%E1%84%8B%E1%85%B5%E1%84%85%E1%85%A7%E1%86%A8%E1%84%89%E1%85%A5_%E1%84%8C%E1%85%A1%E1%84%89%E1%85%A9%E1%84%89%E1%85%A5%20%E1%84%89%E1%85%A2%E1%86%B7%E1%84%91%E1%85%B3%E1%86%AF-2020-05-01T12%3A39%3A38.108.hwp" download>이력서 샘플</a>
        </div>
      </div>
      <div class="hr"></div>
      <el-button type="primary" round @click="writeCV">새로운 이력서 업로드하기</el-button>
    </div> -->

    <!-- 일반 회원 / CV 있을 때 -->
    <div v-else-if="(login1.auth === 0 | login1.auth === 1) & this.$store.state.s_employment.oneMember.cv === 1" class="updateCV">
      <div class="slib">
      <div class="slib_info">
        <div class="tit">
          <img src='@/assets/css/images/resume.png'>
        </div>
        <div class="titup">
          <table class="table1" style="margin-left: 55px">
            <colgroup>
            <col style="width: 400px">
            <col style="width: 30%">
            </colgroup>
            <thead>
              <tr>
                <th>제목</th>
                <th>관심분야</th>
                <th></th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>{{this.$store.state.s_employment.cvDetail.title}}</td>
                <td>{{this.$store.state.s_employment.cvDetail.category}}</td>
              </tr>
              <br><br>
              <tr>
                <td colspan="2">
                  <el-button type="text" @click="updateCV" style="color: #ff5151; font-size: 16px">이력서 수정하기</el-button>
                </td>
              </tr>               
            </tbody>
          </table>
        </div>
      </div>
    </div>
    </div>
      <!-- <div class="cvCntainer">
        <div class="cvInfoTitle">
          <h5>내 이력서</h5>
        </div>
        <div class="cvInfoContent" style="text-align:center">
          <a :href="file.url" download>{{file.fileName}}</a>
        </div>
      </div>
      <div class="hr"></div>
      <el-button type="primary" round @click="updateCV">이력서 수정하기</el-button>
    </div> -->
    
    <!-- 기업 회원 -->
    <div v-else class="CVList">
      <div class="boardTableFrom">
      <p>이력서 열람</p>
        <div class="boardSearchBar">
          <el-input
            v-model="searchWord"
            size="large"
            placeholder="전체목록보기버튼">
          <el-button slot="prepend" icon="el-icon-tickets" circle style="margin-right:10px" @click="allList"></el-button>
          <el-select v-model="s_keyWord" slot="prepend" placeholder="Select">
            <el-option label="작성자" value="writer"></el-option>
            <el-option label="제목" value="title"></el-option>
          </el-select>
          <el-button slot="append" icon="el-icon-search" @click="searchBoard"></el-button>
          </el-input>
        </div>
        <el-table 
          v-loading="this.$store.state.s_employment.loadingCVDetail"
          :row-class-name="clickableRows"
          :data="tableData"
          stripe
          style="width: 100% cursor:pointer"
          @row-click="gotoClick"
        >
          <el-table-column
            prop="finalnum"
            label="글번호"
            width="75px">
          </el-table-column>
          <el-table-column
            prop="category"
            label="지원분야"
            width="150px">
          </el-table-column>
          <el-table-column
            prop="title"
            label="글제목"
            width="300px"
            >
          </el-table-column>
          <el-table-column
            prop="memberDto.memberName"
            label="작성자"
            width="150px"
            >
          </el-table-column>
          <el-table-column
            prop="readCount"
            label="조회수"
            width="100px"
            >
          </el-table-column>
          <el-table-column
            prop="writeDate"
            label="작성일"
            width="170px"
            >
          </el-table-column>
        </el-table> <br>
        <div class="pageination">
          <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { loading } from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import Pagination from '@/components/Pagination'
import logincss from '@/assets/css/member/myinfo.css'

export default {
  components: { Pagination },
  data(){
    return{
      tableData: [],
      total: 0,
      listQuery:{
        page: 1,
        limit: 10,
        title: "",
        searchWord: "",
        s_keyWord: ""
      },
      searchWord:'',
      file: '',
      s_keyWord:'',
      loading: true,
      login1 : "",
    }
  },
  methods:{
    updateCV(){
      this.$emit("showCVUpdate")
    },
    writeCV(){
      this.$emit("showCVWriting")
    },
    allList(){
     this.s_keyWord=''
     this.searchWord=''

     this.loading = true
     var params = new URLSearchParams();
     params.append('page', 1);
     params.append('limit', this.listQuery.limit);
     params.append('keyWord', this.s_keyWord);
     params.append('searchWord', this.searchWord);
     axios.post("http://localhost:9000/CVPagingList", params)
              .then(res => {
          this.tableData = res.data
          this.getTotal()
          this.loading = false
        })
    },
    
    getList(){
      this.loading = true
      // listQuery
      var params = new URLSearchParams();	// post 방식으로 받아야함.
      params.append('page', this.listQuery.page);
      params.append('limit', this.listQuery.limit);
      
      params.append('keyWord', this.s_keyWord);
      params.append('searchWord', this.searchWord);
      axios.post("http://localhost:9000/CVPagingList", params)
              .then(res => {
          this.tableData = res.data
          this.loading = false
        })
    },
    searchBoard(){
      if(this.s_keyWord==''){
        this.$message({ type: 'info', message:'검색타입을 설정해주세요' })
        //alert('검색타입을 설정해주세요')
      }
      if(this.searchWord==""){
        this.$message({ type: 'info', message:'검색어를 입력해주세요' })
        //alert('검색어를 입력해주세요')
      }
      
      if(this.s_keyWord != '' && this.searchWord!=''){
        // alert(this.s_keyWord +"/" + this.searchWord)
        this.loading = true
        var params = new URLSearchParams();	// post 방식으로 받아야함.
        params.append('page', 1);
        params.append('limit', this.listQuery.limit);
        
        params.append('keyWord', this.s_keyWord);
        params.append('searchWord', this.searchWord);
        axios.post("http://localhost:9000/CVPagingList", params)
                .then(res => {
            this.tableData = res.data
            this.getTotal()
            this.loading = false
          })
      }
    },
    getTotal(){
      var params = new URLSearchParams()
      params.append('keyWord', this.s_keyWord);
      params.append('searchWord', this.searchWord)
      axios.post("http://localhost:9000/CVList", params)
            .then(res => {
              this.total = res.data
            })
    },
     handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    gotoClick(row, column, event){
      this.$emit("showCVDetail")
      this.$store.state.s_employment.loadingCVDetail = true
      var params = new URLSearchParams();	// post 방식으로 받아야함. 
      params.append('cvSeq', row.cvSeq);
      axios.post("http://localhost:9000/getOneCV", params).then(res => { 
        
        this.$store.state.s_employment.cvDetail = res.data
        this.$store.state.s_employment.loadingCVDetail = false
        
        })

    },
    clickableRows :function (row, rowIndex) {
      //alert(row.rowIndex)
      return "clickableRows";
    }
  },
  mounted(){
    if(this.login1 == null){
      
		} else {
      var params = new URLSearchParams();	// post 방식으로 받아야함. 
      params.append('memberSeq', this.login1.memberSeq);
      axios.post("http://localhost:9000/cvDetailRefByMemberSeq", params).then(res => { 
        this.$store.state.s_employment.cvDetailRef = res.data
        this.file = res.data
      }),
      axios.post("http://localhost:9000/oneMember", params).then(res => { 
        this.$store.state.s_employment.oneMember = res.data
      }),
      axios.post("http://localhost:9000/getOneCVByMemberSeq", params).then(res => { 
        this.$store.state.s_employment.cvDetail = res.data
        this.title = this.$store.state.s_employment.cvDetail.title
        this.category = this.$store.state.s_employment.cvDetail.category
        })

      
    }

    

    
  
  },
  created(){
		let sMemberSeq = sessionStorage.getItem("loginUser")
    this.login1 = JSON.parse(sMemberSeq)
    
    //페이징
      this.getTotal()
      this.getList()

      
    
		// this.memberSeq = this.$store.state.loginUser.memberSeq
  }
}
</script>

<style scoped>

@import url("https://fonts.googleapis.com/css?family=Lato:400,700");
/* center container in the middle */

/* CV 버튼 */
.CV{
  width: 950px;
  margin: auto;
}

.writeNewCV {
  text-align: center;
  padding: 35px;
}

.updateCV{
  text-align: center;
  padding: 35px;
}

/* 테이블 */
.boardTableFrom{
  margin: auto;
}

/* 검색바 */
.boardSearchBar{
  width: 45%;
  float: right;
}

/* 페이징 */
.pageination{
  margin: auto;
  display: table;
}
.el-select {
  width: 100px;
}

.cvCntainer {
    position: relative;
    margin: auto;
    overflow: hidden;
    height: auto;
    /* background: #f7f7f7; */
}

.cvtitle{
    text-align: center;
    padding: 35px;
    
}

.hr{
    display: flex;
    margin: auto;
    margin-top: 15px;
    margin-bottom: 5px;
    background: #c1c1c1;
    height: 1px;
    width: 80%;
}

.cvInfoTitle{
    float: left;
    padding: 60px 0px 0px 100px;
}

.cvInfoContent{
    float: right;
    padding: 60px 100px 60px 0px;
    width: 600px;
    text-align: initial;
}

</style>
