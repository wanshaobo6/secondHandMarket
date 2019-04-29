 export default {
	template:`
   	 <div>
	    <v-list-tile
	      @click="toggle" class="level1 py-0 my-0" :class="{'selected':isSelected}">
	      <v-list-tile-avatar class="px-1">
	        <v-icon v-if="model.isParent">{{open ? 'folder_open' : 'folder'}}</v-icon>
	        <v-icon v-if="!model.isParent">insert_drive_file</v-icon>
	      </v-list-tile-avatar>
	      <v-list-tile-content>
	        <v-list-tile-title v-show="!beginEdit">
	          <span >{{model.name}}</span>
	        </v-list-tile-title>
		        <input v-show="beginEdit" @click.stop="" :ref="model.id" v-model="model.name"
		               @blur="afterEdit" @keydown.enter="afterEdit"/>
		      </v-list-tile-content>
		      <v-list-tile-action v-if="isEdit">
		        <v-btn flat icon color="indigo"  color="primary" @mouseover="c1='primary'" @mouseout="c1=''"  @click.stop="addChild">
			      <v-icon >add</v-icon>
			    </v-btn>
		      </v-list-tile-action>
		      <v-list-tile-action v-if="isEdit">
		        <v-btn flat icon color="indigo" color="primary"  @mouseover="c2='success'" @mouseout="c2=''"  @click.stop="editChild">
			      <v-icon >edit</v-icon>
			    </v-btn>
		      </v-list-tile-action>
		      <v-list-tile-action v-if="isEdit">
		        <v-btn flat icon color="indigo" @mouseover="c3='error'" @mouseout="c3=''"  @click.stop="deleteChild">
			      <v-icon >remove</v-icon>
			    </v-btn>
		      </v-list-tile-action>
		    </v-list-tile>
		
	    <v-list v-if="isFolder" v-show="open" class="ml-4 pt-0 pb-0" dense transition="scale-transition">
	      <tree-item
	        class="item"
	        v-for="(model, index) in model.children"
	        :key="index"
	        :model="model"
	        :url="url"
	        :isEdit="isEdit"
	        :nodes="nodes"
	        :parentState="open"
	        @handleAdd="handleAdd"
	        @handleEdit="handleEdit"
	        @handleDelete="handleDelete"
	        @handleClick="handleClick"
	      >
     </tree-item>
   </v-list>
 </div>
  `,
    name: "tree-item",
    props: {
      model: Object,
      url: String,
      isEdit: {
        type: Boolean,
        default: false
      },
      nodes: Object,
      parentState:Boolean   //父节点状态
    },
    created() {
    },
    data: function () {
      return {
        c1: '',
        c2: '',
        c3: '',
        isSelected: false,   //是否被选中
        open:false,    //是否打开
        beginEdit:false   //编辑模式
      }
    },
    watch:{
      parentState(val){  
        if(!val){   
          this.open = val;
        }
      }
    },
    computed: {
      isFolder(){  //在model中有无数据的依据判断该结点是不是父节点
       return this.model.children != null &&
          this.model.children.length > 0;
      }
    },
    methods: {
      toggle: function () {
        // 将其它被选中项取消选中
        this.nodes.selected.isSelected = false;
        // 当前项被选中
        this.isSelected = true;
        // 保存当前选中项
        this.nodes.selected = this

	        // 客户自己定义的点击事件回调
	        this.handleClick(this.model);

        // 判断是否为顶级节点，顶级节点需要记录和替换
        if(this.model.parentId == 0){
          // 判断打开节点是否是自己
          if(this.nodes.opened && this != this.nodes.opened){
            // 不是，则关闭原来的节点
            this.nodes.opened.open = false;
          }
          // 将自己记录为打开的节点
          this.nodes.opened = this;
        }
        // 切换开闭状态
        this.open = !this.open;
        // 如果已经是叶子节点,或者自己是关闭的，或者自己已经有儿子了，结束   -->  相当是判断有没有缓存
        if (!this.model.isParent || this.isFolder || !this.open) {
          return;
        }
        // 展开后查询子节点  ---->>这里发送一个Http请求根据父id查询所有子分类
        Vue.set(this.model, 'children',[{"id":1,"name":"图书、音像、电子书刊","parentId":9,"isParent":true},{"id":74,"name":"手机","parentId":6,"isParent":false},{"id":103,"name":"家用电器","parentId":0,"isParent":true},{"id":202,"name":"数码","parentId":0,"isParent":false},{"id":264,"name":"家居家装","parentId":0,"isParent":false},{"id":322,"name":"电脑办公","parentId":0,"isParent":true},{"id":417,"name":"厨具","parentId":0,"isParent":true},{"id":471,"name":"个护化妆","parentId":0,"isParent":false},{"id":548,"name":"服饰内衣","parentId":0,"isParent":false},{"id":666,"name":"钟表","parentId":0,"isParent":true},{"id":680,"name":"鞋靴","parentId":0,"isParent":false},{"id":718,"name":"母婴","parentId":0,"isParent":false},{"id":808,"name":"礼品箱包","parentId":0,"isParent":false},{"id":871,"name":"食品饮料、保健食品","parentId":0,"isParent":false},{"id":931,"name":"珠宝","parentId":0,"isParent":false},{"id":1020,"name":"汽车用品","parentId":0,"isParent":false},{"id":1117,"name":"运动健康","parentId":0,"isParent":true},{"id":1230,"name":"玩具乐器","parentId":0,"isParent":false},{"id":1293,"name":"彩票、旅行、充值、票务","parentId":0,"isParent":false},{"id":1329,"name":"生鲜","parentId":0,"isParent":false},{"id":1401,"name":"整车","parentId":0,"isParent":false}]);
        // 封装当前节点的路径
      this.model.children.forEach(n => {
        n['path'] = [];
        this.model.path.forEach(p => n['path'].push(p));
        n['path'].push(n.name);
    })
      },
      addChild: function () {
        let child = {
          id: 0,
          name: '新的节点',
          parentId: this.model.id,
          isParent: false,
          sort:this.model.children? this.model.children.length + 1:1
        }
        if (!this.model.isParent) {
          Vue.set(this.model, 'children', [child]);
          this.model.isParent = true;
          this.open = true;
          this.handleAdd(child);
        } else {
          if (!this.isFolder) {
              Vue.set(this.model, 'children', [{"id":1,"name":"图书、音像、电子书刊","parentId":0,"isParent":false},{"id":74,"name":"手机","parentId":0,"isParent":false},{"id":103,"name":"家用电器","parentId":0,"isParent":false},{"id":202,"name":"数码","parentId":0,"isParent":false},{"id":264,"name":"家居家装","parentId":0,"isParent":false},{"id":322,"name":"电脑办公","parentId":0,"isParent":true},{"id":417,"name":"厨具","parentId":0,"isParent":true},{"id":471,"name":"个护化妆","parentId":0,"isParent":false},{"id":548,"name":"服饰内衣","parentId":0,"isParent":false},{"id":666,"name":"钟表","parentId":0,"isParent":true},{"id":680,"name":"鞋靴","parentId":0,"isParent":false},{"id":718,"name":"母婴","parentId":0,"isParent":false},{"id":808,"name":"礼品箱包","parentId":0,"isParent":false},{"id":871,"name":"食品饮料、保健食品","parentId":0,"isParent":false},{"id":931,"name":"珠宝","parentId":0,"isParent":false},{"id":1020,"name":"汽车用品","parentId":0,"isParent":false},{"id":1117,"name":"运动健康","parentId":0,"isParent":true},{"id":1230,"name":"玩具乐器","parentId":0,"isParent":false},{"id":1293,"name":"彩票、旅行、充值、票务","parentId":0,"isParent":false},{"id":1329,"name":"生鲜","parentId":0,"isParent":false},{"id":1401,"name":"整车","parentId":0,"isParent":false}]);
              this.model.children.push(child);
              this.open = true;
              this.handleAdd(child);
          } else {
            this.model.children.push(child);
            this.open = true;
            this.handleAdd(child);
          }
        }
      },
      deleteChild: function () {
       /* this.$message.confirm('此操作将永久删除数据，是否继续?', '提示', {
          confirmButtonText: '确定删除',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.handleDelete(this.model.id);
        }).catch(()=>{
          this.$message.info('已取消删除');
        })*/

      },
      editChild() {
        this.beginEdit = true;
        this.$nextTick(() => this.$refs[this.model.id].focus());
      },
      afterEdit() {
        if (this.model.beginEdit) {
          this.beginEdit = false;
          this.handleEdit(this.model.id, this.model.name);
        }
      },
      handleAdd(node) {
        this.$emit("handleAdd", node);
      },
      handleDelete(id) {
        this.$emit("handleDelete", id);
      },
      handleEdit(id, name) {
        this.$emit("handleEdit", id, name)
      },
      handleClick(node) {
        this.$emit("handleClick", node);
      }
    }
  }