<template>
 <div class="app-padding" v-loading.fullscreen.lock="loading" style="width: 600px;" >
    <el-form ref="orderForm" :model="orderForm" :rules="rules" label-width="100px">
      <el-form-item label="订单状态" prop="status">
        <el-select v-model="orderForm.status" placeholder="请选择">
          <el-option
            v-for="item in statsOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
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
        <el-input v-model="orderForm.goodsId" clearable disabled></el-input>
      </el-form-item>
      <el-form-item label="验证码" prop="validateCode">
        <el-input v-model="orderForm.validateCode" clearable></el-input>
      </el-form-item>
       <el-form-item>
        <el-button type="primary" @click="onSubmit">提交</el-button>
      </el-form-item>
    </el-form>
 </div>
</template>
<script>

export default {
  data() {
    return {
      statsOptions: [
        {
          value: 1,
          label: '待发货'
        },
        {
          value: 2,
          label: '已发货'
        },
        {
          value: 3,
          label: '已完成'
        }
      ],
      orderForm: {
        status: this.param.data.status,
        address: this.param.data.address,
        receiverName: this.param.data.receiverName,
        receiverNumber: this.param.data.receiverNumber,
        deliveryNumber: this.param.data.deliveryNumber,
        deliveryName: this.param.data.deliveryName,
        price: this.param.data.price,
        goodsId: this.param.data.goodsId,
        validateCode: ''
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
  props: ['param'],
  methods: {
    onSubmit() {
      this.$refs['orderForm'].validate((valid) => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('ModifyOrder', {
            id: this.param.data.id,
            status: this.orderForm.status,
            address: this.orderForm.address,
            receiverName: this.orderForm.receiverName,
            receiverNumber: this.orderForm.receiverNumber,
            deliveryNumber: this.orderForm.deliveryNumber,
            deliveryName: this.orderForm.deliveryName,
            price: this.orderForm.price,
            goodsId: this.orderForm.goodsId,
            validateCode: this.orderForm.validateCode
          }).then(() => {
            this.resetForm('orderForm')
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