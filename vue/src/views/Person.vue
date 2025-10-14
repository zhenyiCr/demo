<template>
    <div>
        <div>个人中心</div>
        <div>
            <el-form ref="formRef" :model="data.form" :rules="data.rules" label-width="80px"
                     style="padding:20px 30px 20px 0">
                <el-form-item prop="username" label="账号">
                    <el-input v-model="data.user.username" autocomplete="off"/>
                </el-form-item>
                <el-form-item prop="name" label="名称">
                    <el-input v-model="data.user.name" autocomplete="off"/>
                </el-form-item>
                <el-form-item prop="phone" label="电话">
                    <el-input v-model="data.user.phone" autocomplete="off"/>
                </el-form-item>
                <el-form-item prop="email" label="邮箱">
                    <el-input v-model="data.user.email" autocomplete="off"/>
                </el-form-item>
                <el-form-item prop="avatar" label="头像">
                    <el-upload action="http://127.0.0.1:8080/file/upload"
                               :headers="{token: data.user.token}"
                               :on-success="handleFileSuccess"
                               list-type="picture"
                    >
                        <el-button>上传头像</el-button>
                    </el-upload>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script setup>
import {reactive} from "vue";

const data = reactive({
        user: JSON.parse(localStorage.getItem('user') || '{}'),
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
        },
        rows: [],
        ids: [],
    }
)
</script>

<style scoped>

</style>
