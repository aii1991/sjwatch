webpackJsonp([5],{"1gTc":function(e,r,t){var a=t("Cw33");"string"==typeof a&&(a=[[e.i,a,""]]),a.locals&&(e.exports=a.locals);t("rjj0")("7f6dcc9f",a,!0)},Cw33:function(e,r,t){(e.exports=t("FZ+f")(!1)).push([e.i,"\n.line[data-v-78847752]{\r\n  text-align: center;\n}\r\n",""])},"e+iX":function(e,r,t){"use strict";Object.defineProperty(r,"__esModule",{value:!0});var a={render:function(){var e=this,r=e.$createElement,t=e._self._c||r;return t("div",{directives:[{name:"loading",rawName:"v-loading.fullscreen.lock",value:e.loading,expression:"loading",modifiers:{fullscreen:!0,lock:!0}}],staticClass:"app-padding",staticStyle:{width:"600px"}},[t("el-form",{ref:"orderForm",attrs:{model:e.orderForm,rules:e.rules,"label-width":"100px"}},[t("el-form-item",{attrs:{label:"收件人地址",prop:"address"}},[t("el-input",{attrs:{clearable:""},model:{value:e.orderForm.address,callback:function(r){e.$set(e.orderForm,"address",r)},expression:"orderForm.address"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"收件人名",prop:"receiverName"}},[t("el-input",{attrs:{clearable:""},model:{value:e.orderForm.receiverName,callback:function(r){e.$set(e.orderForm,"receiverName",r)},expression:"orderForm.receiverName"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"收件人电话",prop:"receiverNumber"}},[t("el-input",{attrs:{clearable:""},model:{value:e.orderForm.receiverNumber,callback:function(r){e.$set(e.orderForm,"receiverNumber",r)},expression:"orderForm.receiverNumber"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"快递名",prop:"deliveryName"}},[t("el-input",{attrs:{clearable:""},model:{value:e.orderForm.deliveryName,callback:function(r){e.$set(e.orderForm,"deliveryName",r)},expression:"orderForm.deliveryName"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"快递单号",prop:"deliveryNumber"}},[t("el-input",{attrs:{clearable:""},model:{value:e.orderForm.deliveryNumber,callback:function(r){e.$set(e.orderForm,"deliveryNumber",r)},expression:"orderForm.deliveryNumber"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"付款金额",prop:"price"}},[t("el-input",{attrs:{clearable:""},model:{value:e.orderForm.price,callback:function(r){e.$set(e.orderForm,"price",r)},expression:"orderForm.price"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"商品Id",prop:"goodsId"}},[t("el-input",{attrs:{clearable:""},model:{value:e.orderForm.goodsId,callback:function(r){e.$set(e.orderForm,"goodsId",r)},expression:"orderForm.goodsId"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"验证码",prop:"validateCode"}},[t("el-input",{attrs:{clearable:""},model:{value:e.orderForm.validateCode,callback:function(r){e.$set(e.orderForm,"validateCode",r)},expression:"orderForm.validateCode"}})],1),e._v(" "),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:e.onSubmit}},[e._v("提交")]),e._v(" "),t("el-button",{on:{click:function(r){e.resetForm("orderForm")}}},[e._v("复原")])],1)],1)],1)},staticRenderFns:[]};var o=t("VU/8")({data:function(){return{orderForm:{address:"",receiverName:"",receiverNumber:"",deliveryNumber:"",deliveryName:"",price:0,goodsId:this.goodsId,validateCode:""},rules:{address:[{required:!0,message:"请输入收件人地址",trigger:"change"}],receiverName:[{required:!0,message:"请输入收件人名称",trigger:"change"}],receiverNumber:[{required:!0,message:"请输入收件人电话",trigger:"change"}]},loading:!1}},props:["goodsId"],methods:{onSubmit:function(){var e=this;this.$refs.orderForm.validate(function(r){if(!r)return e.$message({showClose:!0,message:"请检查输入内容是否有误",type:"error"}),!1;e.loading=!0,e.$store.dispatch("AddOrder",{address:e.orderForm.address,receiverName:e.orderForm.receiverName,receiverNumber:e.orderForm.receiverNumber,deliveryNumber:e.orderForm.deliveryNumber,deliveryName:e.orderForm.deliveryName,price:e.orderForm.price,goodsId:e.orderForm.goodsId,validateCode:e.orderForm.validateCode}).then(function(){e.resetForm("orderForm"),e.loading=!1,e.$message({showClose:!0,message:"添加成功",type:"success"})}).catch(function(r){e.loading=!1,e.$message({showClose:!0,message:r,type:"error"})})})},resetForm:function(e){this.orderForm.sources=[],this.$refs[e].resetFields()}}},a,!1,function(e){t("1gTc")},"data-v-78847752",null).exports,l=t("Dd8w"),s=t.n(l),i={data:function(){return{searchFrom:{name:"",number:""},loading:!1,orderData:[],currentPage:1,pageSize:15,total:0}},props:["isFetchData"],created:function(){this.fetchData(this.currentPage)},watch:{$route:"fetchData",isFetchData:function(e,r){e&&this.fetchData(this.currentPage)}},methods:{fetchData:function(e,r){var t=this;this.loading=!0;var a=s()({},r);this.$store.dispatch("ListOrder",{currentPage:e,pageSize:this.pageSize,param:a}).then(function(e){t.orderData=e.list,t.total=e.total,t.loading=!1,t.$message({showClose:!0,message:"列表加载完成",type:"success"})}).catch(function(e){t.loading=!1,t.$message({showClose:!0,message:"列表加载失败请刷新重试",type:"error"})})},handleClick:function(e,r,t){r&&r.stopPropagation(),this.$router.push({name:"orderDetial",params:{param:{data:e}}})},handlePageChange:function(e){this.fetchData(e)},handleDeleteClick:function(e,r){var t=this;r&&r.stopPropagation(),this.loading=!0;var a=e.row.id;this.$store.dispatch("DeleteOrder",a).then(function(r){t.loading=!1,t.orderData.splice(e.$index,1),t.$message({showClose:!0,message:"删除成功",type:"success"})}).catch(function(e){console.log(e),t.loading=!1,t.$message({showClose:!0,message:"删除失败",type:"error"})})},handleEditClick:function(e,r){r&&r.stopPropagation(),this.$router.push({name:"orderEdit",params:{param:{data:this.orderData[e.$index]}}})},onSearchSubmit:function(){this.currentPage=1,this.fetchData(this.currentPage,{receiverName:this.searchFrom.receiverName,no:this.searchFrom.no})},formatterDate:function(e,r,t){return new Date(t).toLocaleString()},formatterStatus:function(e,r,t){return 1===t?"待发货":2===t?"已发货":"已完成"},getUrl:function(e){if(""===e)return"";var r=JSON.parse(e);return r.length>0?r[0].url:""}}},n={render:function(){var e=this,r=e.$createElement,t=e._self._c||r;return t("div",[t("div",[t("el-form",{attrs:{inline:!0,model:e.searchFrom}},[t("el-form-item",{attrs:{label:"商品名称"}},[t("el-input",{attrs:{placeholder:"收货人电话"},model:{value:e.searchFrom.receiverName,callback:function(r){e.$set(e.searchFrom,"receiverName",r)},expression:"searchFrom.receiverName"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"订单编号"}},[t("el-input",{attrs:{placeholder:"商品编号"},model:{value:e.searchFrom.no,callback:function(r){e.$set(e.searchFrom,"no",r)},expression:"searchFrom.no"}})],1),e._v(" "),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:e.onSearchSubmit}},[e._v("查询")])],1)],1)],1),e._v(" "),t("el-table",{directives:[{name:"loading",rawName:"v-loading.fullscreen.lock",value:e.loading,expression:"loading",modifiers:{fullscreen:!0,lock:!0}}],staticStyle:{width:"100%"},attrs:{data:e.orderData,"empty-text":"无"},on:{"row-click":e.handleClick}},[t("el-table-column",{attrs:{fixed:"",prop:"id",label:"id"}}),e._v(" "),t("el-table-column",{attrs:{prop:"no",label:"订单编号"}}),e._v(" "),t("el-table-column",{attrs:{prop:"deliveryName",label:"快递名"}}),e._v(" "),t("el-table-column",{attrs:{prop:"deliveryNumber",label:"快递编号"}}),e._v(" "),t("el-table-column",{attrs:{prop:"status",formatter:e.formatterStatus,label:"订单状态"}}),e._v(" "),t("el-table-column",{attrs:{prop:"address",label:"收货地址"}}),e._v(" "),t("el-table-column",{attrs:{prop:"receiverName",label:"收货人姓名"}}),e._v(" "),t("el-table-column",{attrs:{prop:"receiverNumber",label:"收货人电话"}}),e._v(" "),t("el-table-column",{attrs:{prop:"goods.name",label:"商品名"}}),e._v(" "),t("el-table-column",{attrs:{label:"商品缩略图"},scopedSlots:e._u([{key:"default",fn:function(r){return[t("img",{attrs:{src:e.getUrl(r.row.goods.sources),width:"50px",height:"50px"}})]}}])}),e._v(" "),t("el-table-column",{attrs:{prop:"goods.salePrice",label:"商品价格(元)"}}),e._v(" "),t("el-table-column",{attrs:{prop:"price",label:"付款金额(元)"}}),e._v(" "),t("el-table-column",{attrs:{prop:"createTime",formatter:e.formatterDate,label:"创建时间"}}),e._v(" "),t("el-table-column",{attrs:{prop:"modifyTime",formatter:e.formatterDate,label:"修改时间"}}),e._v(" "),t("el-table-column",{attrs:{label:"操作",fixed:"right",width:"100px"},scopedSlots:e._u([{key:"default",fn:function(r){return[t("el-button",{attrs:{type:"text",size:"small"},on:{click:function(t){e.handleEditClick(r,t)}}},[e._v("编辑")]),e._v(" "),t("el-button",{attrs:{type:"text",size:"small"},on:{click:function(t){e.handleDeleteClick(r,t)}}},[e._v("删除")])]}}])})],1),e._v(" "),t("el-pagination",{staticStyle:{"text-align":"center","margin-top":"15px"},attrs:{background:"",layout:"prev, pager, next","page-size":10,"current-page":e.currentPage,total:e.total},on:{"update:currentPage":function(r){e.currentPage=r},"current-change":e.handlePageChange}})],1)},staticRenderFns:[]},c={data:function(){return{activeName:"first",isFetchData:!1,goodsId:0}},components:{AddComponent:o,listComponent:t("VU/8")(i,n,!1,null,null,null).exports},methods:{handleClick:function(e,r){"0"===e.index&&(this.isFetchData=this.$store.state.order.isFetchOrderData)}}},d={render:function(){var e=this,r=e.$createElement,t=e._self._c||r;return t("div",{staticClass:"app-container"},[t("el-tabs",{on:{"tab-click":e.handleClick},model:{value:e.activeName,callback:function(r){e.activeName=r},expression:"activeName"}},[t("el-tab-pane",{attrs:{label:"订单列表",name:"first"}},[t("list-component",{attrs:{"is-fetch-data":e.isFetchData}})],1),e._v(" "),t("el-tab-pane",{attrs:{label:"添加订单",name:"second"}},[t("AddComponent",{attrs:{"goods-id":e.goodsId}})],1)],1)],1)},staticRenderFns:[]},m=t("VU/8")(c,d,!1,null,null,null);r.default=m.exports}});