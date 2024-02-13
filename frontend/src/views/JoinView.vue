<script setup>
import router from '@/router'
import { ref } from 'vue'
import VLongInput from '@/components/common/VLongInput.vue'
import VShortInput from '@/components/common/VShortInput.vue'
import { joinUser, checkMyEmail, checkMyChannel } from '@/components/api/MemberAPI'
import {
  getEmailAuthNumber,
  checkEmailAuthNumber,
  checkPhoneAuthNumber,
  getPhoneAuthNumber
} from '@/components/api/AuthAPI'

const user = ref({
  email: '',
  name: '',
  channel: '',
  password: '',
  phone: '',
  address: '',
  addressDetail: '',
  gender: '',
  birth: ''
})
const userImage = ref('')

const longData = ref({
  name: {
    title: '이름',
    type: 'text'
  },
  password: {
    title: '비밀번호',
    type: 'password'
  },
  passwordCheck: {
    title: '비밀번호 확인',
    type: 'password'
  },
  addressDetail: {
    title: '상세주소',
    type: 'text'
  },
  birth: {
    title: '생년월일',
    type: 'date'
  }
})

const shortData = ref({
  email: {
    title: '이메일',
    type: 'email',
    text: '중복확인'
  },
  emailCerti: {
    title: '이메일 인증',
    type: 'text',
    text: '인증하기',
    value: ''
  },
  channel: {
    title: '채널명',
    type: 'text',
    text: '중복확인'
  },
  phone: {
    title: '휴대폰번호',
    type: 'number',
    text: '번호받기'
  },
  phoneCheck: {
    title: '인증번호 확인',
    type: 'number',
    text: '인증확인'
  },
  address: {
    title: '주소',
    type: 'text',
    text: '주소검색'
  }
})

//이메일 중복확인
const emailCerti = ref(0)
//이메일 인증번호
const emailCertiNum = ref('')
//이메일 인증확인
const resultCheckEmail = ref(0)
//채널명 중복확인
const channelCerti = ref(0)
//핸드폰 인증번호
const phoneCerti = ref(0)
const formData = new FormData()

//이메일, 비밀번호 유효성검사
const strongEmail = (str) => {
  return /^[A-Za-z0-9_.-]+@[A-Za-z0-9-]+\.[A-Za-z0-9-]/.test(str)
}
const strongPassword = (str) => {
  return /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/.test(str)
}
const uploadImg = (e) => {
  const fileInput = e.target
  if (fileInput && fileInput.files && fileInput.files.length > 0) {
    const file = fileInput.files[0]
    const reader = new FileReader()

    reader.onload = (event) => {
      userImage.value = event.target.result
      formData.append('profileFile', file)
    }

    reader.readAsDataURL(file)
  }
}

const checkEmail = async () => {
  if (user.value.email == '') {
    alert('이메일을 입력해주세요')
    return
  }
  if (!strongEmail(user.value.email)) {
    alert('이메일 형식을 확인해주세요.')
    return
  }
  const res = await checkMyEmail(user.value.email)
  if (res == -1) {
    emailCerti.value = 0
  } else if (res) {
    emailCerti.value = 1
    await getEmailAuthNumber(user.value.email)
  } else {
    emailCerti.value = 2
  }
}

const checkEmailCerti = async () => {
  const res = await checkEmailAuthNumber(user.value.email, emailCertiNum.value)
  if (res) {
    resultCheckEmail.value = 1
  } else {
    resultCheckEmail.value = 2
  }
}
const checkChannel = async () => {
  const res = await checkMyChannel(user.value.channel)
  if (res == -1) {
    channelCerti.value = 0
  } else if (res) {
    channelCerti.value = 1
  } else {
    channelCerti.value = 2
  }
}

const sendPhoneNum = async () => {
  await getPhoneAuthNumber(user.value.phone)
}

const checkCertiNum = async () => {
  const res = await checkPhoneAuthNumber(user.value.phone, phoneCerti.value)
  if (res === -1) {
    phoneCerti.value = 0
  } else if (res) {
    phoneCerti.value = 1
  } else {
    phoneCerti.value = 2
  }
}

//주소검색
const openPostCode = async () => {
  new window.daum.Postcode({
    oncomplete: (data) => {
      if (user.value.addressDetail !== '') {
        user.value.addressDetail = ''
      }
      if (data.userSelectedType === 'R') {
        // 사용자가 도로명 주소를 선택했을 경우
        user.value.address = data.roadAddress
      } else {
        // 사용자가 지번 주소를 선택했을 경우(J)
        user.value.address = data.jibunAddress
      }

      // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
      if (data.userSelectedType === 'R') {
        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
        if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
          user.value.addressDetail = data.bname
        }
        // 건물명이 있고, 공동주택일 경우 추가한다.
        if (data.buildingName !== '' && data.apartment === 'Y') {
          user.value.addressDetail +=
            user.value.addressDetail !== '' ? `, ${data.buildingName}` : data.buildingName
        }
        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
        if (user.value.addressDetail !== '') {
          user.value.addressDetail = `(${user.value.addressDetail})`
        }
      } else {
        user.value.addressDetail = ''
      }
    }
  }).open()
}

//회원가입
const join = async () => {
  if (!strongEmail) {
    alert('이메일 확인해주세요.')
    return
  }
  if (
    !strongPassword(user.value.password) ||
    user.value.password == '' ||
    user.value.password != user.value.passwordCheck
  ) {
    alert('비밀번호를 확인해주세요.')
    return
  }
  if (phoneCerti.value == 2) {
    alert('핸드폰 인증을 완료해주세요.')
    return
  }
  if (user.value.password == null) user.value.password = ''
  if (user.value.gender == '여성') user.value.gender = 'F'
  else if (user.value.gender == '남성') user.value.gender = 'M'
  else user.value.gender = ''

  formData.append('member', new Blob([JSON.stringify(user.value)], { type: 'application/json' }))

  console.log(user.value)

  const res = await joinUser(formData)
  if (res) {
    alert('회원가입이 완료 되었습니다.')
    router.push('/')
  } else alert('정보를 다시 확인해주세요.')
}
</script>

<template>
  <div class="container">
    <div class="header">회원가입</div>
    <div class="content">
      <section class="profile">
        <div>
          <div v-if="userImage != ''">
            <img :src="userImage" class="profile-img" />
          </div>
          <div v-else>
            <font-awesome-icon icon="fa-regular fa-user" class="profile-img" />
          </div>
        </div>
        <label for="imageFile">프로필 사진 등록</label>
        <input
          type="file"
          id="imageFile"
          @change="uploadImg"
          accept="image/*"
          class="profile-choose"
        />
      </section>
      <section class="userInfo">
        <v-short-input
          :data="shortData.email"
          @getData="(e) => (user.email = e)"
          @sendData="(e) => checkEmail(e)"
        />
        <div v-if="emailCerti == 1" class="check-text">
          사용가능한 이메일입니다. 인증을 위해 이메일을 확인해주세요.
        </div>
        <v-short-input
          :data="shortData.emailCerti"
          @getData="
            (e) => {
              emailCertiNum = e
            }
          "
          @sendData="(e) => checkEmailCerti(e)"
        />
        <div v-if="resultCheckEmail == 1" class="check-text">이메일 인증이 완료되었습니다.</div>
        <div v-else-if="resultCheckEmail == 2" class="wrong-text">
          인증번호를 다시 입력해주세요.
        </div>

        <div v-else-if="emailCerti == 2" class="wrong-text">
          중복된 이메일입니다. 다른 이메일을 사용해 주세요.
        </div>
      </section>
      <section class="userInfo">
        <v-long-input :data="longData.name" @getData="(e) => (user.name = e)" />
      </section>
      <section class="userInfo">
        <v-short-input
          :data="shortData.channel"
          @getData="(e) => (user.channel = e)"
          @sendData="(e) => checkChannel(e)"
        />
        <div v-if="channelCerti == 1" class="check-text">사용가능한 채널명입니다.</div>
        <div v-else-if="channelCerti == 2" class="wrong-text">
          중복된 채널명입니다. 다른 채널명을 사용해 주세요.
        </div>
      </section>
      <section class="userInfo">
        <v-long-input :data="longData.password" @getData="(e) => (user.password = e)" />
      </section>
      <section class="userInfo">
        <v-long-input :data="longData.passwordCheck" @getData="(e) => (user.passwordCheck = e)" />
        <div
          v-if="
            strongPassword(user.password) &&
            user.password != '' &&
            user.password === user.passwordCheck
          "
          class="check-text"
        >
          비밀번호가 일치합니다.
        </div>
        <div v-else-if="!strongPassword(user.password)" class="wrong-text">
          8글자 이상, 영문, 숫자, 특수문자(@$!%*#?&)를 포함해야 합니다.
        </div>
        <div v-else class="wrong-text">비밀번호가 일치하지 않습니다. 다시 입력해주세요.</div>
      </section>
      <section class="userInfo">
        <v-short-input
          :data="shortData.phone"
          @getData="(e) => (user.phone = e)"
          @sendData="(e) => sendPhoneNum(e)"
        />
      </section>
      <section class="userInfo">
        <v-short-input
          :data="shortData.phoneCheck"
          @getData="(e) => (phoneCerti = e)"
          @sendData="(e) => checkCertiNum(e)"
        />
        <div v-if="phoneCerti == 1" class="check-text">인증번호가 일치합니다.</div>
        <div v-else-if="phoneCerti == 2" class="wrong-text">
          인증번호가 다릅니다. 다시 입력해주세요.
        </div>
      </section>
      <section class="userInfo">
        <v-short-input
          :data="shortData.address"
          :inputData="user.address"
          @getData="(e) => (user.address = e)"
          @sendData="(e) => openPostCode(e)"
        />
      </section>
      <section class="userInfo">
        <v-long-input
          :data="longData.addressDetail"
          :inputData="user.addressDetail"
          @getData="(e) => (user.addressDetail = e)"
        />
      </section>
      <section class="userInfo">
        <div class="gender">
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
        </div>
      </section>
      <section class="userInfo">
        <label for="input" class="label">생년월일</label>
        <input type="date" v-model="user.birth" class="birth" />
      </section>
      <div class="join-btn">
        <button @click="join" class="join">회원가입</button>
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
      width: 100%;
      display: flex;
      flex-direction: column;
      align-items: center;
      margin-bottom: 0.2rem;

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
    .join-btn {
      width: 88%;
      height: 100%;
      display: flex;
      justify-content: center;
      border-top: 1px solid $dark;
      margin: 2rem;
      padding-top: 1rem;
      .join {
        @include box(100%, 3rem, $main, 0, 1rem, 0);
        @include font-factory($fs-1, null, white);
        border: none;
        cursor: pointer;
      }
    }
  }
}
</style>
