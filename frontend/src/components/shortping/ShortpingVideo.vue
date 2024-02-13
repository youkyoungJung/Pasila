<script setup>
import { ref, onMounted, watch } from 'vue'
import { getThumbnail } from '@/components/api/ShortpingAPI'

const vi = ref(null)
const currentTime = ref(0)
const props = defineProps(['data', 'video'])
const videoURL = ref('http://localhost:5173/src/assets/video/test/test.mp4')

onMounted(() => {
  getPictures()
  colorList()
})

const getPictures = () => {
  //이미지 가져오기(상품아이디)
  // const res = getThumbnail(1)
  // console.log(res)
  // videoURL.value = res.liveURL
  // videoImages.value = res.thumbnails
}
watch(currentTime, (newTime) => {
  // currentTime이 바뀔 때마다 스크롤 위치 업데이트
  const index = Math.floor(newTime)
  const targetElement = verticalScrollWrap.value.querySelector(`li:nth-child(${index + 1})`)
  if (targetElement) {
    targetElement.scrollIntoView({ behavior: 'smooth', inline: 'center' })
  }
})

watch(props, () => {
  vi.value = props.video
  videoURL.value = props.video
  colorList()
})

const times = ref([])
const colorList = () => {
  let newTimes = ref([])
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
      newTimes.value.push(j)
    }
  }
  times.value = newTimes.value
}

const videoImages = ref([
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
  },
  {
    src: new URL('@/assets/video/test/9second.png', import.meta.url).href
  },
  {
    src: new URL('@/assets/video/test/10second.png', import.meta.url).href
  },
  {
    src: new URL('@/assets/video/test/11second.png', import.meta.url).href
  },
  {
    src: new URL('@/assets/video/test/12second.png', import.meta.url).href
  },
  {
    src: new URL('@/assets/video/test/13second.png', import.meta.url).href
  },
  {
    src: new URL('@/assets/video/test/14second.png', import.meta.url).href
  },
  {
    src: new URL('@/assets/video/test/15second.png', import.meta.url).href
  }
])

//마우스로 스크롤이동하기
let isMouseDown = ref(false)
let startX = ref(0)
let scrollLeft = ref(0)

const verticalScrollWrap = ref(null)
const controlDown = (e) => {
  isMouseDown.value = true
  startX.value = e.pageX - verticalScrollWrap.value.offsetLeft
  scrollLeft.value = verticalScrollWrap.value.scrollLeft
}

const controlLeave = () => {
  isMouseDown.value = false
}

const controlUp = () => {
  isMouseDown.value = false
}

const controlMove = (e) => {
  if (!isMouseDown.value) return

  e.preventDefault()

  const x = e.pageX - verticalScrollWrap.value.offsetLeft
  const beforeScrollLeft = (x - startX.value) * 1
  verticalScrollWrap.value.scrollLeft = scrollLeft.value - beforeScrollLeft
}
</script>
<template>
  <div class="video-body">
    <div class="shortping-video">
      <video
        controls
        width="400"
        height="300"
        :src="videoURL"
        @play="(e) => (currentTime = e.target.currentTime)"
        @timeupdate="(e) => (currentTime = e.target.currentTime)"
      >
        <source :src="videoURL" type="video/mp4" id="test" />
      </video>
    </div>
    <div class="timeline">
      <ul
        ref="verticalScrollWrap"
        @wheel="controlWheel"
        @mousedown="controlDown"
        @mouseleave="controlLeave"
        @mouseup="controlUp"
        @mousemove="controlMove"
      >
        <li
          v-for="(video, idx) in videoImages"
          :key="idx"
          @click="() => (vi.currentTime = idx)"
          :class="currentTime >= idx && currentTime < idx + 1 ? 'selectImg' : ''"
          :style="[times.includes(idx) ? 'background-color: #f4ff19' : 'background-color: white']"
        >
          <div class="thumbnail" :style="{ 'background-image': `url(${video.src})` }"></div>
          <div class="seconds">
            {{
              Math.floor(idx / 3600)
                .toString()
                .padStart(2, '0')
            }}:{{
              Math.floor(idx / 60)
                .toString()
                .padStart(2, '0')
            }}:{{
              Math.floor(idx % 60)
                .toString()
                .padStart(2, '0')
            }}
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
    margin: 0.5rem;
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
        @include box(98%, 100%, white, 0, 0, 0);
        list-style: none;
        display: flex;
        flex-direction: column;
        align-items: center;
        .thumbnail {
          background-size: cover;
          background-position: center;
          width: 100px;
          height: 150px;
          margin: 0.2rem;
          border-radius: 0.5rem;
        }

        .seconds {
          @include font-factory(0.8rem, bold);
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
