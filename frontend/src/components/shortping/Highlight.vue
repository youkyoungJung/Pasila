<script setup>
const deleteHighlight = () => {
  //하이라이트 삭제하기
}
</script>

<template>
  <div class="highlight">
    <div class="highlight-top">
      <div class="highlight-time">
        <input type="number" />~
        <input type="number" />
      </div>
      <button @click="deleteHighlight" class="delete-btn">삭제</button>
    </div>
    <div class="highlight-title">
      <label for="title">제목</label>
      <input id="title" type="text" placeholder="제목을 입력해주세요." />
    </div>
    <div class="highlight-subtitle">
      <label for="subtitle">자막</label>
      <input id="subtitle" type="text" placeholder="자막을 입력해주세요." />
    </div>
  </div>
</template>

<style lang="scss" scoped>
.highlight {
  @include box(95%, 10rem, white, 0.2rem, 0, 0.1rem);
  @include font-factory($fs-1, bold);
  @include drop-shadow;

  .highlight-top {
    @include box(95%, 25%, white, 0.2rem, 0, 0.1rem);
    display: flex;
    justify-content: space-between;
    align-items: center;
    .highlight-time {
      @include box(50%, 70%, $yellow, 0.1rem, 0, 0.2rem);
      display: flex;
      justify-content: space-evenly;

      input {
        width: 45%;
        border: none;
        background-color: $yellow;
        outline: none;
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

    input {
      width: 60%;
      border: none;
      outline: none;
      background-color: $light-dark;
    }
  }

  .highlight-subtitle {
    @include box(95%, 35%, white, 0.2rem, 0, 0.3rem);
    input {
      width: 60%;
      border: none;
      outline: none;
      background-color: $light-gray;
    }
  }

  label {
    margin-right: 0.5rem;
  }
}
</style>
