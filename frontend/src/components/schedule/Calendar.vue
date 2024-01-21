<script setup>
import { ref, reactive, computed, onMounted } from 'vue'

const selectedDate = ref(0)
const today = reactive(new Date())
const weeks = ['일', '월', '화', '수', '목', '금', '토']

const DateList = reactive([])

//2주 날짜 DateList에 저장
const makeDateList = () => {
  for (let index = 0; index < 14; index++) {
    DateList.push({
      date: today.getMonth() + 1 + ' / ' + today.getDate(),
      day: weeks[today.getDay()]
    })
    today.setDate(today.getDate() + 1)
  }
}

onMounted(() => {
  makeDateList()
})
</script>

<template>
  <div class="calendar">
    <div
      v-for="(item, index) in DateList"
      :key="index"
      class="calendar-card"
      :class="{ selected: selectedDate === index }"
      @click="selectedDate = index"
    >
      <div class="date">{{ item.date }}</div>
      <div class="day">{{ item.day }}</div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.calendar {
  @include flex-box(center, flex-start, row);
  overflow-x: scroll;
  .calendar-card {
    @include flex-box(center, center, column);
    @include box(3.5rem, 3.5rem, $light-gray, 15px, 0 0.8rem, 20px);
    cursor: pointer;
    flex-shrink: 0;

    .date,
    .day {
      @include font-factory($fs-2, bold);
      text-align: center;
      white-space: nowrap;
    }
  }
}
.selected {
  background-color: $yellow !important;
}
</style>
