<script setup>
import { ref, reactive, onMounted } from 'vue'
import LineCalendar from '@/components/schedule/LineCalendar.vue'
import ProductCard from '@/components/live/ProductCard.vue'
import ChannelCard from '@/components/schedule/ChannelCard.vue'
import { getLiveScheduleApi } from '@/components/api/OpenviduAPI'

const products = ref()
const today = reactive(new Date())
let selectedDate = ref(
  today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + today.getDate()
)

onMounted(async () => {
  products.value = await getLiveScheduleApi(selectedDate.value)
})

const changeDate = async (e) => {
  selectedDate.value = e
  products.value = await getLiveScheduleApi(selectedDate.value)
}
</script>

<template>
  <div class="schedule">
    <line-calendar :selected-date="selectedDate" @change-date="changeDate" />
    <div v-for="(item, index) in products" :key="index">
      <div class="row">
        <span class="date">{{ item.liveScheduledAt.substring(11, 16) }}</span>
        <div class="product-channel">
          <channel-card :member="{ member: item.channel, profile: item.profileUrl }" />
          <product-card :product="item" />
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.schedule {
  padding: 2rem;
  .row {
    padding: 2% 10%;
    .date {
      @include font-factory($fs-3, bold, $orange);
      &::after {
        display: inline-block;
        content: '';
        width: 90%;
        height: 1px;
        margin-left: 1rem;
        background-color: $light-dark;
      }
    }
    .product-channel {
      @include flex-box(center, space-between);
    }
  }
}
</style>
