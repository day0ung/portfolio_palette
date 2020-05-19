<template>
  <div class="showMemberDetailFrom">
    <el-form ref="form" :model="showAllMember" label-width="120px">
      

      <!-- <el-form-item label="memberSeq">
        <ul>
          <li>
            <div> {{showAllMember.memberSeq}} 을 </div>
            <div style="width:100px;">
              <el-input v-model="showAllMember.memberSeq"></el-input>
            </div>
            <div> 으로 변경 </div>
          </li>
        </ul>
      </el-form-item> -->
      
      <el-form-item label="회원번호">
        <div class="width80per">
          <el-input 
            v-loading="loading"
            v-model="showAllMember.memberSeq"></el-input>
          </div>
      </el-form-item>

      <el-form-item label="회원ID">
        <div class="width80per">
          <el-input 
          v-loading="loading"
          v-model="showAllMember.memberId"></el-input>
        </div>
      </el-form-item>

      <el-form-item
        v-loading="loading"
        label="회원명">
        <div class="width80per">
          <el-input v-model="showAllMember.memberName"></el-input>
        </div>
      </el-form-item>

      <el-form-item 
        v-loading="loading"
        label="회원주소">
        <div class="width80per">
          <el-input v-model="showAllMember.address"></el-input>
        </div>
      </el-form-item>


      <el-form-item label="email">
        <div class="width80per">
          <el-input 
            v-loading="loading"
            v-model="showAllMember.email"></el-input>
          </div>
      </el-form-item>

      <el-form-item label="나이">
        <div class="width80per">
          <el-input 
            v-loading="loading"
            v-model="showAllMember.age"></el-input>
          </div>
      </el-form-item>

      <el-form-item label="성별">
        <div class="width80per">
          <el-input 
            v-loading="loading"
            v-model="showAllMember.gender"></el-input>
          </div>
      </el-form-item>

      <el-form-item label="적립금">
        <div class="width80per">
          <el-input 
          v-loading="loading"
          v-model="showAllMember.myMoney"></el-input>
        </div>
      </el-form-item>

      <el-form-item label="이력서여부">
        <div class="width80per">
          <el-input 
          v-loading="loading"
          v-model="showAllMember.cv"></el-input>  
        </div>
      </el-form-item>

      <el-form-item label="권한">
        <div class="width80per">
          <el-input 
          v-loading="loading"
          v-model="showAllMember.auth"></el-input>
        </div>
      </el-form-item>

      <el-form-item label="탈퇴여부">
        <div class="width80per">
          <el-input 
          v-loading="loading"
          v-model="showAllMember.del"></el-input>
        </div>
      </el-form-item>

      <el-form-item label="dislike">
        <div class="width80per">
          <el-input 
          v-loading="loading"
          v-model="showAllMember.dislike"></el-input>
        </div>
      </el-form-item>


    <el-form-item>
      <!-- <el-button type="primary" @click="onSubmit">Create</el-button> -->
      <el-button @click="gobacksite">돌아가기</el-button>
    </el-form-item>

    </el-form>
  </div>
</template>

<script scoped>
import 'element-ui/lib/theme-chalk/index.css';
import { loading } from 'element-ui';
  export default {
    data() {
      return {

        showAllMember:[],
        form:[],
        loading:true
      }
    },
    created(){
      this.showMemberList();
      
    },
    methods: {
      // axios data processing
      showMemberList(){
        const getThisParam = this.$route.params.memid;
        var params = new URLSearchParams();
        params.append('memberId', getThisParam);
          axios.post("http://localhost:9000/TestAdminChangeDetail", params)
              .then(res => {
                this.loading=true
                console.log("디테일 출력 부분입니다. ")
                console.log(this.$route.params);
              console.log(JSON.stringify(res.data))
              console.log("디테일 출력 끝.  ")
              console.log(res.data)
              this.loading=false
              this.showAllMember=res.data;
              //attatch data source
              // this.form = this.showAllMember;

              // console.log("여기부터 다시시작 ")
              // console.log(this.form);
              
          })
      },


       //submit process 
      onSubmit() {
        console.log('submit!');
        //axios start
      },

      gobacksite(){
        this.$router.push('/managemember1')
        
      }


    }
  }
</script>

<style>
.width80per{
  width:80%;

}

</style>


