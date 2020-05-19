<template>
        <div class="small">
            <bar-chart></bar-chart>
            
            <div class="content01_view">
                <!-- <h1> {{title}} member333333333333333333</h1> -->
            </div>
        </div>

</template>

<script>
//Importing Bar class from the vue-chartjs wrapper
// import {Bar} from 'vue-chartjs'
import BarChart from './ChartDataArea/content_07_BarChart'
//Exporting this so it can be used in other components
export default {
  components:{
    BarChart
  },
    data() {
      return {
        datacollection: null // instantiating datacollection with null
      }
    },
    created() {
      this.fillData() //anytime the vue instance is created, call the fillData() function.
    },
    methods: {
      fillData() {
        this.datacollection = {
          // Data for the y-axis of the chart
          labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 
          'September', 'October', 'November', 'December'],
          datasets: [
            {
              label: 'Data One',
              backgroundColor: '#f87979',
              // Data for the x-axis of the chart
              data: [this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), 
              this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), 
              this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt()]
            }
          ]
        }
      },
      getRandomInt() {
        // JS function to generate numbers to be used for the chart
        return Math.floor(Math.random() * (50 - 5 + 1)) + 5
      }
    }
  
}
</script>

<style>

 /* .small {
    max-width: 600px;
    margin:  150px auto;
  } */

</style>