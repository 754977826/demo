var grid;
$(function () {
    grid = $("#maingrid").ligerGrid({
        width: 600,
        columns: [
            {
                display: '文件名', name: 'fileName', align:'middle', width: 100,
                render: function (item) {
                    console.log(item);
                    return "<a href='/document/item?id=" + item.id + "'>" + item.fileName + "</a>";
                }
            }
        ],
        usePager: true,
        url: "/document/getDocumentList",
        pageSize: 10,
    });
});

function DataBindList() {

    manager = $("#wordList").ligerGrid({
        isSort: true,
        columns: [
            {display: '', name: '', width: 10},
            {
                display: '文件名', name: 'fileName', width: 100,
                render: function (item) {
                    console.log(item);
                    return "<a href='/document/item?id=" + item.id + "'>" + item.fileName + "</a>";
                }
            },
            /*{ display: '档案编号', name: 'jlh', width: 200 },
            { display: '身份证号', name: 'sfzh', width: 200 },
            { display: '年龄', name: 'nl', width: 80 },*/
            //{ display: '家庭人数', name: 'jtrs', width: 80,},
            /*{
                display: '性别', width: 60,
                render: function (item, rowindex) {
                    if (item.xb == "1") {
                        return "男";
                    }
                    else if (item.xb == "2") {
                        return "女";
                    } else {
                        return "";
                    }
                }
            },
            {display: '联系电话', name: 'sj', width: 120,},
            {display: '建档日期', name: 'jdrqs', width: 120},
            {display: '建档机构', name: 'jdjgmc', width: 180},
            {display: '建档人', name: 'jdrxm', width: 100,},
            {display: '地址', name: 'jtzz'}*/
        ],
        url: "/document/getDocumentList",
        pageSize: 10,
        /*toolbar: {
            items: [
                {text: '增加档案', click: itemclick, icon: 'add_user'},
                {line: true},
                {text: '修改档案', click: itemclick, icon: 'edit_user'},
                {line: true},
                {text: '删除成员', click: itemclick, icon: 'del_user'},
                {line: true},
                {text: '档案详情', click: itemclick, icon: 'file_det'},
                {line: true},
                {text: '死亡标注', click: itemclick, icon: 'death'},
                {line: true},
                {text: '成员迁出', click: itemclick, icon: 'out_user'},
                {line: true},
                {text: '设置户主', click: itemclick, icon: 'householder'},
                {line: true},
                {text: '随访管理', click: itemclick, icon: 'follow'},
                {line: true},
                {text: '档案打印', click: itemclick, icon: 'print'},
                {line: true},
                {text: '申请转档', click: itemclick, icon: 'out_user'},
                {line: true}
            ]
        }*/
    });
    /*$("#pageloading").hide();*/
}