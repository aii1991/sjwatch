webpackJsonp([1],{"8xsm":function(e,r,a){"use strict";Object.defineProperty(r,"__esModule",{value:!0});var i={data:function(){return{orderForm:{address:this.param.data.address,receiverName:this.param.data.receiverName,receiverNumber:this.param.data.receiverNumber,deliveryNumber:this.param.data.deliveryNumber,deliveryName:this.param.data.deliveryName,price:this.param.data.price,goodsId:this.param.data.goodsId,sources:JSON.parse(this.param.data.goods.sources)},loading:!1,uploadUrl:"http://upload-z2.qiniup.com",dialogImageUrl:"",dialogVisible:!1,uploadFileLimit:9}},props:["param"],methods:{handlePictureCardPreview:function(e){this.dialogImageUrl=e.url,this.dialogVisible=!0}}},t={render:function(){var e=this,r=e.$createElement,a=e._self._c||r;return a("div",{directives:[{name:"loading",rawName:"v-loading.fullscreen.lock",value:e.loading,expression:"loading",modifiers:{fullscreen:!0,lock:!0}}],staticClass:"app-padding",staticStyle:{width:"600px"}},[a("el-form",{ref:"orderForm",attrs:{model:e.orderForm,"label-width":"100px"}},[a("el-form-item",{attrs:{label:"收件人地址",prop:"address"}},[a("span",[e._v(e._s(e.orderForm.address))])]),e._v(" "),a("el-form-item",{attrs:{label:"收件人名",prop:"receiverName"}},[a("span",[e._v(e._s(e.orderForm.receiverName))])]),e._v(" "),a("el-form-item",{attrs:{label:"收件人电话",prop:"receiverNumber"}},[a("span",[e._v(e._s(e.orderForm.receiverNumber))])]),e._v(" "),a("el-form-item",{attrs:{label:"快递名",prop:"deliveryName"}},[a("span",[e._v(e._s(e.orderForm.deliveryName))])]),e._v(" "),a("el-form-item",{attrs:{label:"快递单号",prop:"deliveryNumber"}},[a("span",[e._v(e._s(e.orderForm.deliveryNumber))])]),e._v(" "),a("el-form-item",{attrs:{label:"付款金额",prop:"price"}},[a("span",[e._v(e._s(e.orderForm.price))])]),e._v(" "),a("el-form-item",{attrs:{label:"商品Id",prop:"goodsId"}},[a("span",[e._v(e._s(e.orderForm.goodsId))])]),e._v(" "),a("el-form-item",{attrs:{label:"图片描述"}},[a("el-upload",{ref:"upload",attrs:{action:e.uploadUrl,"list-type":"picture-card",name:"file",disabled:"","file-list":e.orderForm.sources,limit:e.uploadFileLimit,data:e.uploadToken,"on-preview":e.handlePictureCardPreview}},[a("i",{staticClass:"el-icon-plus"})]),e._v(" "),a("el-dialog",{attrs:{visible:e.dialogVisible},on:{"update:visible":function(r){e.dialogVisible=r}}},[a("img",{attrs:{width:"100%",src:e.dialogImageUrl,alt:""}})])],1)],1)],1)},staticRenderFns:[]};var l=a("VU/8")(i,t,!1,function(e){a("b+Br")},"data-v-fb6185c4",null);r.default=l.exports},TrSq:function(e,r,a){(e.exports=a("FZ+f")(!1)).push([e.i,"\n.line[data-v-fb6185c4]{\r\n  text-align: center;\n}\r\n",""])},"b+Br":function(e,r,a){var i=a("TrSq");"string"==typeof i&&(i=[[e.i,i,""]]),i.locals&&(e.exports=i.locals);a("rjj0")("601f6b4e",i,!0)}});