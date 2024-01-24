<script setup>
const myProductList = [
  {
    name: '겨울에 필수! 앙고라 니트',
    thumbnail: '/src/assets/img/test/live-thumb.png',
    createdAt: '2022/01/02 11:11:00',
    ProductOptions: [
      {
        name: '핑크',
        stock: 30,
        price: 36000,
        discountPrice: 20000
      },
      {
        name: '블루',
        stock: 20,
        price: 36000,
        discountPrice: 20000
      },
      {
        name: '블랙',
        stock: 31,
        price: 36000,
        discountPrice: 20000
      }
    ]
  },
  {
    name: '겨울에 필수! 앙고라 니트2',
    thumbnail: '/src/assets/img/test/live-thumb.png',
    createdAt: '2022/01/02 11:11:00',
    ProductOptions: [
      {
        name: '핑크',
        stock: 30,
        price: 36000,
        discountPrice: 20000
      },
      {
        name: '블루',
        stock: 20,
        price: 36000,
        discountPrice: 20000
      },
      {
        name: '블랙',
        stock: 31,
        price: 36000,
        discountPrice: 20000
      }
    ]
  },
  {
    name: '겨울에 필수! 앙고라 니트3',
    thumbnail: '/src/assets/img/test/live-thumb.png',
    createdAt: '2022/01/02 11:11:00',
    ProductOptions: [
      {
        name: '핑크',
        stock: 30,
        price: 36000,
        discountPrice: 20000
      },
      {
        name: '블루',
        stock: 20,
        price: 36000,
        discountPrice: 20000
      },
      {
        name: '블랙',
        stock: 0,
        price: 36000,
        discountPrice: 20000
      }
    ]
  }
]
</script>

<template>
  <div class="orders">
    <section class="top">
      <h1 class="title">주문 관리</h1>
      <h3 class="subtitle">내 판매 상품의 주문, 배송 상태를 확인하고 관리하세요.</h3>
    </section>
    <section class="bottom">
      <table>
        <thead>
          <tr>
            <th>상품 정보</th>
            <th>등록일</th>
            <th>재고</th>
            <th>주문 리스트</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, index) in myProductList" :key="index">
            <td class="product">
              <div class="thumbnail" :style="{ backgroundImage: `url(${item.thumbnail})` }"></div>
              <div class="right">
                <span class="name">{{ item.name }}</span>
                <div class="price-line">
                  <span class="price">{{
                    item.ProductOptions[0].price.toLocaleString('kr-KR')
                  }}</span>
                  <span class="discount-price">{{
                    item.ProductOptions[0].discountPrice.toLocaleString('kr-KR')
                  }}</span>
                </div>
              </div>
            </td>
            <td class="date">{{ item.createdAt.substring(0, 10) }}</td>
            <td class="option-stock">
              <template v-for="(option, index2) in item.ProductOptions" :key="index2">
                <div>
                  <span class="name">{{ option.name }} : </span>
                  <span class="stock">{{ option.stock === 0 ? '품절' : option.stock }}</span>
                </div>
              </template>
            </td>
            <td class="list-btn">
              <span>
                <font-awesome-icon icon="fa-solid fa-chevron-down" />
              </span>
            </td>
          </tr>
        </tbody>
      </table>
    </section>
  </div>
</template>

<style lang="scss" scoped>
.orders {
  padding: 1rem 5rem;
  .top {
    @include flex-box(flex-end, flex-start);
    .title {
      @include font-factory($fs-5, bold);
      margin: 0;
    }
    .subtitle {
      @include font-factory($fs-1, 400);
      margin: 0 0 0 1rem;
    }
  }
  .bottom {
    @include flex-box(flex-start, center);
    padding-top: 3rem;
    table {
      border-collapse: collapse;
      tr {
        // border: 1px solid black;
        border-bottom: 1px solid $gray;
      }
      th {
        padding: 1.5rem 0;
        //   border-bottom: 1px solid $gray;
      }
      tbody {
        td {
        }
        .product {
          @include flex-box(flex-start, flex-start);
          padding: 2.5rem 5rem;
          .thumbnail {
            @include box(10rem, 5rem, 0, 1.25rem, null, null);
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
          }
          .right {
            padding: 0 1rem;
            .name {
              @include font-factory($fs-3, 500);
            }
            .price-line {
              margin-top: 1rem;
              .price {
                @include font-factory($fs-2, 500);
                text-decoration: line-through;
              }
              .discount-price {
                @include font-factory($fs-3, bold, $main);
                margin: 0 1rem;
              }
            }
          }
        }
        .date {
          padding: 0 5rem;
        }
        .option-stock {
          padding: 0 5rem;
        }
        .list-btn {
          text-align: center;
          padding: 0 5rem;
          span {
            cursor: pointer;
          }
        }
      }
    }
  }
}
</style>
