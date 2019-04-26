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
					上移 ， 下移
				</td>
			  </template>
			</v-data-table>
		  </div>
		  <v-layout row justify-center>
		    <v-dialog v-model="dialog" persistent max-width="600px">
		      <template v-slot:activator="{ on }">
		        <v-btn color="primary" dark v-on="on">Open Dialog</v-btn>
		      </template>
		      <v-card>
		        <v-card-title>
		          <span class="headline">User Profile</span>
		        </v-card-title>
		        <v-card-text>
		          <v-container grid-list-md>
		            <v-layout wrap>
		              <v-flex>
		                <v-text-field label="广告名称" hint="该广告名称将展示在广告图片下" required></v-text-field>
		              </v-flex>
		              <v-flex xs12>
		                <v-text-field label="Email*" required></v-text-field>
		              </v-flex>
		              <v-flex xs12>
		                <v-text-field label="Password*" type="password" required></v-text-field>
		              </v-flex>
		              <v-flex xs12 sm6>
		                <v-select
		                  :items="['0-17', '18-29', '30-54', '54+']"
		                  label="Age*"
		                  required
		                ></v-select>
		              </v-flex>
		              <v-flex xs12 sm6>
		                <v-autocomplete
		                  :items="['Skiing', 'Ice hockey', 'Soccer', 'Basketball', 'Hockey', 'Reading', 'Writing', 'Coding', 'Basejump']"
		                  label="Interests"
		                  multiple
		                ></v-autocomplete>
		              </v-flex>
		            </v-layout>
		          </v-container>
		          <small>*indicates required field</small>
		        </v-card-text>
		        <v-card-actions>
		          <v-spacer></v-spacer>
		          <v-btn color="blue darken-1" flat @click="dialog = false">Close</v-btn>
		          <v-btn color="blue darken-1" flat @click="dialog = false">Save</v-btn>
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