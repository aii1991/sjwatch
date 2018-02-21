<template>
 <div class="app-padding" v-loading.fullscreen.lock="loading"  style="width: 600px;">
    <el-form :model="goodsTypeForm" ref="goodsTypeForm" :rules="rules" label-width="120px">
      <el-form-item label="商品类型名称" prop="name">
          <el-input v-model="goodsTypeForm.name" clearable/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit('goodsTypeForm')">提交</el-button>
        <el-button @click="resetForm('goodsTypeForm')">重置</el-button>
      </el-form-item>
    </el-form>
 </div>
</template>


<script>
export default {
  data() {
    return {
      goodsTypeForm: {
        name: ''
      },
      loading: false,
      rules: {
        name: [
          { required: true, message: '请输入商品类型名称', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    onSubmit(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('AddGoodsType', this.goodsTypeForm.name).then(() => {
            this.resetForm(formName)
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
    resetForm(formName) {
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