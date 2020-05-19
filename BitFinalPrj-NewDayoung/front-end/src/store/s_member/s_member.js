import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)


export default new Vuex.Store({
    namespaced: true,
    state: {
        todoList:[],
        MyPageInterLikeListLoading: false,
        MyPageInterLikeList: [],
        MyPageGroupList: [],
        MyPageStudyListLoading: false,
        MyPagePermissionListLoading: false,
        MyPagePermissionList: [],
        MyPageCreatGroupListLoading: false,
        MyPageCreatGroupList: [],
        MyPageCreatGroupListLength: false,
    },
    actions: {
   
    }


    
})

