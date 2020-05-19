<template>
    <div class="l_main">
        <h3 style="margin:15px 0px">내 그룹</h3>
        <MyGroup></MyGroup>
        <div class="hr"></div>
        <h6 style="margin:15px 0px">전체 그룹</h6>
        <MyOtherGroup></MyOtherGroup>
    </div>

    
</template>

<script>
import MyOtherGroup from '@/components/GroupList/MyOtherGroup.vue'
import MyGroup from '@/components/GroupList/MyGroup.vue'
export default {
    components: { MyOtherGroup, MyGroup }
}
</script>

<style scoped>
.hr{
  display: flex;
  margin-top: 15px;
  margin-bottom: 5px;
  background:#c1c1c1;
  height: 1px;
  width: 100%;
}
</style>