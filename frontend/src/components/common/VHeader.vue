<script setup>
import { RouterLink, useRouter } from 'vue-router'
import VSearch from './VSearch.vue'

const router = useRouter()

const goHome = () => {
  router.push('/')
}
</script>

<template>
  <header>
    <img src="@/assets/img/pasila-logo.png" alt="logo" @click="goHome()" class="logo" />
    <div class="menu">
      <v-search></v-search>
      <nav>
        <router-link to="">
          <span class="icon-color">
            <font-awesome-icon icon="fa-regular fa-calendar-check" size="2x" />
          </span>
        </router-link>
        <span class="icon-color">
          <font-awesome-icon icon="fa-regular fa-user" size="2x" />
        </span>
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
