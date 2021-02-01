<template>
 <div class="app-padding" v-loading.fullscreen.lock="loading" style="width: 600px;" >
    <el-form ref="goodsForm" :model="goodsForm" label-width="100px">
      <el-form-item label="类型" prop="type">
        <el-select v-model="goodsForm.type" placeholder="请选择" disabled>
            <el-option
                v-for="item in goodsTypeData"
                :key="item.id"
                :label="item.name"
                :value="item.id">
            </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="品牌" prop="tBId">
        <el-select v-model="goodsForm.tBId" disabled placeholder="请选择">
            <el-option
                v-for="item in goodsBrandData"
                :key="item.id"
                :label="item.name"
                :value="item.id">
            </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="商品id" prop="name">
        <span>{{ goodsForm.id }}</span>
      </el-form-item>
      <el-form-item label="名称" prop="name">
        <span>{{ goodsForm.name }}</span>
      </el-form-item>
      <el-form-item label="编号" prop="number">
        <span>{{ goodsForm.number }}</span>
      </el-form-item>
      <el-form-item label="零售价" prop="salePrice">
        <span>{{ goodsForm.salePrice }}</span>
      </el-form-item>
      <el-form-item label="批发价" prop="wholesale">
        <span>{{ goodsForm.wholesale }}</span>
      </el-form-item>
      <el-form-item label="采购价" prop="purchasePrice">
        <span>{{ goodsForm.purchasePrice }}</span>
      </el-form-item>
      <el-form-item label="库存" prop="stock">
        <span>{{ goodsForm.stock }}</span>
      </el-form-item>
      <!-- <el-form-item label="描述" prop="descr">
        <span>{{ goodsForm.descr }}</span>
      </el-form-item> -->
      <el-form-item label="图片描述">
         <el-upload ref="upload"
            :action="uploadUrl"
            list-type="picture-card"
            name="file"
            disabled
            :file-list="goodsForm.sources"
            :on-preview="handlePictureCardPreview">
            <i class="el-icon-plus"></i>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
      </el-form-item>
      <el-form-item>
        <img width="200" height="100" :src="goodsForm.coverSrc"/>
      </el-form-item>
    </el-form>

    <el-button type="primary" v-if="isShowAddToBanner" @click="handleAddToBanner">加入主页广告</el-button>
    <el-button type="danger" @click="handleDelete">删除</el-button>
 </div>
</template>


<script>
import { getGoodsTypeList } from '@/utils/sjcookies'

export default {
  data() {
    return {
      goodsForm: {
        id: this.param.data.id,
        type: this.param.type,
        tBId: this.param.tBId,
        name: this.param.data.name,
        number: this.param.data.number,
        descr: this.param.data.descr,
        salePrice: this.param.data.salePrice,
        wholesale: this.param.data.wholesale,
        purchasePrice: this.param.data.purchasePrice,
        stock: this.param.data.stock,
        sources: JSON.parse(this.param.data.sources),
        coverSrc: this.param.data.coverSrc
      },
      goodsTypeData: this.getGoodsTypeData(),
      goodsBrandData: [{
        id: this.param.tBId,
        name: this.param.tBName
      }],
      brandSelectDisabled: false,
      loading: false,
      uploadUrl: process.env.FILE_UPLOAD_URL,
      dialogImageUrl: '',
      dialogVisible: false,
      uploadFileLimit: 9,
      isShowAddToBanner: this.param.isShowAddToBanner ? true : this.param.isShowAddToBanner
    }
  },
  props: ['param'],
  methods: {
    fetchGoodsBrandData(goodsTypsId) {
      this.$store.dispatch('ListGoodsBrand', goodsTypsId).then((response) => {
        this.goodsBrandData = response
        this.brandSelectDisabled = false
      }).catch((reason) => {
        this.$message({ showClose: true, message: '获取商品品牌失败,原因=>' + reason, type: 'error' })
      })
    },
    getGoodsTypeData() {
      var data = this.$store.state.goods.goodsTypeData
      if (data) {
        return data
      }
      return getGoodsTypeList()
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
    handleAddToBanner() {
      this.loading = true
      this.$store.dispatch('AddBanner', { 'gId': this.goodsForm.id, 'coverSrc': this.goodsForm.coverSrc }).then(response => {
        this.loading = false
        this.$message({ showClose: true, message: '加入成功', type: 'success' })
      }).catch((reason) => {
        this.loading = false
        this.$message({ showClose: true, message: '加入失败', type: 'error' })
      })
    },
    handleDelete() {
      this.$confirm('是否继续删除?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.invokeDelete()
      }).catch(() => {
        this.$message({ type: 'info', message: '已取消删除' })
      })
    },
    invokeDelete() {
      this.loading = true
      this.$store.dispatch('DeleteGoods', this.goodsForm.id).then(response => {
        this.loading = false
        this.$message({ showClose: true, message: '删除成功', type: 'error' })
        this.$router.go(-1)
      }).catch((reason) => {
        this.loading = false
        this.$message({ showClose: true, message: '删除失败', type: 'error' })
      })
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>