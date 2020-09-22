import Vue from 'vue';
import Router from 'vue-router';
import Home from '../components/Home.vue';
import NotFound from '../components/NotFound.vue';
import SignIn from '../components/SignIn.vue';
import Secured from '../components/Secured.vue';

Vue.use(Router);

const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/frontend/',
      redirect: '/frontend/home',
    },
    {
      path: '/frontend/home',
      name: 'Home',
      component: Home,
      meta: { nonRequiresAuth: true },
    },
    {
      path: '/frontend/secured',
      name: 'Secured',
      component: Secured,
    },
    {
      path: '/frontend/signIn',
      name: 'signIn',
      component: SignIn,
      meta: { loginPage: true, nonRequiresAuth: true },
    },
    {
      path: '*',
      component: NotFound,
    },
  ],
});

router.beforeEach((to, from, next) => {
  const requiresAuth = !to.matched.some((record) => record.meta.nonRequiresAuth);
  const isLoginPage = to.matched.some((record) => record.meta.loginPage);
  const isAuthenticated = localStorage.getItem('auth');
  if (requiresAuth && !isAuthenticated) {
    next('/frontend/signIn');
  } else if (isLoginPage && isAuthenticated) {
    router.push('/frontend/home');
  }
  next();
});

export default router;
