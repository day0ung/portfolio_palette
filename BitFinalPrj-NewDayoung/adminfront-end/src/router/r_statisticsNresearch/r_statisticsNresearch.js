import {onlyAuthUser} from '@/api/api_index.js'

export default[{
    path:'/statisticsNresearch',
    name:'StatisticsNresearch',
    component:() => import('@/views/statisticsNresearch/statisticsNresearch.vue'),
    // beforeEnter: onlyAuthUser,
    children:[
        {
            path:'/',
            name:'statisticsNresearchHome',
            component:() => import('@/views/statisticsNresearch/content_01.vue'),
            // beforeEnter: onlyAuthUser,
        },
        {
            path:'/statisticsNresearch1',
            name:'statisticsNresearch1',
            component:() => import('@/views/statisticsNresearch/content_01.vue'),
            // beforeEnter: onlyAuthUser,
        },
        {
            path:'/statisticsNresearch2',
            name:'statisticsNresearch2',
            component:() => import('@/views/statisticsNresearch/content_02.vue'),
            // beforeEnter: onlyAuthUser,
        },
        {
            path:'/statisticsNresearch3',
            name:'statisticsNresearch3',
            component:() => import('@/views/statisticsNresearch/content_03.vue'),
            // beforeEnter: onlyAuthUser,
        },
        {
            path:'/statisticsNresearch4',
            name:'statisticsNresearch4',
            component:() => import('@/views/statisticsNresearch/content_04.vue'),
            // beforeEnter: onlyAuthUser,
        },
        {
            path:'/statisticsNresearch5',
            name:'statisticsNresearch5',
            component:() => import('@/views/statisticsNresearch/content_05.vue'),
            // beforeEnter: onlyAuthUser,
        },
        {
            path:'/statisticsNresearch6',
            name:'statisticsNresearch6',
            component:() => import('@/views/statisticsNresearch/content_06.vue'),
            // beforeEnter: onlyAuthUser,
        },
        {
            path:'/statisticsNresearch7',
            name:'statisticsNresearch7',
            component:() => import('@/views/statisticsNresearch/content_07.vue'),
            // beforeEnter: onlyAuthUser,
        },
        {
            path:'/statisticsNresearch8',
            name:'statisticsNresearch8',
            component:() => import('@/views/statisticsNresearch/content_08.vue'),
            // beforeEnter: onlyAuthUser,
        },
        
    ]

}]