<template>
  <div class="app-container" >
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="订单列表" name="first">
        <list-component :is-fetch-data="isFetchData" />
      </el-tab-pane>
      <el-tab-pane label="添加订单" name="second">
        <AddComponent :goods-id="goodsId"/>
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
      isFetchData: false,
      goodsId: 0
    }
  },
  components: {
    AddComponent,
    listComponent
  },
  methods: {
    handleClick(tab, event) {
      if (tab.index === '0') {
        this.isFetchData = this.$store.state.order.isFetchOrderData
      }
    }
  }
}
</script>
