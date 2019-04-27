import upload from "../js/upload.js";
const loginForm = {
    template:`
    	 <div id="adPane">
    	 	<v-layout>
			 <v-flex xs12 sm12 text-xs-right>
				 <div class="pr-3">
					<v-btn color="primary blue " text-xs-right @click="dialog = !dialog">新增广告</v-btn>
				  </div>
		    </v-flex>
		  </v-layout>
		   <div>
			<v-data-table
			  :headers="headers"
			  :items="addresses"
			  :search="search"
			  :pagination.sync="pagination"
			  class="elevation-1"
			>
			  <template v-slot:items="props">
				<td class="text-xs-center">{{ props.item.adNum }}</td>
				<td class="text-xs-center">{{ props.item.adTitle }}</td>
				<td class="text-xs-center">{{ props.item.adDesc }}</td>
				<td class="text-xs-center">{{ props.item.adImage }}</td>
				<td class="text-xs-center">{{ props.item.adAddress }}</td>
				<td class="text-xs-center">
				 <v-layout>
				 	<v-flex xs3 sm3>
		            <v-btn flat icon small color="primary">
		              <v-icon>expand_more</v-icon>
		            </v-btn>
		          </v-flex>
		           <v-flex xs3 sm3>
		            <v-btn flat icon small color="primary">
		              <v-icon>expand_less</v-icon>
		            </v-btn>
		          </v-flex>
		           <v-flex xs3 sm3>
		            <v-btn flat icon small color="primary">
		              <v-icon>update</v-icon>
		            </v-btn>
		          </v-flex>
		           <v-flex xs3 sm3>
		            <v-btn flat icon small color="primary">
		              <v-icon>delete_forever</v-icon>
		            </v-btn>
		          </v-flex>
				 </v-layout>
				</td>
			  </template>
			</v-data-table>
		  </div>
		  <v-layout row justify-center>
		    <v-dialog v-model="dialog" persistent max-width="600px">
		      <v-card>
		        <v-card-title>
		          <span class="headline">新增广告</span>
		        </v-card-title>
		        <v-card-text>
		          <v-container grid-list-md>
		            <v-layout wrap>
		              <v-flex xs12>
		                <v-text-field label="*广告名称" hint="该广告名称将展示在广告图片下" required></v-text-field>
		              </v-flex>
		                 <v-flex xs12>
					        <v-textarea
					          solo
					          name="input-7-4"
					          label="广告描述"
					          value=""
					        ></v-textarea>
					      </v-flex>
	             		 <v-flex xs12>
			                <v-text-field label="*广告的url" hint="点击图片跳转到该url上" required></v-text-field>
			              </v-flex>
				           <v-layout>
				             <v-flex xs3>
				             	<v-chip label> *图片上传:</v-chip>
				             </v-flex> 
				             <v-flex xs9>
				             	<el-upload></el-upload>
				             </v-flex> 
		             	</v-layout>
		            </v-layout>
		          </v-container>
		          <small>*表示必填选项</small>
		        </v-card-text>
		        <v-card-actions>
		          <v-spacer></v-spacer>
		          <v-btn color="blue darken-1" flat @click="dialog = false">关闭窗口</v-btn>
		          <v-btn color="blue darken-1" flat @click="dialog = false">新增</v-btn>
		        </v-card-actions>
		      </v-card>
		    </v-dialog>
		  </v-layout>
    	</div>
    `,
    data(){
    	return {
    	search: '',
        pagination: {
        sortBy: 'adNum'
        },
        selected: [],
        headers: [
          {
            text: '广告号',
            align: 'center',
            sortable: true,
            value: 'adNum'
          },
          { text: '广告名', value: 'adTitle',align: 'center'},
		  { text: '广告描述' , value: 'adDesc',align: 'center'},
          { text: '广告图片', value: 'adImage' ,align: 'center'},
          { text: '广告地址', value: 'adAddress',align: 'center'},
		    { text: '操作', value: 'adoperation',align: 'center'}
        ],
        addresses: [
          {
            adNum: 1,
            adTitle: '广告标题',
			adDesc:'广告的描述',
            adImage: 6.0,
            adAddress: 24,
          },
          {
            adNum: 2,
            adTitle: '广告标题',
			adDesc:'广告的描述',
            adImage: 6.0,
            adAddress: 24,
          }, {
            adNum: 3,
            adTitle: '广告标题',
			adDesc:'广告的描述',
            adImage: 6.0,
            adAddress: 24,
          }, {
            adNum: 4,
            adTitle: '广告标题',
			adDesc:'广告的描述',
            adImage: 6.0,
            adAddress: 24,
          }, {
            adNum: 5,
            adTitle: '广告标题',
			adDesc:'广告的描述',
            adImage: 6.0,
            adAddress: 24,
          }, {
            adNum: 6,
            adTitle: '广告标题',
			adDesc:'广告的描述',
            adImage: 6.0,
            adAddress: 24,
          }, {
            adNum: 7,
            adTitle: '广告标题',
			adDesc:'广告的描述',
            adImage: 6.0,
            adAddress: 24,
          }, {
            adNum: 8,
            adTitle: '广告标题',
			adDesc:'广告的描述',
            adImage: 6.0,
            adAddress: 24,
          }
        ],
        dialog:false,  //显示对话框
    	}
    },
    methods: {
  
    },
    create:{
    },
    components:{
     elUpload:upload,
    }
}

export default loginForm ;