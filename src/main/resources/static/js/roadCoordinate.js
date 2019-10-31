var roadCoordinateVm = new Vue({
    el: '#app',
    //数据
    data: {
        large:'large',
        ledNumber:'',
        roadID:'',
        mapstart:'',
        mapend:'',
        lonAndLat: {
            startLon: '',
            startLat: '',
            endLon:'',
            endLat:'',
        },//起点/终点的经纬度
        chooseTravel:'drive',
        postURL:'',
    },
   
    /*html加载之前执行*/
    created(){
        this.postURL='/addRoadStartAndEndGPS';
        var urlparse = this.parseUrl();//解析所有参数
        if(urlparse!=undefined){
            this.ledNumber=urlparse['ledNo'];
            this.roadID=urlparse['roadId'];
            this.lonAndLat.startLon=urlparse['startLon'];
            this.lonAndLat.startLat=urlparse['startLat'];
            this.lonAndLat.endLon=urlparse['endLon'];
            this.lonAndLat.endLat=urlparse['endLat'];
            
        }
       
    },

    //html加载完成后执行
    mounted(){
         
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

        tabsclick(name){
            this.chooseTravel=name;
        },

        submitParameter(){
            that = this;
            $.ajax({
                url :that.postURL,
                type : "post",
                dataType : "json",
                data : {
                    LEDNo:that.ledNumber,
                    roadId:that.roadID,
                    startLon:that.lonAndLat.startLon, 
                    startLat:that.lonAndLat.startLat, 
                    endLon:that.lonAndLat.endLon,
                    endLat:that.lonAndLat.endLat,
                },
                success : function(data) {
                    //console.log("success",data);

                    if(data.result==='succes'){
                        that.$Message.success('操作成功,返回即将返回路段操作界面');
                        window.location.href="roadInfEdit.html?ledNo="+that.ledNumber;

                    }else{
                        that.$Message.error('操作失败，请联系管理员');
                    }
                },
                error:function(result){
                    //  console.log("error",result);
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


