const myEditor = {
    template:`
    	<div>
    		<!-- 创建工具栏组件 -->
	        <div id="toolbar">
	            <span class="ql-formats">
	              <button class="ql-bold">Bold</button><!--控制粗细-->
	              <button class="ql-italic">Italic</button>    <!--控制切斜-->
	              <button class="ql-underline">下划线</button>    <!--下划线-->
	              <button class="ql-link">link</button>    <!--链接-->
	          </span>
	          <span class="ql-formats">
	              <button class="ql-list" value="ordered"></button><!--序号-->
	              <button class="ql-list" value="bullet"></button>    <!--点-->
	              <button class="ql-list" value="ql-blockquote"></button>    <!--引用-->
	              <button class="ql-code-block"></button>    <!--代码-->
	              <button class="ql-image" value="bullet"></button>    <!--图片-->
	          </span>
	          <span class="ql-formats">
	            <select class="ql-color">
	              <option selected></option>
	              <option value="red"></option>
	              <option value="orange"></option>
	              <option value="yellow"></option>
	              <option value="green"></option>
	              <option value="blue"></option>
	              <option value="purple"></option>
	            </select>
	            <select class="ql-background">
	              <option selected></option>
	              <option value="red"></option>
	              <option value="orange"></option>
	              <option value="yellow"></option>
	              <option value="green"></option>
	              <option value="blue"></option>
	              <option value="purple"></option>
	            </select>
	          </span>  
	          <span class="ql-formats"><!--控制大小-->
	            <select class="ql-size">
	              <option value="10px">小字体</option>
	              <option selected>中字体</option>
	              <option value="18px">大字体</option>
	              <option value="32px">超大字</option>
	            </select>
	          </span>
	        </div>
	         
	        <!-- 创建文本编辑器 -->
	        <div id="editor" style="min-height:300px">
	          <p>{{text}}</p>
	        </div>    
    	</div>
    `,
    data(){
    	return {
    		text:"",
    	}
    },
    created(){
    	 window.onload=function(){
             var BackgroundClass = Quill.import('attributors/class/background');
             var ColorClass = Quill.import('attributors/class/color');
             var SizeStyle = Quill.import('attributors/style/size');
             Quill.register(BackgroundClass, true);
             Quill.register(ColorClass, true);
             Quill.register(SizeStyle, true);
             
             
             var editor = new Quill('#editor', {
               modules: { toolbar: '#toolbar' },
               placeholder: 'Compose an epic...',
               theme: 'snow'
             });
         }
    },
    components:{
    }
}

export default myEditor;