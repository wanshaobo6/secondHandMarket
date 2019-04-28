import quilleditor from "../js/quilleditor.js"
const loginForm = {
    template:`
    <v-tabs
		    centered
		    color="cyan"
		    dark
		    icons-and-text
		  >
		    <v-tabs-slider color="yellow"></v-tabs-slider>
		
		    <v-tab href="#tab-1">
		           所有通知
		      <v-icon>notes</v-icon>
		    </v-tab>
    	
    		<v-tab href="#tab-2">
		           通知详情
		      <v-icon>import_contacts</v-icon>
		    </v-tab>
		
		     <v-tab-item
		      :key="1"
		      :value="'tab-1'"
		    >
		      <v-card flat>
			        <v-card-text>
				   <v-layout row wrap  fill-height>
    				<!--左边部分-->
			            <v-flex
			              xs12 sm12
			            >
			               <div>
						    <v-data-table
						      :headers="headers"
						      :items="notices"
						      :pagination.sync="pagination"
						      :total-items="totalNotice"
						      :loading="loading"
						      class="elevation-1"
						    >
						      <template v-slot:items="props">
						        <td class="text-xs-center">{{ props.item.title }}</td>
						        <td class="text-xs-center">{{ props.item.adminName }}</td>
						        <td class="text-xs-center">{{ props.item.updatedTime }}</td>
						        <td class="text-xs-center">{{ props.item.isPrivate }}</td>
						        <td class="text-xs-center">{{ props.item.receiver }}</td>
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
			            </v-flex>
			          </v-layout>
				</v-card-text>
		      </v-card>
		    </v-tab-item>
		    
		    <v-tab-item
		      :key="2"
		      :value="'tab-2'"
		    >
		      <v-card flat>
			        <v-card-text>
			        	  <v-layout>
			        	  	<v-spacer></v-spacer>
			        	  	<v-flex xs6 sm6>
					          <v-text-field
					            :label="noticeTitle"
					            single-line
					            solo
					            :readonly="!isEditor"
					          ></v-text-field>
					        </v-flex>
					        <v-spacer></v-spacer>
			        	  </v-layout>
    					<quilleditor xs12 sm12></quilleditor>
    				</v-card-text>
		      </v-card>
		    </v-tab-item>
		  </v-tabs>
    `,
    data(){
    	return{
        		noticeTitle:"标题",
        		isEditor:true,    //当前是否是编辑模式
    			text: `Lorem ipsum dolor sit amet, consectetur adipis
    				cing elit, sed do eiusmod tempor incididunt ut labore et dolore magna 
    				aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco lab
    				oris nisi ut aliquip ex ea commodo consequat.`,
    		    totalNotice: 0,
    		 	notices: [],
    	        loading: true,
    	        pagination: {},
    	        headers: [
    	          {
    	            text: '标题',
    	            align: 'center',
    	            sortable: false,
    	            value: 'title'
    	          },
    	          { text: '最后操作管理员', value: 'adminName', align: 'center', },
    	          { text: '最后更新时间', value: 'updatedTime' , align: 'center',},
    	          { text: '是否私发', value: 'isPrivate' , align: 'center',},
    	          { text: '接收者名', value: 'receiver' , align: 'center',},
    	          { text: '操作', value: 'receiver' , align: 'center',}
    	        ]
    	}
    },
    watch: {
        pagination: {
          handler () {
            this.getDataFromApi()
              .then(data => {
                this.notices = data.items
                this.totalNotice = data.total
              })
          },
          deep: true
        }
      },
      mounted () {
        this.getDataFromApi()
          .then(data => {
            this.desserts = data.items
            this.totalDesserts = data.total
          })
      },
      methods: {
        getDataFromApi () {
          this.loading = true
          return new Promise((resolve, reject) => {
            const { sortBy, descending, page, rowsPerPage } = this.pagination

            let items = this.getDesserts()
            const total = items.length

            if (this.pagination.sortBy) {
              items = items.sort((a, b) => {
                const sortA = a[sortBy]
                const sortB = b[sortBy]

                if (descending) {
                  if (sortA < sortB) return 1
                  if (sortA > sortB) return -1
                  return 0
                } else {
                  if (sortA < sortB) return -1
                  if (sortA > sortB) return 1
                  return 0
                }
              })
            }

            if (rowsPerPage > 0) {
              items = items.slice((page - 1) * rowsPerPage, page * rowsPerPage)
            }

            setTimeout(() => {
              this.loading = false
              resolve({
                items,
                total
              })
            }, 1000)
          })
        },
        getDesserts () {
          return [
            {
              id : '1',
              title: 'Frozen Yogurt',
              adminName: 159,
              updatedTime: 6.0,
              isPrivate: 4.0,
              receiver: '1%'
            },
          {
              id : '2',
              title: 'Frozen Yogurt',
              adminName: 159,
              updatedTime: 6.0,
              isPrivate: 4.0,
              receiver: '1%'
            },
            {
                id : '4',
                title: 'Frozen Yogurt',
                adminName: 159,
                updatedTime: 6.0,
                isPrivate: 4.0,
                receiver: '1%'
              },
              {
                  id : '5',
                  title: 'Frozen Yogurt',
                  adminName: 159,
                  updatedTime: 6.0,
                  isPrivate: 4.0,
                  receiver: '1%'
                },
                {
                    id : '6',
                    title: 'Frozen Yogurt',
                    adminName: 159,
                    updatedTime: 6.0,
                    isPrivate: 4.0,
                    receiver: '1%'
                  },
                  {
                      id : '7',
                      title: 'Frozen Yogurt',
                      adminName: 159,
                      updatedTime: 6.0,
                      isPrivate: 4.0,
                      receiver: '1%'
                    },
                    {
                        id : '8',
                        title: 'Frozen Yogurt',
                        adminName: 159,
                        updatedTime: 6.0,
                        isPrivate: 4.0,
                        receiver: '1%'
                      },
                      {
                          id : '9',
                          title: 'Frozen Yogurt',
                          adminName: 159,
                          updatedTime: 6.0,
                          isPrivate: 4.0,
                          receiver: '1%'
                        },
                        {
                            id : '10',
                            title: 'Frozen Yogurt',
                            adminName: 159,
                            updatedTime: 6.0,
                            isPrivate: 4.0,
                            receiver: '1%'
                          },
                          {
                              id : '11',
                              title: 'Frozen Yogurt',
                              adminName: 159,
                              updatedTime: 6.0,
                              isPrivate: 4.0,
                              receiver: '1%'
                            },
                            {
                                id : '12',
                                title: 'Frozen Yogurt',
                                adminName: 159,
                                updatedTime: 6.0,
                                isPrivate: 4.0,
                                receiver: '1%'
                              },
                          {
                              id : '13',
                              title: 'Frozen Yogurt',
                              adminName: 159,
                              updatedTime: 6.0,
                              isPrivate: 4.0,
                              receiver: '1%'
                            },
                            {
                                id : '14',
                                title: 'Frozen Yogurt',
                                adminName: 159,
                                updatedTime: 6.0,
                                isPrivate: 4.0,
                                receiver: '1%'
                              },
                              {
                                  id : '15',
                                  title: 'Frozen Yogurt',
                                  adminName: 159,
                                  updatedTime: 6.0,
                                  isPrivate: 4.0,
                                  receiver: '1%'
                                },
                                {
                                    id : '16',
                                    title: 'Frozen Yogurt',
                                    adminName: 159,
                                    updatedTime: 6.0,
                                    isPrivate: 4.0,
                                    receiver: '1%'
                                  }
          ]
        }
      },
      components:{
    	  quilleditor,
      }
}
export default loginForm ;