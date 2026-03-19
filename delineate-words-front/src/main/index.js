import { app, shell, BrowserWindow, ipcMain } from 'electron'
import { join } from 'path'
import { electronApp, optimizer, is } from '@electron-toolkit/utils'
import icon from '../../resources/icon.png?asset'
import { log } from 'console'

function createWindow() {
  // 创建浏览器窗口。
  const mainWindow = new BrowserWindow({
    // width: 300,
    // height: 570,
    show: false,
    autoHideMenuBar: true,
    ...(process.platform === 'linux' ? { icon } : {}),
    webPreferences: {
      preload: join(__dirname, '../preload/index.js'),
      sandbox: false
    }
  })

  mainWindow.on('ready-to-show', () => {
    mainWindow.show()
  })

  mainWindow.webContents.setWindowOpenHandler((details) => {
    shell.openExternal(details.url)
    return { action: 'deny' }
  })

  // 基于 electron-vite CLI 为渲染进程启用热更新（HMR）。
  // 开发环境加载远程 URL，生产环境加载本地 html 文件。
  if (is.dev && process.env['ELECTRON_RENDERER_URL']) {
    mainWindow.loadURL(process.env['ELECTRON_RENDERER_URL'])
  } else {
    mainWindow.loadFile(join(__dirname, '../renderer/index.html'))
  }
}

// 当 Electron 完成初始化并准备创建浏览器窗口时会调用此方法。
// 某些 API 只能在该事件发生后使用。
app.whenReady().then(() => {
  // 为 Windows 设置应用用户模型 ID
  electronApp.setAppUserModelId('com.electron')

  // 开发环境可通过 F12 默认打开或关闭 DevTools，
  // 生产环境忽略 CommandOrControl + R。
  // 详见：https://github.com/alex8088/electron-toolkit/tree/master/packages/utils
  app.on('browser-window-created', (_, window) => {
    optimizer.watchWindowShortcuts(window)
  })

  // IPC 测试
  ipcMain.on('ping', () => console.log('pong'))

  createWindow()

  app.on('activate', function () {
    // 在 macOS 上，当点击 Dock 图标且没有其他窗口打开时，
    // 通常会在应用中重新创建一个窗口。
    if (BrowserWindow.getAllWindows().length === 0) createWindow()
  })
})

// 当所有窗口都关闭时退出应用，但 macOS 除外。
// 在 macOS 上，应用及其菜单栏通常会保持激活状态，
// 直到用户显式按下 Cmd + Q 退出。
app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') {
    app.quit()
  }
})

// 你可以在此文件中编写应用主进程的其余业务代码，
// 也可以拆分到单独文件后在这里引入。
//主进程接收渲染进程消息
ipcMain.handle('websss',(event,msg)=>{
  console.log(111,msg);
})
ipcMain.handle('newBrower',(event,msg)=>{
  
})