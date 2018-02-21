<template>
    <div class="app-container" >
        <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="商品列表" name="first">
                <list-component :is-fetch-data="isFetchData" :goods-type-id="goodsTypeId" :goods-brand-id="goodsBrandId" :goods-type-name="param.goodsTypeName" :goods-brand-name="param.goodsBrandName"/>
            </el-tab-pane>
            <el-tab-pane label="添加商品" name="second">
                <AddComponent :goods-type-id="goodsTypeId" :goods-brand-id="goodsBrandId" :goods-type-name="param.goodsTypeName" :goods-brand-name="param.goodsBrandName"/>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>
<script>
import AddComponent from './add.vue'
import listComponent from './list.vue'

export default {
  data() {
    return {
      activeName: 'first',
      isFetchData: false
    }
  },
  props: ['goodsTypeId', 'goodsBrandId', 'param'],
  components: {
    AddComponent,
    listComponent
  },
  methods: {
    handleClick(tab, event) {
      if (tab.index === '0') {
        this.isFetchData = this.$store.state.goods.isFetchGoodData
      }
    }
  }
}
</script>