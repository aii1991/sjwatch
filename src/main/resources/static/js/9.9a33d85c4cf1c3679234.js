webpackJsonp([9],{Puet:function(e,a,t){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var o=t("cCj1"),s={data:function(){return{goodsForm:{id:this.param.data.id,type:this.param.type,tBId:this.param.tBId,name:this.param.data.name,number:this.param.data.number,descr:this.param.data.descr,salePrice:this.param.data.salePrice,wholesale:this.param.data.wholesale,purchasePrice:this.param.data.purchasePrice,stock:this.param.data.stock,sources:JSON.parse(this.param.data.sources)},goodsTypeData:this.getGoodsTypeData(),goodsBrandData:[{id:this.param.tBId,name:this.param.tBName}],brandSelectDisabled:!1,loading:!1,uploadUrl:"http://upload-z2.qiniup.com",dialogImageUrl:"",dialogVisible:!1,uploadFileLimit:9}},props:["param"],methods:{fetchGoodsBrandData:function(e){var a=this;this.$store.dispatch("ListGoodsBrand",e).then(function(e){a.goodsBrandData=e,a.brandSelectDisabled=!1}).catch(function(e){a.$message({showClose:!0,message:"获取商品品牌失败,原因=>"+e,type:"error"})})},getGoodsTypeData:function(){var e=this.$store.state.goods.goodsTypeData;return e||Object(o.a)()},handlePictureCardPreview:function(e){this.dialogImageUrl=e.url,this.dialogVisible=!0}}},r={render:function(){var e=this,a=e.$createElement,t=e._self._c||a;return t("div",{directives:[{name:"loading",rawName:"v-loading.fullscreen.lock",value:e.loading,expression:"loading",modifiers:{fullscreen:!0,lock:!0}}],staticClass:"app-padding",staticStyle:{width:"600px"}},[t("el-form",{ref:"goodsForm",attrs:{model:e.goodsForm,"label-width":"100px"}},[t("el-form-item",{attrs:{label:"类型",prop:"type"}},[t("el-select",{attrs:{placeholder:"请选择",disabled:""},model:{value:e.goodsForm.type,callback:function(a){e.$set(e.goodsForm,"type",a)},expression:"goodsForm.type"}},e._l(e.goodsTypeData,function(e){return t("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],1),e._v(" "),t("el-form-item",{attrs:{label:"品牌",prop:"tBId"}},[t("el-select",{attrs:{disabled:"",placeholder:"请选择"},model:{value:e.goodsForm.tBId,callback:function(a){e.$set(e.goodsForm,"tBId",a)},expression:"goodsForm.tBId"}},e._l(e.goodsBrandData,function(e){return t("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],1),e._v(" "),t("el-form-item",{attrs:{label:"商品id",prop:"name"}},[t("span",[e._v(e._s(e.goodsForm.id))])]),e._v(" "),t("el-form-item",{attrs:{label:"名称",prop:"name"}},[t("span",[e._v(e._s(e.goodsForm.name))])]),e._v(" "),t("el-form-item",{attrs:{label:"编号",prop:"number"}},[t("span",[e._v(e._s(e.goodsForm.number))])]),e._v(" "),t("el-form-item",{attrs:{label:"零售价",prop:"salePrice"}},[t("span",[e._v(e._s(e.goodsForm.salePrice))])]),e._v(" "),t("el-form-item",{attrs:{label:"批发价",prop:"wholesale"}},[t("span",[e._v(e._s(e.goodsForm.wholesale))])]),e._v(" "),t("el-form-item",{attrs:{label:"采购价",prop:"purchasePrice"}},[t("span",[e._v(e._s(e.goodsForm.purchasePrice))])]),e._v(" "),t("el-form-item",{attrs:{label:"库存",prop:"stock"}},[t("span",[e._v(e._s(e.goodsForm.stock))])]),e._v(" "),t("el-form-item",{attrs:{label:"描述",prop:"descr"}},[t("span",[e._v(e._s(e.goodsForm.descr))])]),e._v(" "),t("el-form-item",{attrs:{label:"图片描述"}},[t("el-upload",{ref:"upload",attrs:{action:e.uploadUrl,"list-type":"picture-card",name:"file",disabled:"","file-list":e.goodsForm.sources,"on-preview":e.handlePictureCardPreview}},[t("i",{staticClass:"el-icon-plus"})]),e._v(" "),t("el-dialog",{attrs:{visible:e.dialogVisible},on:{"update:visible":function(a){e.dialogVisible=a}}},[t("img",{attrs:{width:"100%",src:e.dialogImageUrl,alt:""}})])],1)],1)],1)},staticRenderFns:[]};var l=t("VU/8")(s,r,!1,function(e){t("uOIO")},"data-v-21581a14",null);a.default=l.exports},gpBn:function(e,a,t){(e.exports=t("FZ+f")(!1)).push([e.i,"\n.line[data-v-21581a14]{\r\n  text-align: center;\n}\r\n",""])},uOIO:function(e,a,t){var o=t("gpBn");"string"==typeof o&&(o=[[e.i,o,""]]),o.locals&&(e.exports=o.locals);t("rjj0")("64d132fe",o,!0)}});