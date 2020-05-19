<template>
  <div>
      <full-calendar 
        :events="this.$store.state.s_private.memberCalendar"
        :config="config"
        @event-selected="eventSelected" 
        @event-drop="eventDrop"
        @event-created="scheduleCreate"
        v-loading="this.$store.state.s_private.loading"></full-calendar>
        <CalDetail 
        v-if="caldetail"
        @close="detail">
        </CalDetail>
        <CalWrite 
        v-if="calendarwrite"
        @close="calendarwrite = false"
        @insert="writeAf">
        </CalWrite>
  </div>
</template>

<script>
import CalDetail from '@/views/Private/Calendar/DetailCal.vue'
import CalWrite from '@/views/Private/Calendar/WriteCal.vue'
import 'fullcalendar/dist/fullcalendar.css'
import 'fullcalendar/dist/locale/ko'
import { FullCalendar } from 'vue-full-calendar'
import { loading } from 'element-ui'
export default {
    components:{ CalDetail,CalWrite },
    data(){
      return{
      config: {
          locale: 'ko',
          defaultView:'month',
      },
      clickDay:'',
      caldetail: false,
      calendarwrite: false
      }

    },
    methods:{
      getCalenarInfo(){
        this.$store.state.s_private.loading = true
        var loginData = sessionStorage.getItem("loginUser");
        var login = JSON.parse(loginData); 
        var memSeq = login.memberSeq
        var params = new URLSearchParams();
        params.append('memberSeq', memSeq)
        axios.post("http://localhost:9000/getMemberCalendar", params)
          .then(res => {
          let e = JSON.stringify(res.data)
          this.$store.state.s_private.memberCalendar = JSON.parse(e)
          this.$store.state.s_private.loading = false
        })  
      },
      eventSelected(event, jsEvent, view){ //detail
        if(event.end === null){
            event.end = event.start
         }

         this.$store.state.s_private.memberCalendarDetail = event
         this.caldetail = true
      },
      eventDrop(event){
          if(event.end === null){
            event.end = event.start
          }

            this.$confirm('변경하시겠습니까?', '일정변경', {
            confirmButtonText: '확인',
            cancelButtonText: '취소',
            type: 'info'
            }).then(() => {
                let params = new URLSearchParams()	
                params.append('calendarSeq', event.calendarSeq)
                params.append('start', this.$moment(event.start).format('YYYY.MM.DD HH:mm:ss'))
                params.append('end', this.$moment(event.end).format('YYYY.MM.DD HH:mm:ss'))
                axios.post("http://localhost:9000/updateDateCalendar", params)
                  .then(res => {
                    this.$message({ type: 'success', message:'수정이 완료되었습니다.' });
                  })
            
            }).catch(() => {
              this.getCalendar()
              this.$message({
                type: 'info',
                message: '완료되지 못했습니다'

              });          
            });
      },
      writeAf(){
        this.calendarwrite = false
        this.getCalenarInfo()
      },
      detail(){
        this.caldetail = false
       this.getCalenarInfo()
      },
      scheduleCreate(event){
          this.$confirm('일정을 추가하시겠습니까?', '일정추가', {
          confirmButtonText: '추가',
          cancelButtonText: '취소',
          type: 'info'
        }).then(() => {
          this.$store.state.s_private.memberCalendarStartDate = event.start
          this.$store.state.s_private.memberCalendarEndDate = event.end
          this.calendarwrite = true

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '취소되었습니다.'
          });          
        });
      }

    },
    mounted(){
         var loginData = sessionStorage.getItem("loginUser");
         var login = JSON.parse(loginData); 
         var memSeq = login.memberSeq
         var params = new URLSearchParams();
         params.append('memberSeq', memSeq)
         axios.post("http://localhost:9000/getMemberCalendar", params)
          .then(res => {
          let e = JSON.stringify(res.data)
          this.$store.state.s_private.memberCalendar = JSON.parse(e)
        })  
    }
  
}
</script>

<style>

</style>