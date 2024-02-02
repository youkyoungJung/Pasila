<script setup>
// import { RouterLink, useRouter } from 'vue-router'
import router from '@/router'
import VSearch from './VSearch.vue'

// const router = useRouter()

const goHome = () => {
  router.push('/')
}
</script>

<template>
  <header>
    <router-link to="/">
      <img src="@/assets/img/pasila-logo.png" alt="logo" @click="goHome()" class="logo" />
    </router-link>
    <div class="menu">
      <v-search />
      <nav>
        <router-link to="/schedule">
          <span class="icon-color">
            <font-awesome-icon icon="fa-regular fa-calendar-check" size="2x" />
          </span>
        </router-link>
        <router-link to="/login">
          <span class="icon-color">
            <font-awesome-icon icon="fa-regular fa-user" size="2x" />
          </span>
        </router-link>
      </nav>
    </div>
  </header>
</template>

<style lang="scss" scoped>
header {
  @include flex-box(center, space-between);
  padding: 1rem 3rem;
}
.menu {
  @include flex-box(center, space-between);
}
.icon-color {
  color: $dark;
  margin: 0 0 0 2rem;
}
</style>
