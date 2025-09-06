import {createRouter, createWebHistory} from 'vue-router'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {   path: '/', redirect: '/manager/home'},
        {
            path: '/manager', name: 'manager', component: import('../views/Manager.vue'),
            children: [
                {path: 'home', name: 'home', component: import('../views/home.vue')},
                {path: 'users', name: 'users', component: import('../views/users.vue')},
            ]
        },

    ],
})

export default router
