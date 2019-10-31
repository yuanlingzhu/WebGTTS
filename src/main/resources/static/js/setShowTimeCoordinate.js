var setShowTimeCoordinateVm = new Vue({
    el: '#app',
    
    //数据
    data: {

        large:'large',
        ledNumber:'',
        roadID:'',
        pwidth:'300',
        pheight:'150',
        laberx:0,
        labery:0,
    },
   
    created(){

        var urlparse = this.parseUrl();//解析所有参数
        if(urlparse!=undefined){
            this.ledNumber=urlparse['ledNo'];
            this.roadID=urlparse['roadId'];
            this.laberx=urlparse['timeShowX'];
            this.labery=urlparse['timeShowY'];
        }
    },

    //html加载完成后执行
    mounted(){
        this.addLayerLabel();
    },

    //方法
    methods: {

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

        //添加标签
        addLayerLabel(){
            var  that = this;
            let labelX =(this.pwidth)/2-18;
            let labelY =(this.pheight)/2-18;
            //在画板上创建 标签
            $('canvas')
                .addLayer({
                    type: 'text', 
                    name:  "label",
                    fillStyle: '#6c0',
                    strokeStyle: '#6c0',
                    strokeWidth: 1,
                    fontSize: 18,
                    fontFamily: '微软雅黑',
                    text: "0000",
                    x: labelX, y: labelY,
                    fromCenter: false,
                    draggable: true,
                    dragging:true,
                   
                    /*改变鼠标样式 */
                    cursors: {
                        mouseover: 'pointer',
                        mousedown: 'move',
                        mouseup: 'pointer'
                    },
                   
                    /*拖动时获取属性 */
                    drag:function(layer) {
                        that.laberx = layer.x;
                        that.labery = layer.y; 
                    },
                    /*拖动停止时获取属性 */
                    dragstop:function(layer) {
                        // console.log(layer);
                        // console.log('I got the data:', JSON.stringify(that.blocklist))
                    }

                })
                .drawLayers();
  
        },

        submitParameter(){
            that = this;

           $.ajax({
                url : "/addRoadTimeShowCoordinate",
                type : "post",
                dataType : "json",
                data : {
                    LEDNo:that.ledNumber,
                    roadId:that.roadID,
                    x: that.laberx,
                    y: that.labery,
                },
                success : function(data) {
                    // console.log("success",data);
                    if(data.result==='succes'){
                        that.$Message.success('操作成功,返回即将返回路段操作界面');
                        window.location.href="roadInfEdit.html?ledNo="+that.ledNumber;
                    }else{
                        that.$Message.error('操作失败，请联系管理员');
                    }
                },
                error:function(result){
                    // console.log("error",result);
                    that.$Message.error('出现未知异常，请联系管理员');
                }

            })

        },

         /**返回路段信息操作界面 */
         returnRoadInfEdit(){
            window.location.href="roadInfEdit.html?ledNo="+this.ledNumber;
        },

    },

    watch:{

    },

});


