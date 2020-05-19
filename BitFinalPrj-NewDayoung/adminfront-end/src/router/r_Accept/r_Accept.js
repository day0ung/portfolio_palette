import {onlyAuthUser} from '@/api/api_index.js'

export default[{
    path:'/accept',
    name:'Accept',
    component:() => import('@/views/Accept/accept.vue'),
    beforeEnter: onlyAuthUser,
    children:[
        
        {
            path:'/',
            name:'acceptlist',
            component:() => import('@/views/Accept/content_00.vue'),
            beforeEnter: onlyAuthUser,
        },
        {
            path:'/acceptlist1',
            name:'acceptlist1',
            component:() => import('@/views/Accept/content_01.vue'),
            beforeEnter: onlyAuthUser,
        },
        {
            path:'/acceptlist2',
            name:'acceptlist2',
            component:() => import('@/views/Accept/content_02.vue'),
            beforeEnter: onlyAuthUser,
        },
        {
            path:'/acceptlist3',
            name:'acceptlist3',
            component:() => import('@/views/Accept/content_03.vue'),
            beforeEnter: onlyAuthUser,
        },
        {
            path:'/acceptlist4',
            name:'acceptlist4',
            component:() => import('@/views/Accept/content_04.vue'),
            beforeEnter: onlyAuthUser,
        },
        
    ]

}]