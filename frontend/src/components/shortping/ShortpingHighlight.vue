<script setup>
import { ref, onMounted } from 'vue'
import { FFmpeg } from '@ffmpeg/ffmpeg'
import { fetchFile, toBlobURL } from '@ffmpeg/util'
import NewHighlight from '@/components/shortping/NewHighlight.vue'
import SavedHighlight from '@/components/shortping/SavedHighlight.vue'
import { useShortpingStore } from '@/stores/shortping'
import { getThumbnailApi } from '@/components/api/ShortpingAPI'

const props = defineProps(['data', 'liveId'])
const emit = defineEmits(['getData', 'deleteData', 'addEmptyData', 'video'])
let emptyData = ref({
  isEnroll: false,
  highlightTitle: '',
  highlightStartTime: '',
  highlightEndTime: ''
})
const store = useShortpingStore()
const ffmpeg = new FFmpeg()
const baseURL = 'https://unpkg.com/@ffmpeg/core-mt@0.12.6/dist/esm'
const videoURL = ref('')
onMounted(() => {
  getPictures()
})

const getPictures = async () => {
  // 이미지 가져오기(라이브아이디)
  const res = await getThumbnailApi(props.liveId)
  videoURL.value = res.liveUrl
  store.videoURL = res.liveUrl
}
let video = ref('')
let flag = ref(false)

const preview = async () => {
  flag.value = true
  await ffmpeg.load({
    coreURL: await toBlobURL(`${baseURL}/ffmpeg-core.js`, 'text/javascript'),
    wasmURL: await toBlobURL(`${baseURL}/ffmpeg-core.wasm`, 'application/wasm'),
    workerURL: await toBlobURL(`${baseURL}/ffmpeg-core.worker.js`, 'text/javascript')
  })

  const outputs = []
  const inputFileName = 'test.mp4'
  await ffmpeg.writeFile(inputFileName, await fetchFile(videoURL.value))

  for (let i = 0; i < props.data.length; i++) {
    const start = props.data[i].highlightStartTime
    const end = props.data[i].highlightEndTime
    const args = [
      '-i',
      'test.mp4',
      '-ss',
      `${start}`,
      '-to',
      `${end}`,
      '-c',
      'copy',
      `output${i}.mp4`
    ]
    await ffmpeg.exec([...args])
    const convertArgs = ['-i', `output${i}.mp4`, '-c', 'copy', `output${i}.ts`]
    await ffmpeg.exec([...convertArgs])
    outputs.push(`output${i}.ts`)
  }
  const concatArgs = ['-i', `concat:${outputs.join('|')}`, '-c', 'copy', 'output.mp4']
  const result = await ffmpeg.exec([...concatArgs])

  if (result.error) {
    console.error('FFmpeg execution error:', result.error)
    return
  }

  const finalOutput = await ffmpeg.readFile('output.mp4')
  video.value = URL.createObjectURL(new Blob([finalOutput.buffer], { type: 'video/mp4' }))
  emit('video', new File([finalOutput.buffer], 'file.mp4', { type: 'video/mp4' }))
}

const addHighlight = () => {
  emit('addEmptyData', emptyData.value)
  emptyData = ref({
    isEnroll: false,
    highlightTitle: '',
    highlightStartTime: '',
    highlightEndTime: ''
  })
}
</script>

<template>
  <div class="shortping-highlight">
    <div class="highlight-top">
      <span>하이라이트</span>
      <div class="highlight-btn">
        <button @click="preview()" class="preview-btn">미리보기</button>
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
    <div v-if="flag" style="display: none">
      <video controls width="400" height="300" :src="video">
        <source :src="video" type="video/mp4" id="test" />
      </video>
    </div>
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
