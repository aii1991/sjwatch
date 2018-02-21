<template>
    <div class="app-container" >
        <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="商品品牌列表" name="first">
                <list-component :is-fetch-data="isFetchData" :goods-type-id="goodsTypeId == ':goodsTypeId' ? 0 : goodsTypeId" :goods-type-name="param.goodsTypeName" />
            </el-tab-pane>
            <el-tab-pane label="添加商品品牌" name="second">
                <AddComponent :goods-type-id="goodsTypeId == ':goodsTypeId' ? 0 : goodsTypeId"/>
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
  props: ['goodsTypeId', 'param'],
  components: {
    AddComponent,
    listComponent
  },
  methods: {
    handleClick(tab, event) {
      if (tab.index === '0') {
        this.isFetchData = this.$store.state.goods.isFetchGoodsBrandData
      }
    }
  }
}
</script>