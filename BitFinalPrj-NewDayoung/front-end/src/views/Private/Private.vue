<template>
  <div class="private">
   <el-tabs v-model="activeName" @tab-click="handleClick">
    <el-tab-pane label="TO DO LIST" name="first" >
      <Todo></Todo>
    </el-tab-pane>
    <el-tab-pane label="활동로그" name="second">
     <highcharts :options="chartOptions"></highcharts>
    </el-tab-pane>
    <el-tab-pane label="나의 일정" name="third"> 
      <Schedule></Schedule>
    </el-tab-pane>
    <el-tab-pane label="자료실" name="forth">
      <Board></Board>
    </el-tab-pane>
  </el-tabs>
  </div>
</template>


<style scoped>
  .private{
    padding-top: 10px;
    min-height: 100vh;
  }
</style>

<script>
import Vue from 'vue'
import HighchartsVue from 'highcharts-vue'
import {Chart} from 'highcharts-vue'
Vue.use(HighchartsVue)

import Schedule from '@/views/Private/priSchedule.vue'
import Board from '@/views/Private/priBoard.vue'
import Todo from '@/views/Private/priTodo.vue'
 export default {
    components:{
        Schedule,
        Board,
        Todo,
        highcharts: Chart 
    },
    data() {
      return {
        activeName: 'first',
        chartOptions: {
            chart: {
                type: 'column'
            },
            title: {
                text: '지난 TODO LIST 달성'
            },
            series: [{
                data: [],
                color: '#fa5151',
                name: '달성 수',
            
            }],
            xAxis: {
                categories: [],
            },
            yAxis: {
                min: 0,
                title: {
                    text: ''
                }
            }
        } 
      };
    },
    methods: {
      getChart(){
        var loginData = sessionStorage.getItem("loginUser");
        var login = JSON.parse(loginData); 
        var memSeq = login.memberSeq
        var params = new URLSearchParams();
        params.append('memberSeq', memSeq)
        axios.post('http://localhost:9000/selectDate', params)
        .then(res => {
            let count = 0
            var list = res.data
                  for(const key in list){
                      const element = list[key]
                      this.chartOptions.xAxis.categories.push(element.todoDate)
                      this.chartOptions.series[0].data.push(element.delCount)
                  }
          }) 
      },
      handleClick(tab, event) {
            this.$store.state.s_private.list = true
            this.$store.state.s_private.detail = false
            this.$store.state.s_private.write = false
            this.$store.state.s_private.update = false
         if(this.activeName == 'second'){
           this.chartOptions.xAxis.categories = []
           this.chartOptions.series[0].data = []
           this.getChart()
         }else if(this.activeName == 'third'){

         }else if(this.activeName == 'forth'){
         var loginData = sessionStorage.getItem("loginUser");
         var login = JSON.parse(loginData); 
         var memSeq = login.memberSeq
         var params = new URLSearchParams();
         params.append('memberSeq', memSeq)
         axios.post('http://localhost:9000/getMemberBoard', params)
         .then(res => {
            this.$store.state.s_private.BoardData = res.data
           }) 
            this.$store.state.s_private.detail = false
            this.$store.state.s_private.write = false
            this.$store.state.s_private.update = false
         }       
      }
    },
    mounted(){
      this.getChart()
    }
  };
</script>