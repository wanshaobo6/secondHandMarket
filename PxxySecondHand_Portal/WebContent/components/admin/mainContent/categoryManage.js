import Tree from "../js/Tree/Tree.js"; 
const categoryManage = {
	name:"categoryPanel",
    template:`
    	<v-tree :isEdit="isEditor"
    			@handleAdd="handleAdd"
                @handleEdit="handleEdit"
                @handleDelete="handleDelete"
                @handleClick="handleClick"
    	></v-tree>
   `,
   data(){
    	return{
    		isEditor:true,
    	}
    },
   components:{
	   vTree : Tree
   },
   methods: {
	      handleAdd(node) {
	        console.log("add .... ");
	        console.log(node);
	      },
	      handleEdit(id, name) {
	        console.log("edit... id: " + id + ", name: " + name)
	      },
	      handleDelete(id) {
	        console.log("delete ... " + id)
	      },
	      handleClick(node) {
	        console.log("click ... "+node)
	      }
	    }
}
export default categoryManage;