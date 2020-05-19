<template>
        <div class="small">
            <line-chart :chart-data="datacollection"></line-chart>
            <button @click="fillData()">Randomize</button>
            <div class="content01_view">
                <h1> {{title}} member333333333333333333</h1>
            </div>
        </div>

</template>

<script>
import LineChart from '@/api/LineChart.js'

export default {
    components: {
      LineChart
    },
    data () {
      return {
        datacollection: null,
        title:'statisticsNresearch1'
      }
    },
    mounted () {
      this.fillData()
    },
    methods: {
      fillData () {
        this.datacollection = {
          labels: [this.getRandomInt(), this.getRandomInt()],
          datasets: [
            {
              label: 'Data One',
              backgroundColor: '#f87979',
              data: [this.getRandomInt(), this.getRandomInt()]
            }, {
              label: 'Data One',
              backgroundColor: '#f87979',
              data: [this.getRandomInt(), this.getRandomInt()]
            }
          ]
        }
      },
      getRandomInt () {
        return Math.floor(Math.random() * (50 - 5 + 1)) + 5
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