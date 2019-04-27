<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理主面板</title>
	<script type="text/javascript" src="/PxxySecondHand_Portal/js/vue.js"></script>
	<script type="text/javascript" src="/PxxySecondHand_Portal/js/vuetify.js"></script>
	<script type="text/javascript" src="/PxxySecondHand_Portal/js/vue-router.js"></script>
	<link rel="stylesheet" href="/PxxySecondHand_Portal/css/vuetify.css"/>
	<link href="https://fonts.googleapis.com/css?family=Material+Icons" rel="stylesheet"/> 
	<!-- import CSS -->
	<link rel="stylesheet" href="/PxxySecondHand_Portal/js/element-ui@2.8.2/theme-chalk/index.css">
	<!-- import JavaScript -->
	<script src="/PxxySecondHand_Portal/js/element-ui@2.8.2/index.js"></script>  
	<link rel="stylesheet" href="/PxxySecondHand_Portal/components/admin/css/upload.css"/> 
</head>
<body>
	<v-app id="MainFrame" :dark="dark">
		<!-- 左侧导航条 -->
	    <v-navigation-drawer
	      :dark="dark"
	      persistent
	      :mini-variant="miniVariant"  
	      v-model="drawer"
	      enable-resize-watcher
	      fixed
	      app
    	>
    	<v-toolbar flat class="transparent fl-0" >  <!-- 头像 -->
    		<v-list>
	    		 <v-list-tile
		            avatar
	          	>
	            <v-list-tile-avatar>
	              <img src="https://cdn.vuetifyjs.com/images/lists/3.jpg">
	            </v-list-tile-avatar>
	            <v-list-tile-content>
	              <v-list-tile-title >老虎</v-list-tile-title>
	            </v-list-tile-content>
            	</v-list-tile>
	         </v-list>
    	</v-toolbar>
    	
    	<v-divider>     <!-- 分割线  必须双标签 -->
    	</v-divider>
    	
   	   <!-- 左侧菜单 -->
      <v-list class="pt-0" dense>
        <v-list-group
          v-model="item.active"
          v-for="item in items"
          :key="item.title"
          :prepend-icon="item.action"
          no-action
        >
          <!--一级菜单 -->
          <v-list-tile slot="activator">
            <v-list-tile-content>
              <v-list-tile-title>{{ item.title }}</v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>
          <!-- 二级菜单 -->
          <v-list-tile v-for="subItem in item.items" :key="subItem.title" :to="item.path + subItem.path" @click="changeBreadCrumb(item.title,subItem.title)">
            <v-list-tile-content>
              <v-list-tile-title>{{ subItem.title }}</v-list-tile-title>
            </v-list-tile-content>
            <v-list-tile-action>
              <v-icon>{{ subItem.action }}</v-icon>
            </v-list-tile-action>
          </v-list-tile>
        </v-list-group>
      </v-list>
    </v-navigation-drawer>
    
      <!-- 顶部工具条 -->
    <v-toolbar
      app
      dark
      :color="dark ? 'secondary' : 'primary'"
    >
	      <!-- 隐藏左侧菜单的按钮-->
	      <v-toolbar-side-icon @click.stop="drawer = !drawer" >
	      </v-toolbar-side-icon>
	         <!-- 收起左侧菜单的按钮-->
	      <v-btn icon @click.stop="miniVariant = !miniVariant">
	        <v-icon v-html="miniVariant ? 'chevron_right' : 'chevron_left'"/>
	      </v-btn>
	      <!-- 切换黑暗主题 -->
	      <v-flex xs1>
	        <v-switch
	          :label="dark ? '暗黑' : '明亮'"
	          v-model="dark"
	          color="dark"
	          hide-details
	        />
	      </v-flex>
	       <!-- 顶部导航标题 -->
	      <v-flex xs3></v-flex>
	      <v-toolbar-title v-text="title"/>
	      <v-spacer/>
	    </v-toolbar>
	      <!--中间内容主体-->
	    <v-content>
	      <v-breadcrumbs>
	        <v-icon slot="divider">chevron_right</v-icon>
	        <v-breadcrumbs-item>{{item1}}</v-breadcrumbs-item>
	        <v-breadcrumbs-item>{{item2}}</v-breadcrumbs-item>
	      </v-breadcrumbs>
	      <div>
	        <!--定义一个路由锚点，Layout的子组件内容将在这里展示-->
	        <router-view>
	        </router-view>
	      </div>
	    </v-content>
 </v-app>
</body>
<script  type="module">
  import menu from "../components/admin/js/menu.js";    //导入数据
  import advertisement from "../components/admin/mainContent/advertisement.js";    //导入数据
  import publicMessage from "../components/admin/mainContent/publicMessage.js";    //导入数据
	const router = new VueRouter({
		routes:[{path : "/other/advertisement" , component : advertisement }
				,{path : "/other/publicMessage" , component : publicMessage }
				]
	})
	
	
	var Main = new Vue({
		el:"#MainFrame",
		data:{
			dark:false,
			miniVariant:false, //左侧是否收起
			drawer : true  ,//左侧是否隐藏
     		drawerRight: false,
			title:"跳蚤市场后台管理系统",
			item1:"首页",
			item2:"统计"
		},
		methods:{
			changeBreadCrumb(title,subtitle){
				this.item1 = title;
				this.item2 = subtitle;
			}
		},
		computed:{
			items(){
				return menu;
			}
		},
		components:{
	   		advertisement,
			publicMessage
		},
		router,
	})
</script>
</html>