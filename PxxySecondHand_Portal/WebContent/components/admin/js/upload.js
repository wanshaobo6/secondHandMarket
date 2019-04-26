const upload = {
    template:`<div>
				 <el-upload
				  class="avatar-uploader"
				  action="https://jsonplaceholder.typicode.com/posts/"
				  :show-file-list="false"
				  :on-success="handleAvatarSuccess"
				  :before-upload="beforeAvatarUpload">
				  <img v-if="imageUrl" :src="imageUrl" class="avatar">
				  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
				</el-upload>
    		  </div>
    `,
    data(){
	  return {
	        imageUrl: ''
	      };
    },
    methods: {
        handleAvatarSuccess(res, file) {
          this.imageUrl = URL.createObjectURL(file.raw);
        },
        beforeAvatarUpload(file) {
          const isJPG = file.type === 'image/jpeg';
          const isLt2M = file.size / 1024 / 1024 < 2;

          if (!isJPG) {
            this.$message.error('Avatar picture must be JPG format!');
          }
          if (!isLt2M) {
            this.$message.error('Avatar picture size can not exceed 2MB!');
          }
          return isJPG && isLt2M;
        }
      }
}

export default upload;