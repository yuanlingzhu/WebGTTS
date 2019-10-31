var roadInfEditVm = new Vue({
    el: '#app',
    //数据
    data: {
        large:'large',
        delmodal:false,
        modal_loading:false,
        ledNumber:'',
        roadId:'',
        index:0,
        columns: [
            {
                title: '线号卡编号',
                key: 'ledno',
                resizable: true,
                align: 'center',
            },
            {
                title: '路段编号',
                key: 'roadId',
                resizable: true,
                align: 'center',
            },
            {
                title: '开始经度坐标(lon)',
                key: 'startLonGPS',
                resizable: true,
                align: 'left',
            },
            {
                title: '开始纬度坐标(lat)',
                key: 'startLatGPS',
                resizable: true,
                align: 'left',
            },
            {
                title: '结束经度坐标(lon)',
                key: 'endLonGPS',
                resizable: true,
                align: 'left',
            },
            {
                title: '结束纬度坐标(lat)',
                key: 'endLatGPS',
                resizable: true,
                align: 'left',
            },
            {
                title: '行驶时间',
                key: 'twoPointTime',
                resizable: true,
                align: 'center',
            },
            {
                title: '时间显示坐标X',
                key: 'timeShowX',
                resizable: true,
                align: 'center',
            },
            {
                title: '时间显示坐标Y',
                key: 'timeShowY',
                resizable: true,
                align: 'center',
            },
            {
                title: '操作',
                slot: 'action',
                resizable: true,
                align: 'center',
                fixed: 'right',
                width: 300,
            }
        ],
        roadInf:[],
      
    },
   
      /*html加载之前执行*/
    created(){
        var urlparse = this.parseUrl();//解析所有参数
        this.ledNumber=urlparse['ledNo'];
        this.findRoadInf(this.ledNumber);

    },
    //方法
    methods: {

        /* 根据LEDNo 查询信息*/
        findRoadInf(value){
            that = this;
            $.ajax({
                url : "/findRoadInf",
                type : "post",
                dataType : "json",
                data : {
                    LEDNo:value,
                },
                success : function(data) {
                    // console.log("success",data);
                    that.roadInf=data
                },
                error:function(result){
                    // console.log("error",result);
                }

            })

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
    
        /*跳转到编辑路段坐标界面 */
        goToRoadCoordinate(){
            window.location.href="roadCoordinate.html?ledNo="+this.ledNumber;
        },

        /** 跳转修改路段坐标界面 */
        updataRoadCoordinate(index){
            window.location.href="roadCoordinate.html?ledNo="+this.roadInf[index].ledno+"&roadId="+this.roadInf[index].roadId+"&startLon="+this.roadInf[index].startLonGPS+"&startLat="+this.roadInf[index].startLatGPS+"&endLon="+this.roadInf[index].endLonGPS+"&endLat="+this.roadInf[index].endLatGPS;
        },

        /** 跳转设置时间显示坐标界面 */
        setTimeCoordinate(index){
            window.location.href="setShowTimeCoordinate.html?ledNo="+this.roadInf[index].ledno+"&roadId="+this.roadInf[index].roadId+"&timeShowX="+this.roadInf[index].timeShowX+"&timeShowY="+this.roadInf[index].timeShowY;
        },

        /** 弹出确删除提示框 */
        showModel(index){
            this.index = index;
            this.delmodal=true;
            this.roadId= this.roadInf[index].roadId;
        },

         /** 删除方法 */
        del(){
            that = this;
            that.modal_loading = true;
            $.ajax({
                url : "/deleteRoadInf",
                type : "post",
                dataType : "json",
                data : {
                    LEDNo: that.ledNumber,
                    roadId:that.roadId
                },
                success : function(data) {
                    if(data.result==='succes'){
                        that.roadInf.splice(that.index, 1);
                        that.modal_loading = false;
                        that.delmodal=false;
                        that.$Message.success('操作成功');
                     
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

    },

});


