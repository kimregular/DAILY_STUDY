import {createStore} from "vuex";

const store = createStore({
    state() {
        return {
            age : 20
        }
    },
    mutations : {
        addAge(state) {
            state.age++;
        },
    }
});

export default store;