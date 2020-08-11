layui.use(["form", "table", "element"], function(){
    var $ = layui.jquery;
    var form = layui.form;
    var table = layui.table;
    var element = layui.element;

    // 渲染表格
    table.render({
        elem: "#plan-table",
        url: "/plan",
        page: true,
        toolbar: "#plan-table-toolbar",
        defaultToolbar: ['filter', 'exports', 'print'],
        cols: [[
            {field: "createDate", title: "日期"},
            {field: "invest", title: "投资理财"},
            {field: "deposit", title: "定期存款"},
            {field: "eduFund", title: "教育基金"},
            {field: "cash", title: "日常零花"},
            {field: "sum", title: "合计", style:'background-color: #F5F5F5',
                templet: function(d) {
                    return d.invest + d.deposit + d.eduFund + d.cash;
                }
            },
        ]]
    });

    //头工具栏事件
    table.on('toolbar(plan-table)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
        case 'add-plan':
            layer.open({
                type: 2,
                content: "/html/add-plan.html",
                area: ['500px', '530px'],
                btn: ['确定', '取消'],
                yes: function(index, layero){
                    //按钮【按钮一】的回调
                    var iframe = layero.find('iframe').contents();
                    
                    var plan = {
                        invest : iframe.find("#plan-invest").val(),
                        deposit : iframe.find("#plan-deposit").val(),
                        eduFund : iframe.find("#plan-eduFund").val(),
                        cash : iframe.find("#plan-cash").val(),
                        createDate : iframe.find("#plan-month").val()
                    }
                    $.ajax({
                        url: "/plan",
                        type: "POST",
                        data: JSON.stringify(plan),
                        contentType: "application/json;charset=utf-8",
                        success: function(result) {
                            if (result) {
                                layer.close(index);
                                table.reload("plan-table");
                            }
                        }
                    });
                }
            });
        break;
        };
    });

    paintBar();
    
    function paintBar() {
        var data = [];
        data[0] = ['category', '总投金额', '实际金额'];
        data[1] = ['投资理财', 0, 0];
        data[2] = ['定期存款', 0, 0];
        data[3] = ['教育基金', 0, 0];
        
        $.ajax({
            url: "/plan/total",
            type: "GET",
            async: false,
            success: function(result) {
                data[1][1] = result.invest;
                data[2][1] = result.deposit;
                data[3][1] = result.eduFund;
            }
        });
        $.ajax({
            url: "/real-record/total",
            type: "GET",
            async: false,
            success: function(result) {
                data[1][2] = result.invest;
                data[2][2] = result.deposit;
                data[3][2] = result.eduFund;
            }
        });
        var myChart = echarts.init(document.getElementById("bar"));
        var option = {
            legend: {},
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            dataset: {
                source: data
            },
            xAxis: {type: 'category'},
            yAxis: {},
            series: [
                {type: 'bar'},
                {type: 'bar'}
            ]
        };
        myChart.setOption(option);
    }

    function paintPie() {
        var pie1 = [];
        var pie2 = [];
        $.ajax({
            url: "/plan/total",
            type: "GET",
            async: false,
            success: function(result) {
                pie1.push(
                    {value: result.invest, name: '投资理财'},
                    {value: result.deposit, name: '定期存款'},
                    {value: result.eduFund, name: '教育基金'}
                );
            }
        });
        $.ajax({
            url: "/real-record/total",
            type: "GET",
            async: false,
            success: function(result) {
                pie2.push(
                    {value: result.invest, name: '投资理财'},
                    {value: result.deposit, name: '定期存款'},
                    {value: result.eduFund, name: '教育基金'}
                );
            }
        });
        var myChart = echarts.init(document.getElementById("pie"));
        var option = {
            tooltip: {
                trigger: 'item',
                formatter: '{a} <br/>{b}: {c} ({d}%)'
            },
            legend: {
                orient: 'vertical',
                left: 0,
                data: ['投资理财', '定期存款', '教育基金']
            },
            series: [
                {
                    name: '投入金额',
                    type: 'pie',
                    selectedMode: 'single',
                    radius: [0, '50%'],
        
                    label: {
                        position: 'inner'
                    },
                    labelLine: {
                        show: false
                    },
                    data: pie1
                },
                {
                    name: '实际金额',
                    type: 'pie',
                    radius: ['60%', '80%'],
                    data: pie2
                }
            ]
        }
        myChart.setOption(option);
    }
    
    $(".update-real-invest").on("click", function() {
        var realRecord = {
            type: 1,
            value: $(".real-invest-input").val(),
            note: $(".real-invest-note-input").val(),
        };
        $.ajax({
            url: "/real-record",
            type: "POST",
            data: JSON.stringify(realRecord),
            contentType: "application/json;charset=utf-8",
            success: function(result) {
                console.log(result);
                if (result) {
                    paintBar();
                    $(".real-invest-input").val("");
                    $(".real-invest-note-input").val("");
                    layer.msg("更新成功");
                }
            }
        });
    });
    
    $(".update-real-deposit").on("click", function() {
        var realRecord = {
            type: 2,
            value: $(".real-deposit-input").val(),
            note: $(".real-deposit-note-input").val(),
        };
        $.ajax({
            url: "/real-record",
            type: "POST",
            data: JSON.stringify(realRecord),
            contentType: "application/json;charset=utf-8",
            success: function(result) {
                if (result) {
                    paintBar();
                    $(".real-deposit-input").val("");
                    $(".real-deposit-note-input").val("");
                    layer.msg("更新成功");
                }
            }
        });
    });
    
    $(".update-real-cash").on("click", function() {
        var realRecord = {
            type: 3,
            value: $(".real-cash-input").val(),
            note: $(".real-cash-note-input").val(),
        };
        $.ajax({
            url: "/real-record",
            type: "POST",
            data: JSON.stringify(realRecord),
            contentType: "application/json;charset=utf-8",
            success: function(result) {
                console.log(result);
                if (result) {
                    paintBar();
                    $(".real-cash-input").val("");
                    $(".real-cash-note-input").val("");
                    layer.msg("更新成功");
                }
            }
        });
    });
});