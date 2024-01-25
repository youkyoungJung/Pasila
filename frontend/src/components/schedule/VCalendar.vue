<script setup>
import { ref, reactive, onMounted } from 'vue'

const selectedDate = ref(0)
const today = reactive(new Date())
const weeks = ['일', '월', '화', '수', '목', '금', '토']
const DateList = reactive([])

const calendar = ref(null)
let isMouseDown = false
let startX = 0
let scrollLeft = 0

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

const ControlWheel = (e) => {
  e.preventDefault()
  calendar.value.scrollLeft += e.deltaY
}

const ControlDown = (e) => {
  isMouseDown = true
  startX = e.pageX - calendar.value.offsetLeft
  scrollLeft = calendar.value.scrollLeft
}

const ControlLeave = () => {
  isMouseDown = false
}

const ControlUp = () => {
  isMouseDown = false
}

const ControlMove = (e) => {
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
    @wheel="ControlWheel"
    @mousedown="ControlDown"
    @mouseleave="ControlLeave"
    @mouseup="ControlUp"
    @mousemove="ControlMove"
  >
    <div
      v-for="(item, index) in DateList"
      :key="index"
      class="calendar-card"
      :class="{ selected: selectedDate === index }"
      @click="selectedDate = index"
    >
      <div class="date" :class="{ saturday: item.day === '토', sunday: item.day === '일' }">
        {{ item.date }}
      </div>
      <div class="day" :class="{ saturday: item.day === '토', sunday: item.day === '일' }">
        {{ item.day }}
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.calendar {
  @include flex-box(center, flex-start, row);
  overflow-x: scroll;
  // ::-webkit-scrollbar {
  //   height: 1rem;
  // }
  // ::-webkit-scrollbar-thumb {
  // }
  // ::-webkit-scrollbar-track {
  // }
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
}
.selected {
  background-color: $yellow !important;
}

.calendar::-webkit-scrollbar {
  background: none;
  height: 9px;
}

/* 스크롤바 막대 꾸미기 */
.calendar::-webkit-scrollbar-thumb {
  @include box(null, 8px, $light-dark, 30px, 0, 0);
}

/* 스크롤바 트랙 꾸미기 */
// .calendar::-webkit-scrollbar-track {
// }
</style>
