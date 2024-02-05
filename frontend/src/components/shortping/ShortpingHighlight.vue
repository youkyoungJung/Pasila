<script setup>
import { ref, onMounted } from 'vue'
import { FFmpeg } from '@ffmpeg/ffmpeg'
import { fetchFile, toBlobURL } from '@ffmpeg/util'
import NewHighlight from '@/components/shortping/NewHighlight.vue'
import SavedHighlight from '@/components/shortping/SavedHighlight.vue'

const props = defineProps(['data'])
const emit = defineEmits(['getData', 'deleteData', 'addEmptyData', 'video'])
const ffmpeg = new FFmpeg({ log: true })
const baseURL = 'https://unpkg.com/@ffmpeg/core-mt@0.12.6/dist/esm'
onMounted(() => {
  getFfmpeg()
})
const getFfmpeg = async () => {
  await ffmpeg.load({
    coreURL: await toBlobURL(`${baseURL}/ffmpeg-core.js`, 'text/javascript'),
    wasmURL: await toBlobURL(`${baseURL}/ffmpeg-core.wasm`, 'application/wasm'),
    workerURL: await toBlobURL(`${baseURL}/ffmpeg-core.worker.js`, 'text/javascript')
  })
}
let emptyData = ref({
  isEnroll: false,
  highlightTitle: '',
  highlightStartTime: '',
  highlightEndTime: '',
  highlightSubtitle: ''
})

const videoURL = 'http://localhost:5173/src/assets/video/test/test.mp4'
const video1 = ref('')
let datas = ''

const preview = async () => {
  console.log(props.data)
  await ffmpeg.writeFile('test.mp4', await fetchFile(videoURL))
  console.log('2')
  datas = await ffmpeg.readFile('test.mp4')
  console.log('1')
  video1.value = URL.createObjectURL(new Blob([datas.buffer], { type: 'video/mp4' }))
  console.log(datas)
  // let start = props.data[0].highlightStartTime
  // let end = props.data[0].highlightEndTime
  // const args = ['-i', 'test.mp4', '-ss', `${start}`, '-to', `${end}`, '-c', 'copy', 'output.mp4']
  // console.log('2-0')
  // ffmpeg.writeFile('test.mp4', await fetchFile(videoURL))
  // console.log('2-1')
  // ffmpeg.exec(['-i', 'test.mp4', '-ss', `${start}`, '-to', `${end}`, '-c', 'copy', 'output.mp4'])
  // console.log('2-2')
  // for (let index = 1; index < props.data.length; index++) {
  //   const args = [
  //     '-i',
  //     'test.mp4',
  //     '-ss',
  //     `${props.data[index].highlightStartTime}`,
  //     '-to',
  //     `${props.data[index].highlightEndTime}`,
  //     '-c',
  //     'copy',
  //     'output1.mp4'
  //   ]
  //   await ffmpeg.writeFile('test.mp4', fetchFile(videoURL))
  //   await ffmpeg.exec([
  //     '-i',
  //     'test.mp4',
  //     '-ss',
  //     `${props.data[index].highlightStartTime}`,
  //     '-to',
  //     `${props.data[index].highlightEndTime}`,
  //     '-c',
  //     'copy',
  //     'output1.mp4'
  //   ])
  //   datas = ffmpeg.readFile('output1.mp4')
  //   console.log(datas)
  //   await combineVideos()
  // }
  // video1.value = URL.createObjectURL(new Blob([datas.buffer], { type: 'video/mp4' }))
  // console.log(video1.value)
}

const combineVideos = async () => {
  const args1 = ['-i', 'output.mp4', '-c', 'copy', 'output.ts']
  const args2 = ['-i', 'output1.mp4', '-c', 'copy', 'output1.ts']

  await ffmpeg.exec([...args1])
  await ffmpeg.exec([...args2])

  const args3 = ['-i', 'concat:output.ts|output1.ts', '-c', 'copy', 'output.mp4']
  await ffmpeg.exec([...args3])
  datas = ffmpeg.readFile('output.mp4')
  console.log(datas)
}
const addHighlight = () => {
  emit('addEmptyData', emptyData.value)
  emptyData = ref({
    isEnroll: false,
    highlightTitle: '',
    highlightStartTime: '',
    highlightEndTime: '',
    highlightSubtitle: ''
  })
}
</script>

<template>
  <div class="shortping-highlight">
    <div class="highlight-top">
      <span>하이라이트</span>
      <div class="highlight-btn">
        <button @click="preview" class="preview-btn">미리보기</button>
        <button @click="addHighlight" class="add-btn">추가</button>
      </div>
    </div>
    <div class="highlight-body">
      <div v-for="(highlight, index) in props.data" :key="index" class="highlights">
        <div v-if="highlight.isEnroll">
          <saved-highlight
            :data="highlight"
            :index="index"
            @deleteData="
              (e) => {
                $emit('deleteData', e.index)
              }
            "
          />
        </div>
        <div v-else>
          <new-highlight
            :data="highlight"
            :index="index"
            @getTitle="(e) => (highlight.highlightTitle = e)"
            @getSubtitle="(e) => (highlight.highlightSubtitle = e)"
            @getStartTime="(e) => (highlight.highlightStartTime = e)"
            @getEndTime="(e) => (highlight.highlightEndTime = e)"
            @getData="
              (e) => {
                $emit('getData', e.index)
              }
            "
          />
        </div>
      </div>
    </div>
    <video :src="video1" controls style="width: 400px; height: 150px"></video>
  </div>
</template>

<style lang="scss" scoped>
.shortping-highlight {
  @include box(100%, 10%, white, 0, 0, 0.2rem);
  display: flex;
  flex-direction: column;
  align-items: center;

  .highlight-top {
    @include box(95%, 100%, white, 0, 0, 0.2rem);
    display: flex;
    justify-content: space-between;

    span {
      font-weight: bold;
    }

    .highlight-btn {
      width: 30%;
      display: flex;
      text-align: center;
      justify-content: space-evenly;
      margin-right: 1rem;
      .preview-btn {
        width: 45%;
        border: none;
        background-color: $main;
        color: white;
        border-radius: 0.2rem;
        font-size: 0.7rem;
        cursor: pointer;
      }
      .add-btn {
        width: 45%;
        border: none;
        background-color: $dark;
        color: white;
        border-radius: 0.2rem;
        font-size: 0.7rem;
        cursor: pointer;
      }
    }
  }

  .highlight-body {
    @include box(100%, 32rem, null, 0, 0, 0);
    overflow: auto;
  }
  .highlight-body::-webkit-scrollbar {
    width: 0.3rem;
  }

  .highlight-body::-webkit-scrollbar-thumb {
    height: 20%;
    background: $main;
    border-radius: 10px;
  }

  .highlights {
    margin: 0.1rem;
    padding: 0.1rem;
  }
}
</style>
