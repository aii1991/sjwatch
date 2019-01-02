<template>
  <div>
    <el-table :data="logData" style="width: 100%" v-loading.fullscreen.lock="loading" empty-text="无">
      <el-table-column fixed prop="id" label="id" ></el-table-column>
        <el-table-column prop="uid" label="用户Id"></el-table-column>
      <el-table-column prop="loginIp" label="请求ip" ></el-table-column>
      <el-table-column prop="requestUrl" label="请求地址" ></el-table-column>
      <el-table-column prop="createTime" :formatter="formatterDate" label="创建时间" ></el-table-column>
      <el-table-column prop="modifyTime" :formatter="formatterDate" label="修改时间" ></el-table-column>
    </el-table>
    <el-pagination
      style="text-align:center; margin-top:15px;"
      background
      layout="prev, pager, next"
      :page-size="10"
      :current-page.sync="currentPage"
      :total="total"
      @current-change="handlePageChange">
    </el-pagination>
  </div>
</template>

<script>

export default {
  data() {
    return {
      loading: false,
      logData: [],
      currentPage: 1,
      pageSize: 15,
      total: 0
    }
  },
  props: ['isFetchData'],
  created() {
    this.fetchData(this.currentPage)
  },
  watch: {
    // 如果路由有变化，会再次执行该方法
    '$route': 'fetchData',
    isFetchData: function(newQuestion, oldQuestion) {
      if (newQuestion) {
        this.fetchData(this.currentPage)
      }
    }
  },
  methods: {
    fetchData(currentPage, reqParam) {
      this.loading = true
      this.$store.dispatch('ListLog', { currentPage, pageSize: this.pageSize }
      ).then((response) => {
        this.logData = response.list
        this.total = response.total
        this.loading = false
        this.$message({ showClose: true, message: '列表加载完成', type: 'success' })
      }).catch((reason) => {
        this.loading = false
        this.$message({ showClose: true, message: '列表加载失败请刷新重试', type: 'error' })
      })
    },
    handlePageChange(currentPage) {
      this.fetchData(currentPage)
    },
    formatterDate(row, column, cellValue) {
      return new Date(cellValue).toLocaleString()
    }
  }
}
</script>