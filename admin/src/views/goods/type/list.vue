<template>
  <div>
    <el-table :data="goodsTypeData" style="width: 100%" v-loading.fullscreen.lock="loading" @row-click="handleClick">
      <el-table-column fixed prop="id" label="id"></el-table-column>
      <el-table-column prop="name" label="商品类型名称" ></el-table-column>
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
      goodsTypeData: []
    }
  },
  props: ['isFetchData'],
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
      this.$store.dispatch('ListGoodsType').then((response) => {
        this.goodsTypeData = response
        this.loading = false
        this.$message({ showClose: true, message: '列表加载完成', type: 'success' })
      }).catch((reason) => {
        this.loading = false
        this.$message({ showClose: true, message: '列表加载失败请刷新重试', type: 'error' })
      })
    },
    handleClick(row, event, column) {
      this.$router.push({
        name: 'goodsBrand',
        params: {
          goodsTypeId: row.id,
          param: {
            goodsTypeName: row.name
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
      this.$store.dispatch('DeleteGoodsType', id).then((response) => {
        this.loading = false
        this.goodsTypeData.splice(scope.$index, 1)
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

