<script setup>
import steps from '@/components/ready/Steps.vue'
import ProductInput from '@/components/ready/ProductInput.vue'
import NextButton from '@/components/ready/NextButton.vue'
import Preview from '@/components/editor/Preview.vue'
import Editor from '@/components/editor/Editor.vue'

import { ref, watch } from 'vue'

const step = ref('register')
const nextStep = ref('script')
const preview = ref('')

const update = (message) => {
  preview.value = message
}

const sendProduct = () => {
  //상품 정보 보내기
}

const currentTab = ref(0)
const tabs = ref(['작성하기', '미리보기'])
</script>

<template>
  <div class="container">
    <steps :data="step" />
    <div class="body">
      <div class="input-body">
        <product-input />
      </div>
      <div class="editor-body">
        <div id="tabs" class="tabs">
          <ul class="tab-menu">
            <li
              v-for="(tab, index) in tabs"
              v-bind:class="[currentTab == index ? 'active-tab' : 'none-tab']"
            >
              <a href="#" @click="currentTab = index">{{ tab }}</a>
            </li>
          </ul>
        </div>
        <div v-show="currentTab == 0" class="show-body">
          <editor :message="preview" @preview-content="update" />
        </div>
        <div v-show="currentTab == 1" class="show-body">
          <preview :preview="preview" />
        </div>
      </div>
    </div>
    <next-button :data="nextStep" @click="sendProduct" />
  </div>
</template>

<style lang="scss" scoped>
.container {
  @include box(95%, 100%, none, 0.3rem, 0.8rem, 0.5rem);

  .body {
    @include box(100%, 80%, white, 0, 0.3rem, 0.1rem);
    @include flex-box($align: flex-start, $justify: space-evenly);
    border: 2px solid $main;
    border-radius: 0.5rem;

    .input-body {
      @include box(48%, 100%, white, 0, 0.3rem, 0.1rem);
      display: flex;
      flex-direction: column;
      align-items: center;
    }

    .editor-body {
      @include box(48%, 100%, white, 0, 0.3rem, 0.1rem);
      display: flex;
      flex-direction: column;
      border-left: 1px solid $main;
      min-height: 30rem;
      margin-top: 2rem;

      .tabs {
        display: flex;
        flex-direction: row;
        list-style: none;
        font-size: 0.08rem;

        .tab-menu {
          @include box(90%, 100%, white, 0.1rem, 0, 0);
          display: flex;
          flex-direction: row;
          margin-left: 2.5rem;

          .none-tab {
            @include box(5rem, 2rem, $light-gray, 0.1rem, 0, 0);
            @include font-factory($fs-1, null, $dark);
          }
          .active-tab {
            @include box(5rem, 2rem, $main, 0.1rem, 0, 0);
            @include font-factory($fs-1, null, $dark);
            color: white;
          }
          li {
            list-style: none;
            text-align: center;
            margin-bottom: 0;

            a,
            a:visited {
              vertical-align: text-bottom;
              text-decoration-line: none;
              color: inherit;
            }
          }
        }
      }

      .show-body {
        @include box(98%, 100%, none, 0, 0, 0);
        display: flex;
        justify-content: center;
      }
    }
  }
}
</style>
