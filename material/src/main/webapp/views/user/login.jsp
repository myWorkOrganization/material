<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>物资管理</title>
    <link rel="stylesheet" type="text/css" href="/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
    <script type="text/javascript" src="/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<script type="text/javascript">
    $(function () {
        $('#toRegister').click(function () {
            window.location.href="/views/user/register.jsp";
        });
        function toRegister() {
            window.location.href="/views/user/register.jsp";
        }
        //进入页面获取焦点
        $('#userName').textbox().next('span').find('input').focus();
        //回车触发事件
        document.onkeydown=function(e){
            var keycode=document.all?event.keyCode:e.which;
            if(keycode==13) {
                login();
            }
        };
        $("#login").click(function(){
            login();
        })
        function  login() {
            if($("#loginForm").form("validate")){
                var userName=$("#userName").val();
                var password=$("#password").val();
                $.ajax({
                    url:"/user/login",
                    type:"post",
                    data:JSON.stringify({"userName":userName,"password":password}),
                    dataType:"json",
                    contentType:"application/json",
                    success:function(result) {
                        if(result.res=="success"){
                            window.location.href="/index.jsp";
                        }
                        if(result.res=="fail"){
                            $.messager.alert("通知",result.desc,"error");
                        }
                    }
                });
            }
        }

    });
</script>
<body style="background-image: url(../../images/bg.jpg);height:600px;">
<div class="easyui-dialog" id="loginDialog" title="用户登录" style="width:100%;max-width:400px;padding:30px 60px;"  data-options="closable:false,draggable:false">
  <form id="loginForm" method="post">
          <div style="margin-bottom:20px">
              <input class="easyui-textbox easyui-validatebox" label="用户名:" id="userName" labelPosition="top" data-options="required:true,validateOnBlur:true,missingMessage:'用户名不能为空'" style="width:100%;height:52px">
          </div>
          <div style="margin-bottom:40px">
              <input class="easyui-textbox easyui-validatebox" label="密码:" id="password" labelPosition="top" type="password" data-options="required:true,validateOnBlur:true,missingMessage:'密码不能为空'" style="width:100%;height:52px">
          </div>
          <div style="margin-bottom:20px">
              <a href="javascript:void(0)" class="easyui-linkbutton" id="login" iconCls="icon-ok" style="width:100%;height:32px">登陆</a>
          </div>
  </form>
          <div id="loginDialogButtons">
              <a href="" class="easyui-linkbutton" id="forgetPassword"  style="width:30%;height:32px;margin-right: 100px;">忘记密码？</a>
              <a class="easyui-linkbutton" id="toRegister" style="width:30%;height:32px;margin-left: 2px;">注册</a>
          </div>
</div>
</body>
</html>