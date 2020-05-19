import store from "@/store/s_index.js"

const onlyAuthUser = (to, from , next) => {
    if(store.state.isLogin === true){ //아직 로그인이 안된 유저니까 막음
      alert('로그인이 필요한 기능입니다')
      next('/login')
    }else{
      next()
    }
  }

export default [{
    path: '/notice',
    name: 'Notice',
    beforeEnter: onlyAuthUser,
    component: () => import('@/views/Notice/Notice.vue')
},
{
    path: '/notice/noticeWriting',
    name: 'noticeWriting',
    component: () => import('@/views/Notice/NoticeWriting.vue'),
},
{
    path: '/notice/detail/:ContentId',
    name: 'noticeDetail',
    component: () => import('@/views/Notice/NoticeDetail.vue'),
},
{
    path: '/notice/update/:ContentId',
    name: 'noticeUpdate',
    component: () => import('@/views/Notice/NoticeUpdate.vue'),
},
]