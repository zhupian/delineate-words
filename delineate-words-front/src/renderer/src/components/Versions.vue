<script setup>
import { reactive } from 'vue'

const versions = reactive({ ...window.electron.process.versions })
</script>

<template>
  <ul class="versions">
    <li class="electron-version">Electron 版本 v{{ versions.electron }}</li>
    <li class="chrome-version">Chromium 版本 v{{ versions.chrome }}</li>
    <li class="node-version">Node 版本 v{{ versions.node }}</li>
  </ul>
</template>
