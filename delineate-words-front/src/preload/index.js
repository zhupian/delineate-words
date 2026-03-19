import { contextBridge } from 'electron'
import { electronAPI } from '@electron-toolkit/preload'
import { ipcRenderer } from 'electron'
// 提供给渲染进程的自定义 API
const api = {}

// 使用 `contextBridge` 仅在启用上下文隔离时
// 向渲染进程暴露 Electron API，否则
// 直接挂载到 DOM 全局对象上。
if (process.contextIsolated) {
  try {
    contextBridge.exposeInMainWorld('electron', electronAPI)
    contextBridge.exposeInMainWorld('api', api)
  } catch (error) {
    console.error(error)
  }
} else {
  window.electron = electronAPI
  window.api = api
}
