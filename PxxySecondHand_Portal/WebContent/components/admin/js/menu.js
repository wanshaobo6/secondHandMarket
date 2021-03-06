var menus = [
  {
    action: "home",
    title: "首页",
    path:"/index",
    items: [{ title: "统计", path: "/dashboard" }]
  },
  {
	    action: "list",
	    title: "物品管理",
	    path:"/item",
	    items: [
	      { title: "分类管理", path: "/categoryManage" },
	      { title: "订单管理", path: "/order" },
	      { title: "物流管理", path: "/logistics" },
	      { title: "促销管理", path: "/promotion" }
	    ]
},
  {
    action: "people",
    title: "会员管理",
    path:"/user",
    items: [
      { title: "会员统计", path: "/statistics" },
      { title: "会员管理", path: "/list" }
    ]
  },
  {
    action: "settings",
    title: "权限管理",
    path:"/authority",
    items: [
      { title: "权限管理", path: "/list" },
      { title: "角色管理", path: "/role" },
      { title: "人员管理", path: "/member" }
    ]
  },
  {
	    action: "apps",
	    title: "其他",
	    path:"/other",
	    items: [
	      { title: "广告管理", path: "/advertisement"},
	      { title: "动态发布", path: "/publicMessage" }
	    ]
	}
]

export default menus;
