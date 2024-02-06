<script setup>
const props = defineProps(['data', 'index'])
const emit = defineEmits(['getData', 'getTitle', 'getSubtitle', 'getStartTime', 'getEndTime'])

const changeTime = (e) => {
  const parsedTime = e.replace(/:/g, '') || 0
  const hour = parsedTime.substring(0, 2)
  const minute = parsedTime.substring(2, 4)
  const second = parsedTime.substring(4, 6)
  if (isFinite(hour + minute + second) == false) {
    alert('문자는 입력하실 수 없습니다.')
    e = '00:00:00'
    return e
  }
  if (parsedTime.length >= 6) {
    if (minute >= 60) {
      alert('분은 59분을 넘길 수 없습니다.')
      e = hour + ':00:00'
      return e
    } else if (second >= 60) {
      alert('초는 59초를 넘길 수 없습니다.')
      e = hour + ':' + minute + ':00'
      return e
    } else {
      e = hour + ':' + minute + ':' + second
      return e
    }
  }
  return e
}

const newHighlight = () => {
  const start = props.data.highlightStartTime
  const end = props.data.highlightEndTime
  if (start.length < 6 || end.length < 6) {
    alert('시작시간, 끝시간을 확인해주세요.')
  } else {
    const startTime = parseInt(props.data.highlightStartTime.replaceAll(':', ''))
    const endTime = parseInt(props.data.highlightEndTime.replaceAll(':', ''))
    if (endTime - startTime < 0) {
      alert('시작시간이 끝시간보다 늦을 수 없습니다.')
    } else if (endTime == startTime) {
      alert('시작시간과 끝시간이 같을 수 없습니다.')
    } else {
      emit('getData', props)
    }
  }
}
</script>

<template>
  <div class="highlight">
    <div class="highlight-top">
      <div class="highlight-time">
        <input
          type="text"
          :value="props.data.highlightStartTime"
          @input="$emit('getStartTime', changeTime($event.target.value))"
          placeholder="hhmmss"
        />~
        <input
          type="text"
          :value="props.data.highlightEndTime"
          @input="$emit('getEndTime', changeTime($event.target.value))"
          placeholder="hhmmss"
        />
      </div>
      <button @click="newHighlight()" class="delete-btn">등록</button>
    </div>
    <div class="highlight-title">
      <label for="title">제목</label>
      <input
        id="title"
        type="text"
        placeholder="제목을 입력해주세요."
        :value="props.data.highlightTitle"
        @input="$emit('getTitle', $event.target.value)"
      />
    </div>
    <div class="highlight-subtitle">
      <label for="subtitle">자막</label>
      <textarea
        id="subtitle"
        type="text"
        placeholder="자막을 입력해주세요."
        :value="props.data.highlightSubtitle"
        @input="$emit('getSubtitle', $event.target.value)"
      ></textarea>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.highlight {
  @include box(95%, 10rem, white, 0.2rem, 0, 0.3rem);
  @include font-factory($fs-1, bold);
  @include drop-shadow;

  .highlight-top {
    @include box(98%, 25%, white, 0.2rem, 0, 0.1rem);
    display: flex;
    justify-content: space-between;
    align-items: center;
    .highlight-time {
      @include box(40%, 60%, $yellow, 0.3rem, 0.4rem, 0.2rem);
      border: 1px solid $yellow;
      display: flex;
      justify-content: space-evenly;

      input {
        width: 45%;
        border: none;
        background-color: transparent;
        outline: none;
        font-weight: bold;
        text-align: center;
      }

      ::-webkit-outer-spin-button,
      ::-webkit-inner-spin-button {
        -webkit-appearance: none;
        margin: 0;
      }
    }
    .delete-btn {
      @include box(10%, 60%, $main, 0.2rem, 0, 0.2rem);
      font-size: 0.7rem;
      border: none;
      color: white;
      cursor: pointer;
    }
  }

  .highlight-title {
    @include box(95%, 25%, white, 0.2rem, 0, 0.3rem);
    display: flex;
    align-items: flex-start;

    input {
      width: 70%;
      height: 70%;
      border: none;
      outline: none;
      background-color: $light-gray;
    }
  }

  .highlight-subtitle {
    @include box(95%, 35%, white, 0.2rem, 0, 0.3rem);
    display: flex;
    align-items: flex-start;

    input {
      width: 70%;
      border: none;
      outline: none;
      background-color: $light-gray;
    }
    textarea {
      resize: none;
      outline: none;
      width: 70%;
      border: none;
      background-color: $light-gray;
    }
  }

  label {
    margin-right: 0.5rem;
  }
}
</style>
