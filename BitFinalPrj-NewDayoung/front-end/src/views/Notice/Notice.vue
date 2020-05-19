<template>
  <div class="groupboard">
    <br>
    <br>
    <div class="boardTableFrom">
      <h3>익명게시판</h3>
      <div class="boardSearchBar">
        <el-input
          v-model="searchWord"
          size="large"
          placeholder="검색">
          <el-button slot="prepend" icon="el-icon-tickets" circle style="margin-right:10px" @click="allList"></el-button>
          <el-select v-model="s_keyWord" slot="prepend" placeholder="제목" >
            <!-- <el-option label="작성자" value="writer"></el-option> -->
            <el-option label="제목" value="title"></el-option>
          </el-select>
          <el-button slot="append" icon="el-icon-search" @click="searchBoard"></el-button>
        </el-input>
      </div>
      <el-table 
          :row-class-name="clickableRows"
          :data="tableData"
          stripe
          style="width: 100%; cursor:pointer"
          @row-click="gotoClick"
          v-loading="loading"
      >
        <el-table-column
          prop="sfinalnum"
          label="글번호"
          width="150px">
        </el-table-column>
        <el-table-column
          prop="title"
          label="글제목"
          width="550px"
          >
        </el-table-column>
        <!-- <el-table-column
          prop="memberDto.memberName"
          label="작성자"
          width="150px"
          >
        </el-table-column> -->
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
      <div class="writeNewAnonymousBoard">
        <el-button type="primary" round @click="writeAnonymousBoard">글 쓰기</el-button>
      </div>
    </div>
  </div>
  
</template>

<script>
import { loading } from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import Pagination from '@/components/Pagination'

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
        s_keyWord: "title"
      },
      searchWord:'',
      
      s_keyWord:'',
      loading: false,
      login1 : "",
    }
  },
  methods:{
    updateAnonymousBoard(){
      this.$emit("showAnonymousBoardUpdate")
    },
    writeAnonymousBoard(){
      this.$router.push({
        name : "noticeWriting"
      })
    },
    allList(){
     
     this.searchWord=''

     this.loading = true
     var params = new URLSearchParams();
     params.append('page', this.listQuery.page);
     params.append('limit', this.listQuery.limit);
     params.append('keyWord', this.s_keyWord);
     params.append('searchWord', this.searchWord);
     axios.post("http://localhost:9000/anonymousBoardPagingList", params)
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
      axios.post("http://localhost:9000/anonymousBoardPagingList", params)
              .then(res => {
          this.tableData = res.data
          this.loading = false
        })
    },
    searchBoard(){
      if(this.s_keyWord==''){
        this.s_keyWord = 'title'
      }
      if(this.searchWord==""){
        this.$message({ type: 'info', message:'검색어를 입력해주세요'})
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
        axios.post("http://localhost:9000/anonymousBoardPagingList", params)
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
      axios.post("http://localhost:9000/anonymousBoardList", params)
            .then(res => {
              this.total = res.data
            })
    },
     handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    gotoClick(row, column, event){
      //alert(row.boardSeq)
      this.$store.state.s_notice.boardSeq = row.boardSeq
      this.$router.push('/Notice/detail/'+row.boardSeq)
      //this.$router.push('/group/menu/'+groupSeq)
    },
    clickableRows :function (row, rowIndex) {
      //alert(row.rowIndex)
      return "clickableRows";
    }
  },
  mounted(){
 
    
  
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

.writeNewAnonymousBoard {
  text-align: center;
  padding: 0px;
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

</style>

