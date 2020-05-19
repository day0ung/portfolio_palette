export default {
    namespaced: true,
    state: {
        groupSeq: 0,
        groupList: [],
        detailSeq: 0,
        groupMember:[],
        groupCalendar:[],
        groupCalendarDetail:[],
        groupCalendarStartDate:'',
        groupCalendarEndDate:'',
        groupBoardList: [],
        groupBoardDetail: [],
        groupBoardDetailComments: [],
        groupReferenceList: [],
        groupReferenceDetail: [],
        groupReferenceDetailFileList: [],
        grouDetail: [],
        MypageReferenceList: [],
        MyGroupLoading: false,
        MyOtherGroupLoading: false,
        showGroupInfo: false,
        showGroupCalendar: false,
        showBoardList: false,
        showBoardDetail: false,
        showBoardDetailComments: false,
        showDeleteBtn: false,
        showUpdateBtn: false,
        showReferenceDetail: false,
        showGroupReferenceList: false,
        groupDetailLoading: false,
        groupCreateSubmitLoading: false,
        total: 0,
        listQuery:{
          page: 1,
          limit: 5,
        },
        searchWord:'',
        s_keyWord:'',
        check1:false,
        check2:false,

    },
    getters: {
        getDetailSeq(){
            this.detailSeq
        }
    },
    mutations: {
        
    },
    actions: {
    }
}