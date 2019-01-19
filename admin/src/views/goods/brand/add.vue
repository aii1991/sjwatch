<template>
 <div class="app-padding" v-loading.fullscreen.lock="loading" style="width: 600px;">
    <el-form ref="goodsBrandForm" :model="goodsBrandForm" :rules="rules" label-width="100px">
      <el-form-item label="品牌名称" prop="name">
          <el-input v-model="goodsBrandForm.name" clearable></el-input>
      </el-form-item>
      <el-form-item label="品牌logo" prop="logo">
          <el-upload ref="upload"
            :action="uploadUrl"
            list-type="picture-card"
            name="file"
            :limit="1"
            :data="uploadInfo"
            :headers="headers"
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
          <el-input v-model="goodsBrandForm.logo" style="display:none;" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">提交</el-button>
         <el-button @click="resetForm('goodsBrandForm')">重置</el-button>
      </el-form-item>
    </el-form> 
 </div>
</template>


<script>

export default {
  data() {
    return {
      goodsBrandForm: {
        name: '',
        logo: ''
      },
      loading: false,
      headers: {

      },
      uploadInfo: {
        policy: this.getOssUploadInfo()[0],
        OSSAccessKeyId: this.getOssUploadInfo()[1],
        Signature: this.getOssUploadInfo()[2],
        key: 'sj' + new Date().getTime()
      },
      dialogImageUrl: '',
      dialogVisible: false,
      uploadUrl: process.env.FILE_UPLOAD_URL,
      rules: {
        name: [
          { required: true, message: '请输入商品名称', trigger: 'blur' }
        ],
        logo: [
          { required: true, message: '请上传商品logo', trigger: 'blur' }
        ]
      }
    }
  },
  props: ['goodsTypeId'],
  methods: {
    onSubmit() {
      this.$refs['goodsBrandForm'].validate((valid) => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('AddGoodsBrand', { name: this.goodsBrandForm.name, logo: this.goodsBrandForm.logo, goodsTypeId: this.goodsTypeId }).then(() => {
            this.resetForm('goodsBrandForm')
            this.loading = false
            this.$message({ showClose: true, message: '添加成功', type: 'success' })
          }).catch((reason) => {
            this.loading = false
            this.$message({ showClose: true, message: reason, type: 'error' })
          })
        } else {
          this.$message({ showClose: true, message: '请检查输入内容是否有误', type: 'error' })
          return false
        }
      })
    },
    handleRemove(file, fileList) {
      this.goodsBrandForm.logo = ''
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
    handleUpload(response, file, fileList) {
      this.goodsBrandForm.logo = this.uploadUrl + '/' + this.uploadInfo.key
    },
    handleUploadError(err, file, fileList) {
      this.goodsBrandForm.logo = ''
      this.$message({ showClose: true, message: '上传失败,' + err, type: 'error' })
    },
    handleExceed(files, fileList) {
      this.$message({ showClose: true, message: 'Logo最多上传一张图片,', type: 'error' })
    },
    resetForm(formName) {
      this.goodsBrandForm.logo = ''
      this.$refs.upload.clearFiles()
      this.$refs[formName].resetFields()
    },
    getOssUploadInfo() {
      var upToken = this.$store.getters.uploadToken
      return upToken.split('.')
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>