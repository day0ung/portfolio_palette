<template>
        <div class="small">
          <div v-loading="loading">
            <line-chart
             :chart-data="datacollection"></line-chart>
          </div>
            <button @click="fillData()">Randomize</button>
            <div class="content01_view">
                <h1> {{title}} member333333333333333333</h1>
            </div>
        </div>

</template>

<script>
import LineChart from '@/api/LineChart.js'
import { loading } from 'element-ui';

export default {
    components: {
      LineChart
    },
    data () {
      return {
        loading:false,
        datacollection: null,
        title:'statisticsNresearch1',
        getRawData:'',
        setGenderData:[],
        SetMan:[],
        setWoman:[],




        

      }
    },
    // mounted () {
    //   this.fillData()
    //   this.getGenderDataMethod()
    // },
    created(){
      this.fillData()
      this.getGenderDataMethod()
    },
    methods: {
      fillData () {
        this.datacollection = {
          labels: [this.getRandomInt(), this.getRandomInt()],
          datasets: [
            {
              label: '남자',
              backgroundColor: '#f87979',
              data: [this.getRandomInt(), this.getRandomInt()]
            }, {
              label: '여자',
              backgroundColor: '#f87979',
              data: [this.getRandomInt(), this.getRandomInt()]
            }
          ]
        }
      },
      getRandomInt () {
        return Math.floor(Math.random() * (50 - 5 + 1)) + 5
      },
      getGenderDataMethod () {  //axios process

        //getMemberGenderInfo

        

        axios.post("http://localhost:9000/getMemberGenderInfo")
        .then(res=>{
          this.loading=true;
          this.getRawData=res.data;
          console.log("getRawData");
          console.log(this.getRawData);
          console.log("only gender : ");
          console.log(this.getRawData[0]);

          // for(var i=0; i<this.getRawData.length; i++){
          //   console.log("genderdata = "+ i + "["+this.getRawData[i].gender+"]")
          //   // console.log(this.getRawData[i].gender);
          // }

          this.pickGender();

          this.loading=false;
        }).catch(error=>{
          this.loading=true;
          
          console.log("오류발생! 오류내용 : "+error)

          this.loading=false;
        })
      },

      pickGender(){
        this.getRawData;
        this.setGenderData;

        var testList = new Array();

        for(var i=0; i<this.getRawData.length; i++){
          // console.log("genderdata = "+ i + "["+this.getRawData[i].gender+"]")
          testList.push(this.getRawData[i].gender);
        }

        var testListToJsonData = JSON.stringify(testList);
        console.log("testList : ");
        console.log(testList);
        console.log("json.stringify : ");
        console.log(testListToJsonData);
      }


    }

}
</script>

<style>

 .small {
    max-width: 600px;
    margin:  150px auto;
  }

</style>