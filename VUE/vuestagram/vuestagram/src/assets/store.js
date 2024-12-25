import {createStore} from "vuex";

const store = createStore({
    state() {
        return {
            age : 20
        }
    },
    mutations : {
        addAge(state, data) {
            console.log("mutations has called");
            state.age = state.age + data;
        },
    }
});

export default store;