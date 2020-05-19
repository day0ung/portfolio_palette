import {onlyAuthUser} from '@/api/api_index.js'

export default[{
    path:'/memberNGroup',
    name:'MemberNGroup',
    component:() => import('@/views/MemberNGroup/member.vue'),
    beforeEnter: onlyAuthUser,
    children:[
        {   //리스트 출력
            path:'/managemember1/',
            name:'managemember1',
            component:() => import('@/views/MemberNGroup/content_01.vue'),
            beforeEnter: onlyAuthUser,
        },
        {   //디테일 페이지로 이동
            path:'/managemember1/memdetail/:memid',
            name:'managemember1Detail',
            component:() => import('@/views/MemberNGroup/content_01_memdetail.vue'),
            beforeEnter: onlyAuthUser,
        },


        {
            path:'/managemember2',
            name:'managemember2',
            component:() => import('@/views/MemberNGroup/content_02.vue'),
            beforeEnter: onlyAuthUser,
        },
        {   //디테일 페이지로 이동
            path:'/managemember2/companydetail/:comid',
            name:'managemember2Detial',
            component:() => import('@/views/MemberNGroup/content_02_comdetail.vue'),
        },


        {
            path:'/managemember3',
            name:'managemember3',
            component:() => import('@/views/MemberNGroup/content_03.vue'),
            beforeEnter: onlyAuthUser,
        },
        {
            path:'/managemember4',
            name:'managemember4',
            component:() => import('@/views/MemberNGroup/content_04.vue'),
            beforeEnter: onlyAuthUser,
        },
        
    ]

}]