<template>
    <!-- 整体左右布局容器 -->
    <el-container class="settings-layout">
        <!-- 左侧菜单区域 -->
        <el-aside width="220px" class="settings-layout__aside">
            <div class="settings-layout__brand">应用设置</div>
            <el-scrollbar>
                <!--
                  router: 开启“菜单项即路由跳转”模式
                  index: 这里写路由路径，点击时会自动跳转到该路径
                  :default-active="activePath": 当前高亮项跟随路由地址变化
                -->
                <el-menu :default-active="activePath" router>
                    <el-menu-item index="/general-settings">常规设置</el-menu-item>
                    <el-menu-item index="/shortcut-settings">快捷键</el-menu-item>
                    <el-menu-item index="/api-access">API 接入</el-menu-item>
                    <el-menu-item index="/feature-prompts">功能与提示词</el-menu-item>
                    <el-menu-item index="/blacklist-settings">黑名单设置</el-menu-item>
                </el-menu>
            </el-scrollbar>
        </el-aside>

        <el-container>
            <!-- 顶部标题：根据当前路由动态显示 -->
            <el-header class="settings-layout__header">
                <span>{{ pageTitle }}</span>
            </el-header>
            <el-main class="settings-layout__main">
                <!--
                  关键点：右侧内容出口
                  当你点击左侧菜单后，路由地址会变，匹配到的子路由组件会显示在这里
                  例如点击“API 接入”后，这里显示 ApiAccess.vue
                -->
                <router-view />
            </el-main>
        </el-container>
    </el-container>
</template>

<script setup>
import { computed } from 'vue'
import { useRoute } from 'vue-router'

// 读取当前路由对象（包含当前 path）
const route = useRoute()

// 路由 path 与页面标题的对应表
const titleMap = {
    '/general-settings': '常规设置',
    '/shortcut-settings': '快捷键',
    '/api-access': 'API 接入',
    '/feature-prompts': '功能与提示词',
    '/blacklist-settings': '黑名单设置'
}

// 左侧菜单高亮：始终跟随当前路由
const activePath = computed(() => route.path)
// 顶部标题：根据当前路由动态计算
const pageTitle = computed(() => titleMap[route.path] ?? '设置')
</script>

<style scoped>
.settings-layout {
    height: 100vh;
}

.settings-layout__aside {
    border-right: 1px solid var(--el-border-color-light);
    /* 左侧背景色 */
    background: white;
}

.settings-layout__brand {
    padding: 16px;
    font-weight: 600;
    border-bottom: 1px solid var(--el-border-color-light);
}

.settings-layout__header {
    display: flex;
    align-items: center;
    font-size: 16px;
    font-weight: 600;
    border-bottom: 1px solid var(--el-border-color-light);
}

.settings-layout__main {
    background: var(--el-bg-color-page);
}
</style>
