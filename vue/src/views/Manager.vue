<template>
  <div>
    <!-- 头部区域开始 -->
    <div style="height: 60px;display: flex ">
      <div style="width: 240px;display: flex;align-items: center;background-color: #fcde4e ">
        <span style="font-size: 30px;font-weight: bold;color: aqua;margin-left: 50px">xxxxxx</span>
      </div>
      <div style="flex: 1;display: flex;align-items: center;border-bottom: 1px black solid;">

        <span style="font-size: 20px;font-weight: bold;margin-left: 25px">首页/数据分析</span>
      </div>

      <div style="width: fit-content;display: flex;align-items: center ;margin-right: 20px;border-bottom: 1px black solid">

        <el-dropdown >
          <div style="display: flex;align-items: center">
             <img style="height: 40px;width: 40px;border-radius: 50%" src="../assets/imgs/touxiang.png" alt="">
             <span type="margin-left: 5px">管理员</span>
          </div>

          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item>个人信息</el-dropdown-item>
              <el-dropdown-item>修改密码</el-dropdown-item>
              <el-dropdown-item>退出登录</el-dropdown-item>

            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>
    <!-- 头部区域结束 -->

    <!-- 下方区域开始   -->
    <div style="display: flex">

      <!--  菜单区域开始    -->
      <div style="width: 240px">
        <el-menu default-active="/manager/home" style="min-height: calc(100vh - 60px)">
          <el-menu-item index="/manager/home">
            <el-icon>
              <el-icon>
                <House/>
              </el-icon>
            </el-icon>
            <span>首页</span>
          </el-menu-item>
          <el-sub-menu index="1">
            <template #title>
              <el-icon>
                <location/>
              </el-icon>
              <span>数据管理</span>
            </template>

            <el-menu-item index="1-1">item one</el-menu-item>
            <el-menu-item index="1-2">item two</el-menu-item>


          </el-sub-menu>


        </el-menu>
      </div>
      <!--  菜单区域结束    -->

      <!--  数据区域开始    -->
      <div style="margin: 10px;background-color: #e0e3ff;flex: 1">
        <RouterView/>
      </div>
      <!--  数据区域结束    -->

    </div>
    <!-- 下方区域结束   -->


  </div>
</template>

<script setup>

</script>

<style>
.el-menu{
  background-color: #fcde4e;
}
</style>