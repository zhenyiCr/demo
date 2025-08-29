import {createRouter, createWebHistory} from 'vue-router'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {path: '/manager', name: 'manager', component: import('../views/Manager.vue'),
            children: [
                {path: 'home', name: 'home', component: import('../views/Home.vue')},
            ]
        },
        {path: '/test',component: import('../views/test.vue')},



    ],
})

export default router
