<template>
 <div class="app-padding" v-loading.fullscreen.lock="loading" style="width: 600px;" >
    <el-form ref="goodsForm" :model="goodsForm" :rules="rules" label-width="100px">
      <el-form-item label="类型" prop="type">
        <el-select v-model="goodsForm.type" placeholder="请选择" @change="handleGoodsTypeChange">
            <el-option
                v-for="item in goodsTypeData"
                :key="item.id"
                :label="item.name"
                :value="item.id">
            </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="品牌" prop="tBId">
        <el-select v-model="goodsForm.tBId" :disabled="brandSelectDisabled" placeholder="请选择" @change="handleGoodsBrandChange">
            <el-option
                v-for="item in goodsBrandData"
                :key="item.id"
                :label="item.name"
                :value="item.id">
            </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="名称" prop="name">
        <el-input v-model="goodsForm.name" clearable></el-input>
      </el-form-item>
      <el-form-item label="编号" prop="number">
        <el-input v-model="goodsForm.number" clearable></el-input>
      </el-form-item>
      <el-form-item label="零售价" prop="salePrice">
        <el-input v-model.number="goodsForm.salePrice" clearable></el-input>
      </el-form-item>
      <el-form-item label="批发价" prop="wholesale">
        <el-input v-model.number="goodsForm.wholesale" clearable></el-input>
      </el-form-item>
      <el-form-item label="采购价" prop="purchasePrice">
        <el-input v-model.number="goodsForm.purchasePrice" clearable></el-input>
      </el-form-item>
      <el-form-item label="库存" prop="stock">
        <el-input v-model.number="goodsForm.stock" clearable></el-input>
      </el-form-item>
      <el-form-item label="描述" prop="descr">
        <el-input v-model.number="goodsForm.descr" type="textarea" clearable></el-input>
      </el-form-item>
      <el-form-item label="图片描述">
         <el-upload ref="upload"
            :action="uploadUrl"
            list-type="picture-card"
            name="file"
            :limit="uploadFileLimit"
            :data="uploadToken"
            :on-exceed="handleExceed"
            :on-success="handleUpload"
            :on-error="handleUploadError"
            :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove">
            <i class="el-icon-plus"></i>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">提交</el-button>
        <el-button @click="resetForm('goodsForm')">重置</el-button>
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
        type: '',
        tBId: '',
        name: '',
        number: '',
        descr: '',
        salePrice: 0,
        wholesale: 0,
        purchasePrice: 0,
        stock: 0,
        sources: []
      },
      rules: {
        type: [
          { required: true, message: '请选择类型', trigger: 'change' }
        ],
        tBId: [
          { required: true, message: '请选择品牌', trigger: 'change' }
        ],
        name: [
          { required: true, message: '请输入名称', trigger: 'blur' },
          { min: 3, max: 30, message: '长度在 3 到 30 个字符', trigger: 'blur' }
        ],
        salePrice: [
          { required: true, message: '请输入零售价', trigger: 'blur' },
          { type: 'number', message: '零售价必须是数字' }
        ],
        wholesale: [
          { required: true, message: '请输入批发价', trigger: 'blur' },
          { type: 'number', message: '批发价必须是数字' }
        ],
        purchasePrice: [
          { required: true, message: '请输入采购价', trigger: 'blur' },
          { type: 'number', message: '采购价必须是数字' }
        ],
        stock: [
          { required: true, message: '请输入库存', trigger: 'blur' },
          { type: 'number', message: '库存必须是数字' }
        ]
      },
      goodsTypeData: this.getGoodsTypeData(),
      goodsBrandData: [],
      brandSelectDisabled: true,
      loading: false,
      uploadToken: {
        token: this.$store.getters.uploadToken
      },
      uploadUrl: process.env.FILE_UPLOAD_URL,
      dialogImageUrl: '',
      dialogVisible: false,
      uploadFileLimit: 9
    }
  },
  props: ['goodsTypeId'],
  methods: {
    fetchGoodsBrandData(goodsTypsId) {
      this.$store.dispatch('ListGoodsBrand', goodsTypsId).then((response) => {
        this.goodsBrandData = response
        this.brandSelectDisabled = false
      }).catch((reason) => {
        this.$message({ showClose: true, message: '获取商品品牌失败,原因=>' + reason, type: 'error' })
      })
    },
    handleGoodsTypeChange(value) {
      this.type = value
      this.fetchGoodsBrandData(value)
    },
    handleGoodsBrandChange(value) {
      this.tBId = value
    },
    onSubmit() {
      this.$refs['goodsForm'].validate((valid) => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('AddGoods', {
            type: this.goodsForm.type,
            tBId: this.goodsForm.tBId,
            name: this.goodsForm.name,
            number: this.goodsForm.number,
            descr: this.goodsForm.descr,
            salePrice: this.goodsForm.salePrice,
            wholesale: this.goodsForm.wholesale,
            purchasePrice: this.goodsForm.purchasePrice,
            stock: this.goodsForm.stock,
            sources: JSON.stringify(this.goodsForm.sources)
          }).then(() => {
            this.resetForm('goodsForm')
            this.loading = false
            this.$message({ showClose: true, message: '添加成功', type: 'success' })
          }).catch(reason => {
            this.loading = false
            this.$message({ showClose: true, message: reason, type: 'error' })
          })
        } else {
          this.$message({ showClose: true, message: '请检查输入内容是否有误', type: 'error' })
          return false
        }
      })
    },
    getGoodsTypeData() {
      var data = this.$store.state.goods.goodsTypeData
      if (data) {
        return data
      }
      return getGoodsTypeList()
    },
    handleRemove(file, fileList) {
      var rvIndex = -1
      for (var i = 0; i < this.goodsForm.sources.length; i++) {
        var item = this.goodsForm.sources[i]
        if (item.id === file.response.hash) {
          rvIndex = i
          break
        }
      }
      if (rvIndex !== -1) {
        this.goodsForm.sources.splice(rvIndex, 1)
      }
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
    handleUpload(response, file, fileList) {
      var baseDownloadUrl = process.env.BASE_DOWNLOAD_URL
      var fileUrl = baseDownloadUrl + '/' + response.key
      this.goodsForm.sources.push({
        'id': response.hash,
        'url': fileUrl,
        'name': file.name,
        'size': file.size
      })
    },
    handleUploadError(err, file, fileList) {
      this.$message({ showClose: true, message: '上传失败,' + err, type: 'error' })
    },
    handleExceed(files, fileList) {
      this.$message({ showClose: true, message: 'Logo最多上传' + this.uploadFileLimit + '张图片,', type: 'error' })
    },
    resetForm(formName) {
      this.goodsForm.sources = []
      this.$refs.upload.clearFiles()
      this.$refs[formName].resetFields()
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>