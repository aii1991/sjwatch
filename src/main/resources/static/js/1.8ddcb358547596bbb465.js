webpackJsonp([1],{D2lD:function(e,o,t){var a=t("Uv5L");"string"==typeof a&&(a=[[e.i,a,""]]),a.locals&&(e.exports=a.locals);t("rjj0")("cbe8d66e",a,!0)},Uv5L:function(e,o,t){(e.exports=t("FZ+f")(!1)).push([e.i,"\n.line[data-v-62647371]{\r\n  text-align: center;\n}\r\n",""])},uMyE:function(e,o,t){"use strict";Object.defineProperty(o,"__esModule",{value:!0});var a=t("cCj1"),s={data:function(){return{goodsForm:{type:"",tBId:"",name:"",number:"",descr:"",salePrice:0,wholesale:0,purchasePrice:0,stock:0,sources:[]},rules:{type:[{required:!0,message:"请选择类型",trigger:"change"}],tBId:[{required:!0,message:"请选择品牌",trigger:"change"}],name:[{required:!0,message:"请输入名称",trigger:"blur"},{min:3,max:30,message:"长度在 3 到 30 个字符",trigger:"blur"}],salePrice:[{required:!0,message:"请输入零售价",trigger:"blur"},{type:"number",message:"零售价必须是数字"}],wholesale:[{required:!0,message:"请输入批发价",trigger:"blur"},{type:"number",message:"批发价必须是数字"}],purchasePrice:[{required:!0,message:"请输入采购价",trigger:"blur"},{type:"number",message:"采购价必须是数字"}],stock:[{required:!0,message:"请输入库存",trigger:"blur"},{type:"number",message:"库存必须是数字"}]},goodsTypeData:this.getGoodsTypeData(),goodsBrandData:[],brandSelectDisabled:!0,loading:!1,uploadToken:{token:"6bpjaMgBu6ChmdHzr88nG0VYIHMtQgM8iMPJ0fJZ:oKsD1XinRSpa2nmJQVr_EG5Dpm0=:eyJzY29wZSI6InNqd2F0Y2giLCJkZWFkbGluZSI6MTUxODY2NDgzMn0="},uploadUrl:"http://upload-z2.qiniup.com",dialogImageUrl:"",dialogVisible:!1,uploadFileLimit:9}},props:["goodsTypeId"],methods:{fetchGoodsBrandData:function(e){var o=this;this.$store.dispatch("ListGoodsBrand",e).then(function(e){o.goodsBrandData=e,o.brandSelectDisabled=!1}).catch(function(e){o.$message({showClose:!0,message:"获取商品品牌失败,原因=>"+e,type:"error"})})},handleGoodsTypeChange:function(e){this.type=e,this.fetchGoodsBrandData(e)},handleGoodsBrandChange:function(e){this.tBId=e},onSubmit:function(){var e=this;this.$refs.goodsForm.validate(function(o){if(!o)return e.$message({showClose:!0,message:"请检查输入内容是否有误",type:"error"}),!1;e.loading=!0,e.$store.dispatch("AddGoods",{type:e.goodsForm.type,tBId:e.goodsForm.tBId,name:e.goodsForm.name,number:e.goodsForm.number,descr:e.goodsForm.descr,salePrice:e.goodsForm.salePrice,wholesale:e.goodsForm.wholesale,purchasePrice:e.goodsForm.purchasePrice,stock:e.goodsForm.stock,sources:e.goodsForm.sources}).then(function(){e.loading=!1,e.$message({showClose:!0,message:"添加成功",type:"success"})}).catch(function(o){e.loading=!1,e.$message({showClose:!0,message:o,type:"error"})})})},getGoodsTypeData:function(){var e=this.$store.state.goods.goodsTypeData;return e||Object(a.a)()},handleRemove:function(e,o){for(var t=-1,a=0;a<this.goodsForm.sources.length;a++){if(this.goodsForm.sources[a].id===e.response.hash){t=a;break}}-1!==t&&this.goodsForm.sources.splice(t,1)},handlePictureCardPreview:function(e){this.dialogImageUrl=e.url,this.dialogVisible=!0},handleUpload:function(e,o,t){var a="http://p43e9mjtu.bkt.clouddn.com/"+e.key;this.goodsForm.sources.push({id:e.hash,url:a,name:o.name,size:o.size})},handleUploadError:function(e,o,t){this.$message({showClose:!0,message:"上传失败,"+e,type:"error"})},handleExceed:function(e,o){this.$message({showClose:!0,message:"Logo最多上传"+this.uploadFileLimit+"张图片,",type:"error"})},resetForm:function(e){this.goodsForm.sources=[],this.$refs.upload.clearFiles(),this.$refs[e].resetFields()}}},r={render:function(){var e=this,o=e.$createElement,t=e._self._c||o;return t("div",{directives:[{name:"loading",rawName:"v-loading.fullscreen.lock",value:e.loading,expression:"loading",modifiers:{fullscreen:!0,lock:!0}}],staticClass:"app-padding",staticStyle:{width:"600px"}},[t("el-form",{ref:"goodsForm",attrs:{model:e.goodsForm,rules:e.rules,"label-width":"100px"}},[t("el-form-item",{attrs:{label:"类型",prop:"type"}},[t("el-select",{attrs:{placeholder:"请选择"},on:{change:e.handleGoodsTypeChange},model:{value:e.goodsForm.type,callback:function(o){e.$set(e.goodsForm,"type",o)},expression:"goodsForm.type"}},e._l(e.goodsTypeData,function(e){return t("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],1),e._v(" "),t("el-form-item",{attrs:{label:"品牌",prop:"tBId"}},[t("el-select",{attrs:{disabled:e.brandSelectDisabled,placeholder:"请选择"},on:{change:e.handleGoodsBrandChange},model:{value:e.goodsForm.tBId,callback:function(o){e.$set(e.goodsForm,"tBId",o)},expression:"goodsForm.tBId"}},e._l(e.goodsBrandData,function(e){return t("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],1),e._v(" "),t("el-form-item",{attrs:{label:"名称",prop:"name"}},[t("el-input",{attrs:{clearable:""},model:{value:e.goodsForm.name,callback:function(o){e.$set(e.goodsForm,"name",o)},expression:"goodsForm.name"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"编号",prop:"number"}},[t("el-input",{attrs:{clearable:""},model:{value:e.goodsForm.number,callback:function(o){e.$set(e.goodsForm,"number",o)},expression:"goodsForm.number"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"零售价",prop:"salePrice"}},[t("el-input",{attrs:{clearable:""},model:{value:e.goodsForm.salePrice,callback:function(o){e.$set(e.goodsForm,"salePrice",e._n(o))},expression:"goodsForm.salePrice"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"批发价",prop:"wholesale"}},[t("el-input",{attrs:{clearable:""},model:{value:e.goodsForm.wholesale,callback:function(o){e.$set(e.goodsForm,"wholesale",e._n(o))},expression:"goodsForm.wholesale"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"采购价",prop:"purchasePrice"}},[t("el-input",{attrs:{clearable:""},model:{value:e.goodsForm.purchasePrice,callback:function(o){e.$set(e.goodsForm,"purchasePrice",e._n(o))},expression:"goodsForm.purchasePrice"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"库存",prop:"stock"}},[t("el-input",{attrs:{clearable:""},model:{value:e.goodsForm.stock,callback:function(o){e.$set(e.goodsForm,"stock",e._n(o))},expression:"goodsForm.stock"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"描述",prop:"descr"}},[t("el-input",{attrs:{type:"textarea",clearable:""},model:{value:e.goodsForm.descr,callback:function(o){e.$set(e.goodsForm,"descr",e._n(o))},expression:"goodsForm.descr"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"图片描述"}},[t("el-upload",{ref:"upload",attrs:{action:e.uploadUrl,"list-type":"picture-card",name:"file",limit:e.uploadFileLimit,data:e.uploadToken,"on-exceed":e.handleExceed,"on-success":e.handleUpload,"on-error":e.handleUploadError,"on-preview":e.handlePictureCardPreview,"on-remove":e.handleRemove}},[t("i",{staticClass:"el-icon-plus"})]),e._v(" "),t("el-dialog",{attrs:{visible:e.dialogVisible},on:{"update:visible":function(o){e.dialogVisible=o}}},[t("img",{attrs:{width:"100%",src:e.dialogImageUrl,alt:""}})])],1),e._v(" "),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:e.onSubmit}},[e._v("提交")]),e._v(" "),t("el-button",{on:{click:function(o){e.resetForm("goodsForm")}}},[e._v("重置")])],1)],1)],1)},staticRenderFns:[]};var l=t("VU/8")(s,r,!1,function(e){t("D2lD")},"data-v-62647371",null).exports,n=t("Dd8w"),i=t.n(n),d={data:function(){return{searchFrom:{name:"",number:""},loading:!1,goodsData:[],currentPage:1,pageSize:15,total:0}},props:["isFetchData","goodsTypeId","goodsBrandId","goodsTypeName","goodsBrandName"],created:function(){this.fetchData(this.currentPage)},watch:{$route:"fetchData",isFetchData:function(e,o){e&&this.fetchData(this.currentPage)}},methods:{fetchData:function(e,o){var t=this;this.loading=!0;var a=i()({type:this.goodsTypeId,tBId:this.goodsBrandId},o);this.$store.dispatch("ListGoods",{currentPage:e,pageSize:this.pageSize,param:a}).then(function(e){t.goodsData=e.list,t.total=e.total,t.loading=!1,t.$message({showClose:!0,message:"列表加载完成",type:"success"})}).catch(function(e){t.loading=!1,t.$message({showClose:!0,message:"列表加载失败请刷新重试",type:"error"})})},handlePageChange:function(e){this.fetchData(e)},handleDeleteClick:function(e,o){var t=this;o&&o.stopPropagation(),this.loading=!0;var a=e.row.id;this.$store.dispatch("DeleteGoods",a).then(function(o){t.loading=!1,t.goodsData.splice(e.$index,1),t.$message({showClose:!0,message:"删除成功",type:"success"})}).catch(function(e){console.log(e),t.loading=!1,t.$message({showClose:!0,message:"删除失败",type:"error"})})},handleEditClick:function(e,o){o&&o.stopPropagation(),this.$router.push({name:"goodsEdit",params:{param:{type:this.goodsTypeId,tBId:this.goodsBrandId,data:this.goodsData[e.$index]}}})},onSearchSubmit:function(){this.currentPage=1,this.fetchData(this.currentPage,{name:this.searchFrom.name,number:this.searchFrom.number})},formatterDate:function(e,o,t){return new Date(t).toLocaleString()}}},c={render:function(){var e=this,o=e.$createElement,t=e._self._c||o;return t("div",[t("div",[t("el-form",{attrs:{inline:!0,model:e.searchFrom}},[t("el-form-item",{attrs:{label:"商品名称"}},[t("el-input",{attrs:{placeholder:"商品名称"},model:{value:e.searchFrom.name,callback:function(o){e.$set(e.searchFrom,"name",o)},expression:"searchFrom.name"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"商品编号"}},[t("el-input",{attrs:{placeholder:"商品编号"},model:{value:e.searchFrom.number,callback:function(o){e.$set(e.searchFrom,"number",o)},expression:"searchFrom.number"}})],1),e._v(" "),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:e.onSearchSubmit}},[e._v("查询")])],1)],1)],1),e._v(" "),t("el-table",{directives:[{name:"loading",rawName:"v-loading.fullscreen.lock",value:e.loading,expression:"loading",modifiers:{fullscreen:!0,lock:!0}}],staticStyle:{width:"100%"},attrs:{data:e.goodsData,"empty-text":"无"}},[t("el-table-column",{attrs:{fixed:"",prop:"id",label:"id"}}),e._v(" "),t("el-table-column",{attrs:{label:"类型"},scopedSlots:e._u([{key:"default",fn:function(o){return[t("span",[e._v(e._s(e.goodsTypeName))])]}}])}),e._v(" "),t("el-table-column",{attrs:{label:"品牌"},scopedSlots:e._u([{key:"default",fn:function(o){return[t("span",[e._v(e._s(e.goodsBrandName))])]}}])}),e._v(" "),t("el-table-column",{attrs:{prop:"name",label:"商品名称"}}),e._v(" "),t("el-table-column",{attrs:{prop:"descr",label:"商品描述"}}),e._v(" "),t("el-table-column",{attrs:{prop:"number",label:"商品编号"}}),e._v(" "),t("el-table-column",{attrs:{prop:"salePrice",label:"销售价"}}),e._v(" "),t("el-table-column",{attrs:{prop:"wholesale",label:"批发价"}}),e._v(" "),t("el-table-column",{attrs:{prop:"purchasePrice",label:"采购价"}}),e._v(" "),t("el-table-column",{attrs:{prop:"stock",label:"库存"}}),e._v(" "),t("el-table-column",{attrs:{prop:"createTime",formatter:e.formatterDate,label:"创建时间"}}),e._v(" "),t("el-table-column",{attrs:{prop:"modifyTime",formatter:e.formatterDate,label:"修改时间"}}),e._v(" "),t("el-table-column",{attrs:{label:"操作",fixed:"right",width:"100px"},scopedSlots:e._u([{key:"default",fn:function(o){return[t("el-button",{attrs:{type:"text",size:"small"},on:{click:function(t){e.handleEditClick(o,t)}}},[e._v("编辑")]),e._v(" "),t("el-button",{attrs:{type:"text",size:"small"},on:{click:function(t){e.handleDeleteClick(o,t)}}},[e._v("删除")])]}}])})],1),e._v(" "),t("el-pagination",{staticStyle:{"text-align":"center","margin-top":"15px"},attrs:{background:"",layout:"prev, pager, next","page-size":10,"current-page":e.currentPage,total:e.total},on:{"update:currentPage":function(o){e.currentPage=o},"current-change":e.handlePageChange}})],1)},staticRenderFns:[]},m={data:function(){return{activeName:"first",isFetchData:!1}},props:["goodsTypeId","goodsBrandId","param"],components:{AddComponent:l,listComponent:t("VU/8")(d,c,!1,null,null,null).exports},methods:{handleClick:function(e,o){"0"===e.index&&(this.isFetchData=this.$store.state.goods.isFetchGoodData)}}},u={render:function(){var e=this,o=e.$createElement,t=e._self._c||o;return t("div",{staticClass:"app-container"},[t("el-tabs",{on:{"tab-click":e.handleClick},model:{value:e.activeName,callback:function(o){e.activeName=o},expression:"activeName"}},[t("el-tab-pane",{attrs:{label:"商品列表",name:"first"}},[t("list-component",{attrs:{"is-fetch-data":e.isFetchData,"goods-type-id":e.goodsTypeId,"goods-brand-id":e.goodsBrandId,"goods-type-name":e.param.goodsTypeName,"goods-brand-name":e.param.goodsBrandName}})],1),e._v(" "),t("el-tab-pane",{attrs:{label:"添加商品",name:"second"}},[t("AddComponent",{attrs:{"goods-type-id":e.goodsTypeId,"goods-brand-id":e.goodsBrandId,"goods-type-name":e.param.goodsTypeName,"goods-brand-name":e.param.goodsBrandName}})],1)],1)],1)},staticRenderFns:[]},p=t("VU/8")(m,u,!1,null,null,null);o.default=p.exports}});