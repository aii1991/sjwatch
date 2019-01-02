<template>
  <div>
    <div>
      <el-form :inline="true" :model="searchFrom">
        <el-form-item label="商品名称">
          <el-input v-model="searchFrom.receiverName" placeholder="收货人电话"></el-input>
        </el-form-item>
        <el-form-item label="订单编号">
          <el-input v-model="searchFrom.no" placeholder="商品编号"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSearchSubmit">查询</el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-table :data="orderData" style="width: 100%" v-loading.fullscreen.lock="loading" empty-text="无" @row-click="handleClick">
      <el-table-column fixed prop="id" label="id" ></el-table-column>
      <el-table-column prop="no" label="订单编号" ></el-table-column>
        <el-table-column prop="deliveryName" label="快递名"></el-table-column>
      <el-table-column prop="deliveryNumber" label="快递编号" ></el-table-column>
      <el-table-column prop="status" :formatter="formatterStatus" label="订单状态" ></el-table-column>
      <el-table-column prop="address" label="收货地址" ></el-table-column>
      <el-table-column prop="receiverName" label="收货人姓名" ></el-table-column>
      <el-table-column prop="receiverNumber" label="收货人电话" ></el-table-column>
      <el-table-column prop="goods.name" label="商品名"></el-table-column>
      <el-table-column label="商品缩略图" >
          <template slot-scope="scope">
              <img :src ="getUrl(scope.row.goods.sources)" width="50px" height="50px"/>
          </template>
      </el-table-column>
      <el-table-column prop="goods.salePrice" label="商品价格(元)" ></el-table-column>
      <el-table-column prop="price" label="付款金额(元)" ></el-table-column>
      <el-table-column prop="createTime" :formatter="formatterDate" label="创建时间" ></el-table-column>
      <el-table-column prop="modifyTime" :formatter="formatterDate" label="修改时间" ></el-table-column>
      <el-table-column label="操作" fixed="right" width="100px">
          <template slot-scope="scope">
           <el-button @click="handleEditClick(scope,$event)" type="text" size="small">编辑</el-button>
           <el-button @click="handleDeleteClick(scope,$event)" type="text" size="small">删除</el-button>
          </template>
      </el-table-column>
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
      searchFrom: {
        name: '',
        number: ''
      },
      loading: false,
      orderData: [],
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
      var param = { ...reqParam }
      this.$store.dispatch('ListOrder', { currentPage, pageSize: this.pageSize, param }
      ).then((response) => {
        this.orderData = response.list
        this.total = response.total
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
        name: 'orderDetial',
        params: {
          param: {
            data: row
          }
        }
      })
    },
    handlePageChange(currentPage) {
      this.fetchData(currentPage)
    },
    handleDeleteClick(scope, event) {
      if (event) {
        event.stopPropagation()
      }
      this.$confirm('是否继续删除?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.invokeDelete(scope)
      }).catch(() => {
        this.$message({ type: 'info', message: '已取消删除' })
      })
    },
    invokeDelete(scope) {
      this.loading = true
      var id = scope.row.id
      this.$store.dispatch('DeleteOrder', id).then((response) => {
        this.loading = false
        this.orderData.splice(scope.$index, 1)
        this.$message({ showClose: true, message: '删除成功', type: 'success' })
      }).catch((reason) => {
        console.log(reason)
        this.loading = false
        this.$message({ showClose: true, message: '删除失败', type: 'error' })
      })
    },
    handleEditClick(scope, event) {
      if (event) {
        event.stopPropagation()
      }
      this.$router.push({
        name: 'orderEdit',
        params: {
          param: {
            data: this.orderData[scope.$index]
          }
        }
      })
    },
    onSearchSubmit() {
      this.currentPage = 1
      this.fetchData(this.currentPage, {
        receiverName: this.searchFrom.receiverName,
        no: this.searchFrom.no
      })
    },
    formatterDate(row, column, cellValue) {
      return new Date(cellValue).toLocaleString()
    },
    formatterStatus(row, column, cellValue) {
      if (cellValue === 1) {
        return '待发货'
      } else if (cellValue === 2) {
        return '已发货'
      } else {
        return '已完成'
      }
    },
    getUrl(sources) {
      if (sources === '') {
        return ''
      }
      var sArray = JSON.parse(sources)
      if (sArray.length > 0) {
        return sArray[0].url
      }
      return ''
    }
  }
}
</script>