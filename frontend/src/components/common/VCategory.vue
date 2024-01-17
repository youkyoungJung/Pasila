<script>

</script>

<template>
    <div class="category">
        <div class="category-type">
            <img src="@/assets/img/category-all.png" />
            <div class="type-name">전체</div>
        </div>
        <div class="category-type">
            <img src="@/assets/img/category-beauty.png" />
            <div class="type-name">뷰티</div>
        </div>
        <div class="category-type">
            <img src="@/assets/img/category-food.png" />
            <div class="type-name">음식</div>
        </div>
        <div class="category-type">
            <img src="@/assets/img/category-fashion.png" />
            <div class="type-name">패션</div>
        </div>
        <div class="category-type">
            <img src="@/assets/img/category-life.png" />
            <div class="type-name">라이프</div>
        </div>
        <div class="category-type">
            <img src="@/assets/img/category-travel.png" />
            <div class="type-name">여행</div>
        </div>
        <div class="category-type">
            <img src="@/assets/img/category-tech.png" />
            <div class="type-name">테크</div>
        </div>
        <div class="category-type">
            <img src="@/assets/img/category-kid.png" />
            <div class="type-name">유아</div>
        </div>
        <div class="category-type">
            <img src="@/assets/img/category-camp.png" />
            <div class="type-name">레저</div>
        </div>
        <div class="category-type">
            <img src="@/assets/img/category-ticket.png" />
            <div class="type-name">티켓</div>
        </div>
    </div>
    
</template>



<style lang="scss" scoped>
.category {

    @include flex-box($justify: space-evenly);
    margin-top: 10px;
    
    img {
        @include box(50px, 50px, none, 0, 5px, 2px);
    }
    
    .category-type {
        text-align: center;
        @include flex-box($direction:column);
        @include font-factory(13px, 500);

        .type-name {
            margin-top: 5px;
        }
    }

}


</style>