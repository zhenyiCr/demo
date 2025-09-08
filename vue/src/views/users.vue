<template>
  <div>
    <div class="card">
      <div>
        <el-input clearable @clear="getData" style="margin-right: 10px;width: 260px;margin-bottom: 7px;margin-top: 8px" placeholder="请输入内容"
                  :prefix-icon="Search"
                  v-model="data.name"></el-input>
        <el-button type="primary" @click="getData">查询</el-button>
        <el-button @click="reset">重置</el-button>
      </div>
      <div style="margin-bottom: 5px;margin-top: 5px">
        <el-button type="danger">删除</el-button>
        <el-button type="primary">新增</el-button>
        <el-button type="success">导出</el-button>
      </div>
    </div>


    <div>
      <el-table :data="data.tableData" style="width: 100%"
                :header-cell-style="{fontWeight:'bold',background:'#f5f5f5'}">
        <el-table-column type="selection" width="55"/>
        <el-table-column prop="username" label="账户"/>
        <el-table-column prop="name" label="姓名"/>
        <el-table-column prop="phone" label="电话号"/>
        <el-table-column prop="email" label="邮箱"/>
      </el-table>
    </div>

    <div style=": margin-top: 10px">
      <el-pagination
          v-model:current-page="data.pageNum"
          v-model:page-size="data.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :page-sizes="[5, 10, 15, 20]"
          :total="data.total"
          @current-change="getData"
          @size-change="getData"
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
      tableData: []
    }
)

const getData = () => {
  request.get('/admin/selectPage',{
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      name: data.name
    }
      }
  ).then(res => {
    if (res.code === '200')
    {
      data.tableData = res.data.list
      data.total = res.data.total
    }else {
      ElMessage.error(res.msg)
    }
  })
}
getData()
const reset = () => {
  data.name = null
  getData()
}

</script>