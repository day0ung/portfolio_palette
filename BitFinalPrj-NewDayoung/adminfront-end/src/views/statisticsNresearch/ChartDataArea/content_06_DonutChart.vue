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
        labels: ['', '', '', ''],
        datasets: [
          {
            backgroundColor: [randomColor(), randomColor(), randomColor(), randomColor()],
            data: [0,0,0,1]
          }
        ]
      },
      options:{
         scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true
            },
            gridLines: {
              display: true
            }
          }],
          xAxes: [ {
            gridLines: {
              display: false
            }
          }]
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
    // this.getGenderDataMethod();





  
    
    
  },


/*
  methods : {
    getGenderDataMethod () {  //axios process
        //getMemberGenderInfo
      axios.post("http://localhost:9000/getGroupInfo_InterSmallCount")
      .then(res=>{
        console.log("소분류 내용 출력 시작");
        const dataRes = res.data
        console.log(res.data)
        console.log("소분류 내용 출력끝. ");
        this.pushChartData(dataRes);
        this.loading=false;
      }).catch(error=>{
        // this.loading=true;
        console.log("오류발생! 오류내용 : "+error)
        // this.loading=false;
      })
    },
      pushChartData(dataRes){
        // var pushData = new Array();
        // this.$data._chart.update()
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
*/



}

</script>
<style scoped>


</style>