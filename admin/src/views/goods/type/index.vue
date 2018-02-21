<template>
    <div class="app-container" >
        <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="商品类型列表" name="first">
                <list-component :is-fetch-data="isFetchData" />
            </el-tab-pane>
            <el-tab-pane label="添加商品类型" name="second">
                <AddComponent />
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
  components: {
    AddComponent,
    listComponent
  },
  methods: {
    handleClick(tab, event) {
      if (tab.index === '0') {
        this.isFetchData = this.$store.state.goods.isFetchGoodsTypeData
      }
    }
  }
}
</script>