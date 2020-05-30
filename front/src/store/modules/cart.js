


export default{
  
  state: {
  cart: [],
  count :0,
  },






































































































  getters: {
    cart: state => state.cart,
    price: state =>
      state.cart.reduce(
        (acc, current) => parseInt(acc + parseInt(current.price)),
        0
      ),
    count: state =>{
      let i = 0;
      for (const pr of state.cart){
        i = i+ pr.count;
      }
      return i;
    },
  },
  
  actions: { 
       
    addToCart({ commit }, product) {
      let cart=[];
      let updatedCart = [];
      const newProduct = {
        id : product.id,
        name : product.name,
        count : 1
      }
      cart = getData("cart");
      let filter = cart.filter(el => {return el.id === newProduct.id; });

      if (cart.length === 0) {      
        updatedCart[0] = newProduct; 
      }
      else if(filter.length === 0 ){
        updatedCart=[...cart,newProduct];
      }
      else{
        for (let el of cart) {
          if(el.id === newProduct.id){
            el.count++;
         }
        }
        updatedCart = cart;
      }
      setData("cart", updatedCart);
      commit("addToCart", updatedCart);
    },
    
    remove({ commit }, id) {
      const cart = getData("cart");
      let updatedCart = cart.filter(el => el.id !== id);
      setData("cart", updatedCart);
      commit("remove", updatedCart);
    }

  },
  
  mutations: {
    addToCart: (state, updatedCart) => {
      state.cart = updatedCart;
    },
    setCart: (state, cart) => {
      state.cart = cart;
    },
    remove: (state, updatedCart) => {
      state.cart = [...updatedCart];
    }
  },


}
function setData (key , data){
  if(data !== null){
    sessionStorage.setItem(key, JSON.stringify(data));
  }  
}


function getData(key){
 let cart=[];
 if(sessionStorage.getItem(key) !== null 
     && sessionStorage.getItem(key).length !== 0){
      cart = JSON.parse(sessionStorage.getItem(key));
   }
 return cart;
}

