<script setup>
import router from '@/router'
import { ref } from 'vue'
import VLongInput from '@/components/common/VLongInput.vue'
import VShortInput from '@/components/common/VShortInput.vue'

const user = ref({
  profile: new URL('@/assets/img/jenny.jpg', import.meta.url).href,
  email: 'pasila@ssafy.com',
  name: '파시라',
  channelName: '라시파',
  password: '',
  phone: '01012345678',
  address: '서울시 행복구',
  detailAddress: '사랑동',
  bankName: '돈줘은행',
  bankAccount: '3333333333',
  gender: '선택안함',
  birth: '2020-05-05'
})

const longData = ref({
  name: {
    title: '이름',
    type: 'text',
    value: user.value.name
  },
  password: {
    title: '비밀번호',
    type: 'password'
  },
  passwordCheck: {
    title: '비밀번호 확인',
    type: 'password'
  },
  detailAddress: {
    title: '상세주소',
    type: 'text',
    value: user.value.detailAddress
  },
  birth: {
    title: '생년월일',
    type: 'date',
    value: user.value.birth
  },
  bankName: {
    title: '은행명',
    type: 'text',
    value: user.value.bankName
  },
  bankAccount: {
    title: '계좌번호',
    type: 'number',
    value: user.value.bankAccount
  }
})

const shortData = ref({
  email: {
    title: '이메일',
    type: 'email',
    text: '중복확인',
    value: user.value.email
  },
  channel: {
    title: '채널명',
    type: 'text',
    text: '중복확인',
    value: user.value.channelName
  },
  phone: {
    title: '휴대폰번호',
    type: 'number',
    text: '번호받기',
    value: user.value.phone
  },
  phoneCheck: {
    title: '인증번호 확인',
    type: 'number',
    text: '인증확인'
  },
  address: {
    title: '주소',
    type: 'text',
    text: '주소검색',
    value: user.value.address
  }
})

const certi = ref('')

const uploadImg = () => {
  const reader = new FileReader()
  reader.onload = function (e) {
    user.value.profile = e.target.result
  }
  reader.readAsDataURL(file.files[0])
}

const modify = () => {
  //user.value 백에 넘겨주기
  if (user.value.birth != '') {
    user.value.birth += ' 00:00:00'
  }
  router.push('/')
}
</script>

<template>
  <div class="container">
    <div class="header">마이페이지</div>
    <div class="content">
      <section class="profile">
        <div>
          <div v-if="user.profile != ''">
            <img :src="user.profile" id="profileImg" class="profile-img" />
          </div>
          <div v-else="user.profile === ''">
            <font-awesome-icon icon="fa-regular fa-user" class="profile-img" />
          </div>
        </div>
        <label for="file">프로필 사진 변경</label>
        <input type="file" id="file" @change="uploadImg" accept="image/*" class="profile-choose" />
      </section>
      <section class="userInfo">
        <v-short-input :data="shortData.email" @getData="(e) => (user.email = e)" />
      </section>
      <section class="userInfo">
        <v-long-input :data="longData.name" @getData="(e) => (user.name = e)" />
      </section>
      <section class="userInfo">
        <v-short-input :data="shortData.channel" @getData="(e) => (user.channelName = e)" />
      </section>
      <section class="userInfo">
        <v-long-input :data="longData.password" @getData="(e) => (user.password = e)" />
      </section>
      <section class="userInfo">
        <v-long-input :data="longData.passwordCheck" @getData="(e) => (user.passwordCheck = e)" />
        <div v-if="user.password != '' && user.password === user.passwordCheck" class="check-text">
          비밀번호가 일치합니다.
        </div>
        <div v-else class="wrong-text">비밀번호가 일치하지 않습니다. 다시 입력해주세요.</div>
      </section>
      <section class="userInfo">
        <v-short-input :data="shortData.phone" @getData="(e) => (user.phone = e)" />
      </section>
      <section class="userInfo">
        <v-short-input :data="shortData.phoneCheck" @getData="(e) => (certi = e)" />
        <div v-if="certi != ''" class="check-text">인증번호가 일치합니다.</div>
        <div v-else class="wrong-text">인증번호가 다릅니다. 다시 입력해주세요.</div>
      </section>
      <section class="userInfo">
        <v-short-input :data="shortData.address" @getData="(e) => (user.address = e)" />
      </section>
      <section class="userInfo">
        <v-long-input :data="longData.detailAddress" @getData="(e) => (user.detailAddress = e)" />
      </section>
      <section class="userInfo">
        <v-long-input :data="longData.bankName" @getData="(e) => (user.bankName = e)" />
      </section>
      <section class="userInfo">
        <v-long-input :data="longData.bankAccount" @getData="(e) => (user.bankAccount = e)" />
      </section>
      <section class="gender">
        <form>
          <label for="gender" class="gender-title">성별</label>
          <div class="radio" id="gender">
            <label class="gender-option"
              ><input type="radio" name="성별" value="남성" v-model="user.gender" />남성</label
            >
            <label class="gender-option"
              ><input type="radio" name="성별" value="여성" v-model="user.gender" />여성</label
            >
            <label class="gender-option"
              ><input
                type="radio"
                name="성별"
                value="선택안함"
                v-model="user.gender"
              />선택안함</label
            >
          </div>
        </form>
      </section>
      <section class="userInfo">
        <label for="input" class="label">생년월일</label>
        <input type="date" v-model="user.birth" class="birth" />
      </section>
      <div class="modify-btn">
        <button @click="modify" class="modify">수정</button>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.container {
  @include box(500px, 90%, white, 0.625rem, 1rem, 0.1rem);
  @include font-factory(13px, bold);
  @include flex-box($direction: column);
  @include drop-shadow;
  border: 2px solid #d9d9d9;
  margin-left: auto;
  margin-right: auto;
  .header {
    margin-top: 2rem;
    margin-bottom: 1rem;
    @include font-factory($fs-3, bold);
  }
  .content {
    @include box(90%, 90%, none, 0, 0, 0);
    @include font-factory(13px, bold);
    display: flex;
    flex-direction: column;
    align-items: center;
    .profile {
      @include box(100%, 10%, white, 0, 0.2rem, 0.2rem);
      @include flex-box($direction: column);
      margin-bottom: 1rem;

      .profile-img {
        @include box(4rem, 4rem, none, 50%, 0, 0);
      }

      label {
        @include font-factory(10px, null);
        cursor: pointer;
      }
      .profile-choose {
        @include font-factory(13px, null);
        width: 0;
        height: 0;
        padding: 0;
        overflow: hidden;
        border: 0;
      }
    }
    .userInfo {
      @include box(95%, 10%, null, 0, 0, 0);
      display: flex;
      flex-direction: column;
      align-items: center;

      .label {
        width: 90%;
        display: flex;
        justify-content: flex-start;
        text-align: flex-start;
      }

      .birth {
        @include box(90%, 2.5rem, whitesmoke, 0, 0, 0);
        border: none;
        margin-top: 0.2rem;
        padding-left: 0.5rem;
        outline: none;
      }
      ::-webkit-calendar-picker-indicator {
        cursor: pointer;
        width: 10%;
      }
    }

    .check-text {
      margin: 2px;
      color: #0085ff;
      font-size: 0.7rem;
      width: 90%;
      display: flex;
      justify-content: flex-start;
      text-align: flex-start;
      margin-bottom: 0.3rem;
    }
    .wrong-text {
      margin: 2px;
      color: $main;
      font-size: 0.7rem;
      width: 90%;
      display: flex;
      justify-content: flex-start;
      text-align: flex-start;
      margin-bottom: 0.3rem;
    }

    .gender {
      width: 95%;
      display: flex;
      flex-direction: column;
      justify-content: flex-start;
      margin-bottom: 0.2rem;

      form {
        width: 100%;
        display: flex;
        flex-direction: column;
        align-items: center;

        .gender-title {
          width: 90%;
          display: flex;
          justify-content: flex-start;
        }
        .radio {
          @include font-factory(0.7rem, null);
          width: 100%;
          display: flex;
          justify-content: space-evenly;
        }

        .gender-option {
          display: flex;
          justify-content: flex-start;
          align-items: center;
          margin-right: 0.3rem;
          cursor: pointer;
        }
      }
    }
    .modify-btn {
      width: 88%;
      height: 100%;
      display: flex;
      justify-content: center;
      border-top: 1px solid $dark;
      margin: 2rem;
      padding-top: 1rem;
      .modify {
        @include box(100%, 3rem, $main, 0, 1rem, 0);
        @include font-factory($fs-1, null, white);
        border: none;
        cursor: pointer;
      }
    }
  }
}
</style>