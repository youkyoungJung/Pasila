<script setup>
import LineCalendar from '@/components/schedule/LineCalendar.vue'
import ProductCard from '@/components/live/ProductCard.vue'
import ChannelCard from '@/components/schedule/ChannelCard.vue'
import { ref } from 'vue'

const Products = ref([
  {
    on: '2024-01-19 11:00:00',
    title: '따끈따끈 붕어빵>.<',
    price: 20000,
    discountPrice: 12000,
    thumbnail: new URL('@/assets/img/test/fishbread.png', import.meta.url).href,
    member: 'JEENIE',
    profile: new URL('@/assets/img/test/jenny.jpg', import.meta.url).href
  },
  {
    on: '2024-01-19 11:10:00',
    title: '베트남으로 떠나는 3박 5일 여행!',
    price: 2000000,
    discountPrice: 1560000,
    thumbnail: new URL('@/assets/img/test/vietnam.jpg', import.meta.url).href,
    member: 'ROSE',
    profile: new URL('@/assets/img/test/rose.jpg', import.meta.url).href
  }
])
</script>

<template>
  <div class="schedule">
    <line-calendar />
    <div v-for="(item, index) in Products" :key="index" class="product-channel">
      <product-card :product="item" />
      <channel-card :member="{ member: item.member, profile: item.profile }" />
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
