import {onlyAuthUser} from '@/api/api_index.js'


export default[{
    path:'/board',
    name:'Board',
    component:() => import('@/views/Board/board.vue'),
    beforeEnter: onlyAuthUser,
    children:[
        {
            path:'/board1',
            name:'board1',
            component:() => import('@/views/Board/content_01.vue'),
            beforeEnter: onlyAuthUser,
        },
        {
            path:'/board2',
            name:'board2',
            component:() => import('@/views/Board/content_02.vue'),
            beforeEnter: onlyAuthUser,
        },
        {
            path:'/board3',
            name:'board3',
            component:() => import('@/views/Board/content_03.vue'),
            beforeEnter: onlyAuthUser,
        },
        {
            path:'/board4',
            name:'board4',
            component:() => import('@/views/Board/content_04.vue'),
            beforeEnter: onlyAuthUser,
        },
        
    ]

}]