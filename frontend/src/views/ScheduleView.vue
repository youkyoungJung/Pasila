<script setup>
import Calendar from '@/components/schedule/Calendar.vue'
import ProductCard from '@/components/live/ProductCard.vue'
import ChannelCard from '@/components/schedule/ChannelCard.vue'

import { ref, reactive } from 'vue'

const Products = ref([
  {
    on: '2024-01-19 11:00:00',
    title: '따끈따끈 붕어빵>.<',
    price: 20000,
    discountPrice: 12000,
    thumbnail: '/src/assets/img/test/fishbread.png',
    member: 'JEENIE',
    profile: '/src/assets/img/test/jenny.jpg'
  },
  {
    on: '2024-01-19 11:10:00',
    title: '베트남으로 떠나는 3박 5일 여행!',
    price: 2000000,
    discountPrice: 1560000,
    thumbnail: '/src/assets/img/test/vietnam.jpg',
    member: 'ROSE',
    profile: '/src/assets/img/test/rose.jpg'
  }
])
</script>

<template>
  <div class="schedule">
    <calendar></calendar>
    <div v-for="(item, index) in Products" :key="index" class="product-channel">
      <product-card :product="item"></product-card>
      <channel-card :member="{ member: item.member, profile: item.profile }"></channel-card>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.schedule {
  padding: 2rem;
  .product-channel {
    @include flex-box(center, space-between);
    padding: 0 10%;
  }
}
</style>
