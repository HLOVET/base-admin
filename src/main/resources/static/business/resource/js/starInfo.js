/**
 * 表格展示控制
 */
layui.use('table', function(){
    var table = layui.table;
    var upload = layui.upload;

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
        ,defaultToolbar: ['filter', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
            title: '提示'
            ,layEvent: 'LAYTABLE_TIPS'
            ,icon: 'layui-icon-tips'
        }]
        ,title: '红人数据表'
        ,cols: [[
            {field:'nickName', title: '达人昵称',fixed: 'left', minWidth: 165} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
        ,{field:'personUrl', title: '个人主页', templet: '<div><a href="{{d.personUrl}}" target="view_window" class="layui-table-link">{{d.personUrl}}</a></div>' }
        ,{field:'accountLabel',  title: '标签'}
        ,{field:'fansCount',  title: '粉丝数量', sort: true}
        // ,{field:'noteCount', title: '笔记数量'}
        ,{field:'likeCount', title: '点赞收藏总量', sort: true}
        ,{field:'avgLike', title: '平均点赞', sort: true}
        // ,{field:'avgCollection', title: '平均收藏',sort: true}
        // ,{field:'avgComment', title: '平均评论', sort: true}
        ,{field:'contentSharp', title: '内容形式'}
        ,{field:'price', title: '报价', sort: true}
        ,{field:'priupdateTime', title: '报价更新时间', minWidth: 170}
        ,{field:'accountLevel', title: '账号等级'}
        ,{field:'ownerName', title: '所属人'}
        ,{field:'contact', title: '联系方式'}
        ,{field:'updateTime', title: '更新时间', minWidth: 170}
        ,{fixed: 'right', title:'操作', toolbar: '#barDemo', minWidth: 40},
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
        if(obj.event === 'edit'){
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
            var ownerName = $('#ownerName');
            var accountLevel = $('#accountLevel');
            //执行重载
            table.reload('starInfoTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    accountLabel: accountType.val(),
                    nickName: nickName.val(),
                    accountLevel: accountLevel.val(),
                    ownerName: ownerName.val()
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

    //批量上传执行实例
    var uploadInst = upload.render({
        elem: '#batchUpload' //绑定元素
        ,url: '/starInfo/importFile' //上传接口
        ,done: function(res){
            console.log('import suss='+JSON.stringify(res));
            if (res.success){
                layer.msg("导入成功！");
            }else {
                layer.msg("导入失败~~，"+res.msg);
            }
            //上传完毕回调
        }
        ,error: function(){
            layer.msg("导入失败了.. 0.0");
            console.log('import error='+JSON.stringify(res));
            //请求异常回调
        }
    });

});

