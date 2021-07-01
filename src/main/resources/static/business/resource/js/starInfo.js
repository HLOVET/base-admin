/**
 * 表格展示控制
 */
layui.use('table', function(){
    var table = layui.table;

    //所获得的 tableIns 即为当前容器的实例
    var tableIns = table.render({
        elem: '#starInfoTable'
        ,url: ctx + '/starInfo/pageList'
        ,method:'post'
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
        ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
        ,defaultToolbar: ['filter', 'exports', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
            title: '提示'
            ,layEvent: 'LAYTABLE_TIPS'
            ,icon: 'layui-icon-tips'
        }]
        ,title: '红人数据表'
        ,cols: [[
            {field:'nickName', title: '昵称',fixed: 'left', minWidth: 165} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
        ,{field:'accountLabel',  title: '标签'}
        ,{field:'fansCount',  title: '粉丝数量(W)', sort: true}
        ,{field:'noteCount', title: '笔记数量'}
        ,{field:'likeCount', title: '点赞收藏总量(W)', sort: true, minWidth: 170}
        ,{field:'avgLike', title: '点赞平均数', sort: true}
        ,{field:'avgCollection', title: '收藏平均数',sort: true}
        ,{field:'avgComment', title: '评论平均数', sort: true}
        ,{field:'contentSharp', title: '内容形式'}
        ,{field:'price', title: '报价(W)', sort: true}
        ,{field:'accountLevel', title: '账号等级'}
        ,{field:'personUrl', title: '个人主页'}
        ,{fixed: 'right', title:'操作', toolbar: '#barDemo'},
            {field:'id', title: 'id',hide:true} //id列隐藏
        ]]
        ,page:true
        ,even:true
        ,id: 'starInfoTable'
    });

    //头工具栏事件
    table.on('toolbar(starInfoTable)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            //自定义头工具栏右侧图标 - 提示
            case 'LAYTABLE_TIPS':
                layer.alert('这是工具栏右侧自定义的一个图标按钮');
                break;
        };
    });

    //监听操作栏事件
    table.on('tool(starInfoTable)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            layer.confirm('确认删除吗？注意：数据无法恢复', function (index) {
                $.delete(ctx + "/starInfo/delete/" + data.id, {}, function (data) {
                    if (data.success) {
                        layer.msg("删除成功");
                        obj.del();
                    } else {
                        layer.msg(data.msg, {icon: 2, time: 2000}, function () {
                        });
                    }
                });
                layer.close(index);
            });
        } else if(obj.event === 'edit'){
            layer.open({
                type: 2,
                title: "编辑资源信息",
                shadeClose: false,
                share: 0.01,
                area: ['1100px', '600px'],
                content: "/starInfo/getEditPage?dataId=" + data.id,
            });
        }
    });

    //这里以搜索为例 表格数据重载
    var $ = layui.$, active = {
        reload: function(){
            var accountType = $('#accountType');
            var nickName = $('#nickName');
            var fansCount = $('#fansCount');
            //执行重载
            table.reload('starInfoTable', {
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

