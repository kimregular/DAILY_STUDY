import {createStore} from "vuex";

const store = createStore({
    state() {
        return {
            age : 20
        }
    },
    mutations : {
        addAge(state, data) {
            state.age += data;
        },
    }
});

export default store;