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
      <el-form-item label="描述" prop="descr">
        <span>{{ goodsForm.descr }}</span>
      </el-form-item>
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
    </el-form>
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
        sources: JSON.parse(this.param.data.sources)
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
      uploadFileLimit: 9
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
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>