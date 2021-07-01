/**
 * 表格展示控制
 */
layui.use('table', function(){
    var table = layui.table;

    var tableIns = table.render({
        elem: '#starProjectInfoTable'
        ,url: ctx + '/starInfo/pageList'
        ,method:'post'
        ,toolbar: '#toolbarDemo'
        ,request: {
            pageName: 'pageNum', //页码的参数名称，默认：page
            limitName: 'PageSize' //每页数据量的参数名，默认：limit
        }
        ,response: {
            statusName: 'success', //数据状态的字段名称，默认：code
            statusCode: true,//成功的状态码，默认：0
            msgName: 'msg', //状态信息的字段名称，默认：msg
            countName: 'total', //数据总数的字段名称，默认：count
            dataName: 'data', //数据列表的字段名称，默认：data
        }
        ,title: '红人数据表'
        ,cols: [[
            {type: 'checkbox', fixed: 'left'}
            ,{field:'nickName', title: '昵称',fixed: 'left', minWidth: 165} //222
            ,{field:'fansCount',  title: '粉丝数量(W)', sort: true}
            ,{field:'noteCount', title: '笔记数量'}
            ,{field:'likeCount', title: '点赞收藏总量(W)', sort: true, minWidth: 170}
            ,{field:'price', title: '报价(W)', sort: true}
            ,{field:'personUrl', title: '个人主页'}
            ,{fixed: 'right', title:'操作', toolbar: '#barDemo'},
            {field:'id', title: 'id',hide:true} //id列隐藏
        ]]
        ,page:true
        ,id: 'starProjectInfoTable'
    });

    //工具栏事件
    table.on('toolbar(starProjectInfoTable)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'getCheckData':
                var data = checkStatus.data;
                layer.alert(JSON.stringify(data));
                break;
        };
    });

    //这里以搜索为例 表格数据重载
    var $ = layui.$, active = {
        reload: function(){
            var accountType = $('#accountType');
            var nickName = $('#nickName');
            var fansCount = $('#fansCount');
            console.log(accountType);
            //执行重载
            table.reload('starProjectInfoTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    accountLabel: accountType.val(),
                    nickName: nickName.val(),
                    fansCount: fansCount.val()
                }
            });
        }
    };
    //搜索按钮 重载表格
    $('#searchBtn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //重置搜索条件
    $('#resetBut').on('click', function(){
        $("#searchDiv :input").val("");
    });
});