<template>
  <div>
    <div class="card">
      <div>
        <el-input clearable @clear="getData" style="margin-right: 10px;width: 260px;margin-bottom: 7px;margin-top: 8px"
                  placeholder="请输入内容"
                  :prefix-icon="Search"
                  v-model="data.name"></el-input>
        <el-button type="primary" @click="getData">查询</el-button>
        <el-button @click="reset">重置</el-button>
      </div>
      <div style="margin-bottom: 5px;margin-top: 5px">
        <el-button @click="headleAdd" type="primary">新增</el-button>
        <el-button type="danger">删除</el-button>
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
        <el-table-column label="操作" width="150">
          <template #default="scope">
            <el-button @click="headleEdit(scope.row)" type="primary" icon="edit"></el-button>
            <el-button @click="del(scope.row.id)" type="danger" icon="delete"></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div style=": margin-top: 10px">
      <el-pagination
          v-model:current-page="data.pageNum"
          v-model:page-size="data.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :page-sizes="[5, 10, 15]"
          :total="data.total"
          @current-change="getData"
          @size-change="getData"
      />
    </div>
    <el-dialog title="管理员信息" v-model="data.formVisible" width="500" destroy-on-close>
      <el-form ref="formRef" :model="data.form" :rules="data.rules" label-width="80px" style="padding:20px 30px 20px 0">
        <el-form-item prop="username" label="账号">
          <el-input v-model="data.form.username" autocomplete="off"/>
        </el-form-item>
        <el-form-item prop="name" label="名称">
          <el-input v-model="data.form.name" autocomplete="off"/>
        </el-form-item>
        <el-form-item prop="phone" label="电话">
          <el-input v-model="data.form.phone" autocomplete="off"/>
        </el-form-item>
        <el-form-item prop="email" label="邮箱">
          <el-input v-model="data.form.email" autocomplete="off"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <div>
          <el-button @click="data.formVisible = false">取 消</el-button>
          <el-button type="primary" @click="save">提 交</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import {reactive, ref} from "vue"
import {Search} from "@element-plus/icons-vue";

import request from "@/utils/request.js";
import {ElMessage, ElMessageBox} from "element-plus";


const data = reactive({
      name: null,
      pageNum: 1,
      pageSize: 10,
      total: 0,
      tableData: [],
      formVisible: false,
      form: {},
      rules: {
        username: [
          {required: true, message: '请输入账号', trigger: 'blur'},
        ],
        name: [
          {required: true, message: '请输入名称', trigger: 'blur'},
        ],
        phone: []
        , email: [
          {required: true, message: '请输入邮箱', trigger: 'blur'},
        ]
      }
    }
)

const formRef = ref()

const getData = () => {
  request.get('/admin/selectPage', {
        params: {
          pageNum: data.pageNum,
          pageSize: data.pageSize,
          name: data.name
        }
      }
  ).then(res => {
    if (res.code === '200') {
      data.tableData = res.data.list
      data.total = res.data.total
    } else {
      ElMessage.error(res.msg)
    }
  })
}
getData()
const reset = () => {
  data.name = null
  getData()
}
const headleAdd = () => {
  data.formVisible = true
  data.form = {}
}
const add = () => {
  // formRef 表单的验证
  formRef.value.validate((valid) => {
    if (valid) { // 表单验证成功
      request.post('/admin/add', data.form).then(res => {
        if (res.code === '200') {
          ElMessage.success("新增成功")
          data.formVisible = false
          getData()
        } else {
          ElMessage.error(res.msg)
        }
      })
    }
  })
}
const headleEdit = (row) => {
  data.formVisible = true
  data.form = JSON.parse(JSON.stringify(row))
}
const edit = () => {
  // formRef 表单的验证
  formRef.value.validate((valid) => {
    if (valid) { // 表单验证成功
      request.put('/admin/update', data.form).then(res => {
        if (res.code === '200') {
          ElMessage.success("修改成功")
          data.formVisible = false
          getData()
        } else {
          ElMessage.error(res.msg)
        }
      })
    }
  })
}
const save = () => {
  data.form.id ? edit() : add()
}

const del = (id) => {
  ElMessageBox.confirm(' 你确定删除信息吗', 'Warning', {type: 'warning'}).then(() => {
    request.delete('/admin/delete/' + id).then(res => {
      if (res.code === '200') {
        ElMessage.success("删除成功")
        getData()
      } else {
        ElMessage.error(res.msg)
      }
    }).catch(() => {
      ElMessage({
        type: 'info',
        message: 'Delete canceled',
      })
    })
  })
}
</script>