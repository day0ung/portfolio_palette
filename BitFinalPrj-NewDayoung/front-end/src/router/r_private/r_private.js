import store from "@/store/s_index.js"

const onlyAuthUser = (to, from , next) => {
    if(store.state.isLogin === true){ //아직 로그인이 안된 유저니까 막음
      alert('로그인이 필요한 기능입니다')
      next('/login')
    }else if(store.state.loginUser.auth === 2){
      alert('일반 회원만 이용할 수 있는 기능입니다.')
      next('/login')
    }else {
      next()
    }
  }


export default [{
    path: '/private',
    name: 'Private',
    beforeEnter: onlyAuthUser,
    component: () => import('@/views/Private/Private.vue')
}]