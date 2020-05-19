<script>
//Importing Bar class from the vue-chartjs wrapper
import {Doughnut} from 'vue-chartjs'
import randomColor from 'randomcolor'
//Exporting this so it can be used in other components
export default {
  extends: Doughnut,
  data() {
    return { 
      datacollection: {
        labels: ['1','1','1','1','1','1'],
        datasets: [
          {
            backgroundColor: [randomColor(), randomColor(), randomColor(), randomColor(),  randomColor(),  randomColor()],
            data: [1,1,1,1,1,1]
          }
        ]
      },
      options:{
         scales: {
          // yAxes: [{
          //   ticks: {
          //     beginAtZero: true
          //   },
          //   gridLines: {
          //     display: true
          //   }
          // }],
          // xAxes: [ {
          //   gridLines: {
          //     display: false
          //   }
          // }]
        },
        legend: {
            // display: true
          },
        // responsive: true,
        maintainAspectRatio: false,

        //add option for click
        // responsive: true, 
        // maintainAspectRatio: false, 
        onClick:this.handle
        //add option for click
        
      }
     }
  },
  mounted() {
    //renderChart function renders the chart with the datacollection and options object.
    this.renderChart(this.datacollection, this.options);
    this.getDataMethod();

  },



  methods : {
    getDataMethod () {  //axios process
        //getMemberGenderInfo
      axios.post("http://localhost:9000/getGroupInfo_InterSmallCount")
      .then(res=>{
        console.log("소분류 내용 출력 시작");
        var dataRes = res.data
          console.log(dataRes)


        console.log("소분류 내용 출력끝. ");



       
        this.receiveDataSetting(dataRes);
        this.loading=false;
      }).catch(error=>{
        // this.loading=true;
        console.log("오류발생! 오류내용 : "+error)
        // this.loading=false;
      })
     
    },
      receiveDataSetting(dataRes){
        var pushlabel = new Array();
        var pushdata = new Array();
        var backgroundColor = new Array();
        // pushlabel = JSON.stringify(pushlabel);
        // pushdata = JSON.stringify(pushdata);
        // backgroundColor = JSON.stringify(backgroundColor);
        

        var labelsArea = this.datacollection.labels; //표시된칼럼명
        var dataArea = this.datacollection.datasets[0].data;//데이터 카운트숫자
        var colorSet = this.datacollection.datasets[0].backgroundColor;//데이터 카운트숫자

        console.log("====================================111111111111")
        console.log(labelsArea);
        console.log(dataArea);
        console.log(colorSet);
        console.log("====================================111111111111")


        //기존 저장된 데이터 초기화 
        // this.labels=[];
        // this.datacollection.labels=[];
        // this.data=[];
        // this.datacollection.datasets[0].data=[];


        // console.log("receiveDataSetting 내용 ");
        // const receiveLabel = dataRes[0][0].SMALL_NAME;
        // console.log(receiveLabel);
        // const receiveSmallTotalCount = dataRes[0][0].smallTotal;
        // console.log(receiveSmallTotalCount);

        //받은 명칭 push
        for(var i=0; i<dataRes[0].length; i++){
          pushlabel.push(dataRes[0][i].SMALL_NAME);
          pushdata.push(dataRes[0][i].smallTotal);
          backgroundColor.push(randomColor());
        }
        console.log(pushlabel);
        console.log(pushdata);
        console.log(backgroundColor);

        //pushdata 적용
        this.datacollection.labels = pushlabel;
        this.datacollection.datasets[0].data = pushdata;
        this.datacollection.datasets[0].backgroundColor = backgroundColor;

        console.log("====================================22222222222222")
        console.log(this.datacollection.labels );
        console.log(this.datacollection.datasets[0].data );
        console.log(this.datacollection.datasets[0].backgroundColor );
        console.log("====================================22222222222222")

         this.$data._chart.update();

      },

      handle (point, event) {
    	  const item = event[0]
    	  this.$emit('on-receive', {
      	// index: item._index,
        backgroundColor: item._view.backgroundColor,
        value: this.datacollection.datasets[0].data[item._index]
        })
      },
      updateValue(data){
        console.log(data);
      }
  }




}

</script>
<style scoped>


</style>