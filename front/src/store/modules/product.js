export default {
    namespaced: true,
    state: {
        products:[],
    },
    // getters are used to get state data 
    getters: {
        getProducts( state ) {
            return state.products
        },
    },
    // mutations are used to manipulate state data
    mutations: {

    },
    // actions are used to perform async operations
    actions: {
      
    }
}