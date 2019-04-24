<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理主面板</title>
	<script type="text/javascript" src="/PxxySecondHand_Portal/js/vue.js"></script>
	<script type="text/javascript" src="/PxxySecondHand_Portal/js/vuetify.js"></script>
	<link rel="stylesheet" href="/PxxySecondHand_Portal/css/vuetify.css"/>
	<link href="https://fonts.googleapis.com/css?family=Material+Icons" rel="stylesheet"/>    
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
    	<v-divider/>    <!-- 在该容器下面添加一条分割线 -->
    	   <!-- 左侧菜单 -->
	     <!--  <v-list class="pt-0" dense>
	        <v-list-group
	          v-model="item.active"
	          v-for="item in items"
	          :key="item.title"
	          :prepend-icon="item.action"
	          no-action
	        >
	          一级菜单
	          <v-list-tile slot="activator">
	            <v-list-tile-content>
	              <v-list-tile-title>{{ item.title }}</v-list-tile-title>
	            </v-list-tile-content>
	          </v-list-tile>
	          二级菜单
	          <v-list-tile v-for="subItem in item.items" :key="subItem.title" :to="item.path + subItem.path">
	            <v-list-tile-content>
	              <v-list-tile-title>{{ subItem.title }}</v-list-tile-title>
	            </v-list-tile-content>
	            <v-list-tile-action>
	              <v-icon>{{ subItem.action }}</v-icon>
	            </v-list-tile-action>
	          </v-list-tile>
	        </v-list-group>
	      </v-list> -->
    	</v-navigation-drawer>
	</v-app>
</body>
<script  type="module">
  import menu from "../js/menu.js";    //导入数据
	var Main = new Vue({
		el:"#MainFrame",
		data:{
			dark:false,
			miniVariant:false, //左侧是否收起
			drawer : true  //左侧是否隐藏
		},
		methods:{
			
		},
		computed:{
			items(){
				return menu;
			}
		}
	})
</script>
</html>