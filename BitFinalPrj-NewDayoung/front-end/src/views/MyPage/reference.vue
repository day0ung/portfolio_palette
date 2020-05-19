<template>
  <div class="reference">
        <div class="slib">
        <div class="slib_info">
          <div class="tit">
            <img src='../../assets/css/images/MypageReference.png'>
          </div>
          <div class="titup">
              <table class="table1" style="margin-left: 55px" v-loading="this.$store.state.s_member.MyPageInterLikeListLoading">
                <colgroup>
              <col style="width: 270px">
              <col style="width: 30px">
              <col style="width: 50px">
              </colgroup>
                <thead>
                  <tr>
                    <th style="text-align: center;">내가올린 자료</th>
                    <th></th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="reference in this.$store.state.s_group.MypageReferenceList" :key="reference.boardSeq">
                    <td colspan="3">{{reference.fileName}}</td>
                    <td><i class="el-icon-paperclip" style="color: #ff5151"></i> </td>
                    <td style="width: 100px; text-align: left;">
                      <el-button type="text" style="color: #ff5151; font-size: 16px" @click="referenceDown(reference.url)">다운로드</el-button>
                    </td>
                  </tr>                   
                </tbody>
              </table>
              <table class="table1" v-if="addr">
                <tbody>
                  <tr>
                        <el-button type="info" plain style="width: 100px; padding: 11px;" @click="execDaumPostcode">우편번호 찾기</el-button>
                  </tr>
                  <tr>
                    <td>
                      <el-input  placeholder="주소" readonly="readonly" v-model="address"></el-input>
                    </td>
                    <td>
                      <el-input  placeholder="상세주소" style="width: 300px" v-model="extraAddress"></el-input>
                    </td>
                    <td style="width: 14px"></td>
                    <td><i class="el-icon-s-tools" style="color: #d77f4a"></i> </td>
                    <td>
                      <el-button type="text" @click="editArea" style="color: #d77f4a; font-size: 16px">수정하기</el-button>
                    </td>
                  </tr>                   
                </tbody>
              </table>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
    data(){
        return{

        }
    },
    methods:{
        referenceDown(url){
            const link = document.createElement("a");
            link.href = url;
            link.download = "referenceDown";
            link.click();
        }
    },
    mounted(){
        let params = new URLSearchParams();
        params.append("memberSeq", this.$store.state.loginUser.memberSeq)
        axios.post("http://localhost:9000/MypageReferenceList", params).then(res => {
            this.$store.state.s_group.MypageReferenceList = res.data
        })
    }
}
</script>



<style>

</style>