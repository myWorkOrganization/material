<%--
  Created by IntelliJ IDEA.
  User: lxj
  Date: 2018/2/11
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
    <script type="text/javascript" src="/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<script>
    $(function () {
        //关闭注册页面后返回登陆页面
        $('#registerDialog').window({
            onClose:function () {
                window.location.href="/views/user/login.jsp";
            }
        });
        //进入页面获取焦点
        $("#name").textbox().next("span").find("input").focus();

        //注册
        $('#register').click(function () {
            register();
        });

        //回车触发事件
        document.onkeydown=function (e) {
            var keyCode=document.all?event.keyCode:e.which;
            if(keyCode==13){
                register();
            }
        }

        //注册用户
        function register() {
            if ($('#registerForm').form('validate')) {
                var name=$("#name").val();
                var pwd=$("#pwd").val();
                var mail=$("#mail").val();
                var repeatPwd=$("#repeatPwd").val();
                if(pwd!=repeatPwd){
                    $.messager.alert("通知","密码不一致");
                    return;
                }
                if(mail==""){
                    $.messager.alert("通知","邮箱不能为空");
                    return;
                }

                $.ajax({
                    url:"/user/register",
                    type:"post",
                    data:JSON.stringify({"name":name,"pwd":pwd,"repeatPwd":repeatPwd,"mail":mail}),
                    // data:$("#registerForm").serialize(),
                    dataType:"json",
                    contentType:"application/json",
                    success:function (result) {
                        if("success"==result.res){
                            $.messager.alert("通知",result.desc,"info")
                            // history.back();
                        }
                        if("fail"==result.res){
                            $.messager.alert("通知",result.desc,"error")
                        }
                    }

                });
            }
        }

    })
</script>
<body style="background-image: url(../../images/bg.jpg);height:600px;">
<div style="margin:20px 0;"></div>
<div class="easyui-dialog" id="registerDialog" title="用户注册" style="width:100%;max-width:400px;padding:30px 60px;"  data-options="draggable:false">
    <form id="registerForm" method="post">
        <div style="margin-bottom:20px">
            <input class="easyui-textbox easyui-validatebox" label="用户名:" name="name" id="name" labelPosition="top" data-options="required:true,validateOnBlur:true,missingMessage:'用户名不能为空'" style="width:100%;height:52px">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-textbox easyui-validatebox" label="邮箱:" name="mail" id="mail" labelPosition="top" data-options="required:true,validateOnBlur:true,validType:'email',missingMessage:'邮箱不能为空'" style="width:100%;height:52px">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-textbox easyui-validatebox" label="密码:" name="pwd" id="pwd" labelPosition="top" type="password" data-options="required:true,validateOnBlur:true,missingMessage:'密码不能为空'" style="width:100%;height:52px">
        </div>
        <div style="margin-bottom:40px">
            <input class="easyui-textbox easyui-validatebox" label="重复密码:" name="repeatPwd" id="repeatPwd" labelPosition="top" type="password" data-options="validType:'eqPwd[\'#registerForm input[name=pwd]\']',required:true,validateOnBlur:true,missingMessage:'重复密码不能为空'" style="width:100%;height:52px">
        </div>
        <div style="margin-bottom:20px">
            <a href="javascript:void(0);" class="easyui-linkbutton" id="register" iconCls="icon-ok" style="width:100%;height:32px">注册</a>
        </div>
    </form>
</div>
</body>
</html>
