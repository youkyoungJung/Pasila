<template>
    <div class="container">
        <div class="header">회원가입</div>
        <div class="content">
            <section class="profile">
                <div>
                    <div v-if="user.profile != ''">
                        <img :src="user.profile" id="profileImg" class="profile-img">
                    </div>
                    <div v-else="user.profile === ''">
                        <font-awesome-icon icon="fa-regular fa-user" class="profile-img" />
                    </div>
                </div>
                <label for="file">프로필 사진 등록</label>
                <input type="file" id="file" @change="uploadImg" accept="image/*" class="profile-choose" />
            </section>
            <section class="short-type">
                <label for="email">이메일</label>
                <div class="input-area">
                    <input type="email" id="email" v-model="user.email" placeholder="메일을 입력하세요" class="short-input">
                    <input type="submit" value="중복확인" class="input-btn">
                </div>
            </section>
            <section class="long-type">
                <label for="name">이름</label>
                <input type="text" id="name" v-model="user.name" placeholder="이름을 입력하세요" class="long-input">
            </section>
            <section class="short-type">
                <label for="channelName">채널명</label>
                <div class="input-area">
                    <input type="text" id="channelName" v-model="user.channelName" placeholder="채널명을 입력하세요" class="short-input">
                    <input type="submit" value="중복확인" class="input-btn">
                </div>
            </section>
            <section class="long-type">
                <label for="password">비밀번호</label>
                <input type="password" id="password" v-model="user.password" placeholder="비밀번호를 입력하세요" class="long-input">
            </section>
            <section class="long-type">
                <label for="passwordCheck">비밀번호 확인</label>
                <input type="password" id="passwordCheck" placeholder="비밀번호를 한 번 더 입력하세요" class="long-input">
                <div style="margin: 2px; color: #ff495c; font-size: 10px;">
                    비밀번호 맞는지 확인하는 텍스트
                </div>
            </section>
            <section class="short-type">
                <label for="phoneNumber">휴대폰</label>
                <div class="input-area">
                    <input id="phoneNumber" type="number" v-model="user.phone" placeholder="휴대폰 번호를 입력하세요" class="short-input">
                    <input type="submit" value="번호받기" class="input-btn">
                </div>
            </section>
            <section class="short-type">
                <label for="phoneCheck">인증번호 확인</label>
                <div class="input-area">
                    <input id="phoneCheck" type="number" placeholder="인증번호를 입력하세요" class="short-input">
                    <input type="submit" value="인증확인" class="input-btn">
                </div>
                <div style="margin: 2px; color: #ff495c; font-size: 10px;" >
                    인증번호 확인하는 텍스트
                </div>
            </section>
            <section class="short-type">
                <label for="address">주소</label>
                <div class="input-area">
                    <input type="text" id="address" v-model="user.address" placeholder="주소를 입력하세요" class="short-input">
                    <input type="submit" value="주소검색" class="input-btn">
                </div>
            </section>
            <section class="long-type">
                <label for="detailAddress">상세주소</label>
                <input type="text" id="detailAddress" v-model="user.detailAddress" placeholder="상세주소를 입력하세요" class="long-input">
            </section>
            <section class="long-type">
                <form>
                    <label for="gender">성별</label>
                    <div class="radio" id="gender">
                        <label><input type="radio" name="성별" value="남성" v-model="user.gender">남성</label>
                        <label><input type="radio" name="성별" value="여성" v-model="user.gender">여성</label>
                        <label><input type="radio" name="성별" value="선택안함" v-model="user.gender">선택안함</label>
                    </div>
                </form>
            </section>
            <section class="long-type">
                <label for="birth">생년월일</label>
                <input type="date" id="birth" v-model="user.birth" class="long-input">
            </section>
            <button @click="join" class="join">회원가입</button>
        </div>
    </div>
</template>

<script setup>
import router from '@/router';
import { ref } from 'vue';

const user = ref({
    profile: '',
    email: '',
    name: '',
    channelName: '',
    password: '',
    phone: '',
    address: '',
    detailAddress: '',
    gender: '',
    birth: '',
});

const uploadImg = () => {
    const reader = new FileReader();
    reader.onload = function(e) {
        user.value.profile = e.target.result;
    }
    reader.readAsDataURL(file.files[0]);
    console.log(user.value);
}

    

const join = () => {
    //user.value 백에 넘겨주고 메인페이지 가야함
    if (user.value.birth != '') {
        user.value.birth += ' 00:00:00';
    }
    console.log(user.value)
    router.push("/");
}

</script>

<style lang="scss" scoped>
.container {
    @include box(500px, 100%, white, 10px, 5px, 5px);
    border: 2px solid whitesmoke;
    @include flex-box($direction: column);
    @include drop-shadow;
    vertical-align: middle;
    margin-left: auto;
    margin-right: auto;
    .header {
        margin-top: 20px;
        @include font-factory($fs-3, bold);
    }
    .content {
        @include box(90%, 90%, none, 0, 10px, 10px);
        @include flex-box($direction: column, $justify: none);
        @include font-factory(13px, bold);
        
        .profile {
            @include box(100%, 10%, white, 0, 2px, 5px);
            @include flex-box($direction: column);
            
            .profile-img {
                @include box(60px, 60px, none, 50%, 2px, 5px);
                
                
            }

            label {
                @include font-factory(10px, null);
            }
            .profile-choose {
                @include font-factory(13px, null);
                width: 0;
                height: 0;
                padding: 0;
                overflow: hidden;
                border: 0;
                // text-align: flex-end;
                // border: 1px solid $dark;
                // background-color: white;
                // margin-left: 10px;
                // padding: 5px;
            }
            
        }
        .long-type {
            @include box(90%, 10%, null, 0, 2px, 5px);

            label {
                text-align: flex-start;
            }

            .long-input {
                @include box(95%, 50%, whitesmoke, 0, null, 10px);
                border: none;
                margin-top: 5px;
            }

            
        }

        .short-type {
            @include box(90%, 10%, null, 0, 2px, 5px);
            .label {
                text-align: flex-start;
            }

            .input-area {
                @include box(100%, 60%, null, 0, 2px, null);
                @include flex-box($justify: space-between);

                margin-top: 5px;

                .short-input {
                    @include box(80%, 90%, whitesmoke, 0, null, 10px);
                    border: none;
                }
    
                .input-btn {
                    @include font-factory(13px, null);
                    text-align: flex-end;
                    border: 1px solid $dark;
                    background-color: white;
                    margin-left: 10px;
                    padding: 5px;
                }

            }
        }

    .radio {
        @include flex-box($justify: flex-start);
        @include font-factory(11px, null);

        label {
            margin: 10px 10px 0px 10px;
        }
    }


    .join {
        width: 90%;
        height: 43px;
        margin: 10px;
        border: none;
        background-color: $main;
        color: white;
        cursor: pointer;
    }

    }

}
</style>