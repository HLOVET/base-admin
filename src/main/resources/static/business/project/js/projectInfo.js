/**
 * 表格展示控制
 */
layui.use('table', function(){
    var table = layui.table;

    table.render({
        elem: '#projectInfoTable'
        ,url: ctx + '/projectInfo/pageList'
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
        ,title: '项目数据表'
        ,cols: [[
            {field:'name', title: '项目名称',fixed: 'left', minWidth: 165} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
            ,{field:'redbookUrl',  title: '小红书笔记链接'}
            ,{field:'groupName',  title: '项目分组'}
            ,{field:'inputMoney', title: '投放费用(W)',sort: true}
            ,{field:'followPerson', title: '跟进人'}
            ,{field:'status', title: '项目状态'}
            ,{field:'putonStars', title: '投放达人'}
            ,{field:'releaseFlag', title: '发布状态'}
            ,{field:'commentsCount', title: '总评论(W)',sort: true}
            ,{field:'likeCount', title: '总点赞(W)', sort: true}
            ,{field:'forwardCount', title: '总转发(W)',sort: true}
            ,{field:'remark', title: '备注'}
            ,{fixed: 'right', title:'操作', toolbar: '#barDemo', minWidth: 250}
            ,{field:'id', title: 'id',hide:true} //id列隐藏
        ]]
        ,page:true
        ,even:true
        ,id: 'projectInfoTable'
        ,done: function (res, curr, count) {
            //如果是异步请求数据方式，res即为你接口返回的信息。
            //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
            $("[data-field='status']").children().each(function () {
                if ($(this).text() == '1') {
                    $(this).text('进行中');
                } else if ($(this).text() == '2') {
                    $(this).text('已完结');
                }
            });
            $("[data-field='releaseFlag']").children().each(function () {
                if ($(this).text() == '1') {
                    $(this).text('已发布');
                } else if ($(this).text() == '2') {
                    $(this).text('未发布');
                }
            });
        }
    });

    //头工具栏事件
    table.on('toolbar(projectInfoTable)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            //自定义头工具栏右侧图标 - 提示
            case 'LAYTABLE_TIPS':
                layer.alert('这是工具栏右侧自定义的一个图标按钮');
                break;
        };
    });

    //监听操作栏事件
    table.on('tool(projectInfoTable)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            layer.confirm('确认删除吗？注意：数据无法恢复', function (index) {
                $.delete(ctx + "/projectInfo/delete/" + data.id, {}, function (data) {
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
                title: "编辑项目信息",
                shadeClose: false,
                share: 0.01,
                area: ['1100px', '800px'],
                content: "/projectInfo/getEditPage?dataId=" + data.id,
            });
        }else if (obj.event === 'updateStarInfo'){
            //项目关联的人员  来更新数据
            layer.open({
                type: 2,
                title: "编辑项目信息",
                shadeClose: false,
                share: 0.01,
                area: ['1100px', '800px'],
                //TODO 带着达人信息跳转  在页面上直接编辑
                content: "/projectInfo/getEditPage?dataId=" + data.id,
            });
        }
    });

    //这里以搜索为例 表格数据重载
    var $ = layui.$, active = {
        reload: function(){
            var name = $('#name');
            var inputMoney = $('#inputMoney');
            var followPerson = $('#followPerson');
            var projectStatus = $('#projectStatus');
            //执行重载
            table.reload('projectInfoTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    name: name.val(),
                    inputMoney: inputMoney.val(),
                    projectStatus: projectStatus.val(),
                    followPerson: followPerson.val()
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

