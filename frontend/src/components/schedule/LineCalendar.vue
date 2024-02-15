<script setup>
import { ref, reactive, onMounted } from 'vue'

defineProps({
  selectedDate: String
})

const today = reactive(new Date())
const weeks = ['일', '월', '화', '수', '목', '금', '토']
const DateList = reactive([])

const calendar = ref(null)
let isMouseDown = false
let startX = 0
let scrollLeft = 0

onMounted(() => {
  makeDateList()
})

const makeDateList = () => {
  for (let index = 0; index < 14; index++) {
    const date = new Date(today)
    date.setDate(today.getDate() + index)
    DateList.push(date)
  }
}

const controlWheel = (e) => {
  e.preventDefault()
  calendar.value.scrollLeft += e.deltaY
}

const controlDown = (e) => {
  isMouseDown = true
  startX = e.pageX - calendar.value.offsetLeft
  scrollLeft = calendar.value.scrollLeft
}

const controlLeave = () => {
  isMouseDown = false
}

const controlUp = () => {
  isMouseDown = false
}

const controlMove = (e) => {
  if (!isMouseDown) return

  e.preventDefault()
  const x = e.pageX - calendar.value.offsetLeft
  const beforeScrollLeft = (x - startX) * 1
  calendar.value.scrollLeft = scrollLeft - beforeScrollLeft
}
</script>

<template>
  <div
    class="calendar"
    ref="calendar"
    @wheel="controlWheel"
    @mousedown="controlDown"
    @mouseleave="controlLeave"
    @mouseup="controlUp"
    @mousemove="controlMove"
  >
    <div
      v-for="(item, index) in DateList"
      :key="index"
      class="calendar-card"
      :class="{
        selected:
          selectedDate ==
          item.getFullYear() + '-' + ('0' + (item.getMonth() + 1)).slice(-2) + '-' + item.getDate()
      }"
      @click="
        $emit(
          'changeDate',
          item.getFullYear() + '-' + ('0' + (item.getMonth() + 1)).slice(-2) + '-' + item.getDate()
        )
      "
    >
      <div
        class="date"
        :class="{ saturday: weeks[item.getDay()] === '토', sunday: weeks[item.getDay()] === '일' }"
      >
        {{ item.getMonth() + 1 + ' / ' + item.getDate() }}
      </div>
      <div
        class="day"
        :class="{ saturday: weeks[item.getDay()] === '토', sunday: weeks[item.getDay()] === '일' }"
      >
        {{ weeks[item.getDay()] }}
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.calendar {
  @include flex-box(center, flex-start, row);
  overflow-x: scroll;
  .calendar-card {
    @include flex-box(center, center, column);
    @include box(3.5rem, 3.5rem, $light-gray, 15px, 0.8rem, 20px);
    cursor: pointer;
    flex-shrink: 0;
    .date,
    .day {
      @include font-factory($fs-2, bold);
      text-align: center;
      white-space: nowrap;
    }
    .saturday {
      color: blue !important;
    }
    .sunday {
      color: $main !important;
    }
  }
  &::-webkit-scrollbar {
    background: none;
    height: 9px;
  }
  &::-webkit-scrollbar-thumb {
    @include box(null, 8px, $light-dark, 30px, 0, 0);
  }
}
.selected {
  background-color: $yellow !important;
}
</style>
