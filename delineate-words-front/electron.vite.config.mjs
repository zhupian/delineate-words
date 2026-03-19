import { resolve } from 'path'
import { defineConfig } from 'electron-vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
  main: {},
  preload: {},
  renderer: {
    resolve: {
      alias: {
        '@': resolve('src/renderer/src'),
        '@renderer': resolve('src/renderer/src'),
        '@store': resolve('src/renderer/src/store'),
        '@utils': resolve('src/renderer/src/utils'),
      }
    },
    server:{
      "proxy":{
        "/api":{
          target:'http://uat.crm.xuexiluxian.cn',
          changeOrigin:true,
          rewrite: path =>path.replace(/^\/api/,'')
          }
        }
      },
    plugins: [vue()]
  }
})
