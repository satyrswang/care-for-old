/**
 * Created by douyutong on 2015/12/4.
 */
var code;
$('.verify-code').on('click', function(e){
        e.preventDefault();
        $.ajax({
            url:'/OCare/app/code',
            cache: false,
            async: false,
            type: 'get',
            data: 'phoneNum='+$('#new_tel').val(),
            success:function(data){
                alert("verify code has been sent");
                code = data.code;
                console.log(code);
            },
            error: function(error){
                alert("failed to send the verify code");
            }
        })
    });

$('.ChangePhoneNum_form .phoneNum_submit').click(function(e){
    e.preventDefault();
    $.ajax({
        url:'/OCare/app/personInforModifyHandle1',
        type:'post',
        async:false,
        data:{
            phoneNum:$('#new_tel').val(),
            code: $('#verify_code').val(),
            sessionId: null
        },
        success:function(data){
            if(data.error == false){
                alert("reset successfully!");
                document.forms[0].action = "/OCare/pages/index.jsp";
                document.forms[0].submit();
            }
            else{
                alert(data.errorMsg);
            }
        },
        error:function(){
            alert("server error!");
        }

    })
});

$('.ChangePassword_form .password_submit').on('click',function(e){
    e.preventDefault();
    $.ajax({
        url:'/OCare/app/personInforModifyHandle2',
        type:'post',
        async:false,
        data:{
            password:$('#old_password').val(),
            newPassword:$('#new_password').val(),
            sessionId: null
        },
        success:function(data){
            if(data.error == false){
                alert("reset successfully!");
                document.forms[0].action = "/OCare/pages/index.jsp";
                document.forms[0].submit();
            }
            else{
                alert(data.errorMsg);
            }
        }
    })
});

$('.ChangePicture_form .picture_submit').click(function(e){
    e.preventDefault();
    $.ajax({
        url:'/OCare/app/personInforModifyHandle3',
        type:'post',
        async:false,
        data:{
            change:$('#doc1').val(),
            sessionId: null
        },
        success:function(data){
            if(data.error == false){
                alert("reset successfully!");
                document.forms[0].action = "/OCare/pages/index.jsp";
                document.forms[0].submit();
            }
            else{
                alert(data.errorMsg);
            }
        }
    })
});