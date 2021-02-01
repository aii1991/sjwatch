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
      <el-form-item label="短名称" prop="shortName">
        <el-input v-model="goodsForm.shortName" clearable></el-input>
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
      <el-form-item label="描述" prop="descr" hidden>
        <el-input v-model.number="goodsForm.descr" type="textarea" clearable></el-input>
      </el-form-item>
       <el-form-item label="描述">
        <quill-editor v-model="content"
                  ref="myQuillEditor"
                  :options="editorOption"
                  @blur="onEditorBlur($event)"
                  @focus="onEditorFocus($event)"
                  @ready="onEditorReady($event)">
       </quill-editor>
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
            :file-list="coverSrcList"
            :data="uploadInfo"
            :on-exceed="handleCoverExceed"
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
import { quillRedefine } from 'vue-quill-editor-upload'
import { quillEditor } from 'vue-quill-editor'

export default {
  components: { quillEditor, quillRedefine },
  created: function() {
    this.editorOption = quillRedefine(
      {
        // 图片上传的设置
        uploadConfig: {
          action: process.env.FILE_UPLOAD_URL, // 必填参数 图片上传地址
          // 必选参数  res是一个函数，函数接收的response为上传成功时服务器返回的数据
          // 你必须把返回的数据中所包含的图片地址 return 回去
          res: (respnse) => {
            console.log(respnse)
            return respnse
          },
          name: 'file',
          policy: this.getOssUploadInfo()[0],
          OSSAccessKeyId: this.getOssUploadInfo()[1],
          Signature: this.getOssUploadInfo()[2]
        }
      }
    )
    console.log(this.editorOption)
  },
  data() {
    return {
      content: this.param.data.descr,
      contentIsChange: false,
      editorOption: {
        modules: {
          toolbar: [
            ['bold', 'italic', 'underline', 'strike'],
            ['blockquote', 'code-block'],
            [{ 'header': 1 }, { 'header': 2 }],
            [{ 'list': 'ordered' }, { 'list': 'bullet' }],
            [{ 'script': 'sub' }, { 'script': 'super' }],
            [{ 'indent': '-1' }, { 'indent': '+1' }],
            [{ 'direction': 'rtl' }],
            [{ 'size': ['small', false, 'large', 'huge'] }],
            [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
            [{ 'font': [] }],
            [{ 'color': [] }, { 'background': [] }],
            [{ 'align': [] }],
            ['clean'],
            ['link', 'image', 'video']
          ]
        }
      },
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
        shortName: this.param.data.shortName,
        number: this.param.data.number,
        descr: this.param.data.descr,
        salePrice: this.param.data.salePrice,
        wholesale: this.param.data.wholesale,
        purchasePrice: this.param.data.purchasePrice,
        stock: this.param.data.stock,
        sex: this.param.data.sex,
        isHot: this.param.data.isHot,
        isRecommend: this.param.data.isRecommend,
        sources: JSON.parse(this.param.data.sources),
        coverSrc: this.param.data.coverSrc
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
          { min: 3, max: 150, message: '长度在 3 到 150 个字符', trigger: 'blur' }
        ],
        shortName: [
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
        key: this.getKey()
      },
      uploadUrl: process.env.FILE_UPLOAD_URL,
      dialogImageUrl: '',
      dialogVisible: false,
      uploadFileLimit: 9,
      coverSrcList: this.getCoverSrcList()
    }
  },
  props: ['param'],
  mounted: function() {
    this.$store.dispatch('GetGoodsById', this.param.data.id).then((response) => {
      this.content = response.descr
    }).catch((reason) => {
      this.$message({ showClose: true, message: '获取商品描述失败,原因=>' + reason, type: 'error' })
    })
  },
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
          var param = {
            id: this.param.data.id,
            type: this.goodsForm.type,
            tBId: this.goodsForm.tBId,
            name: this.goodsForm.name,
            shortName: this.goodsForm.shortName,
            descr: this.content,
            number: this.goodsForm.number,
            salePrice: this.goodsForm.salePrice,
            wholesale: this.goodsForm.wholesale,
            purchasePrice: this.goodsForm.purchasePrice,
            stock: this.goodsForm.stock,
            sex: this.goodsForm.sex,
            isHot: this.isHot,
            isRecommend: this.isRecommend,
            sources: JSON.stringify(this.goodsForm.sources),
            coverSrc: this.goodsForm.coverSrc
          }
          this.$store.dispatch('ModifyGoods', param).then(() => {
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
        if (file.response) {
          if (item.id === file.response.hash) {
            rvIndex = i
            break
          }
        } else {
          if (item.url === file.url) {
            rvIndex = i
          }
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
      this.uploadInfo.key = this.getKey()
    },
    handleUploadError(err, file, fileList) {
      this.$message({ showClose: true, message: '上传失败,' + err, type: 'error' })
    },
    handleExceed(files, fileList) {
      this.$message({ showClose: true, message: 'Logo最多上传' + this.uploadFileLimit + '张图片,', type: 'error' })
    },
    handleCoverExceed(files, fileList) {
      this.$message({ showClose: true, message: '封面图最多上传' + 1 + '张图片,', type: 'error' })
    },
    resetForm(formName) {
      this.content = ''
      this.goodsForm.sources = []
      this.$refs.upload.clearFiles()
      this.$refs[formName].resetFields()
      this.uploadInfo.key = this.getKey()
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
      this.uploadInfo.key = this.getKey()
    },
    getCoverSrcList() {
      if (this.param.data.coverSrc) {
        return [{
          url: this.param.data.coverSrc,
          name: 'sjAd.jpg',
          uid: 1549595935892
        }]
      }
    },
    getKey() {
      return 'sj' + new Date().getTime()
    },
    onEditorBlur(quill) {
      console.log('editor blur!', quill)
    },
    onEditorFocus(quill) {
      console.log('editor focus!', quill)
    },
    onEditorReady(quill) {
      console.log('editor ready!', quill)
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>