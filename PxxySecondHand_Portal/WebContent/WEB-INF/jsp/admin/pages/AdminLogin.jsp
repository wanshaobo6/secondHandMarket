<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui">
<title>跳蚤市场后台登录页面</title>
</head>
	<script type="text/javascript" src="/PxxySecondHand_Portal/js/vue.js"></script>
	<script type="text/javascript" src="/PxxySecondHand_Portal/js/vuetify.js"></script>
	<link rel="stylesheet" href="/PxxySecondHand_Portal/css/vuetify.css"/>
	<link href="https://fonts.googleapis.com/css?family=Material+Icons" rel="stylesheet"/>    
  <body>
	<div id="loginPage">
		<v-app>
	    	<v-content>
			      <v-container fluid fill-height>
			        <v-layout align-center justify-center>
			          <v-flex xs12 sm8 md4>
			            <v-card class="elevation-12">
			              <v-toolbar dark color="primary">
			                <v-toolbar-title>跳蚤市场后台管理</v-toolbar-title>
			                <v-spacer></v-spacer>
			              </v-toolbar>
			              <v-card-text>
			                <v-form>
			                  <v-text-field  prepend-icon="person" v-model="username" label="用户名" type="text" ></v-text-field>
			                  <v-text-field  
			                    prepend-icon="lock"
			                    v-model="password"
			                    label="密码"
			                    id="password"
			                    :append-icon="e1 ? 'visibility' : 'visibility_off'"
			                    :append-icon-cb="() => (e1 = !e1)"
			                    :type="e1 ? 'text' : 'password'"
			                 ></v-text-field>
			                </v-form>
			              </v-card-text>
			              <v-card-actions>
			                <v-spacer></v-spacer>
			                <v-btn color="primary" @click="doLogin">登录</v-btn>
			              </v-card-actions>
			            </v-card>
			          </v-flex>
			        </v-layout>
			      </v-container>
			  </v-content>
	    <v-dialog v-model="dialog" width="300px">
	      <v-alert icon="warning" color="error" :value="true">
	            用户名和密码不能为空
	      </v-alert>
	    </v-dialog>
  </v-app>
	</div>

	<script >
		var app = new Vue({
			el:"#loginPage",
			data:{
				username:"",
				password:"",
				dialog: false,
			    e1:false
			},
			methods:{
				doLogin(){
					if(this.username == "" || this.password == "")
					{
						this.dialog = true;
						return;
					}
				}
			}
		})
	</script>
</body>
</html>