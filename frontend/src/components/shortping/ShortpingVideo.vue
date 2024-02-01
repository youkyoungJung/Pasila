<script setup>
import { ref, onMounted } from 'vue'

const vi = ref(null)
const currentTime = ref(0)

const props = defineProps(['data'])

const times = ref([])

onMounted(() => {
  for (let i = 0; i < props.data.length; i++) {
    let start = 0
    let startTimeArr = props.data[i].highlightStartTime.split(':')
    if (startTimeArr[0] != '00') start += (startTimeArr[0] - '0') * 3600
    if (startTimeArr[1] != '00') start += (startTimeArr[1] - '0') * 60
    start += startTimeArr[2] - '0'
    let end = 0
    let endTimeArr = props.data[i].highlightEndTime.split(':')
    if (endTimeArr[0] != '00') end += (endTimeArr[0] - '0') * 3600
    if (endTimeArr[1] != '00') end += (endTimeArr[1] - '0') * 60
    end += endTimeArr[2] - '0'
    for (let j = start; j <= end; j++) {
      times.value.push(j)
    }
  }
  console.log(times.value)
})

//샘플 비디오와 샘플 이미지들
const videos = ref([
  {
    src: new URL('@/assets/video/test/0second.png', import.meta.url).href
  },
  {
    src: new URL('@/assets/video/test/1second.png', import.meta.url).href
  },
  {
    src: new URL('@/assets/video/test/2second.png', import.meta.url).href
  },
  {
    src: new URL('@/assets/video/test/3second.png', import.meta.url).href
  },
  {
    src: new URL('@/assets/video/test/4second.png', import.meta.url).href
  },
  {
    src: new URL('@/assets/video/test/5second.png', import.meta.url).href
  },
  {
    src: new URL('@/assets/video/test/6second.png', import.meta.url).href
  },
  {
    src: new URL('@/assets/video/test/7second.png', import.meta.url).href
  },
  {
    src: new URL('@/assets/video/test/8second.png', import.meta.url).href
  }
])

//마우스로 스크롤이동하기
let isMouseDown = ref(false)
let startX = ref(0)
let scrollLeft = ref(0)

const verticalScrollWrap = ref(null)
const ControlDown = (e) => {
  isMouseDown = true
  startX = e.pageX - verticalScrollWrap.value.offsetLeft
  scrollLeft = verticalScrollWrap.value.scrollLeft
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
  const x = e.pageX - verticalScrollWrap.value.offsetLeft
  const beforeScrollLeft = (x - startX) * 1
  verticalScrollWrap.value.scrollLeft = scrollLeft - beforeScrollLeft
}
</script>
<template>
  <div class="video-body">
    <div class="shortping-video">
      <video
        controls
        width="400"
        height="300"
        ref="vi"
        @play="(e) => console.log(e.target.currentTime)"
        @timeupdate="(e) => (currentTime = e.target.currentTime)"
      >
        <source src="@/assets/video/test/sample-video.mp4" id="test" />
      </video>
    </div>
    <div class="timeline">
      <ul
        ref="verticalScrollWrap"
        @wheel="ControlWheel"
        @mousedown="ControlDown"
        @mouseleave="ControlLeave"
        @mouseup="ControlUp"
        @mousemove="ControlMove"
      >
        <li
          v-for="(video, idx) in videos"
          :key="idx"
          @click="() => (vi.currentTime = idx)"
          :class="currentTime >= idx && currentTime < idx + 1 ? 'selectImg' : ''"
          :style="[times.includes(idx) ? 'background-color: #ffd5d9' : '']"
        >
          <div class="thumbnail" :style="{ 'background-image': `url(${video.src})` }"></div>
          <div class="seconds">
            {{ Math.floor(idx / 3600) }}:{{ Math.floor(idx / 60) }}:{{ idx % 60 }}
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.video-body {
  @include box(100%, 100%, white, 0, 0, 0);
  display: flex;
  flex-direction: column;
  align-items: center;
  .shortping-video {
    margin: 0 1rem;
    padding: 1rem 2rem;
  }

  .timeline {
    @include box(95%, 100%, white, 0, 0.3rem, 0.3rem);
    border-top: $dark 1px solid;

    ul {
      @include box(98%, 100%, white, 0, 0, 0);
      display: flex;
      overflow-x: auto;
      cursor: grab;
      .selectImg {
        border: 0.1rem solid $main;
      }

      li {
        @include box(98%, 100%, white, 0.1rem, 0, 0);
        list-style: none;
        display: flex;
        flex-direction: column;
        align-items: center;
        .thumbnail {
          background-size: cover;
          background-position: center;
          width: 100px;
          height: 150px;
          margin: 0.1rem;
          border-radius: 0.5rem;
        }

        .seconds {
          @include font-factory($fs-1, bold);
        }
      }
    }
    ul::-webkit-scrollbar {
      height: 0.5rem;
    }

    ul::-webkit-scrollbar-thumb {
      height: 20%;
      background: $main;
      border-radius: 10px;
    }
  }
}
</style>
