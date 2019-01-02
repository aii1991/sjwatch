<template>
 <div class="app-padding" v-loading.fullscreen.lock="loading" style="width: 600px;" >
    <el-form ref="orderForm" :model="orderForm" label-width="100px">
      <el-form-item label="收件人地址" prop="address">
         <span>{{ orderForm.address }}</span>
      </el-form-item>
      <el-form-item label="收件人名" prop="receiverName">
        <span>{{ orderForm.receiverName }}</span>
      </el-form-item>
      <el-form-item label="收件人电话" prop="receiverNumber">
        <span>{{ orderForm.receiverNumber }}</span>
      </el-form-item>
      <el-form-item label="快递名" prop="deliveryName">
        <span>{{ orderForm.deliveryName }}</span>
      </el-form-item>
      <el-form-item label="快递单号" prop="deliveryNumber">
        <span>{{ orderForm.deliveryNumber }}</span>
      </el-form-item>
       <el-form-item label="付款金额" prop="price">
         <span>{{ orderForm.price }}</span>
      </el-form-item>
      <el-form-item label="商品Id" prop="goodsId">
        <span>{{ orderForm.goodsId }}</span>
      </el-form-item>
      <el-form-item label="图片描述">
         <el-upload ref="upload"
            :action="uploadUrl"
            list-type="picture-card"
            name="file"
            disabled
            :file-list="orderForm.sources"
            :limit="uploadFileLimit"
            :data="uploadToken"
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

export default {
  data() {
    return {
      orderForm: {
        address: this.param.data.address,
        receiverName: this.param.data.receiverName,
        receiverNumber: this.param.data.receiverNumber,
        deliveryNumber: this.param.data.deliveryNumber,
        deliveryName: this.param.data.deliveryName,
        price: this.param.data.price,
        goodsId: this.param.data.goodsId,
        sources: JSON.parse(this.param.data.goods.sources)
      },
      loading: false,
      uploadUrl: process.env.FILE_UPLOAD_URL,
      dialogImageUrl: '',
      dialogVisible: false,
      uploadFileLimit: 9
    }
  },
  props: ['param'],
  methods: {
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