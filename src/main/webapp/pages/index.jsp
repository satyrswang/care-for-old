<%@page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head lang="en">
    <title>HomePage</title>
    <link rel="stylesheet" href="/OCare/Assets/CSS/index.css">
    <link rel="stylesheet" href="/OCare/Assets/CSS/semantic.css" media="screen">
    <link rel="stylesheet" href="/OCare/Assets/CSS/icon.css" media="screen">
    <script src="/OCare/Assets/JS/jquery-2.1.4.js"></script>
    <script src="/OCare/Assets/JS/semantic.js"></script>
</head>
<script src="/OCare/Assets/JS/jquery.address.js"></script>
<body class="home">
<div class="headers" >
    <div class="header-container">
        <img src="/OCare/Assets/Images/Ocare-logo.png">
        <div class="greeting">
             <strong>您好!</strong>
            <a id="login" class="login">登陆</a>
            <a href="/OCare/pages/Register.jsp">注册</a>
        </div>
        <div class="nav" >
            <ul>
                <li>
                    <a class="active item"><i class="home icon"></i> 首页</a>
                </li>
                <li>
                    <a class="item"><i class="gift icon"></i> 关于我们</a>
                </li>
                <li>
                    <a class="item"><i class="photo icon"></i> 幸福相册</a>
                </li>
                <li>
                    <a class="item"><i class="sitemap icon"></i> 合作伙伴</a>
                </li>
                <li>
                    <a class="item"><i class="phone icon"></i> 联系我们</a>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="container" style="background-color: #EDEDED;">
    <div class="ad" >
        <h2>福联网</h2>
    </div>
    <div class="bg-image" style="margin: 30px auto 0;">
        <img src="/OCare/Assets/Images/old-couple.png">
    </div>
    <div class="pic-left">
        <img src="/OCare/Assets/Images/work.jpg">
        <div class="right-block">
            <h3>我们如何工作</h3>
            <p></p>
        </div>
    </div>
    <div class="pic-right">
        <img src="/OCare/Assets/Images/old.jpg">
        <div class="left-block">
            <h3>我们的成果</h3>
            <p></p>
        </div>
    </div>
    <div class="pic-left">
        <img src="/OCare/Assets/Images/co.jpg">
        <div class="right-block">
            <h3>我们与谁合作</h3>
            <p></p>
        </div>
    </div>

    <div class="ui horizontal icon divider">
        <i class="icon home"></i>
    </div>

    <div class="whole">
        <h3>加入我们</h3>
        <div class="one-third">
            <img src="/OCare/Assets/Images/company.jpg">
            <h4>机构</h4>
            <p></p>
        </div>
        <div class="one-third">
            <img src="/OCare/Assets/Images/relative.jpg">
            <h4>家属</h4>
            <p></p>
        </div>
        <div class="one-third">
            <img src="/OCare/Assets/Images/volunteer.jpg">
            <h4>志愿者</h4>
            <p></p>
        </div>
    </div>

    <%--<div class="contect">--%>
        <%--<div class="ui attached message">--%>
            <%--<h2>联系我们</h2>--%>
            <%--<p style="text-align: center">如您想加入我们的团队，或有任何建议，请联系我们</p>--%>
        <%--</div>--%>
        <%--<form class="ui form attached fluid segment">--%>
            <%--<div class="two fields">--%>
                <%--<div class="field">--%>
                    <%--<label>姓名</label>--%>
                    <%--<input placeholder="name..." type="text">--%>
                <%--</div>--%>
                <%--<div class="field">--%>
                    <%--<label>电话</label>--%>
                    <%--<input placeholder="telephone..." type="text">--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="field">--%>
                <%--<label>邮箱</label>--%>
                <%--<input placeholder="email..." type="text">--%>
            <%--</div>--%>
            <%--<div class="field">--%>
                <%--<label>您的意见</label>--%>
                <%--<textarea placeholder="your message... "></textarea>--%>
            <%--</div>--%>
            <%--<div class="inline field">--%>
                <%--<div class="ui checkbox">--%>
                    <%--<input id="terms" type="checkbox">--%>
                    <%--<label for="terms">我同意OCare网站的相关协议</label>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="ui teal submit button" style="text-align: center">提交</div>--%>
        <%--</form>--%>
    <%--</div>--%>
</div>
<!-----------登陆对话框--------->
<!----------------------------->
<div class="ui small modal" style="width:40%; margin-left: -20%">
    <i class="close icon"></i>
    <div class="header" style="background-color: #78D6CC;padding:1.1rem 2rem">
        <i class="user icon"></i>登陆
    </div>

    <div class="content" id="context1" style="line-height: 40px;padding:2em 0rem;display: inline-block;">
        <div class="ui top attached tabular menu" style="margin-left: 30px;">
            <a class="active item " data-tab="LegalPerson">
                法人
            </a>
            <a class="item" data-tab="Employee">
                员工
            </a>
            <a class="item" data-tab="Elder">
                老人
            </a>
            <a class="item" data-tab="Monitor">
                监护人
            </a>
            <a class="item" data-tab="Volenteer">
                志愿者
            </a>
            <a class="item" data-tab="admin">
                Admin
            </a>
        </div>
        <div class="ui bottom attached active tab segment" data-tab="LegalPerson">
            <form class="ui LegalPerson-sign-in form" method="post">
                <div style="padding:0rem 1rem">
                    <label>手机号:</label></br>
                    <div class="ui input" style="display: initial;">
                        <input id="LegalPerson_username" name="username" type="text" >
                    </div></br>
                    <label>密码</label></br>
                    <div class="ui input" style="display: initial;">
                        <input id="LegalPerson_password" name="password" type="password" >
                    </div>
                </div>
                <div class="actions">
                    <a href="/OCare/pages/Register.jsp" style="color: white;">
                        <div class="ui black button" style="float: left;background-color: #FF7770;margin-left: -10px;">
                            注册
                        </div>
                    </a>
                    忘记密码?
                    <div class="ui positive right labeled icon ocSubmit button" style="background-color: #78D6CC;  margin-right: -15px;">
                        登陆
                        <i class="checkmark icon"></i>
                    </div>
                </div>
                <div class="ui error message"  style="width:92.2%;margin-left: 20px; margin-bottom:-1em;text-align: center;"></div>
                <div id="LegalPerson_login_errorMsg" style="text-align: center;border-radius: 5px;background-color: #F1D7D7;font-style: inherit;font-size: 15px;color: #A95252;width: 90.5%;margin-left: 20px;padding: 4px 5px;margin-bottom: -2em;display:none"></div>
            </form>
        </div>
        <!------------------------------------------------------------------------------------>
        <div class="ui bottom attached tab segment" data-tab="Employee">
            <form class="ui Employee-sign-in form" method="post">
                <div style="padding:0rem 1rem">
                    <label>手机号:</label></br>
                    <div class="ui input" style="display: initial;">
                        <input id="Employee_username" name="username" type="text" >
                    </div></br>
                    <label>密码</label></br>
                    <div class="ui input" style="display: initial;">
                        <input id="Employee_password" name="password" type="password" >
                    </div>
                </div>
                <div class="actions">
                    <a href="/OCare/pages/Register.jsp" style="color: white;">
                        <div class="ui black button" style="float: left;background-color: #FF7770;margin-left: -10px;">
                            注册
                        </div>
                    </a>
                    忘记密码?
                    <div class="ui positive right labeled icon ocSubmit button" style="background-color: #78D6CC;  margin-right: -15px;">
                        登陆
                        <i class="checkmark icon"></i>
                    </div>
                </div>
                <div class="ui error message"  style="width:92.2%;margin-left: 20px; margin-bottom:-1em;text-align: center;"></div>
                <div id="Employee_login_errorMsg" style="text-align: center;border-radius: 5px;background-color: #F1D7D7;font-style: inherit;font-size: 15px;color: #A95252;width: 90.5%;margin-left: 20px;padding: 4px 5px;margin-bottom: -2em;display:none"></div>
            </form>
        </div>
        <!------------------------------------------------------------------------------------>
        <div class="ui bottom attached tab segment" data-tab="Elder">
            <form class="ui Elder-sign-in form" method="post">
                <div style="padding:0rem 1rem">
                    <label>手机号:</label></br>
                    <div class="ui input" style="display: initial;">
                        <input id="Elder_username" name="username" type="text" >
                    </div></br>
                    <label>密码</label></br>
                    <div class="ui input" style="display: initial;">
                        <input id="Elder_password" name="password" type="password" >
                    </div>
                </div>
                <div class="actions">
                    <a href="/OCare/pages/Register.jsp" style="color: white;">
                        <div class="ui black button" style="float: left;background-color: #FF7770;margin-left: -10px;">
                            注册
                        </div>
                    </a>
                    忘记密码?
                    <div class="ui positive right labeled icon ocSubmit button" style="background-color: #78D6CC;  margin-right: -15px;">
                        登陆
                        <i class="checkmark icon"></i>
                    </div>
                </div>
                <div class="ui error message"  style="width:92.2%;margin-left: 20px; margin-bottom:-1em;text-align: center;"></div>
                <div id="Elder_login_errorMsg" style="text-align: center;border-radius: 5px;background-color: #F1D7D7;font-style: inherit;font-size: 15px;color: #A95252;width: 90.5%;margin-left: 20px;padding: 4px 5px;margin-bottom: -2em;display:none"></div>
            </form>
        </div>
        <!------------------------------------------------------------------------------------>
        <div class="ui bottom attached tab segment" data-tab="Monitor">
            <form class="ui Monitor-sign-in form" method="post">
                <div style="padding:0rem 1rem">
                    <label>手机号:</label></br>
                    <div class="ui input" style="display: initial;">
                        <input id="Monitor_username" name="username" type="text" >
                    </div></br>
                    <label>密码</label></br>
                    <div class="ui input" style="display: initial;">
                        <input id="Monitor_password" name="password" type="password" >
                    </div>
                </div>
                <div class="actions">
                    <a href="/OCare/pages/Register.jsp" style="color: white;">
                        <div class="ui black button" style="float: left;background-color: #FF7770;margin-left: -10px;">
                            注册
                        </div>
                    </a>
                    忘记密码?
                    <div class="ui positive right labeled icon ocSubmit button" style="background-color: #78D6CC;  margin-right: -15px;">
                        登陆
                        <i class="checkmark icon"></i>
                    </div>
                </div>
                <div class="ui error message"  style="width:92.2%;margin-left: 20px; margin-bottom:-1em;text-align: center;"></div>
                <div id="Monitor_login_errorMsg" style="text-align: center;border-radius: 5px;background-color: #F1D7D7;font-style: inherit;font-size: 15px;color: #A95252;width: 90.5%;margin-left: 20px;padding: 4px 5px;margin-bottom: -2em;display:none"></div>
            </form>
        </div>
        <!------------------------------------------------------------------------------------>
        <div class="ui bottom attached tab segment" data-tab="Volenteer">
            <form class="ui Volenteer-sign-in form" method="post">
                <div style="padding:0rem 1rem">
                    <label>手机号:</label></br>
                    <div class="ui input" style="display: initial;">
                        <input id="Volenteer_username" name="username" type="text" >
                    </div></br>
                    <label>密码</label></br>
                    <div class="ui input" style="display: initial;">
                        <input id="Volenteer_password" name="password" type="password" >
                    </div>
                </div>
                <div class="actions">
                    <a href="/OCare/pages/Register.jsp" style="color: white;">
                        <div class="ui black button" style="float: left;background-color: #FF7770;margin-left: -10px;">
                            注册
                        </div>
                    </a>
                    忘记密码?
                    <div class="ui positive right labeled icon ocSubmit button" style="background-color: #78D6CC;  margin-right: -15px;">
                        登陆
                        <i class="checkmark icon"></i>
                    </div>
                </div>
                <div class="ui error message"  style="width:92.2%;margin-left: 20px; margin-bottom:-1em;text-align: center;"></div>
                <div id="Volenteer_login_errorMsg" style="text-align: center;border-radius: 5px;background-color: #F1D7D7;font-style: inherit;font-size: 15px;color: #A95252;width: 90.5%;margin-left: 20px;padding: 4px 5px;margin-bottom: -2em;display:none"></div>
            </form>
        </div>
        <!------------------------------------------------------------------------------------>
        <div class="ui bottom attached tab segment" data-tab="admin">
            <form class="ui admin-sign-in form" method="post">
                <div style="padding:0rem 1rem">
                    <label>手机号:</label></br>
                    <div class="ui input" style="display: initial;">
                        <input id="adminname" name="adminname" type="text" >
                    </div></br>
                    <label>密码</label></br>
                    <div class="ui input" style="display: initial;">
                        <input id="adminpassword" name="adminpassword" type="password" >
                    </div>
                </div>
                <div class="actions">
                    <a href="/OCare/pages/lostPassword.jsp">忘记密码?</a>
                    <div class="ui positive right labeled icon ocSubmit button" style="background-color: #78D6CC;  margin-right: -15px;">
                        登陆
                        <i class="checkmark icon"></i>
                    </div>
                </div>
                <div class="ui error message"  style="width:92.2%;margin-left: 20px; margin-bottom:-1em;text-align: center;"></div>
                <div id="admin_login_errorMsg" style="text-align: center;border-radius: 5px;background-color: #F1D7D7;font-style: inherit;font-size: 15px;color: #A95252;width: 90.5%;margin-left: 20px;padding: 4px 5px;margin-bottom: -2em;display:none"></div>
            </form>
        </div>
    </div>
</div>
<script>
    /**
     * Created by admin on 2015/11/19.
     */
$('.menu.item')
        .tab();
</script>
<script src="/OCare/Assets/JS/login.js"></script>



</body>
</html>