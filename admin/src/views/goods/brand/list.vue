<template>
  <div>
    <el-table :data="goodsBrandData" style="width: 100%" v-loading.fullscreen.lock="loading" @row-click="handleClick">
      <el-table-column fixed prop="id" label="id" ></el-table-column>
      <el-table-column prop="name" label="商品品牌"></el-table-column>
      <el-table-column label="商品品牌logo" >
          <template slot-scope="scope">
            <img :src ="scope.row.logo" width="50px" height="50px"/>
          </template>
      </el-table-column>
      <el-table-column prop="createTime" :formatter="formatterDate" label="创建时间" ></el-table-column>
      <el-table-column prop="modifyTime" :formatter="formatterDate" label="修改时间" ></el-table-column>
      <el-table-column label="操作" fixed="right" width="100px">
          <template slot-scope="scope">
           <el-button @click="handleDeleteClick(scope,$event)" type="text" size="small">删除</el-button>
          </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loading: false,
      goodsBrandData: []
    }
  },
  props: ['isFetchData', 'goodsTypeId', 'goodsTypeName'],
  created() {
    this.fetchData()
  },
  watch: {
    // 如果路由有变化，会再次执行该方法
    '$route': 'fetchData',
    isFetchData: function(newQuestion, oldQuestion) {
      if (newQuestion) {
        this.fetchData()
      }
    }
  },
  methods: {
    fetchData() {
      this.loading = true
      this.$store.dispatch('ListGoodsBrand', this.goodsTypeId).then((response) => {
        this.goodsBrandData = response
        this.loading = false
        this.$message({ showClose: true, message: '列表加载完成', type: 'success' })
      }).catch((reason) => {
        this.loading = false
        this.$message({ showClose: true, message: '列表加载失败请刷新重试', type: 'error' })
      })
    },
    handleClick(row, event, column) {
      if (event) {
        event.stopPropagation()
      }
      this.$router.push({
        name: 'goods',
        params: {
          goodsTypeId: this.goodsTypeId,
          goodsBrandId: row.id,
          param: {
            goodsTypeName: this.goodsTypeName,
            goodsBrandName: row.name
          }
        }
      })
    },
    handleDeleteClick(scope, event) {
      if (event) {
        event.stopPropagation()
      }
      this.loading = true
      var id = scope.row.id
      this.$store.dispatch('DeleteGoodsBrand', id).then((response) => {
        this.loading = false
        this.goodsBrandData.splice(scope.$index, 1)
        this.$message({ showClose: true, message: '删除成功', type: 'success' })
      }).catch((reason) => {
        this.loading = false
        this.$message({ showClose: true, message: '删除失败', type: 'error' })
      })
    },
    formatterDate(row, column, cellValue) {
      return new Date(cellValue).toLocaleString()
    }
  }
}
</script>