import {onlyAuthUser} from '@/api/api_index.js'

export default[{
    path:'/group',
    name:'Group',
    component:() => import('@/views/Group/group.vue'),
    beforeEnter: onlyAuthUser,
    children:[
  
        {
            path:'/',
            name:'acceptlist',
            component:() => import('@/views/Group/content_00.vue'),
            beforeEnter: onlyAuthUser,
        },
        {
            path:'/group01',
            name:'acceptlist1',
            component:() => import('@/views/Group/content_01.vue'),
            beforeEnter: onlyAuthUser,
        },
        {
            path:'/group02/',
            name:'acceptlist2',
            component:() => import('@/views/Group/content_02.vue'),
            beforeEnter: onlyAuthUser,
        },
        {
            path:'/group03',
            name:'acceptlist3',
            component:() => import('@/views/Group/content_03.vue'),
            beforeEnter: onlyAuthUser,
        },
        {
            path:'/group04',
            name:'acceptlist4',
            component:() => import('@/views/Group/content_04.vue'),
            beforeEnter: onlyAuthUser,
        },

    ]

}]