var indexVm = new Vue({
    el: '#app',
    //数据
    data: {

        delmodal:false,
        modal_loading:false,
        LEDNumber:'',
        index:0,
        columns: [
            {
                title: '线号卡编号',
                key: 'ledno',
                resizable: true,
                align: 'center',
           
            },
            {
                title: '图片',
                key: 'backgroundImage',
                resizable: true,
                align: 'center',
               
                render: (h, params) => {
                    let _img = params.row.backgroundImage; //因为现在后台返回来的只有一张图片，String类型。
                    let imgs = [_img];//组装成 数组
                    if(_img){
                        return h('viewer', {
                            props:{
                                images:imgs //数组
                            }
                        }, imgs.map(img=>{ //循环显示
                            return h('img',  {
                                    attrs: {
                                        src: img, 
                                        style: 'width: 40px;height: 40px;'
                                    },
                                },);
                        })
                        )
                    }else{
                        return h("span", '');
                    }
                   
                }

            },
            {
                title: '宽',
                key: 'pixelWidth',
                resizable: true,
                align: 'center',
           
            },
            {
                title: '高',
                key: 'pixelHeight',
                resizable: true,
                align: 'center',
           
            },
            {
                title: '操作',
                slot: 'action',
                resizable: true,
                align: 'center',
            }
        ],
        roadImgInf:[],
       
    },

    //方法
    methods: {

        /* 根据LEDNo 查询信息*/
        findRoadInf(value){
            that = this;
            $.ajax({
                url : "/findRoadBackgroundImg",
                type : "post",
                dataType : "json",
                data : {
                    LEDNo:value,
                },
                success : function(data) {
                    that.roadImgInf=data
                },
                error:function(result){
                    //  console.log("error",result);
                    that.$Message.error('出现未知异常，请联系管理员');
                }

            })

        },

        /**搜索方法 */
        searchRoadInf(value){
           this.findRoadInf(value);
        },

        /** 跳转路段信息界面 */
        roadInf(index){
            window.location.href="roadInfEdit.html?ledNo="+this.roadImgInf[index].ledno;
        },

        /** 跳转修改图片界面 */
        updataImg(index){
            window.location.href="backgroundImg.html?ledNo="+this.roadImgInf[index].ledno+"&backgroundImage="+this.roadImgInf[index].backgroundImage+"&pixelWidth="+this.roadImgInf[index].pixelWidth+"&pixelHeight="+this.roadImgInf[index].pixelHeight;
        },

        /** 弹出确删除提示框 */
        showModel(index){
            this.index = index;
            this.delmodal=true;
            this.LEDNumber= this.roadImgInf[index].ledno;
        },

        /** 删除方法 */
        del(){
            that = this;
            that.modal_loading = true;
            $.ajax({
                url : "/deleteLEDAllInf",
                type : "post",
                dataType : "json",
                data : {
                    LEDNo:that.LEDNumber,
                },
                success : function(data) {
                    if(data.result==='succes'){
                        that.roadImgInf.splice(that.index, 1);
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


