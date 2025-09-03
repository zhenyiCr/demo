<template>
  <div>
    <div class="card">
<!--      <el-input style="margin-right: 10px;width: 260px" placeholder="请输入内容":prefix-icon="Search" v-model="data.name"></el-input>-->
<!--      <el-button type="primary">查询</el-button>-->
    </div>
  </div>

</template>

<script setup>
import {reactive} from "vue"
import {Search} from "@element-plus/icons-vue";

const data = reactive({
      name: null
    }
)

</script>