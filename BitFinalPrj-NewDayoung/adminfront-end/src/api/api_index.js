//login check
import store from "@/store/s_index.js"


export const onlyAuthUser = (to, from , next) => {
  if(store.state.isLogin ===true){ //아직 로그인이 안된 유저니까 막음
    alert('로그인이 필요합니다.')
    next('/login')
  }else{
    next()
  }
}

