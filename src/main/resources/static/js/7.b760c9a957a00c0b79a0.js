webpackJsonp([7],{BHVk:function(e,t,a){var o=a("hDCJ");"string"==typeof o&&(o=[[e.i,o,""]]),o.locals&&(e.exports=o.locals);a("rjj0")("3ea98848",o,!0)},WSxQ:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{directives:[{name:"loading",rawName:"v-loading.fullscreen.lock",value:e.loading,expression:"loading",modifiers:{fullscreen:!0,lock:!0}}],staticClass:"app-padding",staticStyle:{width:"600px"}},[a("el-form",{ref:"goodsTypeForm",attrs:{model:e.goodsTypeForm,rules:e.rules,"label-width":"120px"}},[a("el-form-item",{attrs:{label:"商品类型名称",prop:"name"}},[a("el-input",{attrs:{clearable:""},model:{value:e.goodsTypeForm.name,callback:function(t){e.$set(e.goodsTypeForm,"name",t)},expression:"goodsTypeForm.name"}})],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.onSubmit("goodsTypeForm")}}},[e._v("提交")]),e._v(" "),a("el-button",{on:{click:function(t){e.resetForm("goodsTypeForm")}}},[e._v("重置")])],1)],1)],1)},staticRenderFns:[]};var n={data:function(){return{loading:!1,goodsTypeData:[]}},props:["isFetchData"],created:function(){this.fetchData()},watch:{$route:"fetchData",isFetchData:function(e,t){e&&this.fetchData()}},methods:{fetchData:function(){var e=this;this.loading=!0,this.$store.dispatch("ListGoodsType").then(function(t){e.goodsTypeData=t,e.loading=!1,e.$message({showClose:!0,message:"列表加载完成",type:"success"})}).catch(function(t){e.loading=!1,e.$message({showClose:!0,message:"列表加载失败请刷新重试",type:"error"})})},handleClick:function(e,t,a){this.$router.push({name:"goodsBrand",params:{goodsTypeId:e.id,param:{goodsTypeName:e.name}}})},handleDeleteClick:function(e,t){var a=this;t&&t.stopPropagation(),this.$confirm("是否继续删除?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){a.invokeDelete(e)}).catch(function(){a.$message({type:"info",message:"已取消删除"})})},invokeDelete:function(e){var t=this;this.loading=!0;var a=e.row.id;this.$store.dispatch("DeleteGoodsType",a).then(function(a){t.loading=!1,t.goodsTypeData.splice(e.$index,1),t.$message({showClose:!0,message:"删除成功",type:"success"})}).catch(function(e){t.loading=!1,t.$message({showClose:!0,message:"删除失败",type:"error"})})},formatterDate:function(e,t,a){return new Date(a).toLocaleString()}}},s={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-table",{directives:[{name:"loading",rawName:"v-loading.fullscreen.lock",value:e.loading,expression:"loading",modifiers:{fullscreen:!0,lock:!0}}],staticStyle:{width:"100%"},attrs:{data:e.goodsTypeData},on:{"row-click":e.handleClick}},[a("el-table-column",{attrs:{fixed:"",prop:"id",label:"id"}}),e._v(" "),a("el-table-column",{attrs:{prop:"name",label:"商品类型名称"}}),e._v(" "),a("el-table-column",{attrs:{prop:"createTime",formatter:e.formatterDate,label:"创建时间"}}),e._v(" "),a("el-table-column",{attrs:{prop:"modifyTime",formatter:e.formatterDate,label:"修改时间"}}),e._v(" "),a("el-table-column",{attrs:{label:"操作",fixed:"right",width:"100px"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){e.handleDeleteClick(t,a)}}},[e._v("删除")])]}}])})],1)],1)},staticRenderFns:[]},i={data:function(){return{activeName:"first",isFetchData:!1}},components:{AddComponent:a("VU/8")({data:function(){return{goodsTypeForm:{name:""},loading:!1,rules:{name:[{required:!0,message:"请输入商品类型名称",trigger:"blur"}]}}},methods:{onSubmit:function(e){var t=this;this.$refs[e].validate(function(a){if(!a)return t.$message({showClose:!0,message:"请检查输入内容是否有误",type:"error"}),!1;t.loading=!0,t.$store.dispatch("AddGoodsType",t.goodsTypeForm.name).then(function(){t.resetForm(e),t.loading=!1,t.$message({showClose:!0,message:"添加成功",type:"success"})}).catch(function(e){t.loading=!1,t.$message({showClose:!0,message:e,type:"error"})})})},resetForm:function(e){this.$refs[e].resetFields()}}},o,!1,function(e){a("BHVk")},"data-v-4dbd3786",null).exports,listComponent:a("VU/8")(n,s,!1,null,null,null).exports},methods:{handleClick:function(e,t){"0"===e.index&&(this.isFetchData=this.$store.state.goods.isFetchGoodsTypeData)}}},l={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-tabs",{on:{"tab-click":e.handleClick},model:{value:e.activeName,callback:function(t){e.activeName=t},expression:"activeName"}},[a("el-tab-pane",{attrs:{label:"商品类型列表",name:"first"}},[a("list-component",{attrs:{"is-fetch-data":e.isFetchData}})],1),e._v(" "),a("el-tab-pane",{attrs:{label:"添加商品类型",name:"second"}},[a("AddComponent")],1)],1)],1)},staticRenderFns:[]},r=a("VU/8")(i,l,!1,null,null,null);t.default=r.exports},hDCJ:function(e,t,a){(e.exports=a("FZ+f")(!1)).push([e.i,"\n.line[data-v-4dbd3786]{\r\n  text-align: center;\n}\r\n",""])}});