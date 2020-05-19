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
        labels: ['Man', 'woma'],
        datasets: [
          {
            backgroundColor: [randomColor(), randomColor()],
            data: [0,1]
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
        // this.loading=true;
        
        console.log("오류발생! 오류내용 : "+error)

        // this.loading=false;
      })
    },
      pickGender(){
        this.getRawData;

        var testList = new Array();
        var mancheck = 0;
        var womancheck=0;


        for(var i=0; i<this.getRawData.length; i++){
          // console.log("genderdata = "+ i + "["+this.getRawData[i].gender+"]")
          // testList.push(this.getRawData[i].gender);
          if(this.getRawData[i].gender == 1){
              mancheck++;
          }else{
            womancheck++;
          }
        }

        
        testList.push(JSON.stringify(mancheck));
        testList.push(JSON.stringify(womancheck));
        this.datacollection.datasets[0].data= testList
        console.log("=========================checkdata=====================")
        console.log(testList)
        console.log(this.datacollection.datasets[0].data)
        this.$data._chart.update()
        console.log("=========================checkdata=====================")
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