<script setup>
import { computed } from 'vue'
import router from '@/router'
import VSearch from './VSearch.vue'

const isAuth = computed(() => localStorage.getItem('token') == null)
const profile = computed(() => {
  if (localStorage.getItem('profile') != null) return `url(${localStorage.getItem('profile')})`
  else return 'none'
})

const goHome = () => {
  router.push('/')
}

const goChannel = () => {
  router.push(`/channel/${localStorage.getItem('id')}`)
}

const logout = () => {
  localStorage.removeItem('token')
  localStorage.removeItem('id')
  localStorage.removeItem('profile')
  router.go()
}
</script>

<template>
  <header>
    <router-link to="/">
      <img src="@/assets/img/pasila-logo.png" alt="logo" @click="goHome()" class="logo" />
    </router-link>
    <div class="menu">
      <v-search />
      <router-link to="/schedule">
        <span class="icon-color">
          <font-awesome-icon icon="fa-regular fa-calendar-check" />
        </span>
      </router-link>
      <router-link to="/login" v-if="isAuth">
        <span class="icon-color">
          <font-awesome-icon icon="fa-regular fa-user" />
        </span>
      </router-link>
      <div class="dropdown" v-else>
        <span class="dropbtn" :style="{ backgroundImage: profile }"></span>
        <div class="dropdown-content">
          <router-link to="/my" class="profile-menu-line">마이페이지</router-link>
          <div @click="goChannel" class="profile-menu-line">내 채널</div>
          <router-link to="/login" class="profile-menu-line">주문내역</router-link>
          <div @click="logout" class="profile-menu-line">로그아웃</div>
        </div>
      </div>
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
  gap: 2rem;
}
.icon-color {
  color: $dark;
  display: inline-block;

  svg {
    width: 2rem;
    height: 2rem;
  }
}
.dropbtn {
  @include box(2.2rem, 2.2rem, $gray, 50%, 0, 0);
  background-size: cover;
  display: inline-block;
  cursor: pointer;
}

.dropdown {
  display: inline-block;
  position: relative;
}

.dropdown-content {
  position: absolute;
  right: 0;
  display: none;
  background-color: #f9f9f9;
  min-width: 8rem;
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  z-index: 1;
}

.dropdown-content .profile-menu-line {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.dropdown-content .profile-menu-line:hover {
  background-color: #f1f1f1;
}

.dropdown:hover .dropdown-content {
  display: block;
}

.dropdown:hover .dropbtn {
  background-color: $main;
}
</style>
