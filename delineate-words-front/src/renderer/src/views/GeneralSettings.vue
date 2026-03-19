<template>
    <el-card shadow="never" class="general-settings-card">
        <template #header>
            <span>常规设置</span>
        </template>

        <div class="setting-row">
            <span class="setting-theme">应用主题</span>
            <el-dropdown trigger="click" @command="handleThemeCommand">
                <el-button class="theme-button" round>
                    {{ themeLabel }}
                </el-button>
                <template #dropdown>
                    <el-dropdown-menu class="theme-menu">
                        <el-dropdown-item command="system">跟随系统</el-dropdown-item>
                        <el-dropdown-item command="light">浅色</el-dropdown-item>
                        <el-dropdown-item command="dark">深色</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
        </div>
    </el-card>
</template>

<script setup>
import { computed, ref } from 'vue'

const theme = ref('system')

const themeLabels = {
    system: '跟随系统',
    light: '浅色',
    dark: '深色'
}

const themeLabel = computed(() => themeLabels[theme.value] ?? themeLabels.system)

const handleThemeCommand = (command) => {
    theme.value = command
}
</script>

<style scoped></style>
