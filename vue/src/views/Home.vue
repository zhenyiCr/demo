<template>
  <div>
    <div>
      <el-input style="margin-right: 10px;width: 260px;margin-bottom: 7px" placeholder="请输入内容"
                :prefix-icon="Search"
                v-model="data.name"></el-input>
      <el-button type="primary">查询</el-button>
    </div>
    <div style="margin-bottom: 5px">
      <el-button type="danger">删除</el-button>
      <el-button type="primary">新增</el-button>
      <el-button type="success">导出</el-button>
    </div>
    <div style="margin-bottom: 530px">
      <el-table :data="data.tableData" style="width: 100%"
                :header-cell-style="{fontWeight:'bold',background:'#f5f5f5'}">
        <el-table-column type="selection" width="55" />
        <el-table-column prop="name" label="姓名" width="180"/>
        <el-table-column prop="phone" label="电话号" width="180"/>
        <el-table-column prop="address" label="地址"/>
      </el-table>
    </div>
    <div style="margin-top: 10px">
      <el-pagination
          v-model:current-page="data.pageNum"
          :page-size="data.pageSize"
          layout="total, prev, pager, next"
          :total="data.total"
      />
    </div>
  </div>

</template>

<script setup>
import {reactive} from "vue"
import {Search} from "@element-plus/icons-vue";

import request from "@/utils/request.js";
import {ElMessage} from "element-plus";

const data = reactive({
      name: null,
      pageNum: 1,
      pageSize: 10,
      total: 0,
      tableData: [
        {name: '王小虎', address: '上海市普陀区金沙江路 1518 弄', phone: '1234567890'},
        {name: '王小虎', address: '上海市普陀区金沙江路 1518 弄', phone: '1234567890'},
        {name: '王小虎', address: '上海市普陀区金沙江路 1518 弄', phone: '1234567890'},
      ]}
)

request.get('/admin/selectAll').then(res => {
  if (res.code === 200) {
    console.log(res)
  } else {
    ElMessage.error(res.msg);
  }


})


</script>