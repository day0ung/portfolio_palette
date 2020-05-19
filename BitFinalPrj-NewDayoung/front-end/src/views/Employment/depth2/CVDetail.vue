<template>
  <div>
    <div class="cvCntainer" v-loading="this.$store.state.s_employment.loadingCVDetail">
      <div class="cvtitle">
        <h1>{{ this.$store.state.s_employment.cvDetail.title }}</h1>
      </div>
      <div class="hr"></div>
      <div class="cvInfoTitle">
        <h5>이름</h5>
      </div>
      <div class="cvInfoContent">
        <div> {{ this.$store.state.s_employment.cvDetail.memberDto.memberName }} </div>
      </div>
      <div class="hr"></div>
      <div class="cvInfoTitle">
        <h5>지원분야</h5>
      </div>
      <div class="cvInfoContent">
        <div> {{ this.$store.state.s_employment.cvDetail.category }} </div>
      </div>
      <div class="hr"></div>
      <div class="cvInfoTitle">
        <h5>이력서</h5>
      </div>
      <div class="cvInfoContent">
        <div>
          <a :href="file.url" download>{{file.fileName}}</a>
        </div>
      </div>
      <div class="hr"></div>
      <div style="text-align:center">
        <el-button type="primary" @click="cvMain" round>돌아가기</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { loading } from 'element-ui';
export default {
  data(){
    return{
      file : "",
      
      
    }
  },
  mounted(){
    var params = new URLSearchParams();	// post 방식으로 받아야함. 
    let seq = this.$store.state.s_employment.cvDetail.cvSeq
    //alert(seq)
      params.append('cvSeq', seq);
      axios.post("http://localhost:9000/cvDetailRef", params).then(res => { 
        this.$store.state.s_employment.cvDetailRef = res.data
        this.file = res.data
      })
  },
  methods:{
    cvMain(){
      this.$emit("showCVMain")
      
    }
  }
}
</script>

<style scoped>
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