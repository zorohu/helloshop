var App = function () {

    var _masterCheckbox;
    var _checkbox;

    //定义一个存放id的数组
    var _idArray ;

    //默认的Dropzone参数
    var defaultDropzoneOpts = {
        url: "",
        method: "post",  // 也可用put
        paramName: "dropFile", // 默认为file
        maxFiles: 1,// 一次性上传的文件数量上限
        maxFilesize: 2, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1,// 一次上传的文件数量
        //previewsContainer:"#preview", // 上传图片的预览窗口
        dictDefaultMessage: '拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "您最多只能上传"+this.maxFiles+"个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持.",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消",
    };
    /**
     * 私有方法
     */
    var handlerInitCheckbox = function () {
        //iCheck for checkbox and radio inputs 激活
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        });

        //获取控制器 Checkbox
        _masterCheckbox = $('input[type="checkbox"].minimal.icheck_master');
        _masterCheckbox.iCheck("uncheck");
        //获取全局 chekbox集合
        _checkbox = $('input[type="checkbox"].minimal');
        _checkbox.iCheck("uncheck");
    };
    
    var handlerCheckboxAll = function () {
        _masterCheckbox.on('ifClicked',function (e) {
            // console.log(e.target.checked);
            if (e.target.checked){
                _checkbox.iCheck("uncheck");
            }
            else {
                _checkbox.iCheck("check");
            }
        });
    };

    var handlerDelete = function del(url,_idArray) {
        $("#modal-default").modal("hide");
        if (_idArray.length === 0) {
            //....
        }
        else {
            setTimeout(function () {
                $.ajax({
                    "url":url,
                    "type": "POST",
                    "data": {"ids":_idArray.toString()},
                    "dataType": "JSON",
                    "success": function (data) {
                        console.log(data);
                        $(".modal-footer .btn-primary").unbind("click");
                        //删除成功
                        if (data.status == 200) {
                            $(".modal-footer .btn-primary").bind("click",function () {
                                window.location.reload();
                            });
                        }
                        //删除失败
                        else {
                            $(".modal-footer .btn-primary").bind("click",function () {
                                $("#modal-default").modal("hide");
                            });
                        }
                        $("#modal-message").html(data.message);
                        $("#modal-default").modal("show");
                    }
                });
            },500);
        }
    };

    var handlerDeleteMulti = function (url) {
        _idArray = new Array();

        _checkbox.each(function () {
            var _id = $(this).attr("id");
            if (_id != null && _id != "undefine" && $(this).is(":checked")){
                _idArray.push(_id);
            }
        });

        //判断用户是否选中了数据
        if (_idArray.length === 0) {
            $("#modal-message").html("您还没有选择任何数据项，请至少选择一项");
        }
        else {
            $("#modal-message").html("您确定要删除这些数据吗");
        }
        $("#modal-default").modal("show");

        $(".modal-footer .btn-primary").bind("click",function () {
            handlerDelete(url,_idArray);
        });

    };

    var handlerInitDataTable = function (url,columns) {
        var _dataTable = $("#dataTable").DataTable({
            "paging": true,
            "info": true,
            "lengthChange": false,
            "ordering": false,
            "processing": true,
            "searching": false,
            "serverSide": true,
            "deferRender": true,
            "ajax": {
                "url": url
            },
            "columns": columns,
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            "drawCallback": function( settings ) {
                handlerInitCheckbox();
                handlerCheckboxAll();
            }
        });
        return _dataTable;
    };

    var handlerInitDropzone = function (id,opts) {
        //opts它包含额外的属性合并到第一个参数如果属性相同则覆盖
        var option = $.extend(defaultDropzoneOpts,opts);
        // 如果只有一个参数提供给$.extend()，这意味着目标参数被省略。
        // jQuery.extend( target [, object1 ] [, objectN ] )
        // opts = $.extend(defaultDropzoneOpts);
        new Dropzone(id,option);
    };

    var handlerShowDetail = function (url) {
        $.ajax({
            url: url,
            async: false,
            type: "get",
            dataType: "html",
            success: function (data) {
                $("#modal-detail-boby").html(data);
                $("#modal-detail").modal("show");
            }
        });
    };

    var handlerInitZTree = function (url,autoParam,callback) {
        var setting = {
            view: {
                selectedMulti: false
            },
            async: {
                enable: true,
                // url:"/content/category/tree/data",
                // autoParam:["id"]
                url:url,
                autoParam:autoParam
            }
        };
        $.fn.zTree.init($("#myTree"), setting);
        // alert("执行了");

        $("#btnModalOk").bind("click",function () {
            var zTree = $.fn.zTree.getZTreeObj("myTree"),
                nodes = zTree.getSelectedNodes();

            if (nodes.length == 0) {
                alert("请先选择一个父节点");
            }
            else {
                callback(nodes);
                // alert(node.id);
            }
        });
    };

    return {
        init:function () {
            handlerInitCheckbox();
            handlerCheckboxAll();
        },
        // getCheckbox:function () {
        //     return _checkbox;
        // },
        deleteMulti:function (url) {
            handlerDeleteMulti(url);
        },
        initDataTables:function (url,columns) {
            return handlerInitDataTable(url,columns);
        },
        initDropzone:function(id,opts){
            return handlerInitDropzone(id,opts);
        },
        showDetail: function (url) {
            handlerShowDetail(url);
        },
        initZTree:function (url,autoParam,callback) {
            handlerInitZTree(url,autoParam,callback);
        },
        delete: function (url,id) {
            handlerDelete(url,id);
        }
    }
}();

$(document).ready(function () {
    console.log("APP初始化");
    App.init();
});