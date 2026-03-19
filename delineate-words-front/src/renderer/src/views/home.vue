<template>
    <el-container class="layout-container-demo" style="height: 500px">
        <el-aside width="200px">
            <el-scrollbar>
                <el-menu :default-openeds="['1', '1']">
                    <template #title>
                        <el-icon>
                            <message />
                        </el-icon>Navigator One
                    </template>
                    <el-menu-item-group>
                        <template #title>设置</template>
                        <el-menu-item index="1-1">开机自启动</el-menu-item>
                        <el-menu-item index="1-2">Option 2</el-menu-item>
                    </el-menu-item-group>
                    <el-sub-menu index="1-4">
                        <template #title>接入自定义api</template>
                        <el-menu-item index="1-4-1">Option 4-1</el-menu-item>
                    </el-sub-menu>
                </el-menu>
            </el-scrollbar>
        </el-aside>
        <el-container>
            <el-header style="text-align: right; font-size: 12px">
                <button @click="cli">按钮</button>
                <button @click="newBrower">打开一个新的窗口</button>
            </el-header>
        </el-container>
    </el-container>
</template>

<script lang="ts" setup>
import { ref } from 'vue'

import { Menu as IconMenu, Message, Setting } from '@element-plus/icons-vue'

const cli = () => {
    console.log("点击了按钮");
    electron.ipcRenderer.invoke('websss', {
        //传递对象
        a: 1,
    });
}
const newBrower = () => {
    console.log("打开一个新的窗口");
    electron.ipcRenderer.invoke('new-window', {
        //传递对象
        b: 2,
    });
}

</script>

<style scoped>
.layout-container-demo .el-header {
    position: relative;
    background-color: var(--el-color-primary-light-7);
    color: var(--el-text-color-primary);
}

.layout-container-demo .el-aside {
    color: var(--el-text-color-primary);
    background: var(--el-color-primary-light-8);
}

.layout-container-demo .el-menu {
    border-right: none;
}

.layout-container-demo .el-main {
    padding: 0;
}

.layout-container-demo .toolbar {
    display: inline-flex;
    align-items: center;
    justify-content: center;
    height: 100%;
    right: 20px;
}
</style>