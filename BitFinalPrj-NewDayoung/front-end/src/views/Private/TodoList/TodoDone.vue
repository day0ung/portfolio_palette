<template>
  <div>
    <el-card shadow="hover" v-for="(task, index) in list" :key="index" style="margin:10px 5px 10px 5px;">
     <div class="default">
      <div v-bind:class="{'done': task.del == 1}" > 
       <p>{{task.todoDate}}</p>
       <h4>{{task.title}}</h4>
       <p v-if="task.del ==1" style="margin-top: 12px; flaot:left">완료 됨</p>
      </div>
     
     </div> <!-- default -->
    </el-card> 
 
  </div>
</template>

<script>
export default {
    props:["list"], 
    data(){
        return{

        }
    }
}
</script>

<style scoped>

.done h4{
 text-decoration: line-through;
  color: #cecece;
}
.done p{
  color: #cecece;
}
.default{
  margin: 10px;
}
.default p{
 margin: 0px 0px 10px;
}
</style>