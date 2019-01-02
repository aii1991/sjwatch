<template>
  <div>
    <el-table :data="bannerData" style="width: 100%" v-loading.fullscreen.lock="loading" empty-text="无" @row-click="handleClick">
      <el-table-column fixed prop="id" label="id" ></el-table-column>
      <el-table-column prop="gId" label="商品id"></el-table-column>
       <el-table-column label="商品广告图" >
          <template slot-scope="scope">
            <img :src ="scope.row.src" width="200px" height="100px"/>
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
      bannerData: []
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.loading = true
      this.$store.dispatch('ListBanner').then((response) => {
        this.bannerData = response
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
      console.log(row)
      var goodsId = row['gId']
      this.loading = true
      this.$store.dispatch('GetGoodsById', goodsId).then((response) => {
        this.$router.push({
          name: 'goodsDetail',
          params: {
            param: {
              type: response.type,
              tBId: response.tBId,
              tBName: '',
              data: response
            }
          }
        })
      }).catch((reason) => {
        this.loading = false
        this.$message({ showClose: true, message: '获取数据失败', type: 'error' })
      })
    },
    handleDeleteClick(scope, event) {
      if (event) {
        event.stopPropagation()
      }
      this.loading = true
      var id = scope.row.id
      this.$store.dispatch('DeleteBanner', id).then((response) => {
        this.loading = false
        this.bannerData.splice(scope.$index, 1)
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