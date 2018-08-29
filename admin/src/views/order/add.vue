<template>
 <div class="app-padding" v-loading.fullscreen.lock="loading" style="width: 600px;" >
    <el-form ref="orderForm" :model="goodsForm" :rules="rules" label-width="100px">
      <el-form-item label="收件人地址" prop="address">
        <el-input v-model="orderForm.address" clearable></el-input>
      </el-form-item>
      <el-form-item label="收件人名" prop="receiverName">
        <el-input v-model="orderForm.receiverName" clearable></el-input>
      </el-form-item>
      <el-form-item label="收件人电话" prop="receiverNumber">
        <el-input v-model="orderForm.receiverNumber" clearable></el-input>
      </el-form-item>
      <el-form-item label="快递名" prop="deliveryName">
        <el-input v-model="orderForm.deliveryName" clearable></el-input>
      </el-form-item>
      <el-form-item label="快递单号" prop="deliveryNumber">
        <el-input v-model="orderForm.deliveryNumber" clearable></el-input>
      </el-form-item>
       <el-form-item label="付款金额" prop="price">
        <el-input v-model="orderForm.price" clearable></el-input>
      </el-form-item>
      <el-form-item label="商品Id" prop="goodsId">
        <el-input v-model="orderForm.goodsId" clearable></el-input>
      </el-form-item>
    </el-form>
 </div>
</template>
<script>

export default {
  data() {
    return {
      orderForm: {
        address: '',
        receiverName: '',
        receiverNumber: '',
        deliveryNumber: '',
        deliveryName: '',
        price: 0,
        goodsId: this.goodsId
      },
      rules: {
        address: [
          { required: true, message: '请输入收件人地址', trigger: 'change' }
        ],
        receiverName: [
          { required: true, message: '请输入收件人名称', trigger: 'change' }
        ],
        receiverNumber: [
          { required: true, message: '请输入收件人电话', trigger: 'change' }
        ]
      },
      loading: false
    }
  },
  props: ['goodsId'],
  methods: {
    onSubmit() {
      this.$refs['orderForm'].validate((valid) => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('AddOrder', {
            address: this.orderForm.address,
            receiverName: this.orderForm.receiverName,
            receiverNumber: this.orderForm.receiverNumber,
            deliveryNumber: this.orderForm.deliveryNumber
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
    resetForm(formName) {
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