<template>
  <div>
    <div>
      <el-form :inline="true" :model="searchFrom">
        <el-form-item label="商品名称">
          <el-input v-model="searchFrom.name" placeholder="商品名称"></el-input>
        </el-form-item>
        <el-form-item label="商品编号">
          <el-input v-model="searchFrom.number" placeholder="商品编号"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSearchSubmit">查询</el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-table :data="goodsData" style="width: 100%" v-loading.fullscreen.lock="loading" empty-text="无" @row-click="handleClick">
      <el-table-column fixed prop="id" label="id" ></el-table-column>
      <el-table-column label="类型" >
        <template slot-scope="scope">
          <span>{{ goodsTypeName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="品牌" >
         <template slot-scope="scope">
          <span>{{ goodsBrandName }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="name" label="商品名称" ></el-table-column>
       <el-table-column label="商品缩略图" >
          <template slot-scope="scope">
              <img :src ="getUrl(scope.row.sources)" width="50px" height="50px"/>
          </template>
      </el-table-column>
       <el-table-column prop="descr" label="商品描述" ></el-table-column>
      <el-table-column prop="number" label="商品编号" ></el-table-column>
      <el-table-column prop="salePrice" label="销售价" ></el-table-column>
      <el-table-column prop="wholesale" label="批发价" ></el-table-column>
      <el-table-column prop="purchasePrice" label="采购价" ></el-table-column>
      <el-table-column prop="stock" label="库存" ></el-table-column>
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
      goodsData: [],
      currentPage: 1,
      pageSize: 15,
      total: 0
    }
  },
  props: ['isFetchData', 'goodsTypeId', 'goodsBrandId', 'goodsTypeName', 'goodsBrandName'],
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
      var param = { type: this.goodsTypeId, tBId: this.goodsBrandId, ...reqParam }
      this.$store.dispatch('ListGoods', { currentPage, pageSize: this.pageSize, param }
      ).then((response) => {
        this.goodsData = response.list
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
        name: 'goodsDetail',
        params: {
          param: {
            type: this.goodsTypeId,
            tBId: this.goodsBrandId,
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
      this.loading = true
      var id = scope.row.id
      this.$store.dispatch('DeleteGoods', id).then((response) => {
        this.loading = false
        this.goodsData.splice(scope.$index, 1)
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
        name: 'goodsEdit',
        params: {
          param: {
            type: this.goodsTypeId,
            tBId: this.goodsBrandId,
            data: this.goodsData[scope.$index]
          }
        }
      })
    },
    onSearchSubmit() {
      this.currentPage = 1
      this.fetchData(this.currentPage, {
        name: this.searchFrom.name,
        number: this.searchFrom.number
      })
    },
    formatterDate(row, column, cellValue) {
      return new Date(cellValue).toLocaleString()
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