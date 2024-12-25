import {createStore} from "vuex";

const store = createStore({
    state() {
        return {
            age : 20,
            likes : 30
        }
    },
    mutations : {
        addAge(state, data) {
            console.log("mutations has called");
            state.age = state.age + data;
        },
        addLikes(state) {
            state.likes++;
        }
    }
});

export default store;