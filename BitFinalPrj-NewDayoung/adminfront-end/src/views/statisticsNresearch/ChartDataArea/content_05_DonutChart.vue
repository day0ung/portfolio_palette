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
        labels: ['대학생/취업', '공무원임용', '어학/회화', '라이프/취미'],
        datasets: [
          {
            backgroundColor: [randomColor(), randomColor(), randomColor(), randomColor()],
            data: [0,0,0,1]
          }
        ]
      },
      options:{
         scales: {
          // yAxes: [{
            // ticks: {
              // beginAtZero: true
            // },
            // gridLines: {
              // display: true
            // }
          // }],
          // xAxes: [ {
            // gridLines: {
              // display: false
            // }
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
    this.getGenderDataMethod();





  
    
    
  },



  methods : {
    getGenderDataMethod () {  //axios process
        //getMemberGenderInfo
      axios.post("http://localhost:9000/getGroupInfo_InterBigCount")
      .then(res=>{
        console.log("대분류 내용 출력 시작");
        const dataRes = res.data
        console.log(dataRes)
        
        

        console.log("대분류 내용 출력끝. ");

        this.pushChartData(dataRes);

        this.loading=false;
      }).catch(error=>{
        // this.loading=true;
        
        console.log("오류발생! 오류내용 : "+error)

        // this.loading=false;
      })
    },
      pushChartData(dataRes){
        var pushData = new Array();
        pushData.push(dataRes.value1);
        pushData.push(dataRes.value2);
        pushData.push(dataRes.value3);
        pushData.push(dataRes.value4);


        this.datacollection.datasets[0].data=[];
        this.datacollection.datasets[0].data.push(dataRes[0].value1);
        this.datacollection.datasets[0].data.push(dataRes[0].value2);
        this.datacollection.datasets[0].data.push(dataRes[0].value3);
        this.datacollection.datasets[0].data.push(dataRes[0].value4);
        // this.datacollection.datasets[0].data
        // console.log("들어온값 : ")
        console.log(dataRes)
        console.log(pushData)

        this.$data._chart.update()
        
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