webpackJsonp([1],{"4UvZ":function(e,o,t){(e.exports=t("FZ+f")(!1)).push([e.i,"\n.line[data-v-ff4e5f02]{\r\n  text-align: center;\n}\r\n",""])},"9+p+":function(e,o,t){var a=t("4UvZ");"string"==typeof a&&(a=[[e.i,a,""]]),a.locals&&(e.exports=a.locals);t("rjj0")("3d00390c",a,!0)},xqSZ:function(e,o,t){"use strict";Object.defineProperty(o,"__esModule",{value:!0});var a={data:function(){return{goodsBrandForm:{name:"",logo:""},loading:!1,headers:{},uploadInfo:{policy:this.getOssUploadInfo()[0],OSSAccessKeyId:this.getOssUploadInfo()[1],Signature:this.getOssUploadInfo()[2],key:"sj"+(new Date).getTime()},dialogImageUrl:"",dialogVisible:!1,uploadUrl:"http://upload-z2.qiniup.com",rules:{name:[{required:!0,message:"请输入商品名称",trigger:"blur"}],logo:[{required:!0,message:"请上传商品logo",trigger:"blur"}]}}},props:["goodsTypeId"],methods:{onSubmit:function(){var e=this;this.$refs.goodsBrandForm.validate(function(o){if(!o)return e.$message({showClose:!0,message:"请检查输入内容是否有误",type:"error"}),!1;e.loading=!0,e.$store.dispatch("AddGoodsBrand",{name:e.goodsBrandForm.name,logo:e.goodsBrandForm.logo,goodsTypeId:e.goodsTypeId}).then(function(){e.resetForm("goodsBrandForm"),e.loading=!1,e.$message({showClose:!0,message:"添加成功",type:"success"})}).catch(function(o){e.loading=!1,e.$message({showClose:!0,message:o,type:"error"})})})},handleRemove:function(e,o){this.goodsBrandForm.logo=""},handlePictureCardPreview:function(e){this.dialogImageUrl=e.url,this.dialogVisible=!0},handleUpload:function(e,o,t){this.goodsBrandForm.logo=this.uploadUrl+"/"+this.uploadInfo.key},handleUploadError:function(e,o,t){this.goodsBrandForm.logo="",this.$message({showClose:!0,message:"上传失败,"+e,type:"error"})},handleExceed:function(e,o){this.$message({showClose:!0,message:"Logo最多上传一张图片,",type:"error"})},resetForm:function(e){this.goodsBrandForm.logo="",this.$refs.upload.clearFiles(),this.$refs[e].resetFields()},getOssUploadInfo:function(){return this.$store.getters.uploadToken.split(".")}}},s={render:function(){var e=this,o=e.$createElement,t=e._self._c||o;return t("div",{directives:[{name:"loading",rawName:"v-loading.fullscreen.lock",value:e.loading,expression:"loading",modifiers:{fullscreen:!0,lock:!0}}],staticClass:"app-padding",staticStyle:{width:"600px"}},[t("el-form",{ref:"goodsBrandForm",attrs:{model:e.goodsBrandForm,rules:e.rules,"label-width":"100px"}},[t("el-form-item",{attrs:{label:"品牌名称",prop:"name"}},[t("el-input",{attrs:{clearable:""},model:{value:e.goodsBrandForm.name,callback:function(o){e.$set(e.goodsBrandForm,"name",o)},expression:"goodsBrandForm.name"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"品牌logo",prop:"logo"}},[t("el-upload",{ref:"upload",attrs:{action:e.uploadUrl,"list-type":"picture-card",name:"file",limit:1,data:e.uploadInfo,headers:e.headers,"on-exceed":e.handleExceed,"on-success":e.handleUpload,"on-error":e.handleUploadError,"on-preview":e.handlePictureCardPreview,"on-remove":e.handleRemove}},[t("i",{staticClass:"el-icon-plus"})]),e._v(" "),t("el-dialog",{attrs:{visible:e.dialogVisible},on:{"update:visible":function(o){e.dialogVisible=o}}},[t("img",{attrs:{width:"100%",src:e.dialogImageUrl,alt:""}})]),e._v(" "),t("el-input",{staticStyle:{display:"none"},attrs:{clearable:""},model:{value:e.goodsBrandForm.logo,callback:function(o){e.$set(e.goodsBrandForm,"logo",o)},expression:"goodsBrandForm.logo"}})],1),e._v(" "),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:e.onSubmit}},[e._v("提交")]),e._v(" "),t("el-button",{on:{click:function(o){e.resetForm("goodsBrandForm")}}},[e._v("重置")])],1)],1)],1)},staticRenderFns:[]};var n={data:function(){return{loading:!1,goodsBrandData:[]}},props:["isFetchData","goodsTypeId","goodsTypeName"],created:function(){this.fetchData()},watch:{$route:"fetchData",isFetchData:function(e,o){e&&this.fetchData()}},methods:{fetchData:function(){var e=this;this.loading=!0,this.$store.dispatch("ListGoodsBrand",this.goodsTypeId).then(function(o){e.goodsBrandData=o,e.loading=!1,e.$message({showClose:!0,message:"列表加载完成",type:"success"})}).catch(function(o){e.loading=!1,e.$message({showClose:!0,message:"列表加载失败请刷新重试",type:"error"})})},handleClick:function(e,o,t){o&&o.stopPropagation(),this.$router.push({name:"goods",params:{goodsTypeId:this.goodsTypeId,goodsBrandId:e.id,param:{goodsTypeName:this.goodsTypeName,goodsBrandName:e.name}}})},handleDeleteClick:function(e,o){var t=this;o&&o.stopPropagation(),this.$confirm("是否继续删除?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){t.invokeDelete(e)}).catch(function(){t.$message({type:"info",message:"已取消删除"})})},invokeDelete:function(e){var o=this;this.loading=!0;var t=e.row.id;this.$store.dispatch("DeleteGoodsBrand",t).then(function(t){o.loading=!1,o.goodsBrandData.splice(e.$index,1),o.$message({showClose:!0,message:"删除成功",type:"success"})}).catch(function(e){o.loading=!1,o.$message({showClose:!0,message:"删除失败",type:"error"})})},formatterDate:function(e,o,t){return new Date(t).toLocaleString()}}},r={render:function(){var e=this,o=e.$createElement,t=e._self._c||o;return t("div",[t("el-table",{directives:[{name:"loading",rawName:"v-loading.fullscreen.lock",value:e.loading,expression:"loading",modifiers:{fullscreen:!0,lock:!0}}],staticStyle:{width:"100%"},attrs:{data:e.goodsBrandData},on:{"row-click":e.handleClick}},[t("el-table-column",{attrs:{fixed:"",prop:"id",label:"id"}}),e._v(" "),t("el-table-column",{attrs:{prop:"name",label:"商品品牌"}}),e._v(" "),t("el-table-column",{attrs:{label:"商品品牌logo"},scopedSlots:e._u([{key:"default",fn:function(e){return[t("img",{attrs:{src:e.row.logo,width:"50px",height:"50px"}})]}}])}),e._v(" "),t("el-table-column",{attrs:{prop:"createTime",formatter:e.formatterDate,label:"创建时间"}}),e._v(" "),t("el-table-column",{attrs:{prop:"modifyTime",formatter:e.formatterDate,label:"修改时间"}}),e._v(" "),t("el-table-column",{attrs:{label:"操作",fixed:"right",width:"100px"},scopedSlots:e._u([{key:"default",fn:function(o){return[t("el-button",{attrs:{type:"text",size:"small"},on:{click:function(t){e.handleDeleteClick(o,t)}}},[e._v("删除")])]}}])})],1)],1)},staticRenderFns:[]},l={data:function(){return{activeName:"first",isFetchData:!1}},props:["goodsTypeId","param"],components:{AddComponent:t("VU/8")(a,s,!1,function(e){t("9+p+")},"data-v-ff4e5f02",null).exports,listComponent:t("VU/8")(n,r,!1,null,null,null).exports},methods:{handleClick:function(e,o){"0"===e.index&&(this.isFetchData=this.$store.state.goods.isFetchGoodsBrandData)}}},i={render:function(){var e=this,o=e.$createElement,t=e._self._c||o;return t("div",{staticClass:"app-container"},[t("el-tabs",{on:{"tab-click":e.handleClick},model:{value:e.activeName,callback:function(o){e.activeName=o},expression:"activeName"}},[t("el-tab-pane",{attrs:{label:"商品品牌列表",name:"first"}},[t("list-component",{attrs:{"is-fetch-data":e.isFetchData,"goods-type-id":":goodsTypeId"==e.goodsTypeId?0:e.goodsTypeId,"goods-type-name":e.param.goodsTypeName}})],1),e._v(" "),t("el-tab-pane",{attrs:{label:"添加商品品牌",name:"second"}},[t("AddComponent",{attrs:{"goods-type-id":":goodsTypeId"==e.goodsTypeId?0:e.goodsTypeId}})],1)],1)],1)},staticRenderFns:[]},d=t("VU/8")(l,i,!1,null,null,null);o.default=d.exports}});