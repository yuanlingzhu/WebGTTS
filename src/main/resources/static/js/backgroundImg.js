var backgroundImgVm = new Vue({
    
    el: '#app',
    
    /*数据*/
    data() {
        const validateNumber = (rule, value, callback) => {
            // console.log(value);
            that = this;
            if (that.ledNo==='') {
                callback(new Error('The number cannot be empty'));
                that.successIcon = false;
            } else{
                $.ajax({
                    url : "/isLEDNoExist",
                    type : "post",
                    dataType : "json",
                    data : {
                        LEDNo:that.ledNo,
                    },
                    success : function(data) {
                        // console.log("success",data.result);
                        if(data.result=="true"){
                            callback(new Error('This number already exists'));
                            that.successIcon = false;
                        }else{
                            callback();
                            that.successIcon = true;
                        }
                    },
                    error:function(result){
                        // console.log("error",result);
                    }

                })
            }
        };

        return{
            large:'large',
            img: new Image(), // 背景图片缓存
            paraDrawer: true,
            styles: {
                height: 'calc(100% - 55px)',
                overflow: 'auto',
                paddingBottom: '53px',
                position: 'static'
            },
            PixelWidth:48,//背景图片的宽
            PixelHeight:32,//背景图片的高
            bmpImgBase64:'',//bmp格式背景图片编码
            ledNo:'',
            insertLEDNo:false,
            ledNo2:'',
            updataLEDNo:false,
            successIcon:false,
            ruleValidate: {
                ledNo: [  
                    { required: true, validator: validateNumber, trigger: 'blur' }, 
                ],
            },

            postURL:'',
        }
        

    },

    /*html加载完成后执行*/
    mounted(){
         
    },
    /*html加载之前执行*/
    created(){

        var urlparse = this.parseUrl();//解析所有参数

        if(urlparse==undefined){
            this.insertLEDNo=true;
            this.postURL="/addLEDBackgroundImg"
        }else{
            this.updataLEDNo=true;
            this.ledNo2=urlparse['ledNo'];
            this.bmpImgBase64=urlparse['backgroundImage'];
            this.pixelWidth=urlparse['pixelWidth'];
            this.pixelHeight=urlparse['pixelHeight'];
            this.updateInf();
            this.ledNo=this.ledNo2
            this.postURL="/updataLEDBackgroundImg"
        }
    },

    /*方法*/
    methods: {

        /*上传背景图片*/
        handleUpload (file) {
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload= () => {
                const imgBase64 = reader.result;
                this.img.src = imgBase64;
                this.img.height=this.PixelHeight*10;
                this.img.width=this.PixelWidth*10;
                this.img.onload = () => {
                    // const ctx= this.$refs.canvas
                    // ctx.toDataURL('image/bmp');
                    $('canvas').removeLayer("bg").drawLayers();
                    $('canvas').drawImage({
                        layer: true,
                        name: "bg",
                        source: this.img,
                        index: 0,
                        x: 0, y: 0,
                        width: this.PixelWidth*10, height:  this.PixelHeight*10,
                        fromCenter: false
                    }).drawLayers();

                    const base641 = $('canvas').getCanvasImage('image/bmp');
                    const arr=base641.split(",");
                    const base642 = "data:image/bmp;base64,"+arr[1];
                    this.bmpImgBase64=base642;
    
                    //赋值给img标签查看测试
                    /* const ctx= this.$refs.canvas2
                    ctx.src=base642; */
                }
    
            }
            return false;
        },

        /* 提交数据*/
        submitParameter(){
            that = this;
            if(that.bmpImgBase64!=='' && that.ledNo!==''){

                $.ajax({
                    url : that.postURL,
                    type : "post",
                    dataType : "json",
                    data : {
                        LEDNo:that.ledNo,
                        backgroundImage:that.bmpImgBase64,
                        pixelWidth:that.PixelWidth,
                        pixelHeight:that.PixelHeight,
                    },
                    success : function(data) {
                        // console.log("success",data);
                        if(data.result==='succes'){
                            that.$Message.success('操作成功,返回主页可以根据编号查询信息');
                            that.paraDrawer=false;
                        }else{
                            that.$Message.error('操作失败，请联系管理员');
                        }
                    },
                    error:function(result){
                        // console.log("error",result);
                        that.$Message.error('出现未知异常，请联系管理员');
                    
                    }
                })
            }else if(that.bmpImgBase64===''){
                that.$Message.error('请上传图片');
            }else if(that.ledNo===''){
                that.$Message.error('请填写编号');
            }
        },

        /*修改信息 */
        updateInf(){
            this.img.src = this.bmpImgBase64;
            this.img.height=this.PixelHeight*10;
            this.img.width=this.PixelWidth*10;
            this.img.onload = () => {
                // const ctx= this.$refs.canvas
                // ctx.toDataURL('image/bmp');
                $('canvas').removeLayer("bg").drawLayers();
                $('canvas').drawImage({
                    layer: true,
                    name: "bg",
                    source: this.img,
                    index: 0,
                    x: 0, y: 0,
                    width: this.PixelWidth*10, height:  this.PixelHeight*10,
                    fromCenter: false
                }).drawLayers();

                const base641 = $('canvas').getCanvasImage('image/bmp');
                const arr=base641.split(",");
                const base642 = "data:image/bmp;base64,"+arr[1];
                this.bmpImgBase64=base642;
            }
        },

        /*获取传递过来url参数信息 */
        parseUrl(){
            var url=location.href;
            var i=url.indexOf('?');
            if(i==-1)return;
            var querystr=url.substr(i+1);
            var arr1=querystr.split('&');
            var arr2=new Object();
            for  (i in arr1){
                var ta=arr1[i].split('=');
                arr2[ta[0]]=ta[1];
            }
            return arr2;
        },
    

    },

    /*监听 */
    watch:{

        PixelWidth: {
            handler (newV, oldV) {
                $('canvas').removeLayer("bg").drawLayers();
                $('canvas').drawImage({
                    layer: true,
                    name: "bg",
                    source: this.img,
                    index: 0,
                    x: 0, y: 0,
                    width: newV*10, height: this.PixelHeight*10,
                    fromCenter: false
                }).drawLayers();
                const base641 = $('canvas').getCanvasImage('image/bmp');
                const arr=base641.split(",");
                const base642 = "data:image/bmp;base64,"+arr[1];
                this.bmpImgBase64=base642;
            },
            deep: true,
        },

        PixelHeight: {
            handler (newV, oldV) {
                $('canvas').removeLayer("bg").drawLayers();
                $('canvas').drawImage({
                    layer: true,
                    name: "bg",
                    source: this.img,
                    index: 0,
                    x: 0, y: 0,
                    width: this.PixelWidth*10, height:  newV*10,
                    fromCenter: false
                }).drawLayers();
                const base641 = $('canvas').getCanvasImage('image/bmp');
                const arr=base641.split(",");
                const base642 = "data:image/bmp;base64,"+arr[1];
                this.bmpImgBase64=base642;
            },
            deep: true,
        },
    },

});