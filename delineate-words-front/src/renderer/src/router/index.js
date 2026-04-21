import { createRouter, createWebHashHistory } from 'vue-router'
import Main from '@/views/Main.vue'
import GeneralSettings from '@/views/GeneralSettings.vue'
import ShortcutSettings from '@/views/ShortcutSettings.vue'
import ApiAccess from '@/views/ApiAccess.vue'
import FeaturePrompts from '@/views/FeaturePrompts.vue'
import BlacklistSettings from '@/views/BlacklistSettings.vue'
// 1. 创建路由实例
const router = createRouter({
	history: createWebHashHistory(),
	routes: [
		{
			path: '/',
			component: Main,
			children: [
				{
					path: '',
					redirect: '/general-settings'
				},
				{
					path: 'general-settings',
					name: 'general-settings',
					component: GeneralSettings
				},
				{
					path: 'shortcut-settings',
					name: 'shortcut-settings',
					component: ShortcutSettings
				},
				{
					path: 'api-access',
					name: 'api-access',
					component: ApiAccess
				},
				{
					path: 'feature-prompts',
					name: 'feature-prompts',
					component: FeaturePrompts
				},
				{
					path: 'blacklist-settings',
					name: 'blacklist-settings',
					component: BlacklistSettings
				}
			]
		}
	]
})

export default router