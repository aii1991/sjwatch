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

      <el-form-item label="适用性别" prop="sex">
         <el-select v-model="goodsForm.sex" placeholder="男女" @change="handleSexChange">
            <el-option
                v-for="item in sexOpts"
                :key="item.key"
                :label="item.label"
                :value="item.value">
            </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="是否为热门" prop="isHot">
         <el-select v-model="goodsForm.isHot" placeholder="否" @change="handleHotChange">
            <el-option
                v-for="item in booleanOpts"
                :key="item.key"
                :label="item.label"
                :value="item.value">
            </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="是否为推荐" prop="isRecommend">
         <el-select v-model="goodsForm.isRecommend" placeholder="否" @change="handleRecommendChange">
            <el-option
                v-for="item in booleanOpts"
                :key="item.key"
                :label="item.label"
                :value="item.value">
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
            :file-list="goodsForm.sources"
            :limit="uploadFileLimit"
            :data="uploadInfo"
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

      <el-form-item label="封面图片">
         <el-upload ref="uploadCover"
            :action="uploadUrl"
            list-type="picture-card"
            name="file"
            :limit="1"
            :data="uploadInfo"
            :on-exceed="handleExceed"
            :on-success="handleUploadCover"
            :on-error="handleUploadError"
            :on-preview="handlePictureCardPreview"
            :on-remove="handelRemoveCover">
            <i class="el-icon-plus"></i>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">提交</el-button>
        <el-button @click="resetForm('goodsForm')">复原</el-button>
      </el-form-item>
    </el-form>
 </div>
</template>


<script>
import { getGoodsTypeList } from '@/utils/sjcookies'

export default {
  data() {
    return {
      sexOpts: [
        {
          key: 0,
          label: '男女',
          value: 0
        },
        {
          key: 1,
          label: '男',
          value: 1
        },
        {
          key: 2,
          label: '女',
          value: 2
        }
      ],
      booleanOpts: [
        {
          key: 0,
          label: '否',
          value: 0
        },
        {
          key: 1,
          label: '是',
          value: 1
        }
      ],
      goodsForm: {
        type: this.param.type,
        tBId: this.param.tBId,
        name: this.param.data.name,
        number: this.param.data.number,
        descr: this.param.data.descr,
        salePrice: this.param.data.salePrice,
        wholesale: this.param.data.wholesale,
        purchasePrice: this.param.data.purchasePrice,
        stock: this.param.data.stock,
        sex: 0,
        isHot: 0,
        isRecommend: 0,
        sources: JSON.parse(this.param.data.sources),
        coverSrc: ''
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
      goodsBrandData: [{
        id: this.param.tBId,
        name: this.param.tBName
      }],
      brandSelectDisabled: false,
      loading: false,
      uploadInfo: {
        policy: this.getOssUploadInfo()[0],
        OSSAccessKeyId: this.getOssUploadInfo()[1],
        Signature: this.getOssUploadInfo()[2],
        key: 'sj' + new Date().getTime()
      },
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
    handleGoodsTypeChange(value) {
      this.type = value
      this.fetchGoodsBrandData(value)
    },
    handleGoodsBrandChange(value) {
      this.tBId = value
    },
    handleSexChange(value) {
      this.sex = value
    },
    handleHotChange(value) {
      this.isHot = value
    },
    handleRecommendChange(value) {
      this.isRecommend = value
    },
    onSubmit() {
      this.$refs['goodsForm'].validate((valid) => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('ModifyGoods', {
            id: this.param.data.id,
            type: this.goodsForm.type,
            tBId: this.goodsForm.tBId,
            name: this.goodsForm.name,
            number: this.goodsForm.number,
            descr: this.goodsForm.descr,
            salePrice: this.goodsForm.salePrice,
            wholesale: this.goodsForm.wholesale,
            purchasePrice: this.goodsForm.purchasePrice,
            stock: this.goodsForm.stock,
            sex: this.goodsForm.sex,
            isHot: this.isHot,
            isRecommend: this.isRecommend,
            sources: JSON.stringify(this.goodsForm.sources)
          }).then(() => {
            this.loading = false
            this.$message({ showClose: true, message: '编辑成功', type: 'success' })
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
      var fileUrl = this.uploadUrl + '/' + this.uploadInfo.key
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
    },
    getOssUploadInfo() {
      var upToken = this.$store.getters.uploadToken
      return upToken.split('.')
    },
    handelRemoveCover() {
      this.goodsForm.coverSrc = ''
    },
    handleUploadCover() {
      var fileUrl = this.uploadUrl + '/' + this.uploadInfo.key
      this.goodsForm.coverSrc = fileUrl
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>