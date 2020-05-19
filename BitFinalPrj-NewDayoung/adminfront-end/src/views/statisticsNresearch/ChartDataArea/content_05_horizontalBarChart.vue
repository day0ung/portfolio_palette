<script>
//Importing Bar class from the vue-chartjs wrapper
import {HorizontalBar} from 'vue-chartjs'
//Exporting this so it can be used in other components
export default {
  extends: HorizontalBar,
  data() {
    return {
      datacollection: {
        //Data to be represented on x-axis
        labels: [
                  'Man', 
                  'woman'
                ],
        datasets: [
          {
            label: 'Data One',
            backgroundColor: '#4CAF50',
            pointBackgroundColor: 'white',
            borderWidth: 1,
            pointBorderColor: '#249EBF',
            //Data to be represented on y-axis
            data: []
          }
        ]
      },
      //Chart.js options that controls the appearance of the chart
    options: {
        scales: {
            yAxes: [{
                stacked: true
          }],
          xAxes:[{
            ticks:{
              min:0
            }

          }]
        }

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


  },
    watch: {
    data: function() {
      this._chart.destroy();
      //this.renderChart(this.data, this.options);
      this.getGenderDataMethod()
      
    }
  },
  
}

</script>