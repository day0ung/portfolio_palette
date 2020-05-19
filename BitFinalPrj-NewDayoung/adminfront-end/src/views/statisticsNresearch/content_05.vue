<template>
        <div class="chartArea">
          <div class="set_one">
            <div class="barchartArea">
              <!-- <h3> 남여 가입자 비율(lineChart) </h3>
              <HorizontalBar></HorizontalBar> -->
            </div>
            <div class="dounutChartArea">
              <h3> 그룹별 대분류 비율(DonutChart) </h3>
              <DonutChart  @on-receive="updateValue1"></DonutChart>
            </div>
          </div>
            <div class="content01_view">
                <!-- <h1> {{title}} member333333333333333333</h1> -->
            </div>
        </div>

</template>

<script>
//Importing Bar class from the vue-chartjs wrapper

// import HorizontalBar from './ChartDataArea/content_05_horizontalBarChart'
import DonutChart from './ChartDataArea/content_05_DonutChart'

//Exporting this so it can be used in other components
export default {
  components:{
    // HorizontalBar,
    DonutChart
  },
    data() {
      return {

      }
    },
    created() {
   
    },
    mounted(){

    },
    methods: {

    //   handle (point, event) {
    // 	const item = event[0]
    // 	this.$emit('on-receive', {
    //   	index: item._index,
    //     backgroundColor: item._view.backgroundColor,
    //     value: this.values[item._index]
    //   })
    // },


      updateValue1(data1){
        // DonutChart.updateValue(data1);
        console.log(data1.value);
      }

    },
}
</script>

<style>

 /* .small {
    max-width: 600px;
    margin:  150px auto;
  } */

.set_one{
  display:flex;
}

</style>