import TreeItem from "./TreeItem.js"; 
export default {
	 template:`
	    	 <v-list class="pt-0 pb-0" dense>
			    <TreeItem
			      class="item" :model="model" v-for="(model, index) in db" :key="index"
			      :url="url"
			      :isEdit="isEdit"
			      :nodes="nodes"
			      @handleAdd="handleAdd"  
			      @handleEdit="handleEdit"
			      @handleDelete="handleDelete"
			      @handleClick="handleClick"
			    ></TreeItem>
	     </v-list>
	   `,
    name: "vTree",
    props: {
      url: String,
      isEdit: {
        type: Boolean,
        default: false
      },
      treeData:{
        type:Array
      }
    },
    data() {
      return {
        db: [],    //这里显示的是一级菜单分类内容
        nodes:{     //存储着该级别分类中打开的元素 , 该元素是一个vue实例 ， 是不是被选中
          opened:null,
          selected:{isSelected:false}
        }
      }
    },
    components: {
      TreeItem
    },
    created() {
      if(this.treeData && this.treeData.length > 0){    //一级分类的内容可以传过来  如果没有的话网络获取
        this.db = this.treeData;
        return;
      }
      this.getData();
    },
    methods: {
      getData() {
          this.db =[{"id":1,"name":"图书、音像、电子书刊","parentId":0,"isParent":true},{"id":74,"name":"手机","parentId":0,"isParent":true},{"id":103,"name":"家用电器","parentId":0,"isParent":true},{"id":202,"name":"数码","parentId":0,"isParent":true},{"id":264,"name":"家居家装","parentId":0,"isParent":true},{"id":322,"name":"电脑办公","parentId":0,"isParent":true},{"id":417,"name":"厨具","parentId":0,"isParent":true},{"id":471,"name":"个护化妆","parentId":0,"isParent":true},{"id":548,"name":"服饰内衣","parentId":0,"isParent":true},{"id":666,"name":"钟表","parentId":0,"isParent":true},{"id":680,"name":"鞋靴","parentId":0,"isParent":true},{"id":718,"name":"母婴","parentId":0,"isParent":true},{"id":808,"name":"礼品箱包","parentId":0,"isParent":true},{"id":871,"name":"食品饮料、保健食品","parentId":0,"isParent":true},{"id":931,"name":"珠宝","parentId":0,"isParent":true},{"id":1020,"name":"汽车用品","parentId":0,"isParent":true},{"id":1117,"name":"运动健康","parentId":0,"isParent":true},{"id":1230,"name":"玩具乐器","parentId":0,"isParent":true},{"id":1293,"name":"彩票、旅行、充值、票务","parentId":0,"isParent":true},{"id":1329,"name":"生鲜","parentId":0,"isParent":true},{"id":1401,"name":"整车","parentId":0,"isParent":true}];
          this.db.forEach(n => n['path'] = [n.name])
      },
      handleAdd(node) {
        this.$emit("handleAdd", this.copyNodeInfo(node));
      },
      handleEdit(id, name) {
        this.$emit("handleEdit", id, name)
      },
      handleDelete(id) {
        this.deleteById(id, this.db);
        this.$emit("handleDelete", id);
      },
      handleClick(node){
        this.$emit("handleClick", this.copyNodeInfo(node))
      },
      // 根据id删除
      deleteById(id, arr) {
        let src = arr || this.db;
        for (let i in src) {
          let d = src[i];
          if (d.id === id) {
            src.splice(i, 1)
            return;
          }
          if (d.children && d.children.length > 0) {
            this.deleteById(id, d.children)
          }
        }
      },
      copyNodeInfo(node){
        const o = {};
        for(let i in node){
          o[i] = node[i];
        }
        return o;
      }
    },
    watch: {}
  }