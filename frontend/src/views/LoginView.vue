<template>
    <div class="container">
        <div class="header">로그인</div>
        <div class="content">
            <section class="long-type">
                <div class="label">이메일</div>
                <input type="text" placeholder="이메일을 입력하세요" class="long-input">
            </section>
            <section class="long-type">
                <div class="label">비밀번호</div>
                <input type="password" placeholder="비밀번호를 입력하세요" class="long-input">
            </section>
            <section class="forget-pw">
                <!-- <span style="font-size: 8px;">비밀번호를 잊으셨나요?</span> -->
                <button @click="findPassword" class="find-pw">비밀번호를 잊으셨나요?</button>
            </section>
            <button @click="login" class="login">로그인</button>
            <button @click="join" class="join">회원가입</button>
            <hr>
            <div class="social-login" @click="googleLogin">
                <img src="@/assets/img/google-logo.png">
                <button class="social-btn">구글로 시작하기</button>
            </div>
            <div class="social-login" @click="kakaoLogin">
                <img src="@/assets/img/kakao-logo.png">
                <button class="social-btn">카카오로 시작하기</button>
            </div>
            <div class="social-login" @click="naverLogin">
                <img src="@/assets/img/naver-logo.png">
                <button class="social-btn">네이버로 시작하기</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import router from '@/router';
import { initCustomFormatter, ref } from 'vue';
import JoinView from './JoinView.vue';

const user = ref({
    userEmail: '',
    userPassword: '',
});

const findPassword = function() {
    router.push("/findpw");
}

const login = () => {
    console.log("로그인 고고");
}

const join = () => {
    router.push("/join");
}

//소셜 로그인 당시 필수나 선택으로 가져온 정보를 회원가입 페이지에 넣어주기
//그리고 필수적으로 이메일을 받아서 이메일로 로그인 가능!
const googleLogin = () => {
    console.log("google");
    try {
        window.location.href ="https://accounts.google.com/o/oauth2/auth?" +
        "client_id=7996849508-02pnc5dga9l9aae1o138b9ng9irsmpkq.apps.googleusercontent.com&"+
        "redirect_uri=https://localhost:5173/auth/google&"+
        "response_type=token&"+
        "scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile";
    } catch(e) {
        console.log(e)
    }
  
//   const parsedHash = new URLSearchParams(window.location.hash.substring(1));
//   const accessToken = parsedHash.get("access_token");
//   console.log(accessToken);
  // const { data } = await Api.post("oauth/google", { accessToken });
}



const kakaoLogin = () => {
    console.log("kakao");
    // Init() {
    //     window.Kakao.init(process.env.VUE_APP_KAKAO_APP_KEY);
    // },
    
    // GetMe(authObj) {
    //     console.log(authObj);
    //     window.Kakao.API.request({
    //         url: '/v2/user/me',
    //         success: async res => {
    //             console.log(res);
    //             const kakao_account = res.kakao_account;
    //             const req
    //         }
    //     })
    // }
}

const naverLogin = () => {
    console.log("naver")
}
</script>

<style lang="scss" scoped>
.container {
    @include box(500px, 90%, white, 10px, 5px, 5px);
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
        @include flex-box($direction: column, $justify: center);
        @include font-factory(13px, bold);
        
        .long-type {
            @include box(90%, 10%, null, 0, 2px, 5px);

            .label {
                text-align: flex-start;
            }

            .long-input {
                @include box(95%, 50%, whitesmoke, 0, null, 10px);
                border: none;
                margin-top: 5px;
            }
            
        }
        
        .login {
            @include box(80%, 43px, $main, 0, 2px, 5px);
            margin-top: 30px;
            border: none;
            color: white;
        }
        
        .forget-pw {
            @include box(90%, 10%, null, 0, 2px, 5px);
            @include flex-box();
            justify-content: flex-end;
            .find-pw {
                justify-content: flex-end;
                border: none;
                background-color: white;
                cursor: pointer;
                color: $main;
            }

        }
        .join {
            @include box(80%, 43px, white, 0, 2px, 5px);
            margin-top: 10px;
            border: 1px solid $dark;
            cursor: pointer;
        }
    }

    hr {
        width: 90%;
        margin: 35px 0px;
    }

    .social-login {
        @include box(80%, 80%, whitesmoke, 0, 10px, 10px);
        @include flex-box();
        vertical-align: middle;
        cursor: pointer;
        
        img {
            width: 5%;
            height: 5%;
            vertical-align: middle;
            
        }
        .social-btn {
            cursor: pointer;
            background-color: whitesmoke;
            border: none;
            
        }
    }
}
</style>